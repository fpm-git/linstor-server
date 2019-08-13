package com.linbit.linstor.dbdrivers;

import com.google.inject.AbstractModule;

import com.linbit.linstor.dbdrivers.interfaces.LuksLayerDatabaseDriver;
import com.linbit.linstor.core.objects.DrbdLayerGenericDbDriver;
import com.linbit.linstor.core.objects.KeyValueStoreDataGenericDbDriver;
import com.linbit.linstor.core.objects.LuksLayerGenericDbDriver;
import com.linbit.linstor.core.objects.NetInterfaceDataGenericDbDriver;
import com.linbit.linstor.core.objects.NodeConnectionDataGenericDbDriver;
import com.linbit.linstor.core.objects.NodeDataGenericDbDriver;
import com.linbit.linstor.core.objects.NvmeLayerGenericDbDriver;
import com.linbit.linstor.core.objects.ResourceConnectionDataGenericDbDriver;
import com.linbit.linstor.core.objects.ResourceDataGenericDbDriver;
import com.linbit.linstor.core.objects.ResourceDefinitionDataGenericDbDriver;
import com.linbit.linstor.core.objects.ResourceGroupDataGenericDbDriver;
import com.linbit.linstor.core.objects.ResourceLayerIdGenericDbDriver;
import com.linbit.linstor.core.objects.SnapshotDataGenericDbDriver;
import com.linbit.linstor.core.objects.SnapshotDefinitionDataGenericDbDriver;
import com.linbit.linstor.core.objects.SnapshotVolumeDataGenericDbDriver;
import com.linbit.linstor.core.objects.SnapshotVolumeDefinitionGenericDbDriver;
import com.linbit.linstor.core.objects.StorPoolDataGenericDbDriver;
import com.linbit.linstor.core.objects.StorPoolDefinitionDataGenericDbDriver;
import com.linbit.linstor.core.objects.StorageLayerGenericDbDriver;
import com.linbit.linstor.core.objects.SwordfishLayerGenericDbDriver;
import com.linbit.linstor.core.objects.VolumeConnectionDataGenericDbDriver;
import com.linbit.linstor.core.objects.VolumeDataGenericDbDriver;
import com.linbit.linstor.core.objects.VolumeDefinitionDataGenericDbDriver;
import com.linbit.linstor.core.objects.VolumeGroupDataGenericDbDriver;
import com.linbit.linstor.dbdrivers.interfaces.DrbdLayerDatabaseDriver;
import com.linbit.linstor.dbdrivers.interfaces.KeyValueStoreDataDatabaseDriver;
import com.linbit.linstor.dbdrivers.interfaces.NetInterfaceDataDatabaseDriver;
import com.linbit.linstor.dbdrivers.interfaces.NodeConnectionDataDatabaseDriver;
import com.linbit.linstor.dbdrivers.interfaces.NodeDataDatabaseDriver;
import com.linbit.linstor.dbdrivers.interfaces.NvmeLayerDatabaseDriver;
import com.linbit.linstor.dbdrivers.interfaces.PropsConDatabaseDriver;
import com.linbit.linstor.dbdrivers.interfaces.ResourceConnectionDataDatabaseDriver;
import com.linbit.linstor.dbdrivers.interfaces.ResourceDataDatabaseDriver;
import com.linbit.linstor.dbdrivers.interfaces.ResourceDefinitionDataDatabaseDriver;
import com.linbit.linstor.dbdrivers.interfaces.ResourceGroupDataDatabaseDriver;
import com.linbit.linstor.dbdrivers.interfaces.ResourceLayerIdDatabaseDriver;
import com.linbit.linstor.dbdrivers.interfaces.SnapshotDataDatabaseDriver;
import com.linbit.linstor.dbdrivers.interfaces.SnapshotDefinitionDataDatabaseDriver;
import com.linbit.linstor.dbdrivers.interfaces.SnapshotVolumeDataDatabaseDriver;
import com.linbit.linstor.dbdrivers.interfaces.SnapshotVolumeDefinitionDatabaseDriver;
import com.linbit.linstor.dbdrivers.interfaces.StorPoolDataDatabaseDriver;
import com.linbit.linstor.dbdrivers.interfaces.StorPoolDefinitionDataDatabaseDriver;
import com.linbit.linstor.dbdrivers.interfaces.StorageLayerDatabaseDriver;
import com.linbit.linstor.dbdrivers.interfaces.SwordfishLayerDatabaseDriver;
import com.linbit.linstor.dbdrivers.interfaces.VolumeConnectionDataDatabaseDriver;
import com.linbit.linstor.dbdrivers.interfaces.VolumeDataDatabaseDriver;
import com.linbit.linstor.dbdrivers.interfaces.VolumeDefinitionDataDatabaseDriver;
import com.linbit.linstor.dbdrivers.interfaces.VolumeGroupDataDatabaseDriver;
import com.linbit.linstor.propscon.PropsConGenericDbDriver;
import com.linbit.linstor.security.DbAccessor;
import com.linbit.linstor.security.DbSQLPersistence;
import com.linbit.linstor.security.ObjectProtectionDatabaseDriver;
import com.linbit.linstor.security.ObjectProtectionGenericDbDriver;

