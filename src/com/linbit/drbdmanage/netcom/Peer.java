package com.linbit.drbdmanage.netcom;

import com.linbit.ServiceName;
import com.linbit.drbdmanage.api.common.Ping;
import com.linbit.drbdmanage.security.AccessContext;
import com.linbit.drbdmanage.security.AccessDeniedException;
import java.net.InetSocketAddress;

import javax.net.ssl.SSLException;

/**
 * Represents the peer of a connection
 *
 * @author Robert Altnoeder &lt;robert.altnoeder@linbit.com&gt;
 */
public interface Peer
{
    /**
     * Returns a unique identifier for this peer object
     *
     * @return Unique peer identifier
     */
    String getId();

    /**
     * Returns the service instance name of the connector associated with this peer object
     *
     * @return Connector service instance name
     */
    ServiceName getConnectorInstanceName();

    /**
     * Returns the security context of the connected peer
     *
     * @return AccessContext object representing the connected peer's security context
     */
    AccessContext getAccessContext();

    /**
     * Sets a new access context for the connected peer
     *
     * @return AccessContext object representing the connected peer's security context
     */
    void setAccessContext(AccessContext privilegedCtx, AccessContext newAccCtx)
        throws AccessDeniedException;

    /**
     * Attaches the object to the peer
     *
     * @param attachment The object to attach to the peer
     */
    void attach(Object attachment);

    /**
     * Fetches the object attached to the peer
     *
     * @return The object attached to the peer
     */
    Object getAttachment();

    /**
     * Creates a new message for sending to this peer
     *
     * @return New message instance
     */
    Message createMessage();

    /**
     * Queues a message for sending to the peer
     *
     * @param msg Message to send
     * @throws IllegalMessageStateException If the message object is not in a valid state for sending
     */
    void sendMessage(Message msg) throws IllegalMessageStateException;

    /**
     * Closes the connection to the peer
     * @throws SSLException
     */
    void closeConnection();

    /**
     * Returns true if the connection has been established. False otherwise.
     */
    boolean isConnected();

    /**
     * Returns the capacity of the queue for outbound messages
     *
     * @return Capacity of the outbound messages queue
     */
    int outQueueCapacity();

    /**
     * Returns the number of currently queued outbound messages
     *
     * @return Number of currently queued outbound messages
     */
    int outQueueCount();

    /**
     * Returns the number of messages that were sent to the peer
     *
     * @return Number of messages that were sent
     */
    long msgSentCount();

    /**
     * Returns the number of messages that were received from the peer
     *
     * @return Number of messages that were received
     */
    long msgRecvCount();

    /**
     * Returns the destination internet address of the peer connection
     *
     * @return Internet address of the peer
     */
    InetSocketAddress peerAddress();

    /**
     * Called when the connection is established
     * @throws SSLException
     */
    void connectionEstablished() throws SSLException;

    /**
     * Waits until someone calls the {@link Peer#connectionEstablished()} method
     * @throws InterruptedException
     */
    void waitUntilConnectionEstablished() throws InterruptedException;

    /**
     * Returns the {@link TcpConnector} handling this peer
     *
     * @return
     */
    TcpConnector getConnector();

    /**
     * Tries to send a ping packet
     */
    void sendPing();

    /**
     * This method should only be called by {@link Ping}, in order to calculate the latency
     */
    void pongReceived();

    /**
     * Returns a timestamp in milliseconds when the last ping message was sent (e.g. {@link #sendPing()} was called)
     *
     * @return
     */
    long getLastPingSent();

    /**
     * Returns a timestamp in milliseconds when the last ping message was received (e.g. {@link #pongReceived()} was called)
     *
     * @return
     */
    long getLastPingReceived();
}
