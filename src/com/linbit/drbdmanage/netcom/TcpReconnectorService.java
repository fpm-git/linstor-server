package com.linbit.drbdmanage.netcom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import com.linbit.ImplementationError;
import com.linbit.InvalidNameException;
import com.linbit.ServiceName;
import com.linbit.SystemService;
import com.linbit.SystemServiceStartException;
import com.linbit.drbdmanage.Controller;
import com.linbit.drbdmanage.DrbdManageException;

/**
 * Holds a list of all peers and checks their connectivity.
 * If a connection is broken / not established,
 *
 * @author Gabor Hernadi &lt;gabor.hernadi@linbit.com&gt;
 */

//TODO try to check if a successfully established connection breaks suddenly - then start reconnecting
public class TcpReconnectorService implements SystemService
{
    private static final int PING_TIMEOUT = 5_000;
    private static final int PING_WORKER_SLEEP = 500;
    private static final int RECONNECT_WORKER_SLEEP = 10_000; // TODO change back to 10 sec


    private static final ServiceName SERVICE_NAME;
    private static final String SERVICE_INFO = "Connector -> Satellite reconnector service";

    private static final int RECONNECT_LIST_KEY = 1;
    private static final int PING_LIST_KEY = 2;

    static
    {
        try
        {
            SERVICE_NAME = new ServiceName("ReconnectorService");
        }
        catch (InvalidNameException nameExc)
        {
            throw new ImplementationError(
                String.format(
                    "%s class contains an invalid name constant",
                    TcpReconnectorService.class.getName()
                ),
                nameExc
            );
        }
    }

    private final Controller controller;

    private final Map<Integer, List<Peer>> lists;
    private final List<Peer> reconnectList;
    private final List<Peer> pingList;

    private final Thread workerThread;
    private final Runnable workerRunnable;

    private ServiceName serviceInstanceName;
    private boolean running = false;

    public TcpReconnectorService(Controller controller)
    {
        this.controller = controller;

        serviceInstanceName = SERVICE_NAME;

        lists = new HashMap<>();
        reconnectList = new LinkedList<>();
        pingList = new ArrayList<>();
        lists.put(RECONNECT_LIST_KEY, reconnectList);
        lists.put(PING_LIST_KEY, pingList);
        workerRunnable = new ReconnectorRunnable();
        workerThread = new Thread(workerRunnable, serviceInstanceName.displayValue);
    }

    @Override
    public ServiceName getServiceName()
    {
        return SERVICE_NAME;
    }

    @Override
    public String getServiceInfo()
    {
        return SERVICE_INFO;
    }

    @Override
    public ServiceName getInstanceName()
    {
        return serviceInstanceName;
    }

    @Override
    public boolean isStarted()
    {
        return running;
    }

    @Override
    public void setServiceInstanceName(ServiceName instanceName)
    {
        if (instanceName == null)
        {
            serviceInstanceName = SERVICE_NAME;
        }
        else
        {
            serviceInstanceName = instanceName;
        }
        if (workerThread != null)
        {
            workerThread.setName(serviceInstanceName.displayValue);
        }
    }

    @Override
    public void start() throws SystemServiceStartException
    {
        workerThread.start();
        running = true;
    }

    @Override
    public void shutdown()
    {
        running = false;
    }

    @Override
    public void awaitShutdown(long timeout) throws InterruptedException
    {
        workerThread.join(timeout);
    }

    public void addReconnect(Peer pendingPeer)
    {
        reconnectList.add(pendingPeer);
    }

    public void addPing(Peer peer)
    {
        pingList.add(peer);
    }

    private class ReconnectorRunnable implements Runnable
    {
        private long nextPingCheck;
        private long nextReconnectCheck;

        public ReconnectorRunnable()
        {
            final long now = System.currentTimeMillis();
            nextPingCheck = now + PING_WORKER_SLEEP;
            nextReconnectCheck = now + RECONNECT_WORKER_SLEEP;
        }

        @Override
        public void run()
        {
            while (running)
            {
                try
                {
                    final long now = System.currentTimeMillis();
                    if (nextReconnectCheck < now)
                    {
                        nextReconnectCheck = now + RECONNECT_WORKER_SLEEP;
                        for (int idx = 0; idx < reconnectList.size(); ++idx)
                        {
                            final Peer peer = reconnectList.get(idx);
                            if (peer.isConnected())
                            {
                                controller.getErrorReporter().logInfo(
                                    "peer " + peer.getId() + " has connected. -reconnectList, +pingList");
                                reconnectList.remove(idx);
                                --idx;
                                pingList.add(peer);
                            }
                            else
                            {
                                controller.getErrorReporter().logInfo(
                                    "peer " + peer.getId() + " has not connected yet - retrying connect");
                                peer.getConnector().reconnect(peer);
                            }
                        }
                    }

                    if (nextPingCheck < now)
                    {
                        nextPingCheck = now + PING_WORKER_SLEEP;
                        for (int idx = 0; idx < pingList.size(); ++idx)
                        {
                            final Peer peer = pingList.get(idx);
                            final long lastPingReceived = peer.getLastPingReceived();
                            final long lastPingSent = peer.getLastPingSent();
                            if (lastPingReceived + PING_TIMEOUT < lastPingSent)
                            {
                                controller.getErrorReporter().logWarning(
                                    "lost connection to peer " + peer.getId() + ". -pingList, +reconnectList"
                                );
                                pingList.remove(peer);
                                --idx;
                                reconnectList.add(peer);
                                peer.getConnector().reconnect(peer);
                            }
                            else
                            {
                                peer.sendPing();
                            }
                        }
                    }

                    final long sleepMs = Math.min(nextPingCheck, nextReconnectCheck) - now + 200;
                    Thread.sleep(sleepMs);
                }
                catch (InterruptedException e)
                {
                    if (running)
                    {
                        controller.getErrorReporter().reportError(
                            new DrbdManageException(
                                "Interrupted exception catched"
                                // TODO: detailed error reporting
                            )
                        );
                    }
                }
                catch (IOException ioExc)
                {
                    // TODO: detailed error reporting
                    controller.getErrorReporter().reportError(ioExc);
                }
            }
        }
    }
}
