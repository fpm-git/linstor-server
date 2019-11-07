package com.linbit.linstor.core.objects;

import com.linbit.InvalidNameException;
import com.linbit.ValueOutOfRangeException;
import com.linbit.linstor.LinStorDBRuntimeException;
import com.linbit.linstor.annotation.SystemContext;
import com.linbit.linstor.core.identifier.NodeName;
import com.linbit.linstor.core.identifier.StorPoolName;
import com.linbit.linstor.core.identifier.VolumeNumber;
import com.linbit.linstor.dbdrivers.DatabaseException;
import com.linbit.linstor.dbdrivers.GeneratedDatabaseTables;
import com.linbit.linstor.dbdrivers.GeneratedDatabaseTables.Column;
import com.linbit.linstor.dbdrivers.GeneratedDatabaseTables.LayerWritecacheVolumes;
import com.linbit.linstor.dbdrivers.etcd.BaseEtcdDriver;
import com.linbit.linstor.dbdrivers.etcd.EtcdUtils;
import com.linbit.linstor.dbdrivers.interfaces.ResourceLayerIdDatabaseDriver;
import com.linbit.linstor.dbdrivers.interfaces.WritecacheLayerCtrlDatabaseDriver;
import com.linbit.linstor.logging.ErrorReporter;
import com.linbit.linstor.security.AccessContext;
import com.linbit.linstor.storage.data.adapter.nvme.NvmeRscData;
import com.linbit.linstor.storage.data.adapter.nvme.NvmeVlmData;
import com.linbit.linstor.storage.data.adapter.writecache.WritecacheRscData;
import com.linbit.linstor.storage.data.adapter.writecache.WritecacheVlmData;
import com.linbit.linstor.storage.interfaces.categories.resource.RscLayerObject;
import com.linbit.linstor.transaction.TransactionMgrETCD;
import com.linbit.linstor.transaction.TransactionObjectFactory;
import com.linbit.utils.Pair;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@Singleton
public class WritecacheLayerETCDDriver extends BaseEtcdDriver implements WritecacheLayerCtrlDatabaseDriver
{
    private final AccessContext dbCtx;
    private final ErrorReporter errorReporter;
    private final ResourceLayerIdDatabaseDriver idDriver;
    private final TransactionObjectFactory transObjFactory;

    @Inject
    public WritecacheLayerETCDDriver(
        @SystemContext AccessContext dbCtxRef,
        ErrorReporter errorReporterRef,
        ResourceLayerIdDatabaseDriver idDriverRef,
        TransactionObjectFactory transObjFactoryRef,
        Provider<TransactionMgrETCD> transMgrProviderRef
    )
    {
        super(transMgrProviderRef);
        dbCtx = dbCtxRef;
        errorReporter = errorReporterRef;
        idDriver = idDriverRef;
        transObjFactory = transObjFactoryRef;
    }

    @Override
    public ResourceLayerIdDatabaseDriver getIdDriver()
    {
        return idDriver;
    }

