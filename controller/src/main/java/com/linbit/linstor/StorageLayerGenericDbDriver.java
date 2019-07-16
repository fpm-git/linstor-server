package com.linbit.linstor;

import com.linbit.ImplementationError;
import com.linbit.InvalidNameException;
import com.linbit.SingleColumnDatabaseDriver;
import com.linbit.ValueOutOfRangeException;
import com.linbit.linstor.StorPool.InitMaps;
import com.linbit.linstor.annotation.SystemContext;
import com.linbit.linstor.dbdrivers.interfaces.ResourceLayerIdDatabaseDriver;
import com.linbit.linstor.dbdrivers.interfaces.StorageLayerDatabaseDriver;
import com.linbit.linstor.logging.ErrorReporter;
import com.linbit.linstor.security.AccessContext;
import com.linbit.linstor.security.AccessDeniedException;
import com.linbit.linstor.storage.data.provider.StorageRscData;
import com.linbit.linstor.storage.data.provider.diskless.DisklessData;
import com.linbit.linstor.storage.data.provider.file.FileData;
import com.linbit.linstor.storage.data.provider.lvm.LvmData;
import com.linbit.linstor.storage.data.provider.lvm.LvmThinData;
import com.linbit.linstor.storage.data.provider.zfs.ZfsData;
import com.linbit.linstor.storage.interfaces.categories.resource.RscLayerObject;
import com.linbit.linstor.storage.interfaces.categories.resource.VlmProviderObject;
import com.linbit.linstor.storage.kinds.DeviceProviderKind;
import com.linbit.linstor.transaction.TransactionMgr;
import com.linbit.linstor.transaction.TransactionObjectFactory;
import com.linbit.utils.Pair;
import com.linbit.utils.StringUtils;
import static com.linbit.linstor.dbdrivers.derby.DbConstants.NODE_NAME;

import static com.linbit.linstor.dbdrivers.derby.DbConstants.LAYER_RESOURCE_ID;
import static com.linbit.linstor.dbdrivers.derby.DbConstants.PROVIDER_KIND;
import static com.linbit.linstor.dbdrivers.derby.DbConstants.STOR_POOL_NAME;
import static com.linbit.linstor.dbdrivers.derby.DbConstants.TBL_LAYER_STORAGE_VOLUMES;
import static com.linbit.linstor.dbdrivers.derby.DbConstants.VLM_NR;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@SuppressWarnings("checkstyle:magicnumber")
@Singleton
public class StorageLayerGenericDbDriver implements StorageLayerDatabaseDriver
{
    private static final String[] VLM_ALL_FIELDS =
    {
        LAYER_RESOURCE_ID,
        VLM_NR,
        PROVIDER_KIND,
        NODE_NAME,
        STOR_POOL_NAME
    };

    private static final String SELECT_ALL_STOR_VLMS =
        " SELECT " + StringUtils.join(", ", VLM_ALL_FIELDS) +
        " FROM " + TBL_LAYER_STORAGE_VOLUMES;

    private static final String INSERT_VLM =
        " INSERT INTO " + TBL_LAYER_STORAGE_VOLUMES +
        " ( " + StringUtils.join(", ", VLM_ALL_FIELDS) + " )" +
        " VALUES ( " + StringUtils.repeat("?", ", ", VLM_ALL_FIELDS.length) + ")";

    private static final String UPDATE_STOR_POOL =
        " UPDATE " + TBL_LAYER_STORAGE_VOLUMES +
            " SET "   + STOR_POOL_NAME + " = ? " +
            " WHERE " + LAYER_RESOURCE_ID + " = ? AND " +
                        VLM_NR            + " = ?";

    private static final String DELETE_VLM =
        " DELETE FROM " + TBL_LAYER_STORAGE_VOLUMES +
        " WHERE " + LAYER_RESOURCE_ID + " = ? AND " +
                    VLM_NR            + " = ?";