public class ControllerDbModule extends AbstractModule
{
    private final DatabaseDriverInfo.DatabaseType dbType;

    public ControllerDbModule(DatabaseDriverInfo.DatabaseType dbTypeRef)
    {
        dbType = dbTypeRef;
    }

    @Override
    protected void configure()
    {
        bind(DatabaseDriver.class).to(DatabaseLoader.class);

        switch (dbType)
        {
            case SQL:
                bind(DbAccessor.class).to(DbSQLPersistence.class);

                bind(ObjectProtectionDatabaseDriver.class).to(ObjectProtectionGenericDbDriver.class);

                bind(PropsConDatabaseDriver.class).to(PropsConGenericDbDriver.class);
                bind(NodeDataDatabaseDriver.class).to(NodeDataGenericDbDriver.class);
                bind(ResourceDefinitionDataDatabaseDriver.class).to(ResourceDefinitionDataGenericDbDriver.class);
                bind(ResourceGroupDataDatabaseDriver.class).to(ResourceGroupDataGenericDbDriver.class);
                bind(ResourceDataDatabaseDriver.class).to(ResourceDataGenericDbDriver.class);
                bind(VolumeDefinitionDataDatabaseDriver.class).to(VolumeDefinitionDataGenericDbDriver.class);
                bind(VolumeGroupDataDatabaseDriver.class).to(VolumeGroupDataGenericDbDriver.class);
                bind(VolumeDataDatabaseDriver.class).to(VolumeDataGenericDbDriver.class);
                bind(StorPoolDefinitionDataDatabaseDriver.class).to(StorPoolDefinitionDataGenericDbDriver.class);
                bind(StorPoolDataDatabaseDriver.class).to(StorPoolDataGenericDbDriver.class);
                bind(NetInterfaceDataDatabaseDriver.class).to(NetInterfaceDataGenericDbDriver.class);
                bind(NodeConnectionDataDatabaseDriver.class).to(NodeConnectionDataGenericDbDriver.class);
                bind(ResourceConnectionDataDatabaseDriver.class).to(ResourceConnectionDataGenericDbDriver.class);
                bind(VolumeConnectionDataDatabaseDriver.class).to(VolumeConnectionDataGenericDbDriver.class);
                bind(SnapshotDefinitionDataDatabaseDriver.class).to(SnapshotDefinitionDataGenericDbDriver.class);
                bind(SnapshotVolumeDefinitionDatabaseDriver.class).to(SnapshotVolumeDefinitionGenericDbDriver.class);
                bind(SnapshotDataDatabaseDriver.class).to(SnapshotDataGenericDbDriver.class);
                bind(SnapshotVolumeDataDatabaseDriver.class).to(SnapshotVolumeDataGenericDbDriver.class);
                bind(KeyValueStoreDataDatabaseDriver.class).to(KeyValueStoreDataGenericDbDriver.class);

                bind(ResourceLayerIdDatabaseDriver.class).to(ResourceLayerIdGenericDbDriver.class);
                bind(DrbdLayerDatabaseDriver.class).to(DrbdLayerGenericDbDriver.class);
                bind(LuksLayerDatabaseDriver.class).to(LuksLayerGenericDbDriver.class);
                bind(StorageLayerDatabaseDriver.class).to(StorageLayerGenericDbDriver.class);
                bind(SwordfishLayerDatabaseDriver.class).to(SwordfishLayerGenericDbDriver.class);
                bind(NvmeLayerDatabaseDriver.class).to(NvmeLayerGenericDbDriver.class);
                break;
            case ETCD:
                default:
                throw new RuntimeException("ETCD database driver not implemented.");
        }
    }
}