    /**
     * Fully loads a {@link NvmeRscData} object including its {@link NvmeVlmData}
     *
     * @param parentRef
     * @return a {@link Pair}, where the first object is the actual NvmeRscData and the second object
     * is the first objects backing list of the children-resource layer data. This list is expected to be filled
     * upon further loading, without triggering transaction (and possibly database-) updates.
     * @throws DatabaseException
     */
    @Override
    public Pair<WritecacheRscData, Set<RscLayerObject>> load(
        Resource rsc,
        int id,
        String rscSuffixRef,
        RscLayerObject parentRef,
        Map<Pair<NodeName, StorPoolName>, Pair<StorPool, StorPool.InitMaps>> tmpStorPoolMapRef
    )
        throws DatabaseException
    {
        Set<RscLayerObject> children = new HashSet<>();
        Map<VolumeNumber, WritecacheVlmData> vlmMap = new TreeMap<>();
        WritecacheRscData writecacheRscData = new WritecacheRscData(
            id,
            rsc,
            parentRef,
            children,
            rscSuffixRef,
            this,
            vlmMap,
            transObjFactory,
            transMgrProvider
        );

        int vlmNrInt = -1;

        Map<String, String> etcdVlmMap = namespace(GeneratedDatabaseTables.LAYER_WRITECACHE_VOLUMES)
            .get(true);
        Set<String> composedPkSet = EtcdUtils.getComposedPkList(etcdVlmMap);
        NodeName nodeName = rsc.getAssignedNode().getName();

        try
        {
            for (String composedPk : composedPkSet)
            {
                String[] pks = composedPk.split(EtcdUtils.PK_DELIMITER);

                vlmNrInt = Integer.parseInt(pks[LayerWritecacheVolumes.VLM_NR.getIndex()]);
                String cacheStorPoolNameStr = get(etcdVlmMap, LayerWritecacheVolumes.POOL_NAME, pks);

                VolumeNumber vlmNr = new VolumeNumber(vlmNrInt);

                Volume vlm = rsc.getVolume(vlmNr);
                StorPool cacheStorPool = tmpStorPoolMapRef.get(
                    new Pair<>(
                        nodeName,
                        new StorPoolName(cacheStorPoolNameStr)
                    )
                ).objA;

                vlmMap.put(
                    vlm.getVolumeDefinition().getVolumeNumber(),
                    new WritecacheVlmData(
                        vlm,
                        writecacheRscData,
                        cacheStorPool,
                        this,
                        transObjFactory,
                        transMgrProvider
                    )
                );
            }
        }
        catch (ValueOutOfRangeException exc)
        {
            throw new LinStorDBRuntimeException(
                "Failed to restore stored volume number " + vlmNrInt +
                    " for resource layer id: " + writecacheRscData.getRscLayerId()
            );
        }
        catch (InvalidNameException exc)
        {
            throw new LinStorDBRuntimeException(
                "Failed to restore stored storage pool name '" + exc.invalidName +
                    "' for resource layer id " + writecacheRscData.getRscLayerId() + " vlmNr: " + vlmNrInt
            );
        }
        return new Pair<>(writecacheRscData, children);
    }

    @Override
    public void persist(WritecacheRscData writecacheRscDataRef) throws DatabaseException
    {
        // no-op - there is no special database table.
        // this method only exists if WritecacheRscData will get a database table in future.
    }

    @Override
    public void delete(WritecacheRscData writecacheRscDataRef) throws DatabaseException
    {
        // no-op - there is no special database table.
        // this method only exists if WritecacheRscData will get a database table in future.
    }

    @Override
    public void persist(WritecacheVlmData writecacheVlmDataRef) throws DatabaseException
    {
        errorReporter.logTrace("Creating WritecacheVlmData %s", getId(writecacheVlmDataRef));
        StorPool extStorPool = writecacheVlmDataRef.getCacheStorPool();
        getNamespace(writecacheVlmDataRef)
            .put(LayerWritecacheVolumes.NODE_NAME, extStorPool.getNode().getName().value)
            .put(LayerWritecacheVolumes.POOL_NAME, extStorPool.getName().value);
    }

    @Override
    public void delete(WritecacheVlmData writecacheVlmDataRef) throws DatabaseException
    {
        errorReporter.logTrace("Deleting WritecacheVlmData %s", getId(writecacheVlmDataRef));
        getNamespace(writecacheVlmDataRef)
            .delete(true);
    }

    private String getId(WritecacheRscData writecacheRscData)
    {
        return "(LayerRscId=" + writecacheRscData.getRscLayerId() +
            ", SuffResName=" + writecacheRscData.getSuffixedResourceName() +
            ")";
    }

    private String getId(WritecacheVlmData writecacheVlmData)
    {
        return "(LayerRscId=" + writecacheVlmData.getRscLayerId() +
            ", SuffResName=" + writecacheVlmData.getRscLayerObject().getSuffixedResourceName() +
            ", VlmNr=" + writecacheVlmData.getVlmNr().value +
            ")";
    }

    private FluentLinstorTransaction getNamespace(WritecacheVlmData writecacheVlmDataRef)
    {
        return namespace(
            GeneratedDatabaseTables.LAYER_WRITECACHE_VOLUMES,
            Integer.toString(writecacheVlmDataRef.getRscLayerId()),
            Integer.toString(writecacheVlmDataRef.getVlmNr().value)
        );
    }

    private String get(Map<String, String> map, Column col, String... pks)
    {
        return map.get(EtcdUtils.buildKey(col, pks));
    }

}