    private final ErrorReporter errorReporter;
    private final AccessContext dbCtx;
    private final ResourceLayerIdDatabaseDriver rscIdDriver;
    private final SwordfishLayerGenericDbDriver sfDbDriver;
    private final TransactionObjectFactory transObjFactory;
    private final Provider<TransactionMgr> transMgrProvider;

    private final SingleColumnDatabaseDriver<VlmProviderObject, StorPool> storPoolDriver;

    private Map<Integer, List<StorVlmInfoData>> cachedStorVlmInfoByRscLayerId;

    @Inject
    public StorageLayerGenericDbDriver(
        ErrorReporter errorReporterRef,
        @SystemContext AccessContext accCtx,
        ResourceLayerIdDatabaseDriver rscIdDriverRef,
        SwordfishLayerGenericDbDriver sfDbDriverRef,
        TransactionObjectFactory transObjFactoryRef,
        Provider<TransactionMgr> transMgrProviderRef
    )
    {
        errorReporter = errorReporterRef;
        dbCtx = accCtx;
        rscIdDriver = rscIdDriverRef;
        sfDbDriver = sfDbDriverRef;
        transObjFactory = transObjFactoryRef;
        transMgrProvider = transMgrProviderRef;

        storPoolDriver = new StorPoolDriver();
    }

    @Override
    public ResourceLayerIdDatabaseDriver getIdDriver()
    {
        return rscIdDriver;
    }

    public void fetchForLoadAll(Map<Pair<NodeName, StorPoolName>, Pair<StorPool, InitMaps>> tmpStorPoolMapRef)
        throws SQLException
    {
        loadStorVlmsIntoCache(tmpStorPoolMapRef);
        // will be extended later with loadStorSnapVlmsIntoCache(tmpStorPoolMapRef);
    }

    private void loadStorVlmsIntoCache(
        Map<Pair<NodeName, StorPoolName>, Pair<StorPool, InitMaps>> tmpStorPoolMapRef
    )
        throws SQLException
    {
        cachedStorVlmInfoByRscLayerId = new HashMap<>();
        try (PreparedStatement stmt = getConnection().prepareStatement(SELECT_ALL_STOR_VLMS))
        {
            int rscLayerId = -1;
            try (ResultSet resultSet = stmt.executeQuery())
            {
                while (resultSet.next())
                {
                    rscLayerId = resultSet.getInt(LAYER_RESOURCE_ID);
                    List<StorVlmInfoData> infoList = cachedStorVlmInfoByRscLayerId.get(rscLayerId);
                    if (infoList == null)
                    {
                        infoList = new ArrayList<>();
                        cachedStorVlmInfoByRscLayerId.put(rscLayerId, infoList);
                    }
                    NodeName nodeName = new NodeName(resultSet.getString(NODE_NAME));
                    StorPoolName storPoolName = new StorPoolName(resultSet.getString(STOR_POOL_NAME));
                    infoList.add(
                        new StorVlmInfoData(
                            rscLayerId,
                            resultSet.getInt(VLM_NR),
                            LinstorParsingUtils.asProviderKind(resultSet.getString(PROVIDER_KIND)),
                            tmpStorPoolMapRef.get(new Pair<>(nodeName, storPoolName)).objA,
                            tmpStorPoolMapRef.get(new Pair<>(nodeName, storPoolName)).objB
                        )
                    );
                }
            }
            catch (InvalidNameException exc)
            {
                throw new LinStorSqlRuntimeException(
                    String.format(
                        "Failed to restore stored name '%s' of (layered) resource id: %d",
                        exc.invalidName,
                        rscLayerId
                    )
                );
            }
        }
    }

    public void clearLoadAllCache()
    {
        cachedStorVlmInfoByRscLayerId.clear();
        cachedStorVlmInfoByRscLayerId = null;
    }

    public void loadLayerData(Map<ResourceName, ResourceDefinition> tmpRscDfnMapRef) throws SQLException
    {
        sfDbDriver.loadLayerData(tmpRscDfnMapRef);
    }

    public Pair<StorageRscData, Set<RscLayerObject>> load(
        Resource resourceRef,
        int rscIdRef,
        String rscSuffixRef,
        RscLayerObject parentRef
    )
        throws AccessDeniedException, SQLException
    {
        Map<VolumeNumber, VlmProviderObject> vlmMap = new TreeMap<>();
        StorageRscData storageRscData = new StorageRscData(
            rscIdRef,
            parentRef,
            resourceRef,
            rscSuffixRef,
            vlmMap,
            this,
            transObjFactory,
            transMgrProvider
        );

        List<StorVlmInfoData> vlmInfoList = cachedStorVlmInfoByRscLayerId.get(rscIdRef);
        if (vlmInfoList != null)
        {
            for (StorVlmInfoData vlmInfo : vlmInfoList)
            {
                try
                {
                    VolumeNumber vlmNr = new VolumeNumber(vlmInfo.vlmNr);
                    Volume vlm = resourceRef.getVolume(vlmNr);

                    if (vlm == null)
                    {
                        throw new LinStorRuntimeException(
                            "Storage volume found but linstor volume missing: " +
                            resourceRef + ", vlmNr: " + vlmNr
                        );
                    }

                    VlmProviderObject vlmData = loadVlmProviderObject(vlm, storageRscData, vlmInfo);
                    vlmMap.put(vlmNr, vlmData);
                }
                catch (ValueOutOfRangeException exc)
                {
                    throw new LinStorSqlRuntimeException(
                        String.format(
                            "Failed to restore stored volume number %d for (layered) resource id: %d",
                            vlmInfo.vlmNr,
                            vlmInfo.rscId
                        )
                    );
                }
            }
        }

        return new Pair<>(
            storageRscData,
            null // storage resources have no children
        );
    }

    private VlmProviderObject loadVlmProviderObject(
        Volume vlmRef,
        StorageRscData rscDataRef,
        StorVlmInfoData vlmInfo
    )
        throws AccessDeniedException, SQLException
    {
        VlmProviderObject vlmProviderObj;
        switch (vlmInfo.kind)
        {
            case DISKLESS:
                // no special database table for diskless DRBD.
                vlmProviderObj = new DisklessData(
                    vlmRef,
                    rscDataRef,
                    vlmRef.getVolumeDefinition().getVolumeSize(dbCtx),
                    vlmInfo.storPool,
                    this,
                    transObjFactory,
                    transMgrProvider
                );
                break;
            case LVM:
                vlmProviderObj = new LvmData(
                    vlmRef,
                    rscDataRef,
                    vlmInfo.storPool,
                    this,
                    transObjFactory,
                    transMgrProvider
                );
                break;
            case LVM_THIN:
                vlmProviderObj = new LvmThinData(
                    vlmRef,
                    rscDataRef,
                    vlmInfo.storPool,
                    this,
                    transObjFactory,
                    transMgrProvider
                );
                break;
            case SWORDFISH_INITIATOR: // fall-through
            case SWORDFISH_TARGET:
                vlmProviderObj = sfDbDriver.load(vlmRef, rscDataRef, vlmInfo.kind, vlmInfo.storPool, this);
                break;
            case ZFS: // fall-trough
            case ZFS_THIN:
                vlmProviderObj = new ZfsData(
                    vlmRef,
                    rscDataRef,
                    vlmInfo.kind,
                    vlmInfo.storPool,
                    this,
                    transObjFactory,
                    transMgrProvider
                );
                break;
            case FILE: // fall-through
            case FILE_THIN:
                vlmProviderObj = new FileData(
                    vlmRef,
                    rscDataRef,
                    vlmInfo.kind,
                    vlmInfo.storPool,
                    this,
                    transObjFactory,
                    transMgrProvider
                );
                break;
            case FAIL_BECAUSE_NOT_A_VLM_PROVIDER_BUT_A_VLM_LAYER:
            default:
                throw new ImplementationError("Unhandled storage type: " + vlmInfo.kind);
        }
        vlmInfo.storPoolInitMaps.getVolumeMap().put(vlmProviderObj.getVolumeKey(), vlmProviderObj);
        return vlmProviderObj;
    }

    @Override
    public void persist(StorageRscData storageRscDataRef) throws SQLException
    {
        // no-op - there is no special database table.
        // this method only exists if StorageRscData will get a database table in future.
    }

    @Override
    public void delete(StorageRscData storgeRscDataRef) throws SQLException
    {
        // no-op - there is no special database table.
        // this method only exists if StorageRscData will get a database table in future.
    }

    @Override
    public void persist(VlmProviderObject vlmDataRef) throws SQLException
    {
        errorReporter.logTrace("Creating StorageVolume %s", getId(vlmDataRef));
        try (PreparedStatement stmt = getConnection().prepareStatement(INSERT_VLM))
        {
            stmt.setInt(1, vlmDataRef.getRscLayerObject().getRscLayerId());
            stmt.setInt(2, vlmDataRef.getVlmNr().value);
            stmt.setString(3, vlmDataRef.getProviderKind().name());
            stmt.setString(4, vlmDataRef.getStorPool().getNode().getName().value);
            stmt.setString(5, vlmDataRef.getStorPool().getName().value);

            stmt.executeUpdate();
            errorReporter.logTrace("StorageVolume created %s", getId(vlmDataRef));
        }
    }

    @Override
    public void delete(VlmProviderObject vlmDataRef) throws SQLException
    {
        errorReporter.logTrace("Deleting StorageVolume %s", getId(vlmDataRef));
        try (PreparedStatement stmt = getConnection().prepareStatement(DELETE_VLM))
        {
            stmt.setInt(1, vlmDataRef.getRscLayerObject().getRscLayerId());
            stmt.setInt(2, vlmDataRef.getVlmNr().value);

            stmt.executeUpdate();
            errorReporter.logTrace("StorageVolume deleted %s", getId(vlmDataRef));
        }
    }

    @Override
    public SingleColumnDatabaseDriver<VlmProviderObject, StorPool> getStorPoolDriver()
    {
        return storPoolDriver;
    }

    private Connection getConnection()
    {
        return transMgrProvider.get().getConnection();
    }

    private String getId(VlmProviderObject vlmData)
    {
        return vlmData.getProviderKind().name() +
            "( rscId: " + vlmData.getRscLayerObject().getRscLayerId() +
            ", vlmNr:" + vlmData.getVlmNr() + ")";
    }

    private class StorPoolDriver implements SingleColumnDatabaseDriver<VlmProviderObject, StorPool>
    {
        @Override
        @SuppressWarnings("checkstyle:magicnumber")
        public void update(VlmProviderObject parent, StorPool storPool) throws SQLException
        {
            errorReporter.logTrace("Updating VlmProviderObject's StorPool from [%s] to [%s] %s",
                parent.getStorPool().getName().displayValue,
                storPool.getName().displayValue,
                getId(parent)
            );
            try (PreparedStatement stmt = getConnection().prepareStatement(UPDATE_STOR_POOL))
            {
                stmt.setString(1, storPool.getName().value);
                stmt.setInt(2, parent.getRscLayerObject().getRscLayerId());
                stmt.setInt(3, parent.getVlmNr().value);

                stmt.executeUpdate();
            }
            errorReporter.logTrace("VlmProviderObject's StorPool updated from [%s] to [%s] %s",
                parent.getStorPool().getName().displayValue,
                storPool.getName().displayValue,
                getId(parent)
            );
        }
    }

    public static class StorVlmInfoData
    {
        public final int rscId;
        public final int vlmNr;
        public final DeviceProviderKind kind;
        public final StorPool storPool;
        public final StorPool.InitMaps storPoolInitMaps;

        public StorVlmInfoData(
            int rscIdRef,
            int vlmNrRef,
            DeviceProviderKind kindRef,
            StorPool storPoolRef,
            StorPool.InitMaps storPoolInitMapsRef
        )
        {
            rscId = rscIdRef;
            vlmNr = vlmNrRef;
            kind = kindRef;
            storPool = storPoolRef;
            storPoolInitMaps = storPoolInitMapsRef;
        }
    }
}
