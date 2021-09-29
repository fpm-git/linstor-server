package com.linbit.linstor.dbdrivers.k8s.crd;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import com.linbit.ImplementationError;
import com.linbit.linstor.dbdrivers.DatabaseTable;
import com.linbit.linstor.dbdrivers.DatabaseTable.Column;
import com.linbit.linstor.dbdrivers.GeneratedDatabaseTables;
import com.linbit.linstor.security.AccessDeniedException;
import com.linbit.linstor.transaction.BaseControllerK8sCrdTransactionMgrContext;
import com.linbit.linstor.transaction.K8sCrdSchemaUpdateContext;
import com.linbit.utils.ExceptionThrowingFunction;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.fabric8.kubernetes.api.model.ObjectMetaBuilder;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Plural;
import io.fabric8.kubernetes.model.annotation.Singular;
import io.fabric8.kubernetes.model.annotation.Version;

public class GenCrdCurrent
{
    public static final String VERSION = "v1-15-0";
    public static final String GROUP = "internal.linstor.linbit.com";


    private GenCrdCurrent()
    {
    }

    @SuppressWarnings("unchecked")
    public static <CRD extends LinstorCrd<SPEC>, SPEC extends LinstorSpec> Class<? extends LinstorCrd<SPEC>> databaseTableToCustomResourceClass(
        DatabaseTable table
    )
    {
        switch(table.getName())
        {
            case "FILES":
                return (Class<CRD>) Files.class;
            case "KEY_VALUE_STORE":
                return (Class<CRD>) KeyValueStore.class;
            case "LAYER_BCACHE_VOLUMES":
                return (Class<CRD>) LayerBcacheVolumes.class;
            case "LAYER_CACHE_VOLUMES":
                return (Class<CRD>) LayerCacheVolumes.class;
            case "LAYER_DRBD_RESOURCES":
                return (Class<CRD>) LayerDrbdResources.class;
            case "LAYER_DRBD_RESOURCE_DEFINITIONS":
                return (Class<CRD>) LayerDrbdResourceDefinitions.class;
            case "LAYER_DRBD_VOLUMES":
                return (Class<CRD>) LayerDrbdVolumes.class;
            case "LAYER_DRBD_VOLUME_DEFINITIONS":
                return (Class<CRD>) LayerDrbdVolumeDefinitions.class;
            case "LAYER_LUKS_VOLUMES":
                return (Class<CRD>) LayerLuksVolumes.class;
            case "LAYER_OPENFLEX_RESOURCE_DEFINITIONS":
                return (Class<CRD>) LayerOpenflexResourceDefinitions.class;
            case "LAYER_OPENFLEX_VOLUMES":
                return (Class<CRD>) LayerOpenflexVolumes.class;
            case "LAYER_RESOURCE_IDS":
                return (Class<CRD>) LayerResourceIds.class;
            case "LAYER_STORAGE_VOLUMES":
                return (Class<CRD>) LayerStorageVolumes.class;
            case "LAYER_WRITECACHE_VOLUMES":
                return (Class<CRD>) LayerWritecacheVolumes.class;
            case "LINSTOR_REMOTES":
                return (Class<CRD>) LinstorRemotes.class;
            case "NODES":
                return (Class<CRD>) Nodes.class;
            case "NODE_CONNECTIONS":
                return (Class<CRD>) NodeConnections.class;
            case "NODE_NET_INTERFACES":
                return (Class<CRD>) NodeNetInterfaces.class;
            case "NODE_STOR_POOL":
                return (Class<CRD>) NodeStorPool.class;
            case "PROPS_CONTAINERS":
                return (Class<CRD>) PropsContainers.class;
            case "RESOURCES":
                return (Class<CRD>) Resources.class;
            case "RESOURCE_CONNECTIONS":
                return (Class<CRD>) ResourceConnections.class;
            case "RESOURCE_DEFINITIONS":
                return (Class<CRD>) ResourceDefinitions.class;
            case "RESOURCE_GROUPS":
                return (Class<CRD>) ResourceGroups.class;
            case "S3_REMOTES":
                return (Class<CRD>) S3Remotes.class;
            case "SATELLITES_CAPACITY":
                return (Class<CRD>) SatellitesCapacity.class;
            case "SEC_ACCESS_TYPES":
                return (Class<CRD>) SecAccessTypes.class;
            case "SEC_ACL_MAP":
                return (Class<CRD>) SecAclMap.class;
            case "SEC_CONFIGURATION":
                return (Class<CRD>) SecConfiguration.class;
            case "SEC_DFLT_ROLES":
                return (Class<CRD>) SecDfltRoles.class;
            case "SEC_IDENTITIES":
                return (Class<CRD>) SecIdentities.class;
            case "SEC_ID_ROLE_MAP":
                return (Class<CRD>) SecIdRoleMap.class;
            case "SEC_OBJECT_PROTECTION":
                return (Class<CRD>) SecObjectProtection.class;
            case "SEC_ROLES":
                return (Class<CRD>) SecRoles.class;
            case "SEC_TYPES":
                return (Class<CRD>) SecTypes.class;
            case "SEC_TYPE_RULES":
                return (Class<CRD>) SecTypeRules.class;
            case "SPACE_HISTORY":
                return (Class<CRD>) SpaceHistory.class;
            case "STOR_POOL_DEFINITIONS":
                return (Class<CRD>) StorPoolDefinitions.class;
            case "TRACKING_DATE":
                return (Class<CRD>) TrackingDate.class;
            case "VOLUMES":
                return (Class<CRD>) Volumes.class;
            case "VOLUME_CONNECTIONS":
                return (Class<CRD>) VolumeConnections.class;
            case "VOLUME_DEFINITIONS":
                return (Class<CRD>) VolumeDefinitions.class;
            case "VOLUME_GROUPS":
                return (Class<CRD>) VolumeGroups.class;
            default:
                throw new ImplementationError("Unknown database table: " + table.getName());
        }
    }

    @SuppressWarnings("unchecked")
    public static <SPEC extends LinstorSpec> LinstorCrd<SPEC> specToCrd(SPEC spec)
    {
        switch(spec.getDatabaseTable().getName())
        {
            case "FILES":
                return (LinstorCrd<SPEC>) new Files((FilesSpec) spec);
            case "KEY_VALUE_STORE":
                return (LinstorCrd<SPEC>) new KeyValueStore((KeyValueStoreSpec) spec);
            case "LAYER_BCACHE_VOLUMES":
                return (LinstorCrd<SPEC>) new LayerBcacheVolumes((LayerBcacheVolumesSpec) spec);
            case "LAYER_CACHE_VOLUMES":
                return (LinstorCrd<SPEC>) new LayerCacheVolumes((LayerCacheVolumesSpec) spec);
            case "LAYER_DRBD_RESOURCES":
                return (LinstorCrd<SPEC>) new LayerDrbdResources((LayerDrbdResourcesSpec) spec);
            case "LAYER_DRBD_RESOURCE_DEFINITIONS":
                return (LinstorCrd<SPEC>) new LayerDrbdResourceDefinitions((LayerDrbdResourceDefinitionsSpec) spec);
            case "LAYER_DRBD_VOLUMES":
                return (LinstorCrd<SPEC>) new LayerDrbdVolumes((LayerDrbdVolumesSpec) spec);
            case "LAYER_DRBD_VOLUME_DEFINITIONS":
                return (LinstorCrd<SPEC>) new LayerDrbdVolumeDefinitions((LayerDrbdVolumeDefinitionsSpec) spec);
            case "LAYER_LUKS_VOLUMES":
                return (LinstorCrd<SPEC>) new LayerLuksVolumes((LayerLuksVolumesSpec) spec);
            case "LAYER_OPENFLEX_RESOURCE_DEFINITIONS":
                return (LinstorCrd<SPEC>) new LayerOpenflexResourceDefinitions((LayerOpenflexResourceDefinitionsSpec) spec);
            case "LAYER_OPENFLEX_VOLUMES":
                return (LinstorCrd<SPEC>) new LayerOpenflexVolumes((LayerOpenflexVolumesSpec) spec);
            case "LAYER_RESOURCE_IDS":
                return (LinstorCrd<SPEC>) new LayerResourceIds((LayerResourceIdsSpec) spec);
            case "LAYER_STORAGE_VOLUMES":
                return (LinstorCrd<SPEC>) new LayerStorageVolumes((LayerStorageVolumesSpec) spec);
            case "LAYER_WRITECACHE_VOLUMES":
                return (LinstorCrd<SPEC>) new LayerWritecacheVolumes((LayerWritecacheVolumesSpec) spec);
            case "LINSTOR_REMOTES":
                return (LinstorCrd<SPEC>) new LinstorRemotes((LinstorRemotesSpec) spec);
            case "NODES":
                return (LinstorCrd<SPEC>) new Nodes((NodesSpec) spec);
            case "NODE_CONNECTIONS":
                return (LinstorCrd<SPEC>) new NodeConnections((NodeConnectionsSpec) spec);
            case "NODE_NET_INTERFACES":
                return (LinstorCrd<SPEC>) new NodeNetInterfaces((NodeNetInterfacesSpec) spec);
            case "NODE_STOR_POOL":
                return (LinstorCrd<SPEC>) new NodeStorPool((NodeStorPoolSpec) spec);
            case "PROPS_CONTAINERS":
                return (LinstorCrd<SPEC>) new PropsContainers((PropsContainersSpec) spec);
            case "RESOURCES":
                return (LinstorCrd<SPEC>) new Resources((ResourcesSpec) spec);
            case "RESOURCE_CONNECTIONS":
                return (LinstorCrd<SPEC>) new ResourceConnections((ResourceConnectionsSpec) spec);
            case "RESOURCE_DEFINITIONS":
                return (LinstorCrd<SPEC>) new ResourceDefinitions((ResourceDefinitionsSpec) spec);
            case "RESOURCE_GROUPS":
                return (LinstorCrd<SPEC>) new ResourceGroups((ResourceGroupsSpec) spec);
            case "S3_REMOTES":
                return (LinstorCrd<SPEC>) new S3Remotes((S3RemotesSpec) spec);
            case "SATELLITES_CAPACITY":
                return (LinstorCrd<SPEC>) new SatellitesCapacity((SatellitesCapacitySpec) spec);
            case "SEC_ACCESS_TYPES":
                return (LinstorCrd<SPEC>) new SecAccessTypes((SecAccessTypesSpec) spec);
            case "SEC_ACL_MAP":
                return (LinstorCrd<SPEC>) new SecAclMap((SecAclMapSpec) spec);
            case "SEC_CONFIGURATION":
                return (LinstorCrd<SPEC>) new SecConfiguration((SecConfigurationSpec) spec);
            case "SEC_DFLT_ROLES":
                return (LinstorCrd<SPEC>) new SecDfltRoles((SecDfltRolesSpec) spec);
            case "SEC_IDENTITIES":
                return (LinstorCrd<SPEC>) new SecIdentities((SecIdentitiesSpec) spec);
            case "SEC_ID_ROLE_MAP":
                return (LinstorCrd<SPEC>) new SecIdRoleMap((SecIdRoleMapSpec) spec);
            case "SEC_OBJECT_PROTECTION":
                return (LinstorCrd<SPEC>) new SecObjectProtection((SecObjectProtectionSpec) spec);
            case "SEC_ROLES":
                return (LinstorCrd<SPEC>) new SecRoles((SecRolesSpec) spec);
            case "SEC_TYPES":
                return (LinstorCrd<SPEC>) new SecTypes((SecTypesSpec) spec);
            case "SEC_TYPE_RULES":
                return (LinstorCrd<SPEC>) new SecTypeRules((SecTypeRulesSpec) spec);
            case "SPACE_HISTORY":
                return (LinstorCrd<SPEC>) new SpaceHistory((SpaceHistorySpec) spec);
            case "STOR_POOL_DEFINITIONS":
                return (LinstorCrd<SPEC>) new StorPoolDefinitions((StorPoolDefinitionsSpec) spec);
            case "TRACKING_DATE":
                return (LinstorCrd<SPEC>) new TrackingDate((TrackingDateSpec) spec);
            case "VOLUMES":
                return (LinstorCrd<SPEC>) new Volumes((VolumesSpec) spec);
            case "VOLUME_CONNECTIONS":
                return (LinstorCrd<SPEC>) new VolumeConnections((VolumeConnectionsSpec) spec);
            case "VOLUME_DEFINITIONS":
                return (LinstorCrd<SPEC>) new VolumeDefinitions((VolumeDefinitionsSpec) spec);
            case "VOLUME_GROUPS":
                return (LinstorCrd<SPEC>) new VolumeGroups((VolumeGroupsSpec) spec);
            default:
                throw new ImplementationError("Unknown database table: " + spec.getDatabaseTable().getName());
        }
    }

    public static <DATA> LinstorCrd<?> dataToCrd(
        DatabaseTable table,
        Map<Column, ExceptionThrowingFunction<DATA, Object, AccessDeniedException>> setters,
        DATA data
    )
        throws AccessDeniedException
    {
        switch(table.getName())
        {
            case "FILES":
            {
                return new Files(
                    new FilesSpec(
                        (String) setters.get(GeneratedDatabaseTables.Files.UUID).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.Files.PATH).accept(data),
                        (long) setters.get(GeneratedDatabaseTables.Files.FLAGS).accept(data),
                        (byte[]) setters.get(GeneratedDatabaseTables.Files.CONTENT).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.Files.CONTENT_CHECKSUM).accept(data)
                    )
                );
            }
            case "KEY_VALUE_STORE":
            {
                return new KeyValueStore(
                    new KeyValueStoreSpec(
                        (String) setters.get(GeneratedDatabaseTables.KeyValueStore.UUID).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.KeyValueStore.KVS_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.KeyValueStore.KVS_DSP_NAME).accept(data)
                    )
                );
            }
            case "LAYER_BCACHE_VOLUMES":
            {
                return new LayerBcacheVolumes(
                    new LayerBcacheVolumesSpec(
                        (int) setters.get(GeneratedDatabaseTables.LayerBcacheVolumes.LAYER_RESOURCE_ID).accept(data),
                        (int) setters.get(GeneratedDatabaseTables.LayerBcacheVolumes.VLM_NR).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.LayerBcacheVolumes.NODE_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.LayerBcacheVolumes.POOL_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.LayerBcacheVolumes.DEV_UUID).accept(data)
                    )
                );
            }
            case "LAYER_CACHE_VOLUMES":
            {
                return new LayerCacheVolumes(
                    new LayerCacheVolumesSpec(
                        (int) setters.get(GeneratedDatabaseTables.LayerCacheVolumes.LAYER_RESOURCE_ID).accept(data),
                        (int) setters.get(GeneratedDatabaseTables.LayerCacheVolumes.VLM_NR).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.LayerCacheVolumes.NODE_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.LayerCacheVolumes.POOL_NAME_CACHE).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.LayerCacheVolumes.POOL_NAME_META).accept(data)
                    )
                );
            }
            case "LAYER_DRBD_RESOURCES":
            {
                return new LayerDrbdResources(
                    new LayerDrbdResourcesSpec(
                        (int) setters.get(GeneratedDatabaseTables.LayerDrbdResources.LAYER_RESOURCE_ID).accept(data),
                        (int) setters.get(GeneratedDatabaseTables.LayerDrbdResources.PEER_SLOTS).accept(data),
                        (int) setters.get(GeneratedDatabaseTables.LayerDrbdResources.AL_STRIPES).accept(data),
                        (long) setters.get(GeneratedDatabaseTables.LayerDrbdResources.AL_STRIPE_SIZE).accept(data),
                        (long) setters.get(GeneratedDatabaseTables.LayerDrbdResources.FLAGS).accept(data),
                        (int) setters.get(GeneratedDatabaseTables.LayerDrbdResources.NODE_ID).accept(data)
                    )
                );
            }
            case "LAYER_DRBD_RESOURCE_DEFINITIONS":
            {
                return new LayerDrbdResourceDefinitions(
                    new LayerDrbdResourceDefinitionsSpec(
                        (String) setters.get(GeneratedDatabaseTables.LayerDrbdResourceDefinitions.RESOURCE_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.LayerDrbdResourceDefinitions.RESOURCE_NAME_SUFFIX).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.LayerDrbdResourceDefinitions.SNAPSHOT_NAME).accept(data),
                        (int) setters.get(GeneratedDatabaseTables.LayerDrbdResourceDefinitions.PEER_SLOTS).accept(data),
                        (int) setters.get(GeneratedDatabaseTables.LayerDrbdResourceDefinitions.AL_STRIPES).accept(data),
                        (long) setters.get(GeneratedDatabaseTables.LayerDrbdResourceDefinitions.AL_STRIPE_SIZE).accept(data),
                        (Integer) setters.get(GeneratedDatabaseTables.LayerDrbdResourceDefinitions.TCP_PORT).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.LayerDrbdResourceDefinitions.TRANSPORT_TYPE).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.LayerDrbdResourceDefinitions.SECRET).accept(data)
                    )
                );
            }
            case "LAYER_DRBD_VOLUMES":
            {
                return new LayerDrbdVolumes(
                    new LayerDrbdVolumesSpec(
                        (int) setters.get(GeneratedDatabaseTables.LayerDrbdVolumes.LAYER_RESOURCE_ID).accept(data),
                        (int) setters.get(GeneratedDatabaseTables.LayerDrbdVolumes.VLM_NR).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.LayerDrbdVolumes.NODE_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.LayerDrbdVolumes.POOL_NAME).accept(data)
                    )
                );
            }
            case "LAYER_DRBD_VOLUME_DEFINITIONS":
            {
                return new LayerDrbdVolumeDefinitions(
                    new LayerDrbdVolumeDefinitionsSpec(
                        (String) setters.get(GeneratedDatabaseTables.LayerDrbdVolumeDefinitions.RESOURCE_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.LayerDrbdVolumeDefinitions.RESOURCE_NAME_SUFFIX).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.LayerDrbdVolumeDefinitions.SNAPSHOT_NAME).accept(data),
                        (int) setters.get(GeneratedDatabaseTables.LayerDrbdVolumeDefinitions.VLM_NR).accept(data),
                        (Integer) setters.get(GeneratedDatabaseTables.LayerDrbdVolumeDefinitions.VLM_MINOR_NR).accept(data)
                    )
                );
            }
            case "LAYER_LUKS_VOLUMES":
            {
                return new LayerLuksVolumes(
                    new LayerLuksVolumesSpec(
                        (int) setters.get(GeneratedDatabaseTables.LayerLuksVolumes.LAYER_RESOURCE_ID).accept(data),
                        (int) setters.get(GeneratedDatabaseTables.LayerLuksVolumes.VLM_NR).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.LayerLuksVolumes.ENCRYPTED_PASSWORD).accept(data)
                    )
                );
            }
            case "LAYER_OPENFLEX_RESOURCE_DEFINITIONS":
            {
                return new LayerOpenflexResourceDefinitions(
                    new LayerOpenflexResourceDefinitionsSpec(
                        (String) setters.get(GeneratedDatabaseTables.LayerOpenflexResourceDefinitions.RESOURCE_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.LayerOpenflexResourceDefinitions.SNAPSHOT_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.LayerOpenflexResourceDefinitions.RESOURCE_NAME_SUFFIX).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.LayerOpenflexResourceDefinitions.NQN).accept(data)
                    )
                );
            }
            case "LAYER_OPENFLEX_VOLUMES":
            {
                return new LayerOpenflexVolumes(
                    new LayerOpenflexVolumesSpec(
                        (int) setters.get(GeneratedDatabaseTables.LayerOpenflexVolumes.LAYER_RESOURCE_ID).accept(data),
                        (int) setters.get(GeneratedDatabaseTables.LayerOpenflexVolumes.VLM_NR).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.LayerOpenflexVolumes.NODE_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.LayerOpenflexVolumes.POOL_NAME).accept(data)
                    )
                );
            }
            case "LAYER_RESOURCE_IDS":
            {
                return new LayerResourceIds(
                    new LayerResourceIdsSpec(
                        (int) setters.get(GeneratedDatabaseTables.LayerResourceIds.LAYER_RESOURCE_ID).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.LayerResourceIds.NODE_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.LayerResourceIds.RESOURCE_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.LayerResourceIds.SNAPSHOT_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.LayerResourceIds.LAYER_RESOURCE_KIND).accept(data),
                        (Integer) setters.get(GeneratedDatabaseTables.LayerResourceIds.LAYER_RESOURCE_PARENT_ID).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.LayerResourceIds.LAYER_RESOURCE_SUFFIX).accept(data),
                        (boolean) setters.get(GeneratedDatabaseTables.LayerResourceIds.LAYER_RESOURCE_SUSPENDED).accept(data)
                    )
                );
            }
            case "LAYER_STORAGE_VOLUMES":
            {
                return new LayerStorageVolumes(
                    new LayerStorageVolumesSpec(
                        (int) setters.get(GeneratedDatabaseTables.LayerStorageVolumes.LAYER_RESOURCE_ID).accept(data),
                        (int) setters.get(GeneratedDatabaseTables.LayerStorageVolumes.VLM_NR).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.LayerStorageVolumes.PROVIDER_KIND).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.LayerStorageVolumes.NODE_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.LayerStorageVolumes.STOR_POOL_NAME).accept(data)
                    )
                );
            }
            case "LAYER_WRITECACHE_VOLUMES":
            {
                return new LayerWritecacheVolumes(
                    new LayerWritecacheVolumesSpec(
                        (int) setters.get(GeneratedDatabaseTables.LayerWritecacheVolumes.LAYER_RESOURCE_ID).accept(data),
                        (int) setters.get(GeneratedDatabaseTables.LayerWritecacheVolumes.VLM_NR).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.LayerWritecacheVolumes.NODE_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.LayerWritecacheVolumes.POOL_NAME).accept(data)
                    )
                );
            }
            case "LINSTOR_REMOTES":
            {
                return new LinstorRemotes(
                    new LinstorRemotesSpec(
                        (String) setters.get(GeneratedDatabaseTables.LinstorRemotes.UUID).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.LinstorRemotes.NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.LinstorRemotes.DSP_NAME).accept(data),
                        (long) setters.get(GeneratedDatabaseTables.LinstorRemotes.FLAGS).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.LinstorRemotes.URL).accept(data),
                        (byte[]) setters.get(GeneratedDatabaseTables.LinstorRemotes.ENCRYPTED_PASSPHRASE).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.LinstorRemotes.CLUSTER_ID).accept(data)
                    )
                );
            }
            case "NODES":
            {
                return new Nodes(
                    new NodesSpec(
                        (String) setters.get(GeneratedDatabaseTables.Nodes.UUID).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.Nodes.NODE_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.Nodes.NODE_DSP_NAME).accept(data),
                        (long) setters.get(GeneratedDatabaseTables.Nodes.NODE_FLAGS).accept(data),
                        (int) setters.get(GeneratedDatabaseTables.Nodes.NODE_TYPE).accept(data)
                    )
                );
            }
            case "NODE_CONNECTIONS":
            {
                return new NodeConnections(
                    new NodeConnectionsSpec(
                        (String) setters.get(GeneratedDatabaseTables.NodeConnections.UUID).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.NodeConnections.NODE_NAME_SRC).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.NodeConnections.NODE_NAME_DST).accept(data)
                    )
                );
            }
            case "NODE_NET_INTERFACES":
            {
                return new NodeNetInterfaces(
                    new NodeNetInterfacesSpec(
                        (String) setters.get(GeneratedDatabaseTables.NodeNetInterfaces.UUID).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.NodeNetInterfaces.NODE_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.NodeNetInterfaces.NODE_NET_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.NodeNetInterfaces.NODE_NET_DSP_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.NodeNetInterfaces.INET_ADDRESS).accept(data),
                        (Short) setters.get(GeneratedDatabaseTables.NodeNetInterfaces.STLT_CONN_PORT).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.NodeNetInterfaces.STLT_CONN_ENCR_TYPE).accept(data)
                    )
                );
            }
            case "NODE_STOR_POOL":
            {
                return new NodeStorPool(
                    new NodeStorPoolSpec(
                        (String) setters.get(GeneratedDatabaseTables.NodeStorPool.UUID).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.NodeStorPool.NODE_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.NodeStorPool.POOL_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.NodeStorPool.DRIVER_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.NodeStorPool.FREE_SPACE_MGR_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.NodeStorPool.FREE_SPACE_MGR_DSP_NAME).accept(data),
                        (boolean) setters.get(GeneratedDatabaseTables.NodeStorPool.EXTERNAL_LOCKING).accept(data)
                    )
                );
            }
            case "PROPS_CONTAINERS":
            {
                return new PropsContainers(
                    new PropsContainersSpec(
                        (String) setters.get(GeneratedDatabaseTables.PropsContainers.PROPS_INSTANCE).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.PropsContainers.PROP_KEY).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.PropsContainers.PROP_VALUE).accept(data)
                    )
                );
            }
            case "RESOURCES":
            {
                return new Resources(
                    new ResourcesSpec(
                        (String) setters.get(GeneratedDatabaseTables.Resources.UUID).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.Resources.NODE_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.Resources.RESOURCE_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.Resources.SNAPSHOT_NAME).accept(data),
                        (long) setters.get(GeneratedDatabaseTables.Resources.RESOURCE_FLAGS).accept(data),
                        (Long) setters.get(GeneratedDatabaseTables.Resources.CREATE_TIMESTAMP).accept(data)
                    )
                );
            }
            case "RESOURCE_CONNECTIONS":
            {
                return new ResourceConnections(
                    new ResourceConnectionsSpec(
                        (String) setters.get(GeneratedDatabaseTables.ResourceConnections.UUID).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.ResourceConnections.NODE_NAME_SRC).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.ResourceConnections.NODE_NAME_DST).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.ResourceConnections.RESOURCE_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.ResourceConnections.SNAPSHOT_NAME).accept(data),
                        (long) setters.get(GeneratedDatabaseTables.ResourceConnections.FLAGS).accept(data),
                        (Integer) setters.get(GeneratedDatabaseTables.ResourceConnections.TCP_PORT).accept(data)
                    )
                );
            }
            case "RESOURCE_DEFINITIONS":
            {
                return new ResourceDefinitions(
                    new ResourceDefinitionsSpec(
                        (String) setters.get(GeneratedDatabaseTables.ResourceDefinitions.UUID).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.ResourceDefinitions.RESOURCE_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.ResourceDefinitions.SNAPSHOT_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.ResourceDefinitions.RESOURCE_DSP_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.ResourceDefinitions.SNAPSHOT_DSP_NAME).accept(data),
                        (long) setters.get(GeneratedDatabaseTables.ResourceDefinitions.RESOURCE_FLAGS).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.ResourceDefinitions.LAYER_STACK).accept(data),
                        (byte[]) setters.get(GeneratedDatabaseTables.ResourceDefinitions.RESOURCE_EXTERNAL_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.ResourceDefinitions.RESOURCE_GROUP_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.ResourceDefinitions.PARENT_UUID).accept(data)
                    )
                );
            }
            case "RESOURCE_GROUPS":
            {
                return new ResourceGroups(
                    new ResourceGroupsSpec(
                        (String) setters.get(GeneratedDatabaseTables.ResourceGroups.UUID).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.ResourceGroups.RESOURCE_GROUP_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.ResourceGroups.RESOURCE_GROUP_DSP_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.ResourceGroups.DESCRIPTION).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.ResourceGroups.LAYER_STACK).accept(data),
                        (int) setters.get(GeneratedDatabaseTables.ResourceGroups.REPLICA_COUNT).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.ResourceGroups.NODE_NAME_LIST).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.ResourceGroups.POOL_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.ResourceGroups.POOL_NAME_DISKLESS).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.ResourceGroups.DO_NOT_PLACE_WITH_RSC_REGEX).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.ResourceGroups.DO_NOT_PLACE_WITH_RSC_LIST).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.ResourceGroups.REPLICAS_ON_SAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.ResourceGroups.REPLICAS_ON_DIFFERENT).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.ResourceGroups.ALLOWED_PROVIDER_LIST).accept(data),
                        (Boolean) setters.get(GeneratedDatabaseTables.ResourceGroups.DISKLESS_ON_REMAINING).accept(data)
                    )
                );
            }
            case "S3_REMOTES":
            {
                return new S3Remotes(
                    new S3RemotesSpec(
                        (String) setters.get(GeneratedDatabaseTables.S3Remotes.UUID).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.S3Remotes.NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.S3Remotes.DSP_NAME).accept(data),
                        (long) setters.get(GeneratedDatabaseTables.S3Remotes.FLAGS).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.S3Remotes.ENDPOINT).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.S3Remotes.BUCKET).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.S3Remotes.REGION).accept(data),
                        (byte[]) setters.get(GeneratedDatabaseTables.S3Remotes.ACCESS_KEY).accept(data),
                        (byte[]) setters.get(GeneratedDatabaseTables.S3Remotes.SECRET_KEY).accept(data)
                    )
                );
            }
            case "SATELLITES_CAPACITY":
            {
                return new SatellitesCapacity(
                    new SatellitesCapacitySpec(
                        (String) setters.get(GeneratedDatabaseTables.SatellitesCapacity.NODE_NAME).accept(data),
                        (byte[]) setters.get(GeneratedDatabaseTables.SatellitesCapacity.CAPACITY).accept(data),
                        (boolean) setters.get(GeneratedDatabaseTables.SatellitesCapacity.FAIL_FLAG).accept(data)
                    )
                );
            }
            case "SEC_ACCESS_TYPES":
            {
                return new SecAccessTypes(
                    new SecAccessTypesSpec(
                        (String) setters.get(GeneratedDatabaseTables.SecAccessTypes.ACCESS_TYPE_NAME).accept(data),
                        (short) setters.get(GeneratedDatabaseTables.SecAccessTypes.ACCESS_TYPE_VALUE).accept(data)
                    )
                );
            }
            case "SEC_ACL_MAP":
            {
                return new SecAclMap(
                    new SecAclMapSpec(
                        (String) setters.get(GeneratedDatabaseTables.SecAclMap.OBJECT_PATH).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.SecAclMap.ROLE_NAME).accept(data),
                        (short) setters.get(GeneratedDatabaseTables.SecAclMap.ACCESS_TYPE).accept(data)
                    )
                );
            }
            case "SEC_CONFIGURATION":
            {
                return new SecConfiguration(
                    new SecConfigurationSpec(
                        (String) setters.get(GeneratedDatabaseTables.SecConfiguration.ENTRY_KEY).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.SecConfiguration.ENTRY_DSP_KEY).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.SecConfiguration.ENTRY_VALUE).accept(data)
                    )
                );
            }
            case "SEC_DFLT_ROLES":
            {
                return new SecDfltRoles(
                    new SecDfltRolesSpec(
                        (String) setters.get(GeneratedDatabaseTables.SecDfltRoles.IDENTITY_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.SecDfltRoles.ROLE_NAME).accept(data)
                    )
                );
            }
            case "SEC_IDENTITIES":
            {
                return new SecIdentities(
                    new SecIdentitiesSpec(
                        (String) setters.get(GeneratedDatabaseTables.SecIdentities.IDENTITY_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.SecIdentities.IDENTITY_DSP_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.SecIdentities.PASS_SALT).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.SecIdentities.PASS_HASH).accept(data),
                        (boolean) setters.get(GeneratedDatabaseTables.SecIdentities.ID_ENABLED).accept(data),
                        (boolean) setters.get(GeneratedDatabaseTables.SecIdentities.ID_LOCKED).accept(data)
                    )
                );
            }
            case "SEC_ID_ROLE_MAP":
            {
                return new SecIdRoleMap(
                    new SecIdRoleMapSpec(
                        (String) setters.get(GeneratedDatabaseTables.SecIdRoleMap.IDENTITY_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.SecIdRoleMap.ROLE_NAME).accept(data)
                    )
                );
            }
            case "SEC_OBJECT_PROTECTION":
            {
                return new SecObjectProtection(
                    new SecObjectProtectionSpec(
                        (String) setters.get(GeneratedDatabaseTables.SecObjectProtection.OBJECT_PATH).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.SecObjectProtection.CREATOR_IDENTITY_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.SecObjectProtection.OWNER_ROLE_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.SecObjectProtection.SECURITY_TYPE_NAME).accept(data)
                    )
                );
            }
            case "SEC_ROLES":
            {
                return new SecRoles(
                    new SecRolesSpec(
                        (String) setters.get(GeneratedDatabaseTables.SecRoles.ROLE_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.SecRoles.ROLE_DSP_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.SecRoles.DOMAIN_NAME).accept(data),
                        (boolean) setters.get(GeneratedDatabaseTables.SecRoles.ROLE_ENABLED).accept(data),
                        (long) setters.get(GeneratedDatabaseTables.SecRoles.ROLE_PRIVILEGES).accept(data)
                    )
                );
            }
            case "SEC_TYPES":
            {
                return new SecTypes(
                    new SecTypesSpec(
                        (String) setters.get(GeneratedDatabaseTables.SecTypes.TYPE_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.SecTypes.TYPE_DSP_NAME).accept(data),
                        (boolean) setters.get(GeneratedDatabaseTables.SecTypes.TYPE_ENABLED).accept(data)
                    )
                );
            }
            case "SEC_TYPE_RULES":
            {
                return new SecTypeRules(
                    new SecTypeRulesSpec(
                        (String) setters.get(GeneratedDatabaseTables.SecTypeRules.DOMAIN_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.SecTypeRules.TYPE_NAME).accept(data),
                        (short) setters.get(GeneratedDatabaseTables.SecTypeRules.ACCESS_TYPE).accept(data)
                    )
                );
            }
            case "SPACE_HISTORY":
            {
                return new SpaceHistory(
                    new SpaceHistorySpec(
                        (Date) setters.get(GeneratedDatabaseTables.SpaceHistory.ENTRY_DATE).accept(data),
                        (byte[]) setters.get(GeneratedDatabaseTables.SpaceHistory.CAPACITY).accept(data)
                    )
                );
            }
            case "STOR_POOL_DEFINITIONS":
            {
                return new StorPoolDefinitions(
                    new StorPoolDefinitionsSpec(
                        (String) setters.get(GeneratedDatabaseTables.StorPoolDefinitions.UUID).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.StorPoolDefinitions.POOL_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.StorPoolDefinitions.POOL_DSP_NAME).accept(data)
                    )
                );
            }
            case "TRACKING_DATE":
            {
                return new TrackingDate(
                    new TrackingDateSpec(
                        (Date) setters.get(GeneratedDatabaseTables.TrackingDate.ENTRY_DATE).accept(data)
                    )
                );
            }
            case "VOLUMES":
            {
                return new Volumes(
                    new VolumesSpec(
                        (String) setters.get(GeneratedDatabaseTables.Volumes.UUID).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.Volumes.NODE_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.Volumes.RESOURCE_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.Volumes.SNAPSHOT_NAME).accept(data),
                        (int) setters.get(GeneratedDatabaseTables.Volumes.VLM_NR).accept(data),
                        (long) setters.get(GeneratedDatabaseTables.Volumes.VLM_FLAGS).accept(data)
                    )
                );
            }
            case "VOLUME_CONNECTIONS":
            {
                return new VolumeConnections(
                    new VolumeConnectionsSpec(
                        (String) setters.get(GeneratedDatabaseTables.VolumeConnections.UUID).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.VolumeConnections.NODE_NAME_SRC).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.VolumeConnections.NODE_NAME_DST).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.VolumeConnections.RESOURCE_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.VolumeConnections.SNAPSHOT_NAME).accept(data),
                        (int) setters.get(GeneratedDatabaseTables.VolumeConnections.VLM_NR).accept(data)
                    )
                );
            }
            case "VOLUME_DEFINITIONS":
            {
                return new VolumeDefinitions(
                    new VolumeDefinitionsSpec(
                        (String) setters.get(GeneratedDatabaseTables.VolumeDefinitions.UUID).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.VolumeDefinitions.RESOURCE_NAME).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.VolumeDefinitions.SNAPSHOT_NAME).accept(data),
                        (int) setters.get(GeneratedDatabaseTables.VolumeDefinitions.VLM_NR).accept(data),
                        (long) setters.get(GeneratedDatabaseTables.VolumeDefinitions.VLM_SIZE).accept(data),
                        (long) setters.get(GeneratedDatabaseTables.VolumeDefinitions.VLM_FLAGS).accept(data)
                    )
                );
            }
            case "VOLUME_GROUPS":
            {
                return new VolumeGroups(
                    new VolumeGroupsSpec(
                        (String) setters.get(GeneratedDatabaseTables.VolumeGroups.UUID).accept(data),
                        (String) setters.get(GeneratedDatabaseTables.VolumeGroups.RESOURCE_GROUP_NAME).accept(data),
                        (int) setters.get(GeneratedDatabaseTables.VolumeGroups.VLM_NR).accept(data),
                        (long) setters.get(GeneratedDatabaseTables.VolumeGroups.FLAGS).accept(data)
                    )
                );
            }
            default:
                throw new ImplementationError("Unknown database table: " + table.getName());
        }
    }

    public static String databaseTableToYamlLocation(DatabaseTable dbTable)
    {
        switch(dbTable.getName())
        {
            case "FILES":
                return "generated-resources/com/linbit/linstor/dbcp/k8s/crd/v1_15_0/Files.yaml";
            case "KEY_VALUE_STORE":
                return "generated-resources/com/linbit/linstor/dbcp/k8s/crd/v1_15_0/KeyValueStore.yaml";
            case "LAYER_BCACHE_VOLUMES":
                return "generated-resources/com/linbit/linstor/dbcp/k8s/crd/v1_15_0/LayerBcacheVolumes.yaml";
            case "LAYER_CACHE_VOLUMES":
                return "generated-resources/com/linbit/linstor/dbcp/k8s/crd/v1_15_0/LayerCacheVolumes.yaml";
            case "LAYER_DRBD_RESOURCES":
                return "generated-resources/com/linbit/linstor/dbcp/k8s/crd/v1_15_0/LayerDrbdResources.yaml";
            case "LAYER_DRBD_RESOURCE_DEFINITIONS":
                return "generated-resources/com/linbit/linstor/dbcp/k8s/crd/v1_15_0/LayerDrbdResourceDefinitions.yaml";
            case "LAYER_DRBD_VOLUMES":
                return "generated-resources/com/linbit/linstor/dbcp/k8s/crd/v1_15_0/LayerDrbdVolumes.yaml";
            case "LAYER_DRBD_VOLUME_DEFINITIONS":
                return "generated-resources/com/linbit/linstor/dbcp/k8s/crd/v1_15_0/LayerDrbdVolumeDefinitions.yaml";
            case "LAYER_LUKS_VOLUMES":
                return "generated-resources/com/linbit/linstor/dbcp/k8s/crd/v1_15_0/LayerLuksVolumes.yaml";
            case "LAYER_OPENFLEX_RESOURCE_DEFINITIONS":
                return "generated-resources/com/linbit/linstor/dbcp/k8s/crd/v1_15_0/LayerOpenflexResourceDefinitions.yaml";
            case "LAYER_OPENFLEX_VOLUMES":
                return "generated-resources/com/linbit/linstor/dbcp/k8s/crd/v1_15_0/LayerOpenflexVolumes.yaml";
            case "LAYER_RESOURCE_IDS":
                return "generated-resources/com/linbit/linstor/dbcp/k8s/crd/v1_15_0/LayerResourceIds.yaml";
            case "LAYER_STORAGE_VOLUMES":
                return "generated-resources/com/linbit/linstor/dbcp/k8s/crd/v1_15_0/LayerStorageVolumes.yaml";
            case "LAYER_WRITECACHE_VOLUMES":
                return "generated-resources/com/linbit/linstor/dbcp/k8s/crd/v1_15_0/LayerWritecacheVolumes.yaml";
            case "LINSTOR_REMOTES":
                return "generated-resources/com/linbit/linstor/dbcp/k8s/crd/v1_15_0/LinstorRemotes.yaml";
            case "NODES":
                return "generated-resources/com/linbit/linstor/dbcp/k8s/crd/v1_15_0/Nodes.yaml";
            case "NODE_CONNECTIONS":
                return "generated-resources/com/linbit/linstor/dbcp/k8s/crd/v1_15_0/NodeConnections.yaml";
            case "NODE_NET_INTERFACES":
                return "generated-resources/com/linbit/linstor/dbcp/k8s/crd/v1_15_0/NodeNetInterfaces.yaml";
            case "NODE_STOR_POOL":
                return "generated-resources/com/linbit/linstor/dbcp/k8s/crd/v1_15_0/NodeStorPool.yaml";
            case "PROPS_CONTAINERS":
                return "generated-resources/com/linbit/linstor/dbcp/k8s/crd/v1_15_0/PropsContainers.yaml";
            case "RESOURCES":
                return "generated-resources/com/linbit/linstor/dbcp/k8s/crd/v1_15_0/Resources.yaml";
            case "RESOURCE_CONNECTIONS":
                return "generated-resources/com/linbit/linstor/dbcp/k8s/crd/v1_15_0/ResourceConnections.yaml";
            case "RESOURCE_DEFINITIONS":
                return "generated-resources/com/linbit/linstor/dbcp/k8s/crd/v1_15_0/ResourceDefinitions.yaml";
            case "RESOURCE_GROUPS":
                return "generated-resources/com/linbit/linstor/dbcp/k8s/crd/v1_15_0/ResourceGroups.yaml";
            case "S3_REMOTES":
                return "generated-resources/com/linbit/linstor/dbcp/k8s/crd/v1_15_0/S3Remotes.yaml";
            case "SATELLITES_CAPACITY":
                return "generated-resources/com/linbit/linstor/dbcp/k8s/crd/v1_15_0/SatellitesCapacity.yaml";
            case "SEC_ACCESS_TYPES":
                return "generated-resources/com/linbit/linstor/dbcp/k8s/crd/v1_15_0/SecAccessTypes.yaml";
            case "SEC_ACL_MAP":
                return "generated-resources/com/linbit/linstor/dbcp/k8s/crd/v1_15_0/SecAclMap.yaml";
            case "SEC_CONFIGURATION":
                return "generated-resources/com/linbit/linstor/dbcp/k8s/crd/v1_15_0/SecConfiguration.yaml";
            case "SEC_DFLT_ROLES":
                return "generated-resources/com/linbit/linstor/dbcp/k8s/crd/v1_15_0/SecDfltRoles.yaml";
            case "SEC_IDENTITIES":
                return "generated-resources/com/linbit/linstor/dbcp/k8s/crd/v1_15_0/SecIdentities.yaml";
            case "SEC_ID_ROLE_MAP":
                return "generated-resources/com/linbit/linstor/dbcp/k8s/crd/v1_15_0/SecIdRoleMap.yaml";
            case "SEC_OBJECT_PROTECTION":
                return "generated-resources/com/linbit/linstor/dbcp/k8s/crd/v1_15_0/SecObjectProtection.yaml";
            case "SEC_ROLES":
                return "generated-resources/com/linbit/linstor/dbcp/k8s/crd/v1_15_0/SecRoles.yaml";
            case "SEC_TYPES":
                return "generated-resources/com/linbit/linstor/dbcp/k8s/crd/v1_15_0/SecTypes.yaml";
            case "SEC_TYPE_RULES":
                return "generated-resources/com/linbit/linstor/dbcp/k8s/crd/v1_15_0/SecTypeRules.yaml";
            case "SPACE_HISTORY":
                return "generated-resources/com/linbit/linstor/dbcp/k8s/crd/v1_15_0/SpaceHistory.yaml";
            case "STOR_POOL_DEFINITIONS":
                return "generated-resources/com/linbit/linstor/dbcp/k8s/crd/v1_15_0/StorPoolDefinitions.yaml";
            case "TRACKING_DATE":
                return "generated-resources/com/linbit/linstor/dbcp/k8s/crd/v1_15_0/TrackingDate.yaml";
            case "VOLUMES":
                return "generated-resources/com/linbit/linstor/dbcp/k8s/crd/v1_15_0/Volumes.yaml";
            case "VOLUME_CONNECTIONS":
                return "generated-resources/com/linbit/linstor/dbcp/k8s/crd/v1_15_0/VolumeConnections.yaml";
            case "VOLUME_DEFINITIONS":
                return "generated-resources/com/linbit/linstor/dbcp/k8s/crd/v1_15_0/VolumeDefinitions.yaml";
            case "VOLUME_GROUPS":
                return "generated-resources/com/linbit/linstor/dbcp/k8s/crd/v1_15_0/VolumeGroups.yaml";
            default:
                throw new ImplementationError("Unknown database table: " + dbTable.getName());
        }
    }

    public static BaseControllerK8sCrdTransactionMgrContext createTxMgrContext()
    {
        return new BaseControllerK8sCrdTransactionMgrContext(
            GenCrdCurrent::databaseTableToCustomResourceClass,
            GenCrdCurrent::specToCrd
        );
    }

    public static K8sCrdSchemaUpdateContext createSchemaUpdateContext()
    {
        return new K8sCrdSchemaUpdateContext(
            GenCrdCurrent::databaseTableToYamlLocation
        );
    }

    public static Files createFiles(
        String uuid,
        String path,
        long flags,
        byte[] content,
        String contentChecksum
    )
    {
        return new Files(
            new FilesSpec(
                uuid,
                path,
                flags,
                content,
                contentChecksum
            )
        );
    }

    public static class FilesSpec implements LinstorSpec
    {
        @JsonIgnore private static final long serialVersionUID = -4332451864562355580L;
        @JsonIgnore private static final String PK_FORMAT = "%s";

        @JsonIgnore private final String formattedPrimaryKey;

        @JsonProperty("uuid") public final String uuid;
        @JsonProperty("path") public final String path; // PK
        @JsonProperty("flags") public final long flags;
        @JsonProperty("content") public final byte[] content;
        @JsonProperty("content_checksum") public final String contentChecksum;

        @JsonCreator
        public FilesSpec(
            @JsonProperty("uuid") String uuidRef,
            @JsonProperty("path") String pathRef,
            @JsonProperty("flags") long flagsRef,
            @JsonProperty("content") byte[] contentRef,
            @JsonProperty("content_checksum") String contentChecksumRef
        )
        {
            uuid = uuidRef;
            path = pathRef;
            flags = flagsRef;
            content = contentRef;
            contentChecksum = contentChecksumRef;

            formattedPrimaryKey = base32Encode(String.format(
                FilesSpec.PK_FORMAT,
                path
            ));
        }

        public final <DATA> FilesSpec create(
            Map<Column, ExceptionThrowingFunction<DATA, Object, AccessDeniedException>> setters,
            DATA data
        )
            throws AccessDeniedException
        {
            return new FilesSpec(
                (String) setters.get(GeneratedDatabaseTables.Files.UUID).accept(data),
                (String) setters.get(GeneratedDatabaseTables.Files.PATH).accept(data),
                (long) setters.get(GeneratedDatabaseTables.Files.FLAGS).accept(data),
                (byte[]) setters.get(GeneratedDatabaseTables.Files.CONTENT).accept(data),
                (String) setters.get(GeneratedDatabaseTables.Files.CONTENT_CHECKSUM).accept(data)
            );
        }

        @JsonIgnore
        @Override
        public Map<String, Object> asRawParameters()
        {
            Map<String, Object> ret = new TreeMap<>();
            ret.put("UUID", uuid);
            ret.put("PATH", path);
            ret.put("FLAGS", flags);
            ret.put("CONTENT", content);
            ret.put("CONTENT_CHECKSUM", contentChecksum);
            return ret;
        }

        @JsonIgnore
        @Override
        public Object getByColumn(Column clm)
        {
            switch(clm.getName())
            {
                case "UUID":
                    return uuid;
                case "PATH":
                    return path;
                case "FLAGS":
                    return flags;
                case "CONTENT":
                    return content;
                case "CONTENT_CHECKSUM":
                    return contentChecksum;
                default:
                    throw new ImplementationError("Unknown database column. Table: " + clm.getTable().getName() + ", Column: " + clm.getName());
            }
        }

        @JsonIgnore
        @Override
        public final String getKey()
        {
            return formattedPrimaryKey;
        }

        @Override
        @JsonIgnore
        public DatabaseTable getDatabaseTable()
        {
            return GeneratedDatabaseTables.FILES;
        }
    }

    @Version(GenCrdCurrent.VERSION)
    @Group(GenCrdCurrent.GROUP)
    @Plural("files")
    @Singular("files")
    public static class Files extends CustomResource<FilesSpec, Void> implements LinstorCrd<FilesSpec>
    {
        private static final long serialVersionUID = 1425489104096324780L;

        public Files()
        {
            super();
        }

        public Files(FilesSpec spec)
        {
            setMetadata(new ObjectMetaBuilder().withName(spec.getKey()).build());
            setSpec(spec);
        }

        @JsonIgnore
        public String getKey()
        {
            return spec.getKey();
        }
    }

    public static KeyValueStore createKeyValueStore(
        String uuid,
        String kvsName,
        String kvsDspName
    )
    {
        return new KeyValueStore(
            new KeyValueStoreSpec(
                uuid,
                kvsName,
                kvsDspName
            )
        );
    }

    public static class KeyValueStoreSpec implements LinstorSpec
    {
        @JsonIgnore private static final long serialVersionUID = 1022852919437968966L;
        @JsonIgnore private static final String PK_FORMAT = "%s";

        @JsonIgnore private final String formattedPrimaryKey;

        @JsonProperty("uuid") public final String uuid;
        @JsonProperty("kvs_name") public final String kvsName; // PK
        @JsonProperty("kvs_dsp_name") public final String kvsDspName;

        @JsonCreator
        public KeyValueStoreSpec(
            @JsonProperty("uuid") String uuidRef,
            @JsonProperty("kvs_name") String kvsNameRef,
            @JsonProperty("kvs_dsp_name") String kvsDspNameRef
        )
        {
            uuid = uuidRef;
            kvsName = kvsNameRef;
            kvsDspName = kvsDspNameRef;

            formattedPrimaryKey = base32Encode(String.format(
                KeyValueStoreSpec.PK_FORMAT,
                kvsName
            ));
        }

        public final <DATA> KeyValueStoreSpec create(
            Map<Column, ExceptionThrowingFunction<DATA, Object, AccessDeniedException>> setters,
            DATA data
        )
            throws AccessDeniedException
        {
            return new KeyValueStoreSpec(
                (String) setters.get(GeneratedDatabaseTables.KeyValueStore.UUID).accept(data),
                (String) setters.get(GeneratedDatabaseTables.KeyValueStore.KVS_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.KeyValueStore.KVS_DSP_NAME).accept(data)
            );
        }

        @JsonIgnore
        @Override
        public Map<String, Object> asRawParameters()
        {
            Map<String, Object> ret = new TreeMap<>();
            ret.put("UUID", uuid);
            ret.put("KVS_NAME", kvsName);
            ret.put("KVS_DSP_NAME", kvsDspName);
            return ret;
        }

        @JsonIgnore
        @Override
        public Object getByColumn(Column clm)
        {
            switch(clm.getName())
            {
                case "UUID":
                    return uuid;
                case "KVS_NAME":
                    return kvsName;
                case "KVS_DSP_NAME":
                    return kvsDspName;
                default:
                    throw new ImplementationError("Unknown database column. Table: " + clm.getTable().getName() + ", Column: " + clm.getName());
            }
        }

        @JsonIgnore
        @Override
        public final String getKey()
        {
            return formattedPrimaryKey;
        }

        @Override
        @JsonIgnore
        public DatabaseTable getDatabaseTable()
        {
            return GeneratedDatabaseTables.KEY_VALUE_STORE;
        }
    }

    @Version(GenCrdCurrent.VERSION)
    @Group(GenCrdCurrent.GROUP)
    @Plural("keyvaluestore")
    @Singular("keyvaluestore")
    public static class KeyValueStore extends CustomResource<KeyValueStoreSpec, Void> implements LinstorCrd<KeyValueStoreSpec>
    {
        private static final long serialVersionUID = -6613301534758312421L;

        public KeyValueStore()
        {
            super();
        }

        public KeyValueStore(KeyValueStoreSpec spec)
        {
            setMetadata(new ObjectMetaBuilder().withName(spec.getKey()).build());
            setSpec(spec);
        }

        @JsonIgnore
        public String getKey()
        {
            return spec.getKey();
        }
    }

    public static LayerBcacheVolumes createLayerBcacheVolumes(
        int layerResourceId,
        int vlmNr,
        String nodeName,
        String poolName,
        String devUuid
    )
    {
        return new LayerBcacheVolumes(
            new LayerBcacheVolumesSpec(
                layerResourceId,
                vlmNr,
                nodeName,
                poolName,
                devUuid
            )
        );
    }

    public static class LayerBcacheVolumesSpec implements LinstorSpec
    {
        @JsonIgnore private static final long serialVersionUID = -861516041805861805L;
        @JsonIgnore private static final String PK_FORMAT = "%d:%d";

        @JsonIgnore private final String formattedPrimaryKey;

        @JsonProperty("layer_resource_id") public final int layerResourceId; // PK
        @JsonProperty("vlm_nr") public final int vlmNr; // PK
        @JsonProperty("node_name") public final String nodeName;
        @JsonProperty("pool_name") public final String poolName;
        @JsonProperty("dev_uuid") public final String devUuid;

        @JsonCreator
        public LayerBcacheVolumesSpec(
            @JsonProperty("layer_resource_id") int layerResourceIdRef,
            @JsonProperty("vlm_nr") int vlmNrRef,
            @JsonProperty("node_name") String nodeNameRef,
            @JsonProperty("pool_name") String poolNameRef,
            @JsonProperty("dev_uuid") String devUuidRef
        )
        {
            layerResourceId = layerResourceIdRef;
            vlmNr = vlmNrRef;
            nodeName = nodeNameRef;
            poolName = poolNameRef;
            devUuid = devUuidRef;

            formattedPrimaryKey = base32Encode(String.format(
                LayerBcacheVolumesSpec.PK_FORMAT,
                layerResourceId,
                vlmNr
            ));
        }

        public final <DATA> LayerBcacheVolumesSpec create(
            Map<Column, ExceptionThrowingFunction<DATA, Object, AccessDeniedException>> setters,
            DATA data
        )
            throws AccessDeniedException
        {
            return new LayerBcacheVolumesSpec(
                (int) setters.get(GeneratedDatabaseTables.LayerBcacheVolumes.LAYER_RESOURCE_ID).accept(data),
                (int) setters.get(GeneratedDatabaseTables.LayerBcacheVolumes.VLM_NR).accept(data),
                (String) setters.get(GeneratedDatabaseTables.LayerBcacheVolumes.NODE_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.LayerBcacheVolumes.POOL_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.LayerBcacheVolumes.DEV_UUID).accept(data)
            );
        }

        @JsonIgnore
        @Override
        public Map<String, Object> asRawParameters()
        {
            Map<String, Object> ret = new TreeMap<>();
            ret.put("LAYER_RESOURCE_ID", layerResourceId);
            ret.put("VLM_NR", vlmNr);
            ret.put("NODE_NAME", nodeName);
            ret.put("POOL_NAME", poolName);
            ret.put("DEV_UUID", devUuid);
            return ret;
        }

        @JsonIgnore
        @Override
        public Object getByColumn(Column clm)
        {
            switch(clm.getName())
            {
                case "LAYER_RESOURCE_ID":
                    return layerResourceId;
                case "VLM_NR":
                    return vlmNr;
                case "NODE_NAME":
                    return nodeName;
                case "POOL_NAME":
                    return poolName;
                case "DEV_UUID":
                    return devUuid;
                default:
                    throw new ImplementationError("Unknown database column. Table: " + clm.getTable().getName() + ", Column: " + clm.getName());
            }
        }

        @JsonIgnore
        @Override
        public final String getKey()
        {
            return formattedPrimaryKey;
        }

        @Override
        @JsonIgnore
        public DatabaseTable getDatabaseTable()
        {
            return GeneratedDatabaseTables.LAYER_BCACHE_VOLUMES;
        }
    }

    @Version(GenCrdCurrent.VERSION)
    @Group(GenCrdCurrent.GROUP)
    @Plural("layerbcachevolumes")
    @Singular("layerbcachevolumes")
    public static class LayerBcacheVolumes extends CustomResource<LayerBcacheVolumesSpec, Void> implements LinstorCrd<LayerBcacheVolumesSpec>
    {
        private static final long serialVersionUID = -524627216543638949L;

        public LayerBcacheVolumes()
        {
            super();
        }

        public LayerBcacheVolumes(LayerBcacheVolumesSpec spec)
        {
            setMetadata(new ObjectMetaBuilder().withName(spec.getKey()).build());
            setSpec(spec);
        }

        @JsonIgnore
        public String getKey()
        {
            return spec.getKey();
        }
    }

    public static LayerCacheVolumes createLayerCacheVolumes(
        int layerResourceId,
        int vlmNr,
        String nodeName,
        String poolNameCache,
        String poolNameMeta
    )
    {
        return new LayerCacheVolumes(
            new LayerCacheVolumesSpec(
                layerResourceId,
                vlmNr,
                nodeName,
                poolNameCache,
                poolNameMeta
            )
        );
    }

    public static class LayerCacheVolumesSpec implements LinstorSpec
    {
        @JsonIgnore private static final long serialVersionUID = -1443993076571409340L;
        @JsonIgnore private static final String PK_FORMAT = "%d:%d";

        @JsonIgnore private final String formattedPrimaryKey;

        @JsonProperty("layer_resource_id") public final int layerResourceId; // PK
        @JsonProperty("vlm_nr") public final int vlmNr; // PK
        @JsonProperty("node_name") public final String nodeName;
        @JsonProperty("pool_name_cache") public final String poolNameCache;
        @JsonProperty("pool_name_meta") public final String poolNameMeta;

        @JsonCreator
        public LayerCacheVolumesSpec(
            @JsonProperty("layer_resource_id") int layerResourceIdRef,
            @JsonProperty("vlm_nr") int vlmNrRef,
            @JsonProperty("node_name") String nodeNameRef,
            @JsonProperty("pool_name_cache") String poolNameCacheRef,
            @JsonProperty("pool_name_meta") String poolNameMetaRef
        )
        {
            layerResourceId = layerResourceIdRef;
            vlmNr = vlmNrRef;
            nodeName = nodeNameRef;
            poolNameCache = poolNameCacheRef;
            poolNameMeta = poolNameMetaRef;

            formattedPrimaryKey = base32Encode(String.format(
                LayerCacheVolumesSpec.PK_FORMAT,
                layerResourceId,
                vlmNr
            ));
        }

        public final <DATA> LayerCacheVolumesSpec create(
            Map<Column, ExceptionThrowingFunction<DATA, Object, AccessDeniedException>> setters,
            DATA data
        )
            throws AccessDeniedException
        {
            return new LayerCacheVolumesSpec(
                (int) setters.get(GeneratedDatabaseTables.LayerCacheVolumes.LAYER_RESOURCE_ID).accept(data),
                (int) setters.get(GeneratedDatabaseTables.LayerCacheVolumes.VLM_NR).accept(data),
                (String) setters.get(GeneratedDatabaseTables.LayerCacheVolumes.NODE_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.LayerCacheVolumes.POOL_NAME_CACHE).accept(data),
                (String) setters.get(GeneratedDatabaseTables.LayerCacheVolumes.POOL_NAME_META).accept(data)
            );
        }

        @JsonIgnore
        @Override
        public Map<String, Object> asRawParameters()
        {
            Map<String, Object> ret = new TreeMap<>();
            ret.put("LAYER_RESOURCE_ID", layerResourceId);
            ret.put("VLM_NR", vlmNr);
            ret.put("NODE_NAME", nodeName);
            ret.put("POOL_NAME_CACHE", poolNameCache);
            ret.put("POOL_NAME_META", poolNameMeta);
            return ret;
        }

        @JsonIgnore
        @Override
        public Object getByColumn(Column clm)
        {
            switch(clm.getName())
            {
                case "LAYER_RESOURCE_ID":
                    return layerResourceId;
                case "VLM_NR":
                    return vlmNr;
                case "NODE_NAME":
                    return nodeName;
                case "POOL_NAME_CACHE":
                    return poolNameCache;
                case "POOL_NAME_META":
                    return poolNameMeta;
                default:
                    throw new ImplementationError("Unknown database column. Table: " + clm.getTable().getName() + ", Column: " + clm.getName());
            }
        }

        @JsonIgnore
        @Override
        public final String getKey()
        {
            return formattedPrimaryKey;
        }

        @Override
        @JsonIgnore
        public DatabaseTable getDatabaseTable()
        {
            return GeneratedDatabaseTables.LAYER_CACHE_VOLUMES;
        }
    }

    @Version(GenCrdCurrent.VERSION)
    @Group(GenCrdCurrent.GROUP)
    @Plural("layercachevolumes")
    @Singular("layercachevolumes")
    public static class LayerCacheVolumes extends CustomResource<LayerCacheVolumesSpec, Void> implements LinstorCrd<LayerCacheVolumesSpec>
    {
        private static final long serialVersionUID = 8115344576939016720L;

        public LayerCacheVolumes()
        {
            super();
        }

        public LayerCacheVolumes(LayerCacheVolumesSpec spec)
        {
            setMetadata(new ObjectMetaBuilder().withName(spec.getKey()).build());
            setSpec(spec);
        }

        @JsonIgnore
        public String getKey()
        {
            return spec.getKey();
        }
    }

    public static LayerDrbdResources createLayerDrbdResources(
        int layerResourceId,
        int peerSlots,
        int alStripes,
        long alStripeSize,
        long flags,
        int nodeId
    )
    {
        return new LayerDrbdResources(
            new LayerDrbdResourcesSpec(
                layerResourceId,
                peerSlots,
                alStripes,
                alStripeSize,
                flags,
                nodeId
            )
        );
    }

    public static class LayerDrbdResourcesSpec implements LinstorSpec
    {
        @JsonIgnore private static final long serialVersionUID = -8495915986226445943L;
        @JsonIgnore private static final String PK_FORMAT = "%d";

        @JsonIgnore private final String formattedPrimaryKey;

        @JsonProperty("layer_resource_id") public final int layerResourceId; // PK
        @JsonProperty("peer_slots") public final int peerSlots;
        @JsonProperty("al_stripes") public final int alStripes;
        @JsonProperty("al_stripe_size") public final long alStripeSize;
        @JsonProperty("flags") public final long flags;
        @JsonProperty("node_id") public final int nodeId;

        @JsonCreator
        public LayerDrbdResourcesSpec(
            @JsonProperty("layer_resource_id") int layerResourceIdRef,
            @JsonProperty("peer_slots") int peerSlotsRef,
            @JsonProperty("al_stripes") int alStripesRef,
            @JsonProperty("al_stripe_size") long alStripeSizeRef,
            @JsonProperty("flags") long flagsRef,
            @JsonProperty("node_id") int nodeIdRef
        )
        {
            layerResourceId = layerResourceIdRef;
            peerSlots = peerSlotsRef;
            alStripes = alStripesRef;
            alStripeSize = alStripeSizeRef;
            flags = flagsRef;
            nodeId = nodeIdRef;

            formattedPrimaryKey = base32Encode(String.format(
                LayerDrbdResourcesSpec.PK_FORMAT,
                layerResourceId
            ));
        }

        public final <DATA> LayerDrbdResourcesSpec create(
            Map<Column, ExceptionThrowingFunction<DATA, Object, AccessDeniedException>> setters,
            DATA data
        )
            throws AccessDeniedException
        {
            return new LayerDrbdResourcesSpec(
                (int) setters.get(GeneratedDatabaseTables.LayerDrbdResources.LAYER_RESOURCE_ID).accept(data),
                (int) setters.get(GeneratedDatabaseTables.LayerDrbdResources.PEER_SLOTS).accept(data),
                (int) setters.get(GeneratedDatabaseTables.LayerDrbdResources.AL_STRIPES).accept(data),
                (long) setters.get(GeneratedDatabaseTables.LayerDrbdResources.AL_STRIPE_SIZE).accept(data),
                (long) setters.get(GeneratedDatabaseTables.LayerDrbdResources.FLAGS).accept(data),
                (int) setters.get(GeneratedDatabaseTables.LayerDrbdResources.NODE_ID).accept(data)
            );
        }

        @JsonIgnore
        @Override
        public Map<String, Object> asRawParameters()
        {
            Map<String, Object> ret = new TreeMap<>();
            ret.put("LAYER_RESOURCE_ID", layerResourceId);
            ret.put("PEER_SLOTS", peerSlots);
            ret.put("AL_STRIPES", alStripes);
            ret.put("AL_STRIPE_SIZE", alStripeSize);
            ret.put("FLAGS", flags);
            ret.put("NODE_ID", nodeId);
            return ret;
        }

        @JsonIgnore
        @Override
        public Object getByColumn(Column clm)
        {
            switch(clm.getName())
            {
                case "LAYER_RESOURCE_ID":
                    return layerResourceId;
                case "PEER_SLOTS":
                    return peerSlots;
                case "AL_STRIPES":
                    return alStripes;
                case "AL_STRIPE_SIZE":
                    return alStripeSize;
                case "FLAGS":
                    return flags;
                case "NODE_ID":
                    return nodeId;
                default:
                    throw new ImplementationError("Unknown database column. Table: " + clm.getTable().getName() + ", Column: " + clm.getName());
            }
        }

        @JsonIgnore
        @Override
        public final String getKey()
        {
            return formattedPrimaryKey;
        }

        @Override
        @JsonIgnore
        public DatabaseTable getDatabaseTable()
        {
            return GeneratedDatabaseTables.LAYER_DRBD_RESOURCES;
        }
    }

    @Version(GenCrdCurrent.VERSION)
    @Group(GenCrdCurrent.GROUP)
    @Plural("layerdrbdresources")
    @Singular("layerdrbdresources")
    public static class LayerDrbdResources extends CustomResource<LayerDrbdResourcesSpec, Void> implements LinstorCrd<LayerDrbdResourcesSpec>
    {
        private static final long serialVersionUID = 9087179956633677210L;

        public LayerDrbdResources()
        {
            super();
        }

        public LayerDrbdResources(LayerDrbdResourcesSpec spec)
        {
            setMetadata(new ObjectMetaBuilder().withName(spec.getKey()).build());
            setSpec(spec);
        }

        @JsonIgnore
        public String getKey()
        {
            return spec.getKey();
        }
    }

    public static LayerDrbdResourceDefinitions createLayerDrbdResourceDefinitions(
        String resourceName,
        String resourceNameSuffix,
        String snapshotName,
        int peerSlots,
        int alStripes,
        long alStripeSize,
        Integer tcpPort,
        String transportType,
        String secret
    )
    {
        return new LayerDrbdResourceDefinitions(
            new LayerDrbdResourceDefinitionsSpec(
                resourceName,
                resourceNameSuffix,
                snapshotName,
                peerSlots,
                alStripes,
                alStripeSize,
                tcpPort,
                transportType,
                secret
            )
        );
    }

    public static class LayerDrbdResourceDefinitionsSpec implements LinstorSpec
    {
        @JsonIgnore private static final long serialVersionUID = 6299069368093429316L;
        @JsonIgnore private static final String PK_FORMAT = "%s:%s:%s";

        @JsonIgnore private final String formattedPrimaryKey;

        @JsonProperty("resource_name") public final String resourceName; // PK
        @JsonProperty("resource_name_suffix") public final String resourceNameSuffix; // PK
        @JsonProperty("snapshot_name") public final String snapshotName; // PK
        @JsonProperty("peer_slots") public final int peerSlots;
        @JsonProperty("al_stripes") public final int alStripes;
        @JsonProperty("al_stripe_size") public final long alStripeSize;
        @JsonProperty("tcp_port") public final Integer tcpPort;
        @JsonProperty("transport_type") public final String transportType;
        @JsonProperty("secret") public final String secret;

        @JsonCreator
        public LayerDrbdResourceDefinitionsSpec(
            @JsonProperty("resource_name") String resourceNameRef,
            @JsonProperty("resource_name_suffix") String resourceNameSuffixRef,
            @JsonProperty("snapshot_name") String snapshotNameRef,
            @JsonProperty("peer_slots") int peerSlotsRef,
            @JsonProperty("al_stripes") int alStripesRef,
            @JsonProperty("al_stripe_size") long alStripeSizeRef,
            @JsonProperty("tcp_port") Integer tcpPortRef,
            @JsonProperty("transport_type") String transportTypeRef,
            @JsonProperty("secret") String secretRef
        )
        {
            resourceName = resourceNameRef;
            resourceNameSuffix = resourceNameSuffixRef;
            snapshotName = snapshotNameRef;
            peerSlots = peerSlotsRef;
            alStripes = alStripesRef;
            alStripeSize = alStripeSizeRef;
            tcpPort = tcpPortRef;
            transportType = transportTypeRef;
            secret = secretRef;

            formattedPrimaryKey = base32Encode(String.format(
                LayerDrbdResourceDefinitionsSpec.PK_FORMAT,
                resourceName,
                resourceNameSuffix,
                snapshotName
            ));
        }

        public final <DATA> LayerDrbdResourceDefinitionsSpec create(
            Map<Column, ExceptionThrowingFunction<DATA, Object, AccessDeniedException>> setters,
            DATA data
        )
            throws AccessDeniedException
        {
            return new LayerDrbdResourceDefinitionsSpec(
                (String) setters.get(GeneratedDatabaseTables.LayerDrbdResourceDefinitions.RESOURCE_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.LayerDrbdResourceDefinitions.RESOURCE_NAME_SUFFIX).accept(data),
                (String) setters.get(GeneratedDatabaseTables.LayerDrbdResourceDefinitions.SNAPSHOT_NAME).accept(data),
                (int) setters.get(GeneratedDatabaseTables.LayerDrbdResourceDefinitions.PEER_SLOTS).accept(data),
                (int) setters.get(GeneratedDatabaseTables.LayerDrbdResourceDefinitions.AL_STRIPES).accept(data),
                (long) setters.get(GeneratedDatabaseTables.LayerDrbdResourceDefinitions.AL_STRIPE_SIZE).accept(data),
                (Integer) setters.get(GeneratedDatabaseTables.LayerDrbdResourceDefinitions.TCP_PORT).accept(data),
                (String) setters.get(GeneratedDatabaseTables.LayerDrbdResourceDefinitions.TRANSPORT_TYPE).accept(data),
                (String) setters.get(GeneratedDatabaseTables.LayerDrbdResourceDefinitions.SECRET).accept(data)
            );
        }

        @JsonIgnore
        @Override
        public Map<String, Object> asRawParameters()
        {
            Map<String, Object> ret = new TreeMap<>();
            ret.put("RESOURCE_NAME", resourceName);
            ret.put("RESOURCE_NAME_SUFFIX", resourceNameSuffix);
            ret.put("SNAPSHOT_NAME", snapshotName);
            ret.put("PEER_SLOTS", peerSlots);
            ret.put("AL_STRIPES", alStripes);
            ret.put("AL_STRIPE_SIZE", alStripeSize);
            ret.put("TCP_PORT", tcpPort);
            ret.put("TRANSPORT_TYPE", transportType);
            ret.put("SECRET", secret);
            return ret;
        }

        @JsonIgnore
        @Override
        public Object getByColumn(Column clm)
        {
            switch(clm.getName())
            {
                case "RESOURCE_NAME":
                    return resourceName;
                case "RESOURCE_NAME_SUFFIX":
                    return resourceNameSuffix;
                case "SNAPSHOT_NAME":
                    return snapshotName;
                case "PEER_SLOTS":
                    return peerSlots;
                case "AL_STRIPES":
                    return alStripes;
                case "AL_STRIPE_SIZE":
                    return alStripeSize;
                case "TCP_PORT":
                    return tcpPort;
                case "TRANSPORT_TYPE":
                    return transportType;
                case "SECRET":
                    return secret;
                default:
                    throw new ImplementationError("Unknown database column. Table: " + clm.getTable().getName() + ", Column: " + clm.getName());
            }
        }

        @JsonIgnore
        @Override
        public final String getKey()
        {
            return formattedPrimaryKey;
        }

        @Override
        @JsonIgnore
        public DatabaseTable getDatabaseTable()
        {
            return GeneratedDatabaseTables.LAYER_DRBD_RESOURCE_DEFINITIONS;
        }
    }

    @Version(GenCrdCurrent.VERSION)
    @Group(GenCrdCurrent.GROUP)
    @Plural("layerdrbdresourcedefinitions")
    @Singular("layerdrbdresourcedefinitions")
    public static class LayerDrbdResourceDefinitions extends CustomResource<LayerDrbdResourceDefinitionsSpec, Void> implements LinstorCrd<LayerDrbdResourceDefinitionsSpec>
    {
        private static final long serialVersionUID = -9132231493364507494L;

        public LayerDrbdResourceDefinitions()
        {
            super();
        }

        public LayerDrbdResourceDefinitions(LayerDrbdResourceDefinitionsSpec spec)
        {
            setMetadata(new ObjectMetaBuilder().withName(spec.getKey()).build());
            setSpec(spec);
        }

        @JsonIgnore
        public String getKey()
        {
            return spec.getKey();
        }
    }

    public static LayerDrbdVolumes createLayerDrbdVolumes(
        int layerResourceId,
        int vlmNr,
        String nodeName,
        String poolName
    )
    {
        return new LayerDrbdVolumes(
            new LayerDrbdVolumesSpec(
                layerResourceId,
                vlmNr,
                nodeName,
                poolName
            )
        );
    }

    public static class LayerDrbdVolumesSpec implements LinstorSpec
    {
        @JsonIgnore private static final long serialVersionUID = -5779542991709357964L;
        @JsonIgnore private static final String PK_FORMAT = "%d:%d";

        @JsonIgnore private final String formattedPrimaryKey;

        @JsonProperty("layer_resource_id") public final int layerResourceId; // PK
        @JsonProperty("vlm_nr") public final int vlmNr; // PK
        @JsonProperty("node_name") public final String nodeName;
        @JsonProperty("pool_name") public final String poolName;

        @JsonCreator
        public LayerDrbdVolumesSpec(
            @JsonProperty("layer_resource_id") int layerResourceIdRef,
            @JsonProperty("vlm_nr") int vlmNrRef,
            @JsonProperty("node_name") String nodeNameRef,
            @JsonProperty("pool_name") String poolNameRef
        )
        {
            layerResourceId = layerResourceIdRef;
            vlmNr = vlmNrRef;
            nodeName = nodeNameRef;
            poolName = poolNameRef;

            formattedPrimaryKey = base32Encode(String.format(
                LayerDrbdVolumesSpec.PK_FORMAT,
                layerResourceId,
                vlmNr
            ));
        }

        public final <DATA> LayerDrbdVolumesSpec create(
            Map<Column, ExceptionThrowingFunction<DATA, Object, AccessDeniedException>> setters,
            DATA data
        )
            throws AccessDeniedException
        {
            return new LayerDrbdVolumesSpec(
                (int) setters.get(GeneratedDatabaseTables.LayerDrbdVolumes.LAYER_RESOURCE_ID).accept(data),
                (int) setters.get(GeneratedDatabaseTables.LayerDrbdVolumes.VLM_NR).accept(data),
                (String) setters.get(GeneratedDatabaseTables.LayerDrbdVolumes.NODE_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.LayerDrbdVolumes.POOL_NAME).accept(data)
            );
        }

        @JsonIgnore
        @Override
        public Map<String, Object> asRawParameters()
        {
            Map<String, Object> ret = new TreeMap<>();
            ret.put("LAYER_RESOURCE_ID", layerResourceId);
            ret.put("VLM_NR", vlmNr);
            ret.put("NODE_NAME", nodeName);
            ret.put("POOL_NAME", poolName);
            return ret;
        }

        @JsonIgnore
        @Override
        public Object getByColumn(Column clm)
        {
            switch(clm.getName())
            {
                case "LAYER_RESOURCE_ID":
                    return layerResourceId;
                case "VLM_NR":
                    return vlmNr;
                case "NODE_NAME":
                    return nodeName;
                case "POOL_NAME":
                    return poolName;
                default:
                    throw new ImplementationError("Unknown database column. Table: " + clm.getTable().getName() + ", Column: " + clm.getName());
            }
        }

        @JsonIgnore
        @Override
        public final String getKey()
        {
            return formattedPrimaryKey;
        }

        @Override
        @JsonIgnore
        public DatabaseTable getDatabaseTable()
        {
            return GeneratedDatabaseTables.LAYER_DRBD_VOLUMES;
        }
    }

    @Version(GenCrdCurrent.VERSION)
    @Group(GenCrdCurrent.GROUP)
    @Plural("layerdrbdvolumes")
    @Singular("layerdrbdvolumes")
    public static class LayerDrbdVolumes extends CustomResource<LayerDrbdVolumesSpec, Void> implements LinstorCrd<LayerDrbdVolumesSpec>
    {
        private static final long serialVersionUID = 8264802293535443381L;

        public LayerDrbdVolumes()
        {
            super();
        }

        public LayerDrbdVolumes(LayerDrbdVolumesSpec spec)
        {
            setMetadata(new ObjectMetaBuilder().withName(spec.getKey()).build());
            setSpec(spec);
        }

        @JsonIgnore
        public String getKey()
        {
            return spec.getKey();
        }
    }

    public static LayerDrbdVolumeDefinitions createLayerDrbdVolumeDefinitions(
        String resourceName,
        String resourceNameSuffix,
        String snapshotName,
        int vlmNr,
        Integer vlmMinorNr
    )
    {
        return new LayerDrbdVolumeDefinitions(
            new LayerDrbdVolumeDefinitionsSpec(
                resourceName,
                resourceNameSuffix,
                snapshotName,
                vlmNr,
                vlmMinorNr
            )
        );
    }

    public static class LayerDrbdVolumeDefinitionsSpec implements LinstorSpec
    {
        @JsonIgnore private static final long serialVersionUID = 1704563899162230102L;
        @JsonIgnore private static final String PK_FORMAT = "%s:%s:%s:%d";

        @JsonIgnore private final String formattedPrimaryKey;

        @JsonProperty("resource_name") public final String resourceName; // PK
        @JsonProperty("resource_name_suffix") public final String resourceNameSuffix; // PK
        @JsonProperty("snapshot_name") public final String snapshotName; // PK
        @JsonProperty("vlm_nr") public final int vlmNr; // PK
        @JsonProperty("vlm_minor_nr") public final Integer vlmMinorNr;

        @JsonCreator
        public LayerDrbdVolumeDefinitionsSpec(
            @JsonProperty("resource_name") String resourceNameRef,
            @JsonProperty("resource_name_suffix") String resourceNameSuffixRef,
            @JsonProperty("snapshot_name") String snapshotNameRef,
            @JsonProperty("vlm_nr") int vlmNrRef,
            @JsonProperty("vlm_minor_nr") Integer vlmMinorNrRef
        )
        {
            resourceName = resourceNameRef;
            resourceNameSuffix = resourceNameSuffixRef;
            snapshotName = snapshotNameRef;
            vlmNr = vlmNrRef;
            vlmMinorNr = vlmMinorNrRef;

            formattedPrimaryKey = base32Encode(String.format(
                LayerDrbdVolumeDefinitionsSpec.PK_FORMAT,
                resourceName,
                resourceNameSuffix,
                snapshotName,
                vlmNr
            ));
        }

        public final <DATA> LayerDrbdVolumeDefinitionsSpec create(
            Map<Column, ExceptionThrowingFunction<DATA, Object, AccessDeniedException>> setters,
            DATA data
        )
            throws AccessDeniedException
        {
            return new LayerDrbdVolumeDefinitionsSpec(
                (String) setters.get(GeneratedDatabaseTables.LayerDrbdVolumeDefinitions.RESOURCE_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.LayerDrbdVolumeDefinitions.RESOURCE_NAME_SUFFIX).accept(data),
                (String) setters.get(GeneratedDatabaseTables.LayerDrbdVolumeDefinitions.SNAPSHOT_NAME).accept(data),
                (int) setters.get(GeneratedDatabaseTables.LayerDrbdVolumeDefinitions.VLM_NR).accept(data),
                (Integer) setters.get(GeneratedDatabaseTables.LayerDrbdVolumeDefinitions.VLM_MINOR_NR).accept(data)
            );
        }

        @JsonIgnore
        @Override
        public Map<String, Object> asRawParameters()
        {
            Map<String, Object> ret = new TreeMap<>();
            ret.put("RESOURCE_NAME", resourceName);
            ret.put("RESOURCE_NAME_SUFFIX", resourceNameSuffix);
            ret.put("SNAPSHOT_NAME", snapshotName);
            ret.put("VLM_NR", vlmNr);
            ret.put("VLM_MINOR_NR", vlmMinorNr);
            return ret;
        }

        @JsonIgnore
        @Override
        public Object getByColumn(Column clm)
        {
            switch(clm.getName())
            {
                case "RESOURCE_NAME":
                    return resourceName;
                case "RESOURCE_NAME_SUFFIX":
                    return resourceNameSuffix;
                case "SNAPSHOT_NAME":
                    return snapshotName;
                case "VLM_NR":
                    return vlmNr;
                case "VLM_MINOR_NR":
                    return vlmMinorNr;
                default:
                    throw new ImplementationError("Unknown database column. Table: " + clm.getTable().getName() + ", Column: " + clm.getName());
            }
        }

        @JsonIgnore
        @Override
        public final String getKey()
        {
            return formattedPrimaryKey;
        }

        @Override
        @JsonIgnore
        public DatabaseTable getDatabaseTable()
        {
            return GeneratedDatabaseTables.LAYER_DRBD_VOLUME_DEFINITIONS;
        }
    }

    @Version(GenCrdCurrent.VERSION)
    @Group(GenCrdCurrent.GROUP)
    @Plural("layerdrbdvolumedefinitions")
    @Singular("layerdrbdvolumedefinitions")
    public static class LayerDrbdVolumeDefinitions extends CustomResource<LayerDrbdVolumeDefinitionsSpec, Void> implements LinstorCrd<LayerDrbdVolumeDefinitionsSpec>
    {
        private static final long serialVersionUID = 3361572907726532247L;

        public LayerDrbdVolumeDefinitions()
        {
            super();
        }

        public LayerDrbdVolumeDefinitions(LayerDrbdVolumeDefinitionsSpec spec)
        {
            setMetadata(new ObjectMetaBuilder().withName(spec.getKey()).build());
            setSpec(spec);
        }

        @JsonIgnore
        public String getKey()
        {
            return spec.getKey();
        }
    }

    public static LayerLuksVolumes createLayerLuksVolumes(
        int layerResourceId,
        int vlmNr,
        String encryptedPassword
    )
    {
        return new LayerLuksVolumes(
            new LayerLuksVolumesSpec(
                layerResourceId,
                vlmNr,
                encryptedPassword
            )
        );
    }

    public static class LayerLuksVolumesSpec implements LinstorSpec
    {
        @JsonIgnore private static final long serialVersionUID = -3793467758870247673L;
        @JsonIgnore private static final String PK_FORMAT = "%d:%d";

        @JsonIgnore private final String formattedPrimaryKey;

        @JsonProperty("layer_resource_id") public final int layerResourceId; // PK
        @JsonProperty("vlm_nr") public final int vlmNr; // PK
        @JsonProperty("encrypted_password") public final String encryptedPassword;

        @JsonCreator
        public LayerLuksVolumesSpec(
            @JsonProperty("layer_resource_id") int layerResourceIdRef,
            @JsonProperty("vlm_nr") int vlmNrRef,
            @JsonProperty("encrypted_password") String encryptedPasswordRef
        )
        {
            layerResourceId = layerResourceIdRef;
            vlmNr = vlmNrRef;
            encryptedPassword = encryptedPasswordRef;

            formattedPrimaryKey = base32Encode(String.format(
                LayerLuksVolumesSpec.PK_FORMAT,
                layerResourceId,
                vlmNr
            ));
        }

        public final <DATA> LayerLuksVolumesSpec create(
            Map<Column, ExceptionThrowingFunction<DATA, Object, AccessDeniedException>> setters,
            DATA data
        )
            throws AccessDeniedException
        {
            return new LayerLuksVolumesSpec(
                (int) setters.get(GeneratedDatabaseTables.LayerLuksVolumes.LAYER_RESOURCE_ID).accept(data),
                (int) setters.get(GeneratedDatabaseTables.LayerLuksVolumes.VLM_NR).accept(data),
                (String) setters.get(GeneratedDatabaseTables.LayerLuksVolumes.ENCRYPTED_PASSWORD).accept(data)
            );
        }

        @JsonIgnore
        @Override
        public Map<String, Object> asRawParameters()
        {
            Map<String, Object> ret = new TreeMap<>();
            ret.put("LAYER_RESOURCE_ID", layerResourceId);
            ret.put("VLM_NR", vlmNr);
            ret.put("ENCRYPTED_PASSWORD", encryptedPassword);
            return ret;
        }

        @JsonIgnore
        @Override
        public Object getByColumn(Column clm)
        {
            switch(clm.getName())
            {
                case "LAYER_RESOURCE_ID":
                    return layerResourceId;
                case "VLM_NR":
                    return vlmNr;
                case "ENCRYPTED_PASSWORD":
                    return encryptedPassword;
                default:
                    throw new ImplementationError("Unknown database column. Table: " + clm.getTable().getName() + ", Column: " + clm.getName());
            }
        }

        @JsonIgnore
        @Override
        public final String getKey()
        {
            return formattedPrimaryKey;
        }

        @Override
        @JsonIgnore
        public DatabaseTable getDatabaseTable()
        {
            return GeneratedDatabaseTables.LAYER_LUKS_VOLUMES;
        }
    }

    @Version(GenCrdCurrent.VERSION)
    @Group(GenCrdCurrent.GROUP)
    @Plural("layerluksvolumes")
    @Singular("layerluksvolumes")
    public static class LayerLuksVolumes extends CustomResource<LayerLuksVolumesSpec, Void> implements LinstorCrd<LayerLuksVolumesSpec>
    {
        private static final long serialVersionUID = 8342252875016913979L;

        public LayerLuksVolumes()
        {
            super();
        }

        public LayerLuksVolumes(LayerLuksVolumesSpec spec)
        {
            setMetadata(new ObjectMetaBuilder().withName(spec.getKey()).build());
            setSpec(spec);
        }

        @JsonIgnore
        public String getKey()
        {
            return spec.getKey();
        }
    }

    public static LayerOpenflexResourceDefinitions createLayerOpenflexResourceDefinitions(
        String resourceName,
        String snapshotName,
        String resourceNameSuffix,
        String nqn
    )
    {
        return new LayerOpenflexResourceDefinitions(
            new LayerOpenflexResourceDefinitionsSpec(
                resourceName,
                snapshotName,
                resourceNameSuffix,
                nqn
            )
        );
    }

    public static class LayerOpenflexResourceDefinitionsSpec implements LinstorSpec
    {
        @JsonIgnore private static final long serialVersionUID = -8745909652719308691L;
        @JsonIgnore private static final String PK_FORMAT = "%s:%s";

        @JsonIgnore private final String formattedPrimaryKey;

        @JsonProperty("resource_name") public final String resourceName; // PK
        @JsonProperty("snapshot_name") public final String snapshotName;
        @JsonProperty("resource_name_suffix") public final String resourceNameSuffix; // PK
        @JsonProperty("nqn") public final String nqn;

        @JsonCreator
        public LayerOpenflexResourceDefinitionsSpec(
            @JsonProperty("resource_name") String resourceNameRef,
            @JsonProperty("snapshot_name") String snapshotNameRef,
            @JsonProperty("resource_name_suffix") String resourceNameSuffixRef,
            @JsonProperty("nqn") String nqnRef
        )
        {
            resourceName = resourceNameRef;
            snapshotName = snapshotNameRef;
            resourceNameSuffix = resourceNameSuffixRef;
            nqn = nqnRef;

            formattedPrimaryKey = base32Encode(String.format(
                LayerOpenflexResourceDefinitionsSpec.PK_FORMAT,
                resourceName,
                resourceNameSuffix
            ));
        }

        public final <DATA> LayerOpenflexResourceDefinitionsSpec create(
            Map<Column, ExceptionThrowingFunction<DATA, Object, AccessDeniedException>> setters,
            DATA data
        )
            throws AccessDeniedException
        {
            return new LayerOpenflexResourceDefinitionsSpec(
                (String) setters.get(GeneratedDatabaseTables.LayerOpenflexResourceDefinitions.RESOURCE_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.LayerOpenflexResourceDefinitions.SNAPSHOT_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.LayerOpenflexResourceDefinitions.RESOURCE_NAME_SUFFIX).accept(data),
                (String) setters.get(GeneratedDatabaseTables.LayerOpenflexResourceDefinitions.NQN).accept(data)
            );
        }

        @JsonIgnore
        @Override
        public Map<String, Object> asRawParameters()
        {
            Map<String, Object> ret = new TreeMap<>();
            ret.put("RESOURCE_NAME", resourceName);
            ret.put("SNAPSHOT_NAME", snapshotName);
            ret.put("RESOURCE_NAME_SUFFIX", resourceNameSuffix);
            ret.put("NQN", nqn);
            return ret;
        }

        @JsonIgnore
        @Override
        public Object getByColumn(Column clm)
        {
            switch(clm.getName())
            {
                case "RESOURCE_NAME":
                    return resourceName;
                case "SNAPSHOT_NAME":
                    return snapshotName;
                case "RESOURCE_NAME_SUFFIX":
                    return resourceNameSuffix;
                case "NQN":
                    return nqn;
                default:
                    throw new ImplementationError("Unknown database column. Table: " + clm.getTable().getName() + ", Column: " + clm.getName());
            }
        }

        @JsonIgnore
        @Override
        public final String getKey()
        {
            return formattedPrimaryKey;
        }

        @Override
        @JsonIgnore
        public DatabaseTable getDatabaseTable()
        {
            return GeneratedDatabaseTables.LAYER_OPENFLEX_RESOURCE_DEFINITIONS;
        }
    }

    @Version(GenCrdCurrent.VERSION)
    @Group(GenCrdCurrent.GROUP)
    @Plural("layeropenflexresourcedefinitions")
    @Singular("layeropenflexresourcedefinitions")
    public static class LayerOpenflexResourceDefinitions extends CustomResource<LayerOpenflexResourceDefinitionsSpec, Void> implements LinstorCrd<LayerOpenflexResourceDefinitionsSpec>
    {
        private static final long serialVersionUID = -3388311861321546904L;

        public LayerOpenflexResourceDefinitions()
        {
            super();
        }

        public LayerOpenflexResourceDefinitions(LayerOpenflexResourceDefinitionsSpec spec)
        {
            setMetadata(new ObjectMetaBuilder().withName(spec.getKey()).build());
            setSpec(spec);
        }

        @JsonIgnore
        public String getKey()
        {
            return spec.getKey();
        }
    }

    public static LayerOpenflexVolumes createLayerOpenflexVolumes(
        int layerResourceId,
        int vlmNr,
        String nodeName,
        String poolName
    )
    {
        return new LayerOpenflexVolumes(
            new LayerOpenflexVolumesSpec(
                layerResourceId,
                vlmNr,
                nodeName,
                poolName
            )
        );
    }

    public static class LayerOpenflexVolumesSpec implements LinstorSpec
    {
        @JsonIgnore private static final long serialVersionUID = -1934037394962832552L;
        @JsonIgnore private static final String PK_FORMAT = "%d:%d";

        @JsonIgnore private final String formattedPrimaryKey;

        @JsonProperty("layer_resource_id") public final int layerResourceId; // PK
        @JsonProperty("vlm_nr") public final int vlmNr; // PK
        @JsonProperty("node_name") public final String nodeName;
        @JsonProperty("pool_name") public final String poolName;

        @JsonCreator
        public LayerOpenflexVolumesSpec(
            @JsonProperty("layer_resource_id") int layerResourceIdRef,
            @JsonProperty("vlm_nr") int vlmNrRef,
            @JsonProperty("node_name") String nodeNameRef,
            @JsonProperty("pool_name") String poolNameRef
        )
        {
            layerResourceId = layerResourceIdRef;
            vlmNr = vlmNrRef;
            nodeName = nodeNameRef;
            poolName = poolNameRef;

            formattedPrimaryKey = base32Encode(String.format(
                LayerOpenflexVolumesSpec.PK_FORMAT,
                layerResourceId,
                vlmNr
            ));
        }

        public final <DATA> LayerOpenflexVolumesSpec create(
            Map<Column, ExceptionThrowingFunction<DATA, Object, AccessDeniedException>> setters,
            DATA data
        )
            throws AccessDeniedException
        {
            return new LayerOpenflexVolumesSpec(
                (int) setters.get(GeneratedDatabaseTables.LayerOpenflexVolumes.LAYER_RESOURCE_ID).accept(data),
                (int) setters.get(GeneratedDatabaseTables.LayerOpenflexVolumes.VLM_NR).accept(data),
                (String) setters.get(GeneratedDatabaseTables.LayerOpenflexVolumes.NODE_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.LayerOpenflexVolumes.POOL_NAME).accept(data)
            );
        }

        @JsonIgnore
        @Override
        public Map<String, Object> asRawParameters()
        {
            Map<String, Object> ret = new TreeMap<>();
            ret.put("LAYER_RESOURCE_ID", layerResourceId);
            ret.put("VLM_NR", vlmNr);
            ret.put("NODE_NAME", nodeName);
            ret.put("POOL_NAME", poolName);
            return ret;
        }

        @JsonIgnore
        @Override
        public Object getByColumn(Column clm)
        {
            switch(clm.getName())
            {
                case "LAYER_RESOURCE_ID":
                    return layerResourceId;
                case "VLM_NR":
                    return vlmNr;
                case "NODE_NAME":
                    return nodeName;
                case "POOL_NAME":
                    return poolName;
                default:
                    throw new ImplementationError("Unknown database column. Table: " + clm.getTable().getName() + ", Column: " + clm.getName());
            }
        }

        @JsonIgnore
        @Override
        public final String getKey()
        {
            return formattedPrimaryKey;
        }

        @Override
        @JsonIgnore
        public DatabaseTable getDatabaseTable()
        {
            return GeneratedDatabaseTables.LAYER_OPENFLEX_VOLUMES;
        }
    }

    @Version(GenCrdCurrent.VERSION)
    @Group(GenCrdCurrent.GROUP)
    @Plural("layeropenflexvolumes")
    @Singular("layeropenflexvolumes")
    public static class LayerOpenflexVolumes extends CustomResource<LayerOpenflexVolumesSpec, Void> implements LinstorCrd<LayerOpenflexVolumesSpec>
    {
        private static final long serialVersionUID = -7385823270064838274L;

        public LayerOpenflexVolumes()
        {
            super();
        }

        public LayerOpenflexVolumes(LayerOpenflexVolumesSpec spec)
        {
            setMetadata(new ObjectMetaBuilder().withName(spec.getKey()).build());
            setSpec(spec);
        }

        @JsonIgnore
        public String getKey()
        {
            return spec.getKey();
        }
    }

    public static LayerResourceIds createLayerResourceIds(
        int layerResourceId,
        String nodeName,
        String resourceName,
        String snapshotName,
        String layerResourceKind,
        Integer layerResourceParentId,
        String layerResourceSuffix,
        boolean layerResourceSuspended
    )
    {
        return new LayerResourceIds(
            new LayerResourceIdsSpec(
                layerResourceId,
                nodeName,
                resourceName,
                snapshotName,
                layerResourceKind,
                layerResourceParentId,
                layerResourceSuffix,
                layerResourceSuspended
            )
        );
    }

    public static class LayerResourceIdsSpec implements LinstorSpec
    {
        @JsonIgnore private static final long serialVersionUID = -774380137302674205L;
        @JsonIgnore private static final String PK_FORMAT = "%d";

        @JsonIgnore private final String formattedPrimaryKey;

        @JsonProperty("layer_resource_id") public final int layerResourceId; // PK
        @JsonProperty("node_name") public final String nodeName;
        @JsonProperty("resource_name") public final String resourceName;
        @JsonProperty("snapshot_name") public final String snapshotName;
        @JsonProperty("layer_resource_kind") public final String layerResourceKind;
        @JsonProperty("layer_resource_parent_id") public final Integer layerResourceParentId;
        @JsonProperty("layer_resource_suffix") public final String layerResourceSuffix;
        @JsonProperty("layer_resource_suspended") public final boolean layerResourceSuspended;

        @JsonCreator
        public LayerResourceIdsSpec(
            @JsonProperty("layer_resource_id") int layerResourceIdRef,
            @JsonProperty("node_name") String nodeNameRef,
            @JsonProperty("resource_name") String resourceNameRef,
            @JsonProperty("snapshot_name") String snapshotNameRef,
            @JsonProperty("layer_resource_kind") String layerResourceKindRef,
            @JsonProperty("layer_resource_parent_id") Integer layerResourceParentIdRef,
            @JsonProperty("layer_resource_suffix") String layerResourceSuffixRef,
            @JsonProperty("layer_resource_suspended") boolean layerResourceSuspendedRef
        )
        {
            layerResourceId = layerResourceIdRef;
            nodeName = nodeNameRef;
            resourceName = resourceNameRef;
            snapshotName = snapshotNameRef;
            layerResourceKind = layerResourceKindRef;
            layerResourceParentId = layerResourceParentIdRef;
            layerResourceSuffix = layerResourceSuffixRef;
            layerResourceSuspended = layerResourceSuspendedRef;

            formattedPrimaryKey = base32Encode(String.format(
                LayerResourceIdsSpec.PK_FORMAT,
                layerResourceId
            ));
        }

        public final <DATA> LayerResourceIdsSpec create(
            Map<Column, ExceptionThrowingFunction<DATA, Object, AccessDeniedException>> setters,
            DATA data
        )
            throws AccessDeniedException
        {
            return new LayerResourceIdsSpec(
                (int) setters.get(GeneratedDatabaseTables.LayerResourceIds.LAYER_RESOURCE_ID).accept(data),
                (String) setters.get(GeneratedDatabaseTables.LayerResourceIds.NODE_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.LayerResourceIds.RESOURCE_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.LayerResourceIds.SNAPSHOT_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.LayerResourceIds.LAYER_RESOURCE_KIND).accept(data),
                (Integer) setters.get(GeneratedDatabaseTables.LayerResourceIds.LAYER_RESOURCE_PARENT_ID).accept(data),
                (String) setters.get(GeneratedDatabaseTables.LayerResourceIds.LAYER_RESOURCE_SUFFIX).accept(data),
                (boolean) setters.get(GeneratedDatabaseTables.LayerResourceIds.LAYER_RESOURCE_SUSPENDED).accept(data)
            );
        }

        @JsonIgnore
        @Override
        public Map<String, Object> asRawParameters()
        {
            Map<String, Object> ret = new TreeMap<>();
            ret.put("LAYER_RESOURCE_ID", layerResourceId);
            ret.put("NODE_NAME", nodeName);
            ret.put("RESOURCE_NAME", resourceName);
            ret.put("SNAPSHOT_NAME", snapshotName);
            ret.put("LAYER_RESOURCE_KIND", layerResourceKind);
            ret.put("LAYER_RESOURCE_PARENT_ID", layerResourceParentId);
            ret.put("LAYER_RESOURCE_SUFFIX", layerResourceSuffix);
            ret.put("LAYER_RESOURCE_SUSPENDED", layerResourceSuspended);
            return ret;
        }

        @JsonIgnore
        @Override
        public Object getByColumn(Column clm)
        {
            switch(clm.getName())
            {
                case "LAYER_RESOURCE_ID":
                    return layerResourceId;
                case "NODE_NAME":
                    return nodeName;
                case "RESOURCE_NAME":
                    return resourceName;
                case "SNAPSHOT_NAME":
                    return snapshotName;
                case "LAYER_RESOURCE_KIND":
                    return layerResourceKind;
                case "LAYER_RESOURCE_PARENT_ID":
                    return layerResourceParentId;
                case "LAYER_RESOURCE_SUFFIX":
                    return layerResourceSuffix;
                case "LAYER_RESOURCE_SUSPENDED":
                    return layerResourceSuspended;
                default:
                    throw new ImplementationError("Unknown database column. Table: " + clm.getTable().getName() + ", Column: " + clm.getName());
            }
        }

        @JsonIgnore
        @Override
        public final String getKey()
        {
            return formattedPrimaryKey;
        }

        @Override
        @JsonIgnore
        public DatabaseTable getDatabaseTable()
        {
            return GeneratedDatabaseTables.LAYER_RESOURCE_IDS;
        }
    }

    @Version(GenCrdCurrent.VERSION)
    @Group(GenCrdCurrent.GROUP)
    @Plural("layerresourceids")
    @Singular("layerresourceids")
    public static class LayerResourceIds extends CustomResource<LayerResourceIdsSpec, Void> implements LinstorCrd<LayerResourceIdsSpec>
    {
        private static final long serialVersionUID = -1847762492423502478L;

        public LayerResourceIds()
        {
            super();
        }

        public LayerResourceIds(LayerResourceIdsSpec spec)
        {
            setMetadata(new ObjectMetaBuilder().withName(spec.getKey()).build());
            setSpec(spec);
        }

        @JsonIgnore
        public String getKey()
        {
            return spec.getKey();
        }
    }

    public static LayerStorageVolumes createLayerStorageVolumes(
        int layerResourceId,
        int vlmNr,
        String providerKind,
        String nodeName,
        String storPoolName
    )
    {
        return new LayerStorageVolumes(
            new LayerStorageVolumesSpec(
                layerResourceId,
                vlmNr,
                providerKind,
                nodeName,
                storPoolName
            )
        );
    }

    public static class LayerStorageVolumesSpec implements LinstorSpec
    {
        @JsonIgnore private static final long serialVersionUID = 448013887603074080L;
        @JsonIgnore private static final String PK_FORMAT = "%d:%d";

        @JsonIgnore private final String formattedPrimaryKey;

        @JsonProperty("layer_resource_id") public final int layerResourceId; // PK
        @JsonProperty("vlm_nr") public final int vlmNr; // PK
        @JsonProperty("provider_kind") public final String providerKind;
        @JsonProperty("node_name") public final String nodeName;
        @JsonProperty("stor_pool_name") public final String storPoolName;

        @JsonCreator
        public LayerStorageVolumesSpec(
            @JsonProperty("layer_resource_id") int layerResourceIdRef,
            @JsonProperty("vlm_nr") int vlmNrRef,
            @JsonProperty("provider_kind") String providerKindRef,
            @JsonProperty("node_name") String nodeNameRef,
            @JsonProperty("stor_pool_name") String storPoolNameRef
        )
        {
            layerResourceId = layerResourceIdRef;
            vlmNr = vlmNrRef;
            providerKind = providerKindRef;
            nodeName = nodeNameRef;
            storPoolName = storPoolNameRef;

            formattedPrimaryKey = base32Encode(String.format(
                LayerStorageVolumesSpec.PK_FORMAT,
                layerResourceId,
                vlmNr
            ));
        }

        public final <DATA> LayerStorageVolumesSpec create(
            Map<Column, ExceptionThrowingFunction<DATA, Object, AccessDeniedException>> setters,
            DATA data
        )
            throws AccessDeniedException
        {
            return new LayerStorageVolumesSpec(
                (int) setters.get(GeneratedDatabaseTables.LayerStorageVolumes.LAYER_RESOURCE_ID).accept(data),
                (int) setters.get(GeneratedDatabaseTables.LayerStorageVolumes.VLM_NR).accept(data),
                (String) setters.get(GeneratedDatabaseTables.LayerStorageVolumes.PROVIDER_KIND).accept(data),
                (String) setters.get(GeneratedDatabaseTables.LayerStorageVolumes.NODE_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.LayerStorageVolumes.STOR_POOL_NAME).accept(data)
            );
        }

        @JsonIgnore
        @Override
        public Map<String, Object> asRawParameters()
        {
            Map<String, Object> ret = new TreeMap<>();
            ret.put("LAYER_RESOURCE_ID", layerResourceId);
            ret.put("VLM_NR", vlmNr);
            ret.put("PROVIDER_KIND", providerKind);
            ret.put("NODE_NAME", nodeName);
            ret.put("STOR_POOL_NAME", storPoolName);
            return ret;
        }

        @JsonIgnore
        @Override
        public Object getByColumn(Column clm)
        {
            switch(clm.getName())
            {
                case "LAYER_RESOURCE_ID":
                    return layerResourceId;
                case "VLM_NR":
                    return vlmNr;
                case "PROVIDER_KIND":
                    return providerKind;
                case "NODE_NAME":
                    return nodeName;
                case "STOR_POOL_NAME":
                    return storPoolName;
                default:
                    throw new ImplementationError("Unknown database column. Table: " + clm.getTable().getName() + ", Column: " + clm.getName());
            }
        }

        @JsonIgnore
        @Override
        public final String getKey()
        {
            return formattedPrimaryKey;
        }

        @Override
        @JsonIgnore
        public DatabaseTable getDatabaseTable()
        {
            return GeneratedDatabaseTables.LAYER_STORAGE_VOLUMES;
        }
    }

    @Version(GenCrdCurrent.VERSION)
    @Group(GenCrdCurrent.GROUP)
    @Plural("layerstoragevolumes")
    @Singular("layerstoragevolumes")
    public static class LayerStorageVolumes extends CustomResource<LayerStorageVolumesSpec, Void> implements LinstorCrd<LayerStorageVolumesSpec>
    {
        private static final long serialVersionUID = -1836533915798286058L;

        public LayerStorageVolumes()
        {
            super();
        }

        public LayerStorageVolumes(LayerStorageVolumesSpec spec)
        {
            setMetadata(new ObjectMetaBuilder().withName(spec.getKey()).build());
            setSpec(spec);
        }

        @JsonIgnore
        public String getKey()
        {
            return spec.getKey();
        }
    }

    public static LayerWritecacheVolumes createLayerWritecacheVolumes(
        int layerResourceId,
        int vlmNr,
        String nodeName,
        String poolName
    )
    {
        return new LayerWritecacheVolumes(
            new LayerWritecacheVolumesSpec(
                layerResourceId,
                vlmNr,
                nodeName,
                poolName
            )
        );
    }

    public static class LayerWritecacheVolumesSpec implements LinstorSpec
    {
        @JsonIgnore private static final long serialVersionUID = -4836912705479287053L;
        @JsonIgnore private static final String PK_FORMAT = "%d:%d";

        @JsonIgnore private final String formattedPrimaryKey;

        @JsonProperty("layer_resource_id") public final int layerResourceId; // PK
        @JsonProperty("vlm_nr") public final int vlmNr; // PK
        @JsonProperty("node_name") public final String nodeName;
        @JsonProperty("pool_name") public final String poolName;

        @JsonCreator
        public LayerWritecacheVolumesSpec(
            @JsonProperty("layer_resource_id") int layerResourceIdRef,
            @JsonProperty("vlm_nr") int vlmNrRef,
            @JsonProperty("node_name") String nodeNameRef,
            @JsonProperty("pool_name") String poolNameRef
        )
        {
            layerResourceId = layerResourceIdRef;
            vlmNr = vlmNrRef;
            nodeName = nodeNameRef;
            poolName = poolNameRef;

            formattedPrimaryKey = base32Encode(String.format(
                LayerWritecacheVolumesSpec.PK_FORMAT,
                layerResourceId,
                vlmNr
            ));
        }

        public final <DATA> LayerWritecacheVolumesSpec create(
            Map<Column, ExceptionThrowingFunction<DATA, Object, AccessDeniedException>> setters,
            DATA data
        )
            throws AccessDeniedException
        {
            return new LayerWritecacheVolumesSpec(
                (int) setters.get(GeneratedDatabaseTables.LayerWritecacheVolumes.LAYER_RESOURCE_ID).accept(data),
                (int) setters.get(GeneratedDatabaseTables.LayerWritecacheVolumes.VLM_NR).accept(data),
                (String) setters.get(GeneratedDatabaseTables.LayerWritecacheVolumes.NODE_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.LayerWritecacheVolumes.POOL_NAME).accept(data)
            );
        }

        @JsonIgnore
        @Override
        public Map<String, Object> asRawParameters()
        {
            Map<String, Object> ret = new TreeMap<>();
            ret.put("LAYER_RESOURCE_ID", layerResourceId);
            ret.put("VLM_NR", vlmNr);
            ret.put("NODE_NAME", nodeName);
            ret.put("POOL_NAME", poolName);
            return ret;
        }

        @JsonIgnore
        @Override
        public Object getByColumn(Column clm)
        {
            switch(clm.getName())
            {
                case "LAYER_RESOURCE_ID":
                    return layerResourceId;
                case "VLM_NR":
                    return vlmNr;
                case "NODE_NAME":
                    return nodeName;
                case "POOL_NAME":
                    return poolName;
                default:
                    throw new ImplementationError("Unknown database column. Table: " + clm.getTable().getName() + ", Column: " + clm.getName());
            }
        }

        @JsonIgnore
        @Override
        public final String getKey()
        {
            return formattedPrimaryKey;
        }

        @Override
        @JsonIgnore
        public DatabaseTable getDatabaseTable()
        {
            return GeneratedDatabaseTables.LAYER_WRITECACHE_VOLUMES;
        }
    }

    @Version(GenCrdCurrent.VERSION)
    @Group(GenCrdCurrent.GROUP)
    @Plural("layerwritecachevolumes")
    @Singular("layerwritecachevolumes")
    public static class LayerWritecacheVolumes extends CustomResource<LayerWritecacheVolumesSpec, Void> implements LinstorCrd<LayerWritecacheVolumesSpec>
    {
        private static final long serialVersionUID = -8348904519761700195L;

        public LayerWritecacheVolumes()
        {
            super();
        }

        public LayerWritecacheVolumes(LayerWritecacheVolumesSpec spec)
        {
            setMetadata(new ObjectMetaBuilder().withName(spec.getKey()).build());
            setSpec(spec);
        }

        @JsonIgnore
        public String getKey()
        {
            return spec.getKey();
        }
    }

    public static LinstorRemotes createLinstorRemotes(
        String uuid,
        String name,
        String dspName,
        long flags,
        String url,
        byte[] encryptedPassphrase,
        String clusterId
    )
    {
        return new LinstorRemotes(
            new LinstorRemotesSpec(
                uuid,
                name,
                dspName,
                flags,
                url,
                encryptedPassphrase,
                clusterId
            )
        );
    }

    public static class LinstorRemotesSpec implements LinstorSpec
    {
        @JsonIgnore private static final long serialVersionUID = -7758231826798464706L;
        @JsonIgnore private static final String PK_FORMAT = "%s";

        @JsonIgnore private final String formattedPrimaryKey;

        @JsonProperty("uuid") public final String uuid;
        @JsonProperty("name") public final String name; // PK
        @JsonProperty("dsp_name") public final String dspName;
        @JsonProperty("flags") public final long flags;
        @JsonProperty("url") public final String url;
        @JsonProperty("encrypted_passphrase") public final byte[] encryptedPassphrase;
        @JsonProperty("cluster_id") public final String clusterId;

        @JsonCreator
        public LinstorRemotesSpec(
            @JsonProperty("uuid") String uuidRef,
            @JsonProperty("name") String nameRef,
            @JsonProperty("dsp_name") String dspNameRef,
            @JsonProperty("flags") long flagsRef,
            @JsonProperty("url") String urlRef,
            @JsonProperty("encrypted_passphrase") byte[] encryptedPassphraseRef,
            @JsonProperty("cluster_id") String clusterIdRef
        )
        {
            uuid = uuidRef;
            name = nameRef;
            dspName = dspNameRef;
            flags = flagsRef;
            url = urlRef;
            encryptedPassphrase = encryptedPassphraseRef;
            clusterId = clusterIdRef;

            formattedPrimaryKey = base32Encode(String.format(
                LinstorRemotesSpec.PK_FORMAT,
                name
            ));
        }

        public final <DATA> LinstorRemotesSpec create(
            Map<Column, ExceptionThrowingFunction<DATA, Object, AccessDeniedException>> setters,
            DATA data
        )
            throws AccessDeniedException
        {
            return new LinstorRemotesSpec(
                (String) setters.get(GeneratedDatabaseTables.LinstorRemotes.UUID).accept(data),
                (String) setters.get(GeneratedDatabaseTables.LinstorRemotes.NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.LinstorRemotes.DSP_NAME).accept(data),
                (long) setters.get(GeneratedDatabaseTables.LinstorRemotes.FLAGS).accept(data),
                (String) setters.get(GeneratedDatabaseTables.LinstorRemotes.URL).accept(data),
                (byte[]) setters.get(GeneratedDatabaseTables.LinstorRemotes.ENCRYPTED_PASSPHRASE).accept(data),
                (String) setters.get(GeneratedDatabaseTables.LinstorRemotes.CLUSTER_ID).accept(data)
            );
        }

        @JsonIgnore
        @Override
        public Map<String, Object> asRawParameters()
        {
            Map<String, Object> ret = new TreeMap<>();
            ret.put("UUID", uuid);
            ret.put("NAME", name);
            ret.put("DSP_NAME", dspName);
            ret.put("FLAGS", flags);
            ret.put("URL", url);
            ret.put("ENCRYPTED_PASSPHRASE", encryptedPassphrase);
            ret.put("CLUSTER_ID", clusterId);
            return ret;
        }

        @JsonIgnore
        @Override
        public Object getByColumn(Column clm)
        {
            switch(clm.getName())
            {
                case "UUID":
                    return uuid;
                case "NAME":
                    return name;
                case "DSP_NAME":
                    return dspName;
                case "FLAGS":
                    return flags;
                case "URL":
                    return url;
                case "ENCRYPTED_PASSPHRASE":
                    return encryptedPassphrase;
                case "CLUSTER_ID":
                    return clusterId;
                default:
                    throw new ImplementationError("Unknown database column. Table: " + clm.getTable().getName() + ", Column: " + clm.getName());
            }
        }

        @JsonIgnore
        @Override
        public final String getKey()
        {
            return formattedPrimaryKey;
        }

        @Override
        @JsonIgnore
        public DatabaseTable getDatabaseTable()
        {
            return GeneratedDatabaseTables.LINSTOR_REMOTES;
        }
    }

    @Version(GenCrdCurrent.VERSION)
    @Group(GenCrdCurrent.GROUP)
    @Plural("linstorremotes")
    @Singular("linstorremotes")
    public static class LinstorRemotes extends CustomResource<LinstorRemotesSpec, Void> implements LinstorCrd<LinstorRemotesSpec>
    {
        private static final long serialVersionUID = -6721542275951082914L;

        public LinstorRemotes()
        {
            super();
        }

        public LinstorRemotes(LinstorRemotesSpec spec)
        {
            setMetadata(new ObjectMetaBuilder().withName(spec.getKey()).build());
            setSpec(spec);
        }

        @JsonIgnore
        public String getKey()
        {
            return spec.getKey();
        }
    }

    public static Nodes createNodes(
        String uuid,
        String nodeName,
        String nodeDspName,
        long nodeFlags,
        int nodeType
    )
    {
        return new Nodes(
            new NodesSpec(
                uuid,
                nodeName,
                nodeDspName,
                nodeFlags,
                nodeType
            )
        );
    }

    public static class NodesSpec implements LinstorSpec
    {
        @JsonIgnore private static final long serialVersionUID = 2849158441770844734L;
        @JsonIgnore private static final String PK_FORMAT = "%s";

        @JsonIgnore private final String formattedPrimaryKey;

        @JsonProperty("uuid") public final String uuid;
        @JsonProperty("node_name") public final String nodeName; // PK
        @JsonProperty("node_dsp_name") public final String nodeDspName;
        @JsonProperty("node_flags") public final long nodeFlags;
        @JsonProperty("node_type") public final int nodeType;

        @JsonCreator
        public NodesSpec(
            @JsonProperty("uuid") String uuidRef,
            @JsonProperty("node_name") String nodeNameRef,
            @JsonProperty("node_dsp_name") String nodeDspNameRef,
            @JsonProperty("node_flags") long nodeFlagsRef,
            @JsonProperty("node_type") int nodeTypeRef
        )
        {
            uuid = uuidRef;
            nodeName = nodeNameRef;
            nodeDspName = nodeDspNameRef;
            nodeFlags = nodeFlagsRef;
            nodeType = nodeTypeRef;

            formattedPrimaryKey = base32Encode(String.format(
                NodesSpec.PK_FORMAT,
                nodeName
            ));
        }

        public final <DATA> NodesSpec create(
            Map<Column, ExceptionThrowingFunction<DATA, Object, AccessDeniedException>> setters,
            DATA data
        )
            throws AccessDeniedException
        {
            return new NodesSpec(
                (String) setters.get(GeneratedDatabaseTables.Nodes.UUID).accept(data),
                (String) setters.get(GeneratedDatabaseTables.Nodes.NODE_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.Nodes.NODE_DSP_NAME).accept(data),
                (long) setters.get(GeneratedDatabaseTables.Nodes.NODE_FLAGS).accept(data),
                (int) setters.get(GeneratedDatabaseTables.Nodes.NODE_TYPE).accept(data)
            );
        }

        @JsonIgnore
        @Override
        public Map<String, Object> asRawParameters()
        {
            Map<String, Object> ret = new TreeMap<>();
            ret.put("UUID", uuid);
            ret.put("NODE_NAME", nodeName);
            ret.put("NODE_DSP_NAME", nodeDspName);
            ret.put("NODE_FLAGS", nodeFlags);
            ret.put("NODE_TYPE", nodeType);
            return ret;
        }

        @JsonIgnore
        @Override
        public Object getByColumn(Column clm)
        {
            switch(clm.getName())
            {
                case "UUID":
                    return uuid;
                case "NODE_NAME":
                    return nodeName;
                case "NODE_DSP_NAME":
                    return nodeDspName;
                case "NODE_FLAGS":
                    return nodeFlags;
                case "NODE_TYPE":
                    return nodeType;
                default:
                    throw new ImplementationError("Unknown database column. Table: " + clm.getTable().getName() + ", Column: " + clm.getName());
            }
        }

        @JsonIgnore
        @Override
        public final String getKey()
        {
            return formattedPrimaryKey;
        }

        @Override
        @JsonIgnore
        public DatabaseTable getDatabaseTable()
        {
            return GeneratedDatabaseTables.NODES;
        }
    }

    @Version(GenCrdCurrent.VERSION)
    @Group(GenCrdCurrent.GROUP)
    @Plural("nodes")
    @Singular("nodes")
    public static class Nodes extends CustomResource<NodesSpec, Void> implements LinstorCrd<NodesSpec>
    {
        private static final long serialVersionUID = -5123146334305939240L;

        public Nodes()
        {
            super();
        }

        public Nodes(NodesSpec spec)
        {
            setMetadata(new ObjectMetaBuilder().withName(spec.getKey()).build());
            setSpec(spec);
        }

        @JsonIgnore
        public String getKey()
        {
            return spec.getKey();
        }
    }

    public static NodeConnections createNodeConnections(
        String uuid,
        String nodeNameSrc,
        String nodeNameDst
    )
    {
        return new NodeConnections(
            new NodeConnectionsSpec(
                uuid,
                nodeNameSrc,
                nodeNameDst
            )
        );
    }

    public static class NodeConnectionsSpec implements LinstorSpec
    {
        @JsonIgnore private static final long serialVersionUID = -6620765402558872524L;
        @JsonIgnore private static final String PK_FORMAT = "%s:%s";

        @JsonIgnore private final String formattedPrimaryKey;

        @JsonProperty("uuid") public final String uuid;
        @JsonProperty("node_name_src") public final String nodeNameSrc; // PK
        @JsonProperty("node_name_dst") public final String nodeNameDst; // PK

        @JsonCreator
        public NodeConnectionsSpec(
            @JsonProperty("uuid") String uuidRef,
            @JsonProperty("node_name_src") String nodeNameSrcRef,
            @JsonProperty("node_name_dst") String nodeNameDstRef
        )
        {
            uuid = uuidRef;
            nodeNameSrc = nodeNameSrcRef;
            nodeNameDst = nodeNameDstRef;

            formattedPrimaryKey = base32Encode(String.format(
                NodeConnectionsSpec.PK_FORMAT,
                nodeNameSrc,
                nodeNameDst
            ));
        }

        public final <DATA> NodeConnectionsSpec create(
            Map<Column, ExceptionThrowingFunction<DATA, Object, AccessDeniedException>> setters,
            DATA data
        )
            throws AccessDeniedException
        {
            return new NodeConnectionsSpec(
                (String) setters.get(GeneratedDatabaseTables.NodeConnections.UUID).accept(data),
                (String) setters.get(GeneratedDatabaseTables.NodeConnections.NODE_NAME_SRC).accept(data),
                (String) setters.get(GeneratedDatabaseTables.NodeConnections.NODE_NAME_DST).accept(data)
            );
        }

        @JsonIgnore
        @Override
        public Map<String, Object> asRawParameters()
        {
            Map<String, Object> ret = new TreeMap<>();
            ret.put("UUID", uuid);
            ret.put("NODE_NAME_SRC", nodeNameSrc);
            ret.put("NODE_NAME_DST", nodeNameDst);
            return ret;
        }

        @JsonIgnore
        @Override
        public Object getByColumn(Column clm)
        {
            switch(clm.getName())
            {
                case "UUID":
                    return uuid;
                case "NODE_NAME_SRC":
                    return nodeNameSrc;
                case "NODE_NAME_DST":
                    return nodeNameDst;
                default:
                    throw new ImplementationError("Unknown database column. Table: " + clm.getTable().getName() + ", Column: " + clm.getName());
            }
        }

        @JsonIgnore
        @Override
        public final String getKey()
        {
            return formattedPrimaryKey;
        }

        @Override
        @JsonIgnore
        public DatabaseTable getDatabaseTable()
        {
            return GeneratedDatabaseTables.NODE_CONNECTIONS;
        }
    }

    @Version(GenCrdCurrent.VERSION)
    @Group(GenCrdCurrent.GROUP)
    @Plural("nodeconnections")
    @Singular("nodeconnections")
    public static class NodeConnections extends CustomResource<NodeConnectionsSpec, Void> implements LinstorCrd<NodeConnectionsSpec>
    {
        private static final long serialVersionUID = 1052173651342502134L;

        public NodeConnections()
        {
            super();
        }

        public NodeConnections(NodeConnectionsSpec spec)
        {
            setMetadata(new ObjectMetaBuilder().withName(spec.getKey()).build());
            setSpec(spec);
        }

        @JsonIgnore
        public String getKey()
        {
            return spec.getKey();
        }
    }

    public static NodeNetInterfaces createNodeNetInterfaces(
        String uuid,
        String nodeName,
        String nodeNetName,
        String nodeNetDspName,
        String inetAddress,
        Short stltConnPort,
        String stltConnEncrType
    )
    {
        return new NodeNetInterfaces(
            new NodeNetInterfacesSpec(
                uuid,
                nodeName,
                nodeNetName,
                nodeNetDspName,
                inetAddress,
                stltConnPort,
                stltConnEncrType
            )
        );
    }

    public static class NodeNetInterfacesSpec implements LinstorSpec
    {
        @JsonIgnore private static final long serialVersionUID = 4609326287102851655L;
        @JsonIgnore private static final String PK_FORMAT = "%s:%s";

        @JsonIgnore private final String formattedPrimaryKey;

        @JsonProperty("uuid") public final String uuid;
        @JsonProperty("node_name") public final String nodeName; // PK
        @JsonProperty("node_net_name") public final String nodeNetName; // PK
        @JsonProperty("node_net_dsp_name") public final String nodeNetDspName;
        @JsonProperty("inet_address") public final String inetAddress;
        @JsonProperty("stlt_conn_port") public final Short stltConnPort;
        @JsonProperty("stlt_conn_encr_type") public final String stltConnEncrType;

        @JsonCreator
        public NodeNetInterfacesSpec(
            @JsonProperty("uuid") String uuidRef,
            @JsonProperty("node_name") String nodeNameRef,
            @JsonProperty("node_net_name") String nodeNetNameRef,
            @JsonProperty("node_net_dsp_name") String nodeNetDspNameRef,
            @JsonProperty("inet_address") String inetAddressRef,
            @JsonProperty("stlt_conn_port") Short stltConnPortRef,
            @JsonProperty("stlt_conn_encr_type") String stltConnEncrTypeRef
        )
        {
            uuid = uuidRef;
            nodeName = nodeNameRef;
            nodeNetName = nodeNetNameRef;
            nodeNetDspName = nodeNetDspNameRef;
            inetAddress = inetAddressRef;
            stltConnPort = stltConnPortRef;
            stltConnEncrType = stltConnEncrTypeRef;

            formattedPrimaryKey = base32Encode(String.format(
                NodeNetInterfacesSpec.PK_FORMAT,
                nodeName,
                nodeNetName
            ));
        }

        public final <DATA> NodeNetInterfacesSpec create(
            Map<Column, ExceptionThrowingFunction<DATA, Object, AccessDeniedException>> setters,
            DATA data
        )
            throws AccessDeniedException
        {
            return new NodeNetInterfacesSpec(
                (String) setters.get(GeneratedDatabaseTables.NodeNetInterfaces.UUID).accept(data),
                (String) setters.get(GeneratedDatabaseTables.NodeNetInterfaces.NODE_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.NodeNetInterfaces.NODE_NET_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.NodeNetInterfaces.NODE_NET_DSP_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.NodeNetInterfaces.INET_ADDRESS).accept(data),
                (Short) setters.get(GeneratedDatabaseTables.NodeNetInterfaces.STLT_CONN_PORT).accept(data),
                (String) setters.get(GeneratedDatabaseTables.NodeNetInterfaces.STLT_CONN_ENCR_TYPE).accept(data)
            );
        }

        @JsonIgnore
        @Override
        public Map<String, Object> asRawParameters()
        {
            Map<String, Object> ret = new TreeMap<>();
            ret.put("UUID", uuid);
            ret.put("NODE_NAME", nodeName);
            ret.put("NODE_NET_NAME", nodeNetName);
            ret.put("NODE_NET_DSP_NAME", nodeNetDspName);
            ret.put("INET_ADDRESS", inetAddress);
            ret.put("STLT_CONN_PORT", stltConnPort);
            ret.put("STLT_CONN_ENCR_TYPE", stltConnEncrType);
            return ret;
        }

        @JsonIgnore
        @Override
        public Object getByColumn(Column clm)
        {
            switch(clm.getName())
            {
                case "UUID":
                    return uuid;
                case "NODE_NAME":
                    return nodeName;
                case "NODE_NET_NAME":
                    return nodeNetName;
                case "NODE_NET_DSP_NAME":
                    return nodeNetDspName;
                case "INET_ADDRESS":
                    return inetAddress;
                case "STLT_CONN_PORT":
                    return stltConnPort;
                case "STLT_CONN_ENCR_TYPE":
                    return stltConnEncrType;
                default:
                    throw new ImplementationError("Unknown database column. Table: " + clm.getTable().getName() + ", Column: " + clm.getName());
            }
        }

        @JsonIgnore
        @Override
        public final String getKey()
        {
            return formattedPrimaryKey;
        }

        @Override
        @JsonIgnore
        public DatabaseTable getDatabaseTable()
        {
            return GeneratedDatabaseTables.NODE_NET_INTERFACES;
        }
    }

    @Version(GenCrdCurrent.VERSION)
    @Group(GenCrdCurrent.GROUP)
    @Plural("nodenetinterfaces")
    @Singular("nodenetinterfaces")
    public static class NodeNetInterfaces extends CustomResource<NodeNetInterfacesSpec, Void> implements LinstorCrd<NodeNetInterfacesSpec>
    {
        private static final long serialVersionUID = -141177581739042157L;

        public NodeNetInterfaces()
        {
            super();
        }

        public NodeNetInterfaces(NodeNetInterfacesSpec spec)
        {
            setMetadata(new ObjectMetaBuilder().withName(spec.getKey()).build());
            setSpec(spec);
        }

        @JsonIgnore
        public String getKey()
        {
            return spec.getKey();
        }
    }

    public static NodeStorPool createNodeStorPool(
        String uuid,
        String nodeName,
        String poolName,
        String driverName,
        String freeSpaceMgrName,
        String freeSpaceMgrDspName,
        boolean externalLocking
    )
    {
        return new NodeStorPool(
            new NodeStorPoolSpec(
                uuid,
                nodeName,
                poolName,
                driverName,
                freeSpaceMgrName,
                freeSpaceMgrDspName,
                externalLocking
            )
        );
    }

    public static class NodeStorPoolSpec implements LinstorSpec
    {
        @JsonIgnore private static final long serialVersionUID = 6072345702714964136L;
        @JsonIgnore private static final String PK_FORMAT = "%s:%s";

        @JsonIgnore private final String formattedPrimaryKey;

        @JsonProperty("uuid") public final String uuid;
        @JsonProperty("node_name") public final String nodeName; // PK
        @JsonProperty("pool_name") public final String poolName; // PK
        @JsonProperty("driver_name") public final String driverName;
        @JsonProperty("free_space_mgr_name") public final String freeSpaceMgrName;
        @JsonProperty("free_space_mgr_dsp_name") public final String freeSpaceMgrDspName;
        @JsonProperty("external_locking") public final boolean externalLocking;

        @JsonCreator
        public NodeStorPoolSpec(
            @JsonProperty("uuid") String uuidRef,
            @JsonProperty("node_name") String nodeNameRef,
            @JsonProperty("pool_name") String poolNameRef,
            @JsonProperty("driver_name") String driverNameRef,
            @JsonProperty("free_space_mgr_name") String freeSpaceMgrNameRef,
            @JsonProperty("free_space_mgr_dsp_name") String freeSpaceMgrDspNameRef,
            @JsonProperty("external_locking") boolean externalLockingRef
        )
        {
            uuid = uuidRef;
            nodeName = nodeNameRef;
            poolName = poolNameRef;
            driverName = driverNameRef;
            freeSpaceMgrName = freeSpaceMgrNameRef;
            freeSpaceMgrDspName = freeSpaceMgrDspNameRef;
            externalLocking = externalLockingRef;

            formattedPrimaryKey = base32Encode(String.format(
                NodeStorPoolSpec.PK_FORMAT,
                nodeName,
                poolName
            ));
        }

        public final <DATA> NodeStorPoolSpec create(
            Map<Column, ExceptionThrowingFunction<DATA, Object, AccessDeniedException>> setters,
            DATA data
        )
            throws AccessDeniedException
        {
            return new NodeStorPoolSpec(
                (String) setters.get(GeneratedDatabaseTables.NodeStorPool.UUID).accept(data),
                (String) setters.get(GeneratedDatabaseTables.NodeStorPool.NODE_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.NodeStorPool.POOL_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.NodeStorPool.DRIVER_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.NodeStorPool.FREE_SPACE_MGR_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.NodeStorPool.FREE_SPACE_MGR_DSP_NAME).accept(data),
                (boolean) setters.get(GeneratedDatabaseTables.NodeStorPool.EXTERNAL_LOCKING).accept(data)
            );
        }

        @JsonIgnore
        @Override
        public Map<String, Object> asRawParameters()
        {
            Map<String, Object> ret = new TreeMap<>();
            ret.put("UUID", uuid);
            ret.put("NODE_NAME", nodeName);
            ret.put("POOL_NAME", poolName);
            ret.put("DRIVER_NAME", driverName);
            ret.put("FREE_SPACE_MGR_NAME", freeSpaceMgrName);
            ret.put("FREE_SPACE_MGR_DSP_NAME", freeSpaceMgrDspName);
            ret.put("EXTERNAL_LOCKING", externalLocking);
            return ret;
        }

        @JsonIgnore
        @Override
        public Object getByColumn(Column clm)
        {
            switch(clm.getName())
            {
                case "UUID":
                    return uuid;
                case "NODE_NAME":
                    return nodeName;
                case "POOL_NAME":
                    return poolName;
                case "DRIVER_NAME":
                    return driverName;
                case "FREE_SPACE_MGR_NAME":
                    return freeSpaceMgrName;
                case "FREE_SPACE_MGR_DSP_NAME":
                    return freeSpaceMgrDspName;
                case "EXTERNAL_LOCKING":
                    return externalLocking;
                default:
                    throw new ImplementationError("Unknown database column. Table: " + clm.getTable().getName() + ", Column: " + clm.getName());
            }
        }

        @JsonIgnore
        @Override
        public final String getKey()
        {
            return formattedPrimaryKey;
        }

        @Override
        @JsonIgnore
        public DatabaseTable getDatabaseTable()
        {
            return GeneratedDatabaseTables.NODE_STOR_POOL;
        }
    }

    @Version(GenCrdCurrent.VERSION)
    @Group(GenCrdCurrent.GROUP)
    @Plural("nodestorpool")
    @Singular("nodestorpool")
    public static class NodeStorPool extends CustomResource<NodeStorPoolSpec, Void> implements LinstorCrd<NodeStorPoolSpec>
    {
        private static final long serialVersionUID = -8543903584061712065L;

        public NodeStorPool()
        {
            super();
        }

        public NodeStorPool(NodeStorPoolSpec spec)
        {
            setMetadata(new ObjectMetaBuilder().withName(spec.getKey()).build());
            setSpec(spec);
        }

        @JsonIgnore
        public String getKey()
        {
            return spec.getKey();
        }
    }

    public static PropsContainers createPropsContainers(
        String propsInstance,
        String propKey,
        String propValue
    )
    {
        return new PropsContainers(
            new PropsContainersSpec(
                propsInstance,
                propKey,
                propValue
            )
        );
    }

    public static class PropsContainersSpec implements LinstorSpec
    {
        @JsonIgnore private static final long serialVersionUID = 2967503225377768183L;
        @JsonIgnore private static final String PK_FORMAT = "%s:%s";

        @JsonIgnore private final String formattedPrimaryKey;

        @JsonProperty("props_instance") public final String propsInstance; // PK
        @JsonProperty("prop_key") public final String propKey; // PK
        @JsonProperty("prop_value") public final String propValue;

        @JsonCreator
        public PropsContainersSpec(
            @JsonProperty("props_instance") String propsInstanceRef,
            @JsonProperty("prop_key") String propKeyRef,
            @JsonProperty("prop_value") String propValueRef
        )
        {
            propsInstance = propsInstanceRef;
            propKey = propKeyRef;
            propValue = propValueRef;

            formattedPrimaryKey = base32Encode(String.format(
                PropsContainersSpec.PK_FORMAT,
                propsInstance,
                propKey
            ));
        }

        public final <DATA> PropsContainersSpec create(
            Map<Column, ExceptionThrowingFunction<DATA, Object, AccessDeniedException>> setters,
            DATA data
        )
            throws AccessDeniedException
        {
            return new PropsContainersSpec(
                (String) setters.get(GeneratedDatabaseTables.PropsContainers.PROPS_INSTANCE).accept(data),
                (String) setters.get(GeneratedDatabaseTables.PropsContainers.PROP_KEY).accept(data),
                (String) setters.get(GeneratedDatabaseTables.PropsContainers.PROP_VALUE).accept(data)
            );
        }

        @JsonIgnore
        @Override
        public Map<String, Object> asRawParameters()
        {
            Map<String, Object> ret = new TreeMap<>();
            ret.put("PROPS_INSTANCE", propsInstance);
            ret.put("PROP_KEY", propKey);
            ret.put("PROP_VALUE", propValue);
            return ret;
        }

        @JsonIgnore
        @Override
        public Object getByColumn(Column clm)
        {
            switch(clm.getName())
            {
                case "PROPS_INSTANCE":
                    return propsInstance;
                case "PROP_KEY":
                    return propKey;
                case "PROP_VALUE":
                    return propValue;
                default:
                    throw new ImplementationError("Unknown database column. Table: " + clm.getTable().getName() + ", Column: " + clm.getName());
            }
        }

        @JsonIgnore
        @Override
        public final String getKey()
        {
            return formattedPrimaryKey;
        }

        @Override
        @JsonIgnore
        public DatabaseTable getDatabaseTable()
        {
            return GeneratedDatabaseTables.PROPS_CONTAINERS;
        }
    }

    @Version(GenCrdCurrent.VERSION)
    @Group(GenCrdCurrent.GROUP)
    @Plural("propscontainers")
    @Singular("propscontainers")
    public static class PropsContainers extends CustomResource<PropsContainersSpec, Void> implements LinstorCrd<PropsContainersSpec>
    {
        private static final long serialVersionUID = -2815223962691560637L;

        public PropsContainers()
        {
            super();
        }

        public PropsContainers(PropsContainersSpec spec)
        {
            setMetadata(new ObjectMetaBuilder().withName(spec.getKey()).build());
            setSpec(spec);
        }

        @JsonIgnore
        public String getKey()
        {
            return spec.getKey();
        }
    }

    public static Resources createResources(
        String uuid,
        String nodeName,
        String resourceName,
        String snapshotName,
        long resourceFlags,
        Long createTimestamp
    )
    {
        return new Resources(
            new ResourcesSpec(
                uuid,
                nodeName,
                resourceName,
                snapshotName,
                resourceFlags,
                createTimestamp
            )
        );
    }

    public static class ResourcesSpec implements LinstorSpec
    {
        @JsonIgnore private static final long serialVersionUID = -8590892533024068379L;
        @JsonIgnore private static final String PK_FORMAT = "%s:%s:%s";

        @JsonIgnore private final String formattedPrimaryKey;

        @JsonProperty("uuid") public final String uuid;
        @JsonProperty("node_name") public final String nodeName; // PK
        @JsonProperty("resource_name") public final String resourceName; // PK
        @JsonProperty("snapshot_name") public final String snapshotName; // PK
        @JsonProperty("resource_flags") public final long resourceFlags;
        @JsonProperty("create_timestamp") public final Long createTimestamp;

        @JsonCreator
        public ResourcesSpec(
            @JsonProperty("uuid") String uuidRef,
            @JsonProperty("node_name") String nodeNameRef,
            @JsonProperty("resource_name") String resourceNameRef,
            @JsonProperty("snapshot_name") String snapshotNameRef,
            @JsonProperty("resource_flags") long resourceFlagsRef,
            @JsonProperty("create_timestamp") Long createTimestampRef
        )
        {
            uuid = uuidRef;
            nodeName = nodeNameRef;
            resourceName = resourceNameRef;
            snapshotName = snapshotNameRef;
            resourceFlags = resourceFlagsRef;
            createTimestamp = createTimestampRef;

            formattedPrimaryKey = base32Encode(String.format(
                ResourcesSpec.PK_FORMAT,
                nodeName,
                resourceName,
                snapshotName
            ));
        }

        public final <DATA> ResourcesSpec create(
            Map<Column, ExceptionThrowingFunction<DATA, Object, AccessDeniedException>> setters,
            DATA data
        )
            throws AccessDeniedException
        {
            return new ResourcesSpec(
                (String) setters.get(GeneratedDatabaseTables.Resources.UUID).accept(data),
                (String) setters.get(GeneratedDatabaseTables.Resources.NODE_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.Resources.RESOURCE_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.Resources.SNAPSHOT_NAME).accept(data),
                (long) setters.get(GeneratedDatabaseTables.Resources.RESOURCE_FLAGS).accept(data),
                (Long) setters.get(GeneratedDatabaseTables.Resources.CREATE_TIMESTAMP).accept(data)
            );
        }

        @JsonIgnore
        @Override
        public Map<String, Object> asRawParameters()
        {
            Map<String, Object> ret = new TreeMap<>();
            ret.put("UUID", uuid);
            ret.put("NODE_NAME", nodeName);
            ret.put("RESOURCE_NAME", resourceName);
            ret.put("SNAPSHOT_NAME", snapshotName);
            ret.put("RESOURCE_FLAGS", resourceFlags);
            ret.put("CREATE_TIMESTAMP", createTimestamp);
            return ret;
        }

        @JsonIgnore
        @Override
        public Object getByColumn(Column clm)
        {
            switch(clm.getName())
            {
                case "UUID":
                    return uuid;
                case "NODE_NAME":
                    return nodeName;
                case "RESOURCE_NAME":
                    return resourceName;
                case "SNAPSHOT_NAME":
                    return snapshotName;
                case "RESOURCE_FLAGS":
                    return resourceFlags;
                case "CREATE_TIMESTAMP":
                    return createTimestamp;
                default:
                    throw new ImplementationError("Unknown database column. Table: " + clm.getTable().getName() + ", Column: " + clm.getName());
            }
        }

        @JsonIgnore
        @Override
        public final String getKey()
        {
            return formattedPrimaryKey;
        }

        @Override
        @JsonIgnore
        public DatabaseTable getDatabaseTable()
        {
            return GeneratedDatabaseTables.RESOURCES;
        }
    }

    @Version(GenCrdCurrent.VERSION)
    @Group(GenCrdCurrent.GROUP)
    @Plural("resources")
    @Singular("resources")
    public static class Resources extends CustomResource<ResourcesSpec, Void> implements LinstorCrd<ResourcesSpec>
    {
        private static final long serialVersionUID = 6496142312430789858L;

        public Resources()
        {
            super();
        }

        public Resources(ResourcesSpec spec)
        {
            setMetadata(new ObjectMetaBuilder().withName(spec.getKey()).build());
            setSpec(spec);
        }

        @JsonIgnore
        public String getKey()
        {
            return spec.getKey();
        }
    }

    public static ResourceConnections createResourceConnections(
        String uuid,
        String nodeNameSrc,
        String nodeNameDst,
        String resourceName,
        String snapshotName,
        long flags,
        Integer tcpPort
    )
    {
        return new ResourceConnections(
            new ResourceConnectionsSpec(
                uuid,
                nodeNameSrc,
                nodeNameDst,
                resourceName,
                snapshotName,
                flags,
                tcpPort
            )
        );
    }

    public static class ResourceConnectionsSpec implements LinstorSpec
    {
        @JsonIgnore private static final long serialVersionUID = -3088095736042315495L;
        @JsonIgnore private static final String PK_FORMAT = "%s:%s:%s:%s";

        @JsonIgnore private final String formattedPrimaryKey;

        @JsonProperty("uuid") public final String uuid;
        @JsonProperty("node_name_src") public final String nodeNameSrc; // PK
        @JsonProperty("node_name_dst") public final String nodeNameDst; // PK
        @JsonProperty("resource_name") public final String resourceName; // PK
        @JsonProperty("snapshot_name") public final String snapshotName; // PK
        @JsonProperty("flags") public final long flags;
        @JsonProperty("tcp_port") public final Integer tcpPort;

        @JsonCreator
        public ResourceConnectionsSpec(
            @JsonProperty("uuid") String uuidRef,
            @JsonProperty("node_name_src") String nodeNameSrcRef,
            @JsonProperty("node_name_dst") String nodeNameDstRef,
            @JsonProperty("resource_name") String resourceNameRef,
            @JsonProperty("snapshot_name") String snapshotNameRef,
            @JsonProperty("flags") long flagsRef,
            @JsonProperty("tcp_port") Integer tcpPortRef
        )
        {
            uuid = uuidRef;
            nodeNameSrc = nodeNameSrcRef;
            nodeNameDst = nodeNameDstRef;
            resourceName = resourceNameRef;
            snapshotName = snapshotNameRef;
            flags = flagsRef;
            tcpPort = tcpPortRef;

            formattedPrimaryKey = base32Encode(String.format(
                ResourceConnectionsSpec.PK_FORMAT,
                nodeNameSrc,
                nodeNameDst,
                resourceName,
                snapshotName
            ));
        }

        public final <DATA> ResourceConnectionsSpec create(
            Map<Column, ExceptionThrowingFunction<DATA, Object, AccessDeniedException>> setters,
            DATA data
        )
            throws AccessDeniedException
        {
            return new ResourceConnectionsSpec(
                (String) setters.get(GeneratedDatabaseTables.ResourceConnections.UUID).accept(data),
                (String) setters.get(GeneratedDatabaseTables.ResourceConnections.NODE_NAME_SRC).accept(data),
                (String) setters.get(GeneratedDatabaseTables.ResourceConnections.NODE_NAME_DST).accept(data),
                (String) setters.get(GeneratedDatabaseTables.ResourceConnections.RESOURCE_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.ResourceConnections.SNAPSHOT_NAME).accept(data),
                (long) setters.get(GeneratedDatabaseTables.ResourceConnections.FLAGS).accept(data),
                (Integer) setters.get(GeneratedDatabaseTables.ResourceConnections.TCP_PORT).accept(data)
            );
        }

        @JsonIgnore
        @Override
        public Map<String, Object> asRawParameters()
        {
            Map<String, Object> ret = new TreeMap<>();
            ret.put("UUID", uuid);
            ret.put("NODE_NAME_SRC", nodeNameSrc);
            ret.put("NODE_NAME_DST", nodeNameDst);
            ret.put("RESOURCE_NAME", resourceName);
            ret.put("SNAPSHOT_NAME", snapshotName);
            ret.put("FLAGS", flags);
            ret.put("TCP_PORT", tcpPort);
            return ret;
        }

        @JsonIgnore
        @Override
        public Object getByColumn(Column clm)
        {
            switch(clm.getName())
            {
                case "UUID":
                    return uuid;
                case "NODE_NAME_SRC":
                    return nodeNameSrc;
                case "NODE_NAME_DST":
                    return nodeNameDst;
                case "RESOURCE_NAME":
                    return resourceName;
                case "SNAPSHOT_NAME":
                    return snapshotName;
                case "FLAGS":
                    return flags;
                case "TCP_PORT":
                    return tcpPort;
                default:
                    throw new ImplementationError("Unknown database column. Table: " + clm.getTable().getName() + ", Column: " + clm.getName());
            }
        }

        @JsonIgnore
        @Override
        public final String getKey()
        {
            return formattedPrimaryKey;
        }

        @Override
        @JsonIgnore
        public DatabaseTable getDatabaseTable()
        {
            return GeneratedDatabaseTables.RESOURCE_CONNECTIONS;
        }
    }

    @Version(GenCrdCurrent.VERSION)
    @Group(GenCrdCurrent.GROUP)
    @Plural("resourceconnections")
    @Singular("resourceconnections")
    public static class ResourceConnections extends CustomResource<ResourceConnectionsSpec, Void> implements LinstorCrd<ResourceConnectionsSpec>
    {
        private static final long serialVersionUID = -4241124025928612895L;

        public ResourceConnections()
        {
            super();
        }

        public ResourceConnections(ResourceConnectionsSpec spec)
        {
            setMetadata(new ObjectMetaBuilder().withName(spec.getKey()).build());
            setSpec(spec);
        }

        @JsonIgnore
        public String getKey()
        {
            return spec.getKey();
        }
    }

    public static ResourceDefinitions createResourceDefinitions(
        String uuid,
        String resourceName,
        String snapshotName,
        String resourceDspName,
        String snapshotDspName,
        long resourceFlags,
        String layerStack,
        byte[] resourceExternalName,
        String resourceGroupName,
        String parentUuid
    )
    {
        return new ResourceDefinitions(
            new ResourceDefinitionsSpec(
                uuid,
                resourceName,
                snapshotName,
                resourceDspName,
                snapshotDspName,
                resourceFlags,
                layerStack,
                resourceExternalName,
                resourceGroupName,
                parentUuid
            )
        );
    }

    public static class ResourceDefinitionsSpec implements LinstorSpec
    {
        @JsonIgnore private static final long serialVersionUID = -4037651531363700137L;
        @JsonIgnore private static final String PK_FORMAT = "%s:%s";

        @JsonIgnore private final String formattedPrimaryKey;

        @JsonProperty("uuid") public final String uuid;
        @JsonProperty("resource_name") public final String resourceName; // PK
        @JsonProperty("snapshot_name") public final String snapshotName; // PK
        @JsonProperty("resource_dsp_name") public final String resourceDspName;
        @JsonProperty("snapshot_dsp_name") public final String snapshotDspName;
        @JsonProperty("resource_flags") public final long resourceFlags;
        @JsonProperty("layer_stack") public final String layerStack;
        @JsonProperty("resource_external_name") public final byte[] resourceExternalName;
        @JsonProperty("resource_group_name") public final String resourceGroupName;
        @JsonProperty("parent_uuid") public final String parentUuid;

        @JsonCreator
        public ResourceDefinitionsSpec(
            @JsonProperty("uuid") String uuidRef,
            @JsonProperty("resource_name") String resourceNameRef,
            @JsonProperty("snapshot_name") String snapshotNameRef,
            @JsonProperty("resource_dsp_name") String resourceDspNameRef,
            @JsonProperty("snapshot_dsp_name") String snapshotDspNameRef,
            @JsonProperty("resource_flags") long resourceFlagsRef,
            @JsonProperty("layer_stack") String layerStackRef,
            @JsonProperty("resource_external_name") byte[] resourceExternalNameRef,
            @JsonProperty("resource_group_name") String resourceGroupNameRef,
            @JsonProperty("parent_uuid") String parentUuidRef
        )
        {
            uuid = uuidRef;
            resourceName = resourceNameRef;
            snapshotName = snapshotNameRef;
            resourceDspName = resourceDspNameRef;
            snapshotDspName = snapshotDspNameRef;
            resourceFlags = resourceFlagsRef;
            layerStack = layerStackRef;
            resourceExternalName = resourceExternalNameRef;
            resourceGroupName = resourceGroupNameRef;
            parentUuid = parentUuidRef;

            formattedPrimaryKey = base32Encode(String.format(
                ResourceDefinitionsSpec.PK_FORMAT,
                resourceName,
                snapshotName
            ));
        }

        public final <DATA> ResourceDefinitionsSpec create(
            Map<Column, ExceptionThrowingFunction<DATA, Object, AccessDeniedException>> setters,
            DATA data
        )
            throws AccessDeniedException
        {
            return new ResourceDefinitionsSpec(
                (String) setters.get(GeneratedDatabaseTables.ResourceDefinitions.UUID).accept(data),
                (String) setters.get(GeneratedDatabaseTables.ResourceDefinitions.RESOURCE_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.ResourceDefinitions.SNAPSHOT_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.ResourceDefinitions.RESOURCE_DSP_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.ResourceDefinitions.SNAPSHOT_DSP_NAME).accept(data),
                (long) setters.get(GeneratedDatabaseTables.ResourceDefinitions.RESOURCE_FLAGS).accept(data),
                (String) setters.get(GeneratedDatabaseTables.ResourceDefinitions.LAYER_STACK).accept(data),
                (byte[]) setters.get(GeneratedDatabaseTables.ResourceDefinitions.RESOURCE_EXTERNAL_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.ResourceDefinitions.RESOURCE_GROUP_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.ResourceDefinitions.PARENT_UUID).accept(data)
            );
        }

        @JsonIgnore
        @Override
        public Map<String, Object> asRawParameters()
        {
            Map<String, Object> ret = new TreeMap<>();
            ret.put("UUID", uuid);
            ret.put("RESOURCE_NAME", resourceName);
            ret.put("SNAPSHOT_NAME", snapshotName);
            ret.put("RESOURCE_DSP_NAME", resourceDspName);
            ret.put("SNAPSHOT_DSP_NAME", snapshotDspName);
            ret.put("RESOURCE_FLAGS", resourceFlags);
            ret.put("LAYER_STACK", layerStack);
            ret.put("RESOURCE_EXTERNAL_NAME", resourceExternalName);
            ret.put("RESOURCE_GROUP_NAME", resourceGroupName);
            ret.put("PARENT_UUID", parentUuid);
            return ret;
        }

        @JsonIgnore
        @Override
        public Object getByColumn(Column clm)
        {
            switch(clm.getName())
            {
                case "UUID":
                    return uuid;
                case "RESOURCE_NAME":
                    return resourceName;
                case "SNAPSHOT_NAME":
                    return snapshotName;
                case "RESOURCE_DSP_NAME":
                    return resourceDspName;
                case "SNAPSHOT_DSP_NAME":
                    return snapshotDspName;
                case "RESOURCE_FLAGS":
                    return resourceFlags;
                case "LAYER_STACK":
                    return layerStack;
                case "RESOURCE_EXTERNAL_NAME":
                    return resourceExternalName;
                case "RESOURCE_GROUP_NAME":
                    return resourceGroupName;
                case "PARENT_UUID":
                    return parentUuid;
                default:
                    throw new ImplementationError("Unknown database column. Table: " + clm.getTable().getName() + ", Column: " + clm.getName());
            }
        }

        @JsonIgnore
        @Override
        public final String getKey()
        {
            return formattedPrimaryKey;
        }

        @Override
        @JsonIgnore
        public DatabaseTable getDatabaseTable()
        {
            return GeneratedDatabaseTables.RESOURCE_DEFINITIONS;
        }
    }

    @Version(GenCrdCurrent.VERSION)
    @Group(GenCrdCurrent.GROUP)
    @Plural("resourcedefinitions")
    @Singular("resourcedefinitions")
    public static class ResourceDefinitions extends CustomResource<ResourceDefinitionsSpec, Void> implements LinstorCrd<ResourceDefinitionsSpec>
    {
        private static final long serialVersionUID = -4040470836596768951L;

        public ResourceDefinitions()
        {
            super();
        }

        public ResourceDefinitions(ResourceDefinitionsSpec spec)
        {
            setMetadata(new ObjectMetaBuilder().withName(spec.getKey()).build());
            setSpec(spec);
        }

        @JsonIgnore
        public String getKey()
        {
            return spec.getKey();
        }
    }

    public static ResourceGroups createResourceGroups(
        String uuid,
        String resourceGroupName,
        String resourceGroupDspName,
        String description,
        String layerStack,
        int replicaCount,
        String nodeNameList,
        String poolName,
        String poolNameDiskless,
        String doNotPlaceWithRscRegex,
        String doNotPlaceWithRscList,
        String replicasOnSame,
        String replicasOnDifferent,
        String allowedProviderList,
        Boolean disklessOnRemaining
    )
    {
        return new ResourceGroups(
            new ResourceGroupsSpec(
                uuid,
                resourceGroupName,
                resourceGroupDspName,
                description,
                layerStack,
                replicaCount,
                nodeNameList,
                poolName,
                poolNameDiskless,
                doNotPlaceWithRscRegex,
                doNotPlaceWithRscList,
                replicasOnSame,
                replicasOnDifferent,
                allowedProviderList,
                disklessOnRemaining
            )
        );
    }

    public static class ResourceGroupsSpec implements LinstorSpec
    {
        @JsonIgnore private static final long serialVersionUID = -3394988788490151201L;
        @JsonIgnore private static final String PK_FORMAT = "%s";

        @JsonIgnore private final String formattedPrimaryKey;

        @JsonProperty("uuid") public final String uuid;
        @JsonProperty("resource_group_name") public final String resourceGroupName; // PK
        @JsonProperty("resource_group_dsp_name") public final String resourceGroupDspName;
        @JsonProperty("description") public final String description;
        @JsonProperty("layer_stack") public final String layerStack;
        @JsonProperty("replica_count") public final int replicaCount;
        @JsonProperty("node_name_list") public final String nodeNameList;
        @JsonProperty("pool_name") public final String poolName;
        @JsonProperty("pool_name_diskless") public final String poolNameDiskless;
        @JsonProperty("do_not_place_with_rsc_regex") public final String doNotPlaceWithRscRegex;
        @JsonProperty("do_not_place_with_rsc_list") public final String doNotPlaceWithRscList;
        @JsonProperty("replicas_on_same") public final String replicasOnSame;
        @JsonProperty("replicas_on_different") public final String replicasOnDifferent;
        @JsonProperty("allowed_provider_list") public final String allowedProviderList;
        @JsonProperty("diskless_on_remaining") public final Boolean disklessOnRemaining;

        @JsonCreator
        public ResourceGroupsSpec(
            @JsonProperty("uuid") String uuidRef,
            @JsonProperty("resource_group_name") String resourceGroupNameRef,
            @JsonProperty("resource_group_dsp_name") String resourceGroupDspNameRef,
            @JsonProperty("description") String descriptionRef,
            @JsonProperty("layer_stack") String layerStackRef,
            @JsonProperty("replica_count") int replicaCountRef,
            @JsonProperty("node_name_list") String nodeNameListRef,
            @JsonProperty("pool_name") String poolNameRef,
            @JsonProperty("pool_name_diskless") String poolNameDisklessRef,
            @JsonProperty("do_not_place_with_rsc_regex") String doNotPlaceWithRscRegexRef,
            @JsonProperty("do_not_place_with_rsc_list") String doNotPlaceWithRscListRef,
            @JsonProperty("replicas_on_same") String replicasOnSameRef,
            @JsonProperty("replicas_on_different") String replicasOnDifferentRef,
            @JsonProperty("allowed_provider_list") String allowedProviderListRef,
            @JsonProperty("diskless_on_remaining") Boolean disklessOnRemainingRef
        )
        {
            uuid = uuidRef;
            resourceGroupName = resourceGroupNameRef;
            resourceGroupDspName = resourceGroupDspNameRef;
            description = descriptionRef;
            layerStack = layerStackRef;
            replicaCount = replicaCountRef;
            nodeNameList = nodeNameListRef;
            poolName = poolNameRef;
            poolNameDiskless = poolNameDisklessRef;
            doNotPlaceWithRscRegex = doNotPlaceWithRscRegexRef;
            doNotPlaceWithRscList = doNotPlaceWithRscListRef;
            replicasOnSame = replicasOnSameRef;
            replicasOnDifferent = replicasOnDifferentRef;
            allowedProviderList = allowedProviderListRef;
            disklessOnRemaining = disklessOnRemainingRef;

            formattedPrimaryKey = base32Encode(String.format(
                ResourceGroupsSpec.PK_FORMAT,
                resourceGroupName
            ));
        }

        public final <DATA> ResourceGroupsSpec create(
            Map<Column, ExceptionThrowingFunction<DATA, Object, AccessDeniedException>> setters,
            DATA data
        )
            throws AccessDeniedException
        {
            return new ResourceGroupsSpec(
                (String) setters.get(GeneratedDatabaseTables.ResourceGroups.UUID).accept(data),
                (String) setters.get(GeneratedDatabaseTables.ResourceGroups.RESOURCE_GROUP_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.ResourceGroups.RESOURCE_GROUP_DSP_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.ResourceGroups.DESCRIPTION).accept(data),
                (String) setters.get(GeneratedDatabaseTables.ResourceGroups.LAYER_STACK).accept(data),
                (int) setters.get(GeneratedDatabaseTables.ResourceGroups.REPLICA_COUNT).accept(data),
                (String) setters.get(GeneratedDatabaseTables.ResourceGroups.NODE_NAME_LIST).accept(data),
                (String) setters.get(GeneratedDatabaseTables.ResourceGroups.POOL_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.ResourceGroups.POOL_NAME_DISKLESS).accept(data),
                (String) setters.get(GeneratedDatabaseTables.ResourceGroups.DO_NOT_PLACE_WITH_RSC_REGEX).accept(data),
                (String) setters.get(GeneratedDatabaseTables.ResourceGroups.DO_NOT_PLACE_WITH_RSC_LIST).accept(data),
                (String) setters.get(GeneratedDatabaseTables.ResourceGroups.REPLICAS_ON_SAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.ResourceGroups.REPLICAS_ON_DIFFERENT).accept(data),
                (String) setters.get(GeneratedDatabaseTables.ResourceGroups.ALLOWED_PROVIDER_LIST).accept(data),
                (Boolean) setters.get(GeneratedDatabaseTables.ResourceGroups.DISKLESS_ON_REMAINING).accept(data)
            );
        }

        @JsonIgnore
        @Override
        public Map<String, Object> asRawParameters()
        {
            Map<String, Object> ret = new TreeMap<>();
            ret.put("UUID", uuid);
            ret.put("RESOURCE_GROUP_NAME", resourceGroupName);
            ret.put("RESOURCE_GROUP_DSP_NAME", resourceGroupDspName);
            ret.put("DESCRIPTION", description);
            ret.put("LAYER_STACK", layerStack);
            ret.put("REPLICA_COUNT", replicaCount);
            ret.put("NODE_NAME_LIST", nodeNameList);
            ret.put("POOL_NAME", poolName);
            ret.put("POOL_NAME_DISKLESS", poolNameDiskless);
            ret.put("DO_NOT_PLACE_WITH_RSC_REGEX", doNotPlaceWithRscRegex);
            ret.put("DO_NOT_PLACE_WITH_RSC_LIST", doNotPlaceWithRscList);
            ret.put("REPLICAS_ON_SAME", replicasOnSame);
            ret.put("REPLICAS_ON_DIFFERENT", replicasOnDifferent);
            ret.put("ALLOWED_PROVIDER_LIST", allowedProviderList);
            ret.put("DISKLESS_ON_REMAINING", disklessOnRemaining);
            return ret;
        }

        @JsonIgnore
        @Override
        public Object getByColumn(Column clm)
        {
            switch(clm.getName())
            {
                case "UUID":
                    return uuid;
                case "RESOURCE_GROUP_NAME":
                    return resourceGroupName;
                case "RESOURCE_GROUP_DSP_NAME":
                    return resourceGroupDspName;
                case "DESCRIPTION":
                    return description;
                case "LAYER_STACK":
                    return layerStack;
                case "REPLICA_COUNT":
                    return replicaCount;
                case "NODE_NAME_LIST":
                    return nodeNameList;
                case "POOL_NAME":
                    return poolName;
                case "POOL_NAME_DISKLESS":
                    return poolNameDiskless;
                case "DO_NOT_PLACE_WITH_RSC_REGEX":
                    return doNotPlaceWithRscRegex;
                case "DO_NOT_PLACE_WITH_RSC_LIST":
                    return doNotPlaceWithRscList;
                case "REPLICAS_ON_SAME":
                    return replicasOnSame;
                case "REPLICAS_ON_DIFFERENT":
                    return replicasOnDifferent;
                case "ALLOWED_PROVIDER_LIST":
                    return allowedProviderList;
                case "DISKLESS_ON_REMAINING":
                    return disklessOnRemaining;
                default:
                    throw new ImplementationError("Unknown database column. Table: " + clm.getTable().getName() + ", Column: " + clm.getName());
            }
        }

        @JsonIgnore
        @Override
        public final String getKey()
        {
            return formattedPrimaryKey;
        }

        @Override
        @JsonIgnore
        public DatabaseTable getDatabaseTable()
        {
            return GeneratedDatabaseTables.RESOURCE_GROUPS;
        }
    }

    @Version(GenCrdCurrent.VERSION)
    @Group(GenCrdCurrent.GROUP)
    @Plural("resourcegroups")
    @Singular("resourcegroups")
    public static class ResourceGroups extends CustomResource<ResourceGroupsSpec, Void> implements LinstorCrd<ResourceGroupsSpec>
    {
        private static final long serialVersionUID = 5925432331397939051L;

        public ResourceGroups()
        {
            super();
        }

        public ResourceGroups(ResourceGroupsSpec spec)
        {
            setMetadata(new ObjectMetaBuilder().withName(spec.getKey()).build());
            setSpec(spec);
        }

        @JsonIgnore
        public String getKey()
        {
            return spec.getKey();
        }
    }

    public static S3Remotes createS3Remotes(
        String uuid,
        String name,
        String dspName,
        long flags,
        String endpoint,
        String bucket,
        String region,
        byte[] accessKey,
        byte[] secretKey
    )
    {
        return new S3Remotes(
            new S3RemotesSpec(
                uuid,
                name,
                dspName,
                flags,
                endpoint,
                bucket,
                region,
                accessKey,
                secretKey
            )
        );
    }

    public static class S3RemotesSpec implements LinstorSpec
    {
        @JsonIgnore private static final long serialVersionUID = -8174072396908589549L;
        @JsonIgnore private static final String PK_FORMAT = "%s";

        @JsonIgnore private final String formattedPrimaryKey;

        @JsonProperty("uuid") public final String uuid;
        @JsonProperty("name") public final String name; // PK
        @JsonProperty("dsp_name") public final String dspName;
        @JsonProperty("flags") public final long flags;
        @JsonProperty("endpoint") public final String endpoint;
        @JsonProperty("bucket") public final String bucket;
        @JsonProperty("region") public final String region;
        @JsonProperty("access_key") public final byte[] accessKey;
        @JsonProperty("secret_key") public final byte[] secretKey;

        @JsonCreator
        public S3RemotesSpec(
            @JsonProperty("uuid") String uuidRef,
            @JsonProperty("name") String nameRef,
            @JsonProperty("dsp_name") String dspNameRef,
            @JsonProperty("flags") long flagsRef,
            @JsonProperty("endpoint") String endpointRef,
            @JsonProperty("bucket") String bucketRef,
            @JsonProperty("region") String regionRef,
            @JsonProperty("access_key") byte[] accessKeyRef,
            @JsonProperty("secret_key") byte[] secretKeyRef
        )
        {
            uuid = uuidRef;
            name = nameRef;
            dspName = dspNameRef;
            flags = flagsRef;
            endpoint = endpointRef;
            bucket = bucketRef;
            region = regionRef;
            accessKey = accessKeyRef;
            secretKey = secretKeyRef;

            formattedPrimaryKey = base32Encode(String.format(
                S3RemotesSpec.PK_FORMAT,
                name
            ));
        }

        public final <DATA> S3RemotesSpec create(
            Map<Column, ExceptionThrowingFunction<DATA, Object, AccessDeniedException>> setters,
            DATA data
        )
            throws AccessDeniedException
        {
            return new S3RemotesSpec(
                (String) setters.get(GeneratedDatabaseTables.S3Remotes.UUID).accept(data),
                (String) setters.get(GeneratedDatabaseTables.S3Remotes.NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.S3Remotes.DSP_NAME).accept(data),
                (long) setters.get(GeneratedDatabaseTables.S3Remotes.FLAGS).accept(data),
                (String) setters.get(GeneratedDatabaseTables.S3Remotes.ENDPOINT).accept(data),
                (String) setters.get(GeneratedDatabaseTables.S3Remotes.BUCKET).accept(data),
                (String) setters.get(GeneratedDatabaseTables.S3Remotes.REGION).accept(data),
                (byte[]) setters.get(GeneratedDatabaseTables.S3Remotes.ACCESS_KEY).accept(data),
                (byte[]) setters.get(GeneratedDatabaseTables.S3Remotes.SECRET_KEY).accept(data)
            );
        }

        @JsonIgnore
        @Override
        public Map<String, Object> asRawParameters()
        {
            Map<String, Object> ret = new TreeMap<>();
            ret.put("UUID", uuid);
            ret.put("NAME", name);
            ret.put("DSP_NAME", dspName);
            ret.put("FLAGS", flags);
            ret.put("ENDPOINT", endpoint);
            ret.put("BUCKET", bucket);
            ret.put("REGION", region);
            ret.put("ACCESS_KEY", accessKey);
            ret.put("SECRET_KEY", secretKey);
            return ret;
        }

        @JsonIgnore
        @Override
        public Object getByColumn(Column clm)
        {
            switch(clm.getName())
            {
                case "UUID":
                    return uuid;
                case "NAME":
                    return name;
                case "DSP_NAME":
                    return dspName;
                case "FLAGS":
                    return flags;
                case "ENDPOINT":
                    return endpoint;
                case "BUCKET":
                    return bucket;
                case "REGION":
                    return region;
                case "ACCESS_KEY":
                    return accessKey;
                case "SECRET_KEY":
                    return secretKey;
                default:
                    throw new ImplementationError("Unknown database column. Table: " + clm.getTable().getName() + ", Column: " + clm.getName());
            }
        }

        @JsonIgnore
        @Override
        public final String getKey()
        {
            return formattedPrimaryKey;
        }

        @Override
        @JsonIgnore
        public DatabaseTable getDatabaseTable()
        {
            return GeneratedDatabaseTables.S3_REMOTES;
        }
    }

    @Version(GenCrdCurrent.VERSION)
    @Group(GenCrdCurrent.GROUP)
    @Plural("s3remotes")
    @Singular("s3remotes")
    public static class S3Remotes extends CustomResource<S3RemotesSpec, Void> implements LinstorCrd<S3RemotesSpec>
    {
        private static final long serialVersionUID = 1478571457899262501L;

        public S3Remotes()
        {
            super();
        }

        public S3Remotes(S3RemotesSpec spec)
        {
            setMetadata(new ObjectMetaBuilder().withName(spec.getKey()).build());
            setSpec(spec);
        }

        @JsonIgnore
        public String getKey()
        {
            return spec.getKey();
        }
    }

    public static SatellitesCapacity createSatellitesCapacity(
        String nodeName,
        byte[] capacity,
        boolean failFlag
    )
    {
        return new SatellitesCapacity(
            new SatellitesCapacitySpec(
                nodeName,
                capacity,
                failFlag
            )
        );
    }

    public static class SatellitesCapacitySpec implements LinstorSpec
    {
        @JsonIgnore private static final long serialVersionUID = 7203012779856314459L;
        @JsonIgnore private static final String PK_FORMAT = "%s";

        @JsonIgnore private final String formattedPrimaryKey;

        @JsonProperty("node_name") public final String nodeName; // PK
        @JsonProperty("capacity") public final byte[] capacity;
        @JsonProperty("fail_flag") public final boolean failFlag;

        @JsonCreator
        public SatellitesCapacitySpec(
            @JsonProperty("node_name") String nodeNameRef,
            @JsonProperty("capacity") byte[] capacityRef,
            @JsonProperty("fail_flag") boolean failFlagRef
        )
        {
            nodeName = nodeNameRef;
            capacity = capacityRef;
            failFlag = failFlagRef;

            formattedPrimaryKey = base32Encode(String.format(
                SatellitesCapacitySpec.PK_FORMAT,
                nodeName
            ));
        }

        public final <DATA> SatellitesCapacitySpec create(
            Map<Column, ExceptionThrowingFunction<DATA, Object, AccessDeniedException>> setters,
            DATA data
        )
            throws AccessDeniedException
        {
            return new SatellitesCapacitySpec(
                (String) setters.get(GeneratedDatabaseTables.SatellitesCapacity.NODE_NAME).accept(data),
                (byte[]) setters.get(GeneratedDatabaseTables.SatellitesCapacity.CAPACITY).accept(data),
                (boolean) setters.get(GeneratedDatabaseTables.SatellitesCapacity.FAIL_FLAG).accept(data)
            );
        }

        @JsonIgnore
        @Override
        public Map<String, Object> asRawParameters()
        {
            Map<String, Object> ret = new TreeMap<>();
            ret.put("NODE_NAME", nodeName);
            ret.put("CAPACITY", capacity);
            ret.put("FAIL_FLAG", failFlag);
            return ret;
        }

        @JsonIgnore
        @Override
        public Object getByColumn(Column clm)
        {
            switch(clm.getName())
            {
                case "NODE_NAME":
                    return nodeName;
                case "CAPACITY":
                    return capacity;
                case "FAIL_FLAG":
                    return failFlag;
                default:
                    throw new ImplementationError("Unknown database column. Table: " + clm.getTable().getName() + ", Column: " + clm.getName());
            }
        }

        @JsonIgnore
        @Override
        public final String getKey()
        {
            return formattedPrimaryKey;
        }

        @Override
        @JsonIgnore
        public DatabaseTable getDatabaseTable()
        {
            return GeneratedDatabaseTables.SATELLITES_CAPACITY;
        }
    }

    @Version(GenCrdCurrent.VERSION)
    @Group(GenCrdCurrent.GROUP)
    @Plural("satellitescapacity")
    @Singular("satellitescapacity")
    public static class SatellitesCapacity extends CustomResource<SatellitesCapacitySpec, Void> implements LinstorCrd<SatellitesCapacitySpec>
    {
        private static final long serialVersionUID = 8718007324775575394L;

        public SatellitesCapacity()
        {
            super();
        }

        public SatellitesCapacity(SatellitesCapacitySpec spec)
        {
            setMetadata(new ObjectMetaBuilder().withName(spec.getKey()).build());
            setSpec(spec);
        }

        @JsonIgnore
        public String getKey()
        {
            return spec.getKey();
        }
    }

    public static SecAccessTypes createSecAccessTypes(
        String accessTypeName,
        short accessTypeValue
    )
    {
        return new SecAccessTypes(
            new SecAccessTypesSpec(
                accessTypeName,
                accessTypeValue
            )
        );
    }

    public static class SecAccessTypesSpec implements LinstorSpec
    {
        @JsonIgnore private static final long serialVersionUID = 3110224716593514828L;
        @JsonIgnore private static final String PK_FORMAT = "%s";

        @JsonIgnore private final String formattedPrimaryKey;

        @JsonProperty("access_type_name") public final String accessTypeName; // PK
        @JsonProperty("access_type_value") public final short accessTypeValue;

        @JsonCreator
        public SecAccessTypesSpec(
            @JsonProperty("access_type_name") String accessTypeNameRef,
            @JsonProperty("access_type_value") short accessTypeValueRef
        )
        {
            accessTypeName = accessTypeNameRef;
            accessTypeValue = accessTypeValueRef;

            formattedPrimaryKey = base32Encode(String.format(
                SecAccessTypesSpec.PK_FORMAT,
                accessTypeName
            ));
        }

        public final <DATA> SecAccessTypesSpec create(
            Map<Column, ExceptionThrowingFunction<DATA, Object, AccessDeniedException>> setters,
            DATA data
        )
            throws AccessDeniedException
        {
            return new SecAccessTypesSpec(
                (String) setters.get(GeneratedDatabaseTables.SecAccessTypes.ACCESS_TYPE_NAME).accept(data),
                (short) setters.get(GeneratedDatabaseTables.SecAccessTypes.ACCESS_TYPE_VALUE).accept(data)
            );
        }

        @JsonIgnore
        @Override
        public Map<String, Object> asRawParameters()
        {
            Map<String, Object> ret = new TreeMap<>();
            ret.put("ACCESS_TYPE_NAME", accessTypeName);
            ret.put("ACCESS_TYPE_VALUE", accessTypeValue);
            return ret;
        }

        @JsonIgnore
        @Override
        public Object getByColumn(Column clm)
        {
            switch(clm.getName())
            {
                case "ACCESS_TYPE_NAME":
                    return accessTypeName;
                case "ACCESS_TYPE_VALUE":
                    return accessTypeValue;
                default:
                    throw new ImplementationError("Unknown database column. Table: " + clm.getTable().getName() + ", Column: " + clm.getName());
            }
        }

        @JsonIgnore
        @Override
        public final String getKey()
        {
            return formattedPrimaryKey;
        }

        @Override
        @JsonIgnore
        public DatabaseTable getDatabaseTable()
        {
            return GeneratedDatabaseTables.SEC_ACCESS_TYPES;
        }
    }

    @Version(GenCrdCurrent.VERSION)
    @Group(GenCrdCurrent.GROUP)
    @Plural("secaccesstypes")
    @Singular("secaccesstypes")
    public static class SecAccessTypes extends CustomResource<SecAccessTypesSpec, Void> implements LinstorCrd<SecAccessTypesSpec>
    {
        private static final long serialVersionUID = 4038311593889718758L;

        public SecAccessTypes()
        {
            super();
        }

        public SecAccessTypes(SecAccessTypesSpec spec)
        {
            setMetadata(new ObjectMetaBuilder().withName(spec.getKey()).build());
            setSpec(spec);
        }

        @JsonIgnore
        public String getKey()
        {
            return spec.getKey();
        }
    }

    public static SecAclMap createSecAclMap(
        String objectPath,
        String roleName,
        short accessType
    )
    {
        return new SecAclMap(
            new SecAclMapSpec(
                objectPath,
                roleName,
                accessType
            )
        );
    }

    public static class SecAclMapSpec implements LinstorSpec
    {
        @JsonIgnore private static final long serialVersionUID = 8088557165655933959L;
        @JsonIgnore private static final String PK_FORMAT = "%s:%s";

        @JsonIgnore private final String formattedPrimaryKey;

        @JsonProperty("object_path") public final String objectPath; // PK
        @JsonProperty("role_name") public final String roleName; // PK
        @JsonProperty("access_type") public final short accessType;

        @JsonCreator
        public SecAclMapSpec(
            @JsonProperty("object_path") String objectPathRef,
            @JsonProperty("role_name") String roleNameRef,
            @JsonProperty("access_type") short accessTypeRef
        )
        {
            objectPath = objectPathRef;
            roleName = roleNameRef;
            accessType = accessTypeRef;

            formattedPrimaryKey = base32Encode(String.format(
                SecAclMapSpec.PK_FORMAT,
                objectPath,
                roleName
            ));
        }

        public final <DATA> SecAclMapSpec create(
            Map<Column, ExceptionThrowingFunction<DATA, Object, AccessDeniedException>> setters,
            DATA data
        )
            throws AccessDeniedException
        {
            return new SecAclMapSpec(
                (String) setters.get(GeneratedDatabaseTables.SecAclMap.OBJECT_PATH).accept(data),
                (String) setters.get(GeneratedDatabaseTables.SecAclMap.ROLE_NAME).accept(data),
                (short) setters.get(GeneratedDatabaseTables.SecAclMap.ACCESS_TYPE).accept(data)
            );
        }

        @JsonIgnore
        @Override
        public Map<String, Object> asRawParameters()
        {
            Map<String, Object> ret = new TreeMap<>();
            ret.put("OBJECT_PATH", objectPath);
            ret.put("ROLE_NAME", roleName);
            ret.put("ACCESS_TYPE", accessType);
            return ret;
        }

        @JsonIgnore
        @Override
        public Object getByColumn(Column clm)
        {
            switch(clm.getName())
            {
                case "OBJECT_PATH":
                    return objectPath;
                case "ROLE_NAME":
                    return roleName;
                case "ACCESS_TYPE":
                    return accessType;
                default:
                    throw new ImplementationError("Unknown database column. Table: " + clm.getTable().getName() + ", Column: " + clm.getName());
            }
        }

        @JsonIgnore
        @Override
        public final String getKey()
        {
            return formattedPrimaryKey;
        }

        @Override
        @JsonIgnore
        public DatabaseTable getDatabaseTable()
        {
            return GeneratedDatabaseTables.SEC_ACL_MAP;
        }
    }

    @Version(GenCrdCurrent.VERSION)
    @Group(GenCrdCurrent.GROUP)
    @Plural("secaclmap")
    @Singular("secaclmap")
    public static class SecAclMap extends CustomResource<SecAclMapSpec, Void> implements LinstorCrd<SecAclMapSpec>
    {
        private static final long serialVersionUID = 5564569579885574219L;

        public SecAclMap()
        {
            super();
        }

        public SecAclMap(SecAclMapSpec spec)
        {
            setMetadata(new ObjectMetaBuilder().withName(spec.getKey()).build());
            setSpec(spec);
        }

        @JsonIgnore
        public String getKey()
        {
            return spec.getKey();
        }
    }

    public static SecConfiguration createSecConfiguration(
        String entryKey,
        String entryDspKey,
        String entryValue
    )
    {
        return new SecConfiguration(
            new SecConfigurationSpec(
                entryKey,
                entryDspKey,
                entryValue
            )
        );
    }

    public static class SecConfigurationSpec implements LinstorSpec
    {
        @JsonIgnore private static final long serialVersionUID = 1499181990377066975L;
        @JsonIgnore private static final String PK_FORMAT = "%s";

        @JsonIgnore private final String formattedPrimaryKey;

        @JsonProperty("entry_key") public final String entryKey; // PK
        @JsonProperty("entry_dsp_key") public final String entryDspKey;
        @JsonProperty("entry_value") public final String entryValue;

        @JsonCreator
        public SecConfigurationSpec(
            @JsonProperty("entry_key") String entryKeyRef,
            @JsonProperty("entry_dsp_key") String entryDspKeyRef,
            @JsonProperty("entry_value") String entryValueRef
        )
        {
            entryKey = entryKeyRef;
            entryDspKey = entryDspKeyRef;
            entryValue = entryValueRef;

            formattedPrimaryKey = base32Encode(String.format(
                SecConfigurationSpec.PK_FORMAT,
                entryKey
            ));
        }

        public final <DATA> SecConfigurationSpec create(
            Map<Column, ExceptionThrowingFunction<DATA, Object, AccessDeniedException>> setters,
            DATA data
        )
            throws AccessDeniedException
        {
            return new SecConfigurationSpec(
                (String) setters.get(GeneratedDatabaseTables.SecConfiguration.ENTRY_KEY).accept(data),
                (String) setters.get(GeneratedDatabaseTables.SecConfiguration.ENTRY_DSP_KEY).accept(data),
                (String) setters.get(GeneratedDatabaseTables.SecConfiguration.ENTRY_VALUE).accept(data)
            );
        }

        @JsonIgnore
        @Override
        public Map<String, Object> asRawParameters()
        {
            Map<String, Object> ret = new TreeMap<>();
            ret.put("ENTRY_KEY", entryKey);
            ret.put("ENTRY_DSP_KEY", entryDspKey);
            ret.put("ENTRY_VALUE", entryValue);
            return ret;
        }

        @JsonIgnore
        @Override
        public Object getByColumn(Column clm)
        {
            switch(clm.getName())
            {
                case "ENTRY_KEY":
                    return entryKey;
                case "ENTRY_DSP_KEY":
                    return entryDspKey;
                case "ENTRY_VALUE":
                    return entryValue;
                default:
                    throw new ImplementationError("Unknown database column. Table: " + clm.getTable().getName() + ", Column: " + clm.getName());
            }
        }

        @JsonIgnore
        @Override
        public final String getKey()
        {
            return formattedPrimaryKey;
        }

        @Override
        @JsonIgnore
        public DatabaseTable getDatabaseTable()
        {
            return GeneratedDatabaseTables.SEC_CONFIGURATION;
        }
    }

    @Version(GenCrdCurrent.VERSION)
    @Group(GenCrdCurrent.GROUP)
    @Plural("secconfiguration")
    @Singular("secconfiguration")
    public static class SecConfiguration extends CustomResource<SecConfigurationSpec, Void> implements LinstorCrd<SecConfigurationSpec>
    {
        private static final long serialVersionUID = -952812496400473530L;

        public SecConfiguration()
        {
            super();
        }

        public SecConfiguration(SecConfigurationSpec spec)
        {
            setMetadata(new ObjectMetaBuilder().withName(spec.getKey()).build());
            setSpec(spec);
        }

        @JsonIgnore
        public String getKey()
        {
            return spec.getKey();
        }
    }

    public static SecDfltRoles createSecDfltRoles(
        String identityName,
        String roleName
    )
    {
        return new SecDfltRoles(
            new SecDfltRolesSpec(
                identityName,
                roleName
            )
        );
    }

    public static class SecDfltRolesSpec implements LinstorSpec
    {
        @JsonIgnore private static final long serialVersionUID = 6224538930545419070L;
        @JsonIgnore private static final String PK_FORMAT = "%s";

        @JsonIgnore private final String formattedPrimaryKey;

        @JsonProperty("identity_name") public final String identityName; // PK
        @JsonProperty("role_name") public final String roleName;

        @JsonCreator
        public SecDfltRolesSpec(
            @JsonProperty("identity_name") String identityNameRef,
            @JsonProperty("role_name") String roleNameRef
        )
        {
            identityName = identityNameRef;
            roleName = roleNameRef;

            formattedPrimaryKey = base32Encode(String.format(
                SecDfltRolesSpec.PK_FORMAT,
                identityName
            ));
        }

        public final <DATA> SecDfltRolesSpec create(
            Map<Column, ExceptionThrowingFunction<DATA, Object, AccessDeniedException>> setters,
            DATA data
        )
            throws AccessDeniedException
        {
            return new SecDfltRolesSpec(
                (String) setters.get(GeneratedDatabaseTables.SecDfltRoles.IDENTITY_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.SecDfltRoles.ROLE_NAME).accept(data)
            );
        }

        @JsonIgnore
        @Override
        public Map<String, Object> asRawParameters()
        {
            Map<String, Object> ret = new TreeMap<>();
            ret.put("IDENTITY_NAME", identityName);
            ret.put("ROLE_NAME", roleName);
            return ret;
        }

        @JsonIgnore
        @Override
        public Object getByColumn(Column clm)
        {
            switch(clm.getName())
            {
                case "IDENTITY_NAME":
                    return identityName;
                case "ROLE_NAME":
                    return roleName;
                default:
                    throw new ImplementationError("Unknown database column. Table: " + clm.getTable().getName() + ", Column: " + clm.getName());
            }
        }

        @JsonIgnore
        @Override
        public final String getKey()
        {
            return formattedPrimaryKey;
        }

        @Override
        @JsonIgnore
        public DatabaseTable getDatabaseTable()
        {
            return GeneratedDatabaseTables.SEC_DFLT_ROLES;
        }
    }

    @Version(GenCrdCurrent.VERSION)
    @Group(GenCrdCurrent.GROUP)
    @Plural("secdfltroles")
    @Singular("secdfltroles")
    public static class SecDfltRoles extends CustomResource<SecDfltRolesSpec, Void> implements LinstorCrd<SecDfltRolesSpec>
    {
        private static final long serialVersionUID = -973417960206262738L;

        public SecDfltRoles()
        {
            super();
        }

        public SecDfltRoles(SecDfltRolesSpec spec)
        {
            setMetadata(new ObjectMetaBuilder().withName(spec.getKey()).build());
            setSpec(spec);
        }

        @JsonIgnore
        public String getKey()
        {
            return spec.getKey();
        }
    }

    public static SecIdentities createSecIdentities(
        String identityName,
        String identityDspName,
        String passSalt,
        String passHash,
        boolean idEnabled,
        boolean idLocked
    )
    {
        return new SecIdentities(
            new SecIdentitiesSpec(
                identityName,
                identityDspName,
                passSalt,
                passHash,
                idEnabled,
                idLocked
            )
        );
    }

    public static class SecIdentitiesSpec implements LinstorSpec
    {
        @JsonIgnore private static final long serialVersionUID = -4934240261264741688L;
        @JsonIgnore private static final String PK_FORMAT = "%s";

        @JsonIgnore private final String formattedPrimaryKey;

        @JsonProperty("identity_name") public final String identityName; // PK
        @JsonProperty("identity_dsp_name") public final String identityDspName;
        @JsonProperty("pass_salt") public final String passSalt;
        @JsonProperty("pass_hash") public final String passHash;
        @JsonProperty("id_enabled") public final boolean idEnabled;
        @JsonProperty("id_locked") public final boolean idLocked;

        @JsonCreator
        public SecIdentitiesSpec(
            @JsonProperty("identity_name") String identityNameRef,
            @JsonProperty("identity_dsp_name") String identityDspNameRef,
            @JsonProperty("pass_salt") String passSaltRef,
            @JsonProperty("pass_hash") String passHashRef,
            @JsonProperty("id_enabled") boolean idEnabledRef,
            @JsonProperty("id_locked") boolean idLockedRef
        )
        {
            identityName = identityNameRef;
            identityDspName = identityDspNameRef;
            passSalt = passSaltRef;
            passHash = passHashRef;
            idEnabled = idEnabledRef;
            idLocked = idLockedRef;

            formattedPrimaryKey = base32Encode(String.format(
                SecIdentitiesSpec.PK_FORMAT,
                identityName
            ));
        }

        public final <DATA> SecIdentitiesSpec create(
            Map<Column, ExceptionThrowingFunction<DATA, Object, AccessDeniedException>> setters,
            DATA data
        )
            throws AccessDeniedException
        {
            return new SecIdentitiesSpec(
                (String) setters.get(GeneratedDatabaseTables.SecIdentities.IDENTITY_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.SecIdentities.IDENTITY_DSP_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.SecIdentities.PASS_SALT).accept(data),
                (String) setters.get(GeneratedDatabaseTables.SecIdentities.PASS_HASH).accept(data),
                (boolean) setters.get(GeneratedDatabaseTables.SecIdentities.ID_ENABLED).accept(data),
                (boolean) setters.get(GeneratedDatabaseTables.SecIdentities.ID_LOCKED).accept(data)
            );
        }

        @JsonIgnore
        @Override
        public Map<String, Object> asRawParameters()
        {
            Map<String, Object> ret = new TreeMap<>();
            ret.put("IDENTITY_NAME", identityName);
            ret.put("IDENTITY_DSP_NAME", identityDspName);
            ret.put("PASS_SALT", passSalt);
            ret.put("PASS_HASH", passHash);
            ret.put("ID_ENABLED", idEnabled);
            ret.put("ID_LOCKED", idLocked);
            return ret;
        }

        @JsonIgnore
        @Override
        public Object getByColumn(Column clm)
        {
            switch(clm.getName())
            {
                case "IDENTITY_NAME":
                    return identityName;
                case "IDENTITY_DSP_NAME":
                    return identityDspName;
                case "PASS_SALT":
                    return passSalt;
                case "PASS_HASH":
                    return passHash;
                case "ID_ENABLED":
                    return idEnabled;
                case "ID_LOCKED":
                    return idLocked;
                default:
                    throw new ImplementationError("Unknown database column. Table: " + clm.getTable().getName() + ", Column: " + clm.getName());
            }
        }

        @JsonIgnore
        @Override
        public final String getKey()
        {
            return formattedPrimaryKey;
        }

        @Override
        @JsonIgnore
        public DatabaseTable getDatabaseTable()
        {
            return GeneratedDatabaseTables.SEC_IDENTITIES;
        }
    }

    @Version(GenCrdCurrent.VERSION)
    @Group(GenCrdCurrent.GROUP)
    @Plural("secidentities")
    @Singular("secidentities")
    public static class SecIdentities extends CustomResource<SecIdentitiesSpec, Void> implements LinstorCrd<SecIdentitiesSpec>
    {
        private static final long serialVersionUID = -1799349567664301377L;

        public SecIdentities()
        {
            super();
        }

        public SecIdentities(SecIdentitiesSpec spec)
        {
            setMetadata(new ObjectMetaBuilder().withName(spec.getKey()).build());
            setSpec(spec);
        }

        @JsonIgnore
        public String getKey()
        {
            return spec.getKey();
        }
    }

    public static SecIdRoleMap createSecIdRoleMap(
        String identityName,
        String roleName
    )
    {
        return new SecIdRoleMap(
            new SecIdRoleMapSpec(
                identityName,
                roleName
            )
        );
    }

    public static class SecIdRoleMapSpec implements LinstorSpec
    {
        @JsonIgnore private static final long serialVersionUID = -6748446103418429760L;
        @JsonIgnore private static final String PK_FORMAT = "%s:%s";

        @JsonIgnore private final String formattedPrimaryKey;

        @JsonProperty("identity_name") public final String identityName; // PK
        @JsonProperty("role_name") public final String roleName; // PK

        @JsonCreator
        public SecIdRoleMapSpec(
            @JsonProperty("identity_name") String identityNameRef,
            @JsonProperty("role_name") String roleNameRef
        )
        {
            identityName = identityNameRef;
            roleName = roleNameRef;

            formattedPrimaryKey = base32Encode(String.format(
                SecIdRoleMapSpec.PK_FORMAT,
                identityName,
                roleName
            ));
        }

        public final <DATA> SecIdRoleMapSpec create(
            Map<Column, ExceptionThrowingFunction<DATA, Object, AccessDeniedException>> setters,
            DATA data
        )
            throws AccessDeniedException
        {
            return new SecIdRoleMapSpec(
                (String) setters.get(GeneratedDatabaseTables.SecIdRoleMap.IDENTITY_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.SecIdRoleMap.ROLE_NAME).accept(data)
            );
        }

        @JsonIgnore
        @Override
        public Map<String, Object> asRawParameters()
        {
            Map<String, Object> ret = new TreeMap<>();
            ret.put("IDENTITY_NAME", identityName);
            ret.put("ROLE_NAME", roleName);
            return ret;
        }

        @JsonIgnore
        @Override
        public Object getByColumn(Column clm)
        {
            switch(clm.getName())
            {
                case "IDENTITY_NAME":
                    return identityName;
                case "ROLE_NAME":
                    return roleName;
                default:
                    throw new ImplementationError("Unknown database column. Table: " + clm.getTable().getName() + ", Column: " + clm.getName());
            }
        }

        @JsonIgnore
        @Override
        public final String getKey()
        {
            return formattedPrimaryKey;
        }

        @Override
        @JsonIgnore
        public DatabaseTable getDatabaseTable()
        {
            return GeneratedDatabaseTables.SEC_ID_ROLE_MAP;
        }
    }

    @Version(GenCrdCurrent.VERSION)
    @Group(GenCrdCurrent.GROUP)
    @Plural("secidrolemap")
    @Singular("secidrolemap")
    public static class SecIdRoleMap extends CustomResource<SecIdRoleMapSpec, Void> implements LinstorCrd<SecIdRoleMapSpec>
    {
        private static final long serialVersionUID = 7729787670302814490L;

        public SecIdRoleMap()
        {
            super();
        }

        public SecIdRoleMap(SecIdRoleMapSpec spec)
        {
            setMetadata(new ObjectMetaBuilder().withName(spec.getKey()).build());
            setSpec(spec);
        }

        @JsonIgnore
        public String getKey()
        {
            return spec.getKey();
        }
    }

    public static SecObjectProtection createSecObjectProtection(
        String objectPath,
        String creatorIdentityName,
        String ownerRoleName,
        String securityTypeName
    )
    {
        return new SecObjectProtection(
            new SecObjectProtectionSpec(
                objectPath,
                creatorIdentityName,
                ownerRoleName,
                securityTypeName
            )
        );
    }

    public static class SecObjectProtectionSpec implements LinstorSpec
    {
        @JsonIgnore private static final long serialVersionUID = 365859057959054507L;
        @JsonIgnore private static final String PK_FORMAT = "%s";

        @JsonIgnore private final String formattedPrimaryKey;

        @JsonProperty("object_path") public final String objectPath; // PK
        @JsonProperty("creator_identity_name") public final String creatorIdentityName;
        @JsonProperty("owner_role_name") public final String ownerRoleName;
        @JsonProperty("security_type_name") public final String securityTypeName;

        @JsonCreator
        public SecObjectProtectionSpec(
            @JsonProperty("object_path") String objectPathRef,
            @JsonProperty("creator_identity_name") String creatorIdentityNameRef,
            @JsonProperty("owner_role_name") String ownerRoleNameRef,
            @JsonProperty("security_type_name") String securityTypeNameRef
        )
        {
            objectPath = objectPathRef;
            creatorIdentityName = creatorIdentityNameRef;
            ownerRoleName = ownerRoleNameRef;
            securityTypeName = securityTypeNameRef;

            formattedPrimaryKey = base32Encode(String.format(
                SecObjectProtectionSpec.PK_FORMAT,
                objectPath
            ));
        }

        public final <DATA> SecObjectProtectionSpec create(
            Map<Column, ExceptionThrowingFunction<DATA, Object, AccessDeniedException>> setters,
            DATA data
        )
            throws AccessDeniedException
        {
            return new SecObjectProtectionSpec(
                (String) setters.get(GeneratedDatabaseTables.SecObjectProtection.OBJECT_PATH).accept(data),
                (String) setters.get(GeneratedDatabaseTables.SecObjectProtection.CREATOR_IDENTITY_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.SecObjectProtection.OWNER_ROLE_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.SecObjectProtection.SECURITY_TYPE_NAME).accept(data)
            );
        }

        @JsonIgnore
        @Override
        public Map<String, Object> asRawParameters()
        {
            Map<String, Object> ret = new TreeMap<>();
            ret.put("OBJECT_PATH", objectPath);
            ret.put("CREATOR_IDENTITY_NAME", creatorIdentityName);
            ret.put("OWNER_ROLE_NAME", ownerRoleName);
            ret.put("SECURITY_TYPE_NAME", securityTypeName);
            return ret;
        }

        @JsonIgnore
        @Override
        public Object getByColumn(Column clm)
        {
            switch(clm.getName())
            {
                case "OBJECT_PATH":
                    return objectPath;
                case "CREATOR_IDENTITY_NAME":
                    return creatorIdentityName;
                case "OWNER_ROLE_NAME":
                    return ownerRoleName;
                case "SECURITY_TYPE_NAME":
                    return securityTypeName;
                default:
                    throw new ImplementationError("Unknown database column. Table: " + clm.getTable().getName() + ", Column: " + clm.getName());
            }
        }

        @JsonIgnore
        @Override
        public final String getKey()
        {
            return formattedPrimaryKey;
        }

        @Override
        @JsonIgnore
        public DatabaseTable getDatabaseTable()
        {
            return GeneratedDatabaseTables.SEC_OBJECT_PROTECTION;
        }
    }

    @Version(GenCrdCurrent.VERSION)
    @Group(GenCrdCurrent.GROUP)
    @Plural("secobjectprotection")
    @Singular("secobjectprotection")
    public static class SecObjectProtection extends CustomResource<SecObjectProtectionSpec, Void> implements LinstorCrd<SecObjectProtectionSpec>
    {
        private static final long serialVersionUID = -989285762644952304L;

        public SecObjectProtection()
        {
            super();
        }

        public SecObjectProtection(SecObjectProtectionSpec spec)
        {
            setMetadata(new ObjectMetaBuilder().withName(spec.getKey()).build());
            setSpec(spec);
        }

        @JsonIgnore
        public String getKey()
        {
            return spec.getKey();
        }
    }

    public static SecRoles createSecRoles(
        String roleName,
        String roleDspName,
        String domainName,
        boolean roleEnabled,
        long rolePrivileges
    )
    {
        return new SecRoles(
            new SecRolesSpec(
                roleName,
                roleDspName,
                domainName,
                roleEnabled,
                rolePrivileges
            )
        );
    }

    public static class SecRolesSpec implements LinstorSpec
    {
        @JsonIgnore private static final long serialVersionUID = 7714797591578346290L;
        @JsonIgnore private static final String PK_FORMAT = "%s";

        @JsonIgnore private final String formattedPrimaryKey;

        @JsonProperty("role_name") public final String roleName; // PK
        @JsonProperty("role_dsp_name") public final String roleDspName;
        @JsonProperty("domain_name") public final String domainName;
        @JsonProperty("role_enabled") public final boolean roleEnabled;
        @JsonProperty("role_privileges") public final long rolePrivileges;

        @JsonCreator
        public SecRolesSpec(
            @JsonProperty("role_name") String roleNameRef,
            @JsonProperty("role_dsp_name") String roleDspNameRef,
            @JsonProperty("domain_name") String domainNameRef,
            @JsonProperty("role_enabled") boolean roleEnabledRef,
            @JsonProperty("role_privileges") long rolePrivilegesRef
        )
        {
            roleName = roleNameRef;
            roleDspName = roleDspNameRef;
            domainName = domainNameRef;
            roleEnabled = roleEnabledRef;
            rolePrivileges = rolePrivilegesRef;

            formattedPrimaryKey = base32Encode(String.format(
                SecRolesSpec.PK_FORMAT,
                roleName
            ));
        }

        public final <DATA> SecRolesSpec create(
            Map<Column, ExceptionThrowingFunction<DATA, Object, AccessDeniedException>> setters,
            DATA data
        )
            throws AccessDeniedException
        {
            return new SecRolesSpec(
                (String) setters.get(GeneratedDatabaseTables.SecRoles.ROLE_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.SecRoles.ROLE_DSP_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.SecRoles.DOMAIN_NAME).accept(data),
                (boolean) setters.get(GeneratedDatabaseTables.SecRoles.ROLE_ENABLED).accept(data),
                (long) setters.get(GeneratedDatabaseTables.SecRoles.ROLE_PRIVILEGES).accept(data)
            );
        }

        @JsonIgnore
        @Override
        public Map<String, Object> asRawParameters()
        {
            Map<String, Object> ret = new TreeMap<>();
            ret.put("ROLE_NAME", roleName);
            ret.put("ROLE_DSP_NAME", roleDspName);
            ret.put("DOMAIN_NAME", domainName);
            ret.put("ROLE_ENABLED", roleEnabled);
            ret.put("ROLE_PRIVILEGES", rolePrivileges);
            return ret;
        }

        @JsonIgnore
        @Override
        public Object getByColumn(Column clm)
        {
            switch(clm.getName())
            {
                case "ROLE_NAME":
                    return roleName;
                case "ROLE_DSP_NAME":
                    return roleDspName;
                case "DOMAIN_NAME":
                    return domainName;
                case "ROLE_ENABLED":
                    return roleEnabled;
                case "ROLE_PRIVILEGES":
                    return rolePrivileges;
                default:
                    throw new ImplementationError("Unknown database column. Table: " + clm.getTable().getName() + ", Column: " + clm.getName());
            }
        }

        @JsonIgnore
        @Override
        public final String getKey()
        {
            return formattedPrimaryKey;
        }

        @Override
        @JsonIgnore
        public DatabaseTable getDatabaseTable()
        {
            return GeneratedDatabaseTables.SEC_ROLES;
        }
    }

    @Version(GenCrdCurrent.VERSION)
    @Group(GenCrdCurrent.GROUP)
    @Plural("secroles")
    @Singular("secroles")
    public static class SecRoles extends CustomResource<SecRolesSpec, Void> implements LinstorCrd<SecRolesSpec>
    {
        private static final long serialVersionUID = 8326354634324724127L;

        public SecRoles()
        {
            super();
        }

        public SecRoles(SecRolesSpec spec)
        {
            setMetadata(new ObjectMetaBuilder().withName(spec.getKey()).build());
            setSpec(spec);
        }

        @JsonIgnore
        public String getKey()
        {
            return spec.getKey();
        }
    }

    public static SecTypes createSecTypes(
        String typeName,
        String typeDspName,
        boolean typeEnabled
    )
    {
        return new SecTypes(
            new SecTypesSpec(
                typeName,
                typeDspName,
                typeEnabled
            )
        );
    }

    public static class SecTypesSpec implements LinstorSpec
    {
        @JsonIgnore private static final long serialVersionUID = 234388807775458892L;
        @JsonIgnore private static final String PK_FORMAT = "%s";

        @JsonIgnore private final String formattedPrimaryKey;

        @JsonProperty("type_name") public final String typeName; // PK
        @JsonProperty("type_dsp_name") public final String typeDspName;
        @JsonProperty("type_enabled") public final boolean typeEnabled;

        @JsonCreator
        public SecTypesSpec(
            @JsonProperty("type_name") String typeNameRef,
            @JsonProperty("type_dsp_name") String typeDspNameRef,
            @JsonProperty("type_enabled") boolean typeEnabledRef
        )
        {
            typeName = typeNameRef;
            typeDspName = typeDspNameRef;
            typeEnabled = typeEnabledRef;

            formattedPrimaryKey = base32Encode(String.format(
                SecTypesSpec.PK_FORMAT,
                typeName
            ));
        }

        public final <DATA> SecTypesSpec create(
            Map<Column, ExceptionThrowingFunction<DATA, Object, AccessDeniedException>> setters,
            DATA data
        )
            throws AccessDeniedException
        {
            return new SecTypesSpec(
                (String) setters.get(GeneratedDatabaseTables.SecTypes.TYPE_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.SecTypes.TYPE_DSP_NAME).accept(data),
                (boolean) setters.get(GeneratedDatabaseTables.SecTypes.TYPE_ENABLED).accept(data)
            );
        }

        @JsonIgnore
        @Override
        public Map<String, Object> asRawParameters()
        {
            Map<String, Object> ret = new TreeMap<>();
            ret.put("TYPE_NAME", typeName);
            ret.put("TYPE_DSP_NAME", typeDspName);
            ret.put("TYPE_ENABLED", typeEnabled);
            return ret;
        }

        @JsonIgnore
        @Override
        public Object getByColumn(Column clm)
        {
            switch(clm.getName())
            {
                case "TYPE_NAME":
                    return typeName;
                case "TYPE_DSP_NAME":
                    return typeDspName;
                case "TYPE_ENABLED":
                    return typeEnabled;
                default:
                    throw new ImplementationError("Unknown database column. Table: " + clm.getTable().getName() + ", Column: " + clm.getName());
            }
        }

        @JsonIgnore
        @Override
        public final String getKey()
        {
            return formattedPrimaryKey;
        }

        @Override
        @JsonIgnore
        public DatabaseTable getDatabaseTable()
        {
            return GeneratedDatabaseTables.SEC_TYPES;
        }
    }

    @Version(GenCrdCurrent.VERSION)
    @Group(GenCrdCurrent.GROUP)
    @Plural("sectypes")
    @Singular("sectypes")
    public static class SecTypes extends CustomResource<SecTypesSpec, Void> implements LinstorCrd<SecTypesSpec>
    {
        private static final long serialVersionUID = -1583174251162353732L;

        public SecTypes()
        {
            super();
        }

        public SecTypes(SecTypesSpec spec)
        {
            setMetadata(new ObjectMetaBuilder().withName(spec.getKey()).build());
            setSpec(spec);
        }

        @JsonIgnore
        public String getKey()
        {
            return spec.getKey();
        }
    }

    public static SecTypeRules createSecTypeRules(
        String domainName,
        String typeName,
        short accessType
    )
    {
        return new SecTypeRules(
            new SecTypeRulesSpec(
                domainName,
                typeName,
                accessType
            )
        );
    }

    public static class SecTypeRulesSpec implements LinstorSpec
    {
        @JsonIgnore private static final long serialVersionUID = 2231285451984617884L;
        @JsonIgnore private static final String PK_FORMAT = "%s:%s";

        @JsonIgnore private final String formattedPrimaryKey;

        @JsonProperty("domain_name") public final String domainName; // PK
        @JsonProperty("type_name") public final String typeName; // PK
        @JsonProperty("access_type") public final short accessType;

        @JsonCreator
        public SecTypeRulesSpec(
            @JsonProperty("domain_name") String domainNameRef,
            @JsonProperty("type_name") String typeNameRef,
            @JsonProperty("access_type") short accessTypeRef
        )
        {
            domainName = domainNameRef;
            typeName = typeNameRef;
            accessType = accessTypeRef;

            formattedPrimaryKey = base32Encode(String.format(
                SecTypeRulesSpec.PK_FORMAT,
                domainName,
                typeName
            ));
        }

        public final <DATA> SecTypeRulesSpec create(
            Map<Column, ExceptionThrowingFunction<DATA, Object, AccessDeniedException>> setters,
            DATA data
        )
            throws AccessDeniedException
        {
            return new SecTypeRulesSpec(
                (String) setters.get(GeneratedDatabaseTables.SecTypeRules.DOMAIN_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.SecTypeRules.TYPE_NAME).accept(data),
                (short) setters.get(GeneratedDatabaseTables.SecTypeRules.ACCESS_TYPE).accept(data)
            );
        }

        @JsonIgnore
        @Override
        public Map<String, Object> asRawParameters()
        {
            Map<String, Object> ret = new TreeMap<>();
            ret.put("DOMAIN_NAME", domainName);
            ret.put("TYPE_NAME", typeName);
            ret.put("ACCESS_TYPE", accessType);
            return ret;
        }

        @JsonIgnore
        @Override
        public Object getByColumn(Column clm)
        {
            switch(clm.getName())
            {
                case "DOMAIN_NAME":
                    return domainName;
                case "TYPE_NAME":
                    return typeName;
                case "ACCESS_TYPE":
                    return accessType;
                default:
                    throw new ImplementationError("Unknown database column. Table: " + clm.getTable().getName() + ", Column: " + clm.getName());
            }
        }

        @JsonIgnore
        @Override
        public final String getKey()
        {
            return formattedPrimaryKey;
        }

        @Override
        @JsonIgnore
        public DatabaseTable getDatabaseTable()
        {
            return GeneratedDatabaseTables.SEC_TYPE_RULES;
        }
    }

    @Version(GenCrdCurrent.VERSION)
    @Group(GenCrdCurrent.GROUP)
    @Plural("sectyperules")
    @Singular("sectyperules")
    public static class SecTypeRules extends CustomResource<SecTypeRulesSpec, Void> implements LinstorCrd<SecTypeRulesSpec>
    {
        private static final long serialVersionUID = 1789391619603076434L;

        public SecTypeRules()
        {
            super();
        }

        public SecTypeRules(SecTypeRulesSpec spec)
        {
            setMetadata(new ObjectMetaBuilder().withName(spec.getKey()).build());
            setSpec(spec);
        }

        @JsonIgnore
        public String getKey()
        {
            return spec.getKey();
        }
    }

    public static SpaceHistory createSpaceHistory(
        Date entryDate,
        byte[] capacity
    )
    {
        return new SpaceHistory(
            new SpaceHistorySpec(
                entryDate,
                capacity
            )
        );
    }

    public static class SpaceHistorySpec implements LinstorSpec
    {
        @JsonIgnore private static final long serialVersionUID = -835286477031084777L;
        @JsonIgnore private static final String PK_FORMAT = "%s";

        @JsonIgnore private final String formattedPrimaryKey;

        @JsonProperty("entry_date") public final Date entryDate; // PK
        @JsonProperty("capacity") public final byte[] capacity;

        @JsonCreator
        public SpaceHistorySpec(
            @JsonProperty("entry_date") Date entryDateRef,
            @JsonProperty("capacity") byte[] capacityRef
        )
        {
            entryDate = entryDateRef;
            capacity = capacityRef;

            formattedPrimaryKey = base32Encode(String.format(
                SpaceHistorySpec.PK_FORMAT,
                entryDate
            ));
        }

        public final <DATA> SpaceHistorySpec create(
            Map<Column, ExceptionThrowingFunction<DATA, Object, AccessDeniedException>> setters,
            DATA data
        )
            throws AccessDeniedException
        {
            return new SpaceHistorySpec(
                (Date) setters.get(GeneratedDatabaseTables.SpaceHistory.ENTRY_DATE).accept(data),
                (byte[]) setters.get(GeneratedDatabaseTables.SpaceHistory.CAPACITY).accept(data)
            );
        }

        @JsonIgnore
        @Override
        public Map<String, Object> asRawParameters()
        {
            Map<String, Object> ret = new TreeMap<>();
            ret.put("ENTRY_DATE", entryDate);
            ret.put("CAPACITY", capacity);
            return ret;
        }

        @JsonIgnore
        @Override
        public Object getByColumn(Column clm)
        {
            switch(clm.getName())
            {
                case "ENTRY_DATE":
                    return entryDate;
                case "CAPACITY":
                    return capacity;
                default:
                    throw new ImplementationError("Unknown database column. Table: " + clm.getTable().getName() + ", Column: " + clm.getName());
            }
        }

        @JsonIgnore
        @Override
        public final String getKey()
        {
            return formattedPrimaryKey;
        }

        @Override
        @JsonIgnore
        public DatabaseTable getDatabaseTable()
        {
            return GeneratedDatabaseTables.SPACE_HISTORY;
        }
    }

    @Version(GenCrdCurrent.VERSION)
    @Group(GenCrdCurrent.GROUP)
    @Plural("spacehistory")
    @Singular("spacehistory")
    public static class SpaceHistory extends CustomResource<SpaceHistorySpec, Void> implements LinstorCrd<SpaceHistorySpec>
    {
        private static final long serialVersionUID = 3909147273139095178L;

        public SpaceHistory()
        {
            super();
        }

        public SpaceHistory(SpaceHistorySpec spec)
        {
            setMetadata(new ObjectMetaBuilder().withName(spec.getKey()).build());
            setSpec(spec);
        }

        @JsonIgnore
        public String getKey()
        {
            return spec.getKey();
        }
    }

    public static StorPoolDefinitions createStorPoolDefinitions(
        String uuid,
        String poolName,
        String poolDspName
    )
    {
        return new StorPoolDefinitions(
            new StorPoolDefinitionsSpec(
                uuid,
                poolName,
                poolDspName
            )
        );
    }

    public static class StorPoolDefinitionsSpec implements LinstorSpec
    {
        @JsonIgnore private static final long serialVersionUID = -1235608243955673111L;
        @JsonIgnore private static final String PK_FORMAT = "%s";

        @JsonIgnore private final String formattedPrimaryKey;

        @JsonProperty("uuid") public final String uuid;
        @JsonProperty("pool_name") public final String poolName; // PK
        @JsonProperty("pool_dsp_name") public final String poolDspName;

        @JsonCreator
        public StorPoolDefinitionsSpec(
            @JsonProperty("uuid") String uuidRef,
            @JsonProperty("pool_name") String poolNameRef,
            @JsonProperty("pool_dsp_name") String poolDspNameRef
        )
        {
            uuid = uuidRef;
            poolName = poolNameRef;
            poolDspName = poolDspNameRef;

            formattedPrimaryKey = base32Encode(String.format(
                StorPoolDefinitionsSpec.PK_FORMAT,
                poolName
            ));
        }

        public final <DATA> StorPoolDefinitionsSpec create(
            Map<Column, ExceptionThrowingFunction<DATA, Object, AccessDeniedException>> setters,
            DATA data
        )
            throws AccessDeniedException
        {
            return new StorPoolDefinitionsSpec(
                (String) setters.get(GeneratedDatabaseTables.StorPoolDefinitions.UUID).accept(data),
                (String) setters.get(GeneratedDatabaseTables.StorPoolDefinitions.POOL_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.StorPoolDefinitions.POOL_DSP_NAME).accept(data)
            );
        }

        @JsonIgnore
        @Override
        public Map<String, Object> asRawParameters()
        {
            Map<String, Object> ret = new TreeMap<>();
            ret.put("UUID", uuid);
            ret.put("POOL_NAME", poolName);
            ret.put("POOL_DSP_NAME", poolDspName);
            return ret;
        }

        @JsonIgnore
        @Override
        public Object getByColumn(Column clm)
        {
            switch(clm.getName())
            {
                case "UUID":
                    return uuid;
                case "POOL_NAME":
                    return poolName;
                case "POOL_DSP_NAME":
                    return poolDspName;
                default:
                    throw new ImplementationError("Unknown database column. Table: " + clm.getTable().getName() + ", Column: " + clm.getName());
            }
        }

        @JsonIgnore
        @Override
        public final String getKey()
        {
            return formattedPrimaryKey;
        }

        @Override
        @JsonIgnore
        public DatabaseTable getDatabaseTable()
        {
            return GeneratedDatabaseTables.STOR_POOL_DEFINITIONS;
        }
    }

    @Version(GenCrdCurrent.VERSION)
    @Group(GenCrdCurrent.GROUP)
    @Plural("storpooldefinitions")
    @Singular("storpooldefinitions")
    public static class StorPoolDefinitions extends CustomResource<StorPoolDefinitionsSpec, Void> implements LinstorCrd<StorPoolDefinitionsSpec>
    {
        private static final long serialVersionUID = 3096536508531979085L;

        public StorPoolDefinitions()
        {
            super();
        }

        public StorPoolDefinitions(StorPoolDefinitionsSpec spec)
        {
            setMetadata(new ObjectMetaBuilder().withName(spec.getKey()).build());
            setSpec(spec);
        }

        @JsonIgnore
        public String getKey()
        {
            return spec.getKey();
        }
    }

    public static TrackingDate createTrackingDate(
        Date entryDate
    )
    {
        return new TrackingDate(
            new TrackingDateSpec(
                entryDate
            )
        );
    }

    public static class TrackingDateSpec implements LinstorSpec
    {
        @JsonIgnore private static final long serialVersionUID = 5344676886222908825L;
        @JsonIgnore private static final String PK_FORMAT = "%s";

        @JsonIgnore private final String formattedPrimaryKey;

        @JsonProperty("entry_date") public final Date entryDate;

        @JsonCreator
        public TrackingDateSpec(
            @JsonProperty("entry_date") Date entryDateRef
        )
        {
            entryDate = entryDateRef;

            formattedPrimaryKey = base32Encode(String.format(
                TrackingDateSpec.PK_FORMAT
            ));
        }

        public final <DATA> TrackingDateSpec create(
            Map<Column, ExceptionThrowingFunction<DATA, Object, AccessDeniedException>> setters,
            DATA data
        )
            throws AccessDeniedException
        {
            return new TrackingDateSpec(
                (Date) setters.get(GeneratedDatabaseTables.TrackingDate.ENTRY_DATE).accept(data)
            );
        }

        @JsonIgnore
        @Override
        public Map<String, Object> asRawParameters()
        {
            Map<String, Object> ret = new TreeMap<>();
            ret.put("ENTRY_DATE", entryDate);
            return ret;
        }

        @JsonIgnore
        @Override
        public Object getByColumn(Column clm)
        {
            switch(clm.getName())
            {
                case "ENTRY_DATE":
                    return entryDate;
                default:
                    throw new ImplementationError("Unknown database column. Table: " + clm.getTable().getName() + ", Column: " + clm.getName());
            }
        }

        @JsonIgnore
        @Override
        public final String getKey()
        {
            return formattedPrimaryKey;
        }

        @Override
        @JsonIgnore
        public DatabaseTable getDatabaseTable()
        {
            return GeneratedDatabaseTables.TRACKING_DATE;
        }
    }

    @Version(GenCrdCurrent.VERSION)
    @Group(GenCrdCurrent.GROUP)
    @Plural("trackingdate")
    @Singular("trackingdate")
    public static class TrackingDate extends CustomResource<TrackingDateSpec, Void> implements LinstorCrd<TrackingDateSpec>
    {
        private static final long serialVersionUID = -9214502177686572282L;

        public TrackingDate()
        {
            super();
        }

        public TrackingDate(TrackingDateSpec spec)
        {
            setMetadata(new ObjectMetaBuilder().withName(spec.getKey()).build());
            setSpec(spec);
        }

        @JsonIgnore
        public String getKey()
        {
            return spec.getKey();
        }
    }

    public static Volumes createVolumes(
        String uuid,
        String nodeName,
        String resourceName,
        String snapshotName,
        int vlmNr,
        long vlmFlags
    )
    {
        return new Volumes(
            new VolumesSpec(
                uuid,
                nodeName,
                resourceName,
                snapshotName,
                vlmNr,
                vlmFlags
            )
        );
    }

    public static class VolumesSpec implements LinstorSpec
    {
        @JsonIgnore private static final long serialVersionUID = 2853580310863792490L;
        @JsonIgnore private static final String PK_FORMAT = "%s:%s:%s:%d";

        @JsonIgnore private final String formattedPrimaryKey;

        @JsonProperty("uuid") public final String uuid;
        @JsonProperty("node_name") public final String nodeName; // PK
        @JsonProperty("resource_name") public final String resourceName; // PK
        @JsonProperty("snapshot_name") public final String snapshotName; // PK
        @JsonProperty("vlm_nr") public final int vlmNr; // PK
        @JsonProperty("vlm_flags") public final long vlmFlags;

        @JsonCreator
        public VolumesSpec(
            @JsonProperty("uuid") String uuidRef,
            @JsonProperty("node_name") String nodeNameRef,
            @JsonProperty("resource_name") String resourceNameRef,
            @JsonProperty("snapshot_name") String snapshotNameRef,
            @JsonProperty("vlm_nr") int vlmNrRef,
            @JsonProperty("vlm_flags") long vlmFlagsRef
        )
        {
            uuid = uuidRef;
            nodeName = nodeNameRef;
            resourceName = resourceNameRef;
            snapshotName = snapshotNameRef;
            vlmNr = vlmNrRef;
            vlmFlags = vlmFlagsRef;

            formattedPrimaryKey = base32Encode(String.format(
                VolumesSpec.PK_FORMAT,
                nodeName,
                resourceName,
                snapshotName,
                vlmNr
            ));
        }

        public final <DATA> VolumesSpec create(
            Map<Column, ExceptionThrowingFunction<DATA, Object, AccessDeniedException>> setters,
            DATA data
        )
            throws AccessDeniedException
        {
            return new VolumesSpec(
                (String) setters.get(GeneratedDatabaseTables.Volumes.UUID).accept(data),
                (String) setters.get(GeneratedDatabaseTables.Volumes.NODE_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.Volumes.RESOURCE_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.Volumes.SNAPSHOT_NAME).accept(data),
                (int) setters.get(GeneratedDatabaseTables.Volumes.VLM_NR).accept(data),
                (long) setters.get(GeneratedDatabaseTables.Volumes.VLM_FLAGS).accept(data)
            );
        }

        @JsonIgnore
        @Override
        public Map<String, Object> asRawParameters()
        {
            Map<String, Object> ret = new TreeMap<>();
            ret.put("UUID", uuid);
            ret.put("NODE_NAME", nodeName);
            ret.put("RESOURCE_NAME", resourceName);
            ret.put("SNAPSHOT_NAME", snapshotName);
            ret.put("VLM_NR", vlmNr);
            ret.put("VLM_FLAGS", vlmFlags);
            return ret;
        }

        @JsonIgnore
        @Override
        public Object getByColumn(Column clm)
        {
            switch(clm.getName())
            {
                case "UUID":
                    return uuid;
                case "NODE_NAME":
                    return nodeName;
                case "RESOURCE_NAME":
                    return resourceName;
                case "SNAPSHOT_NAME":
                    return snapshotName;
                case "VLM_NR":
                    return vlmNr;
                case "VLM_FLAGS":
                    return vlmFlags;
                default:
                    throw new ImplementationError("Unknown database column. Table: " + clm.getTable().getName() + ", Column: " + clm.getName());
            }
        }

        @JsonIgnore
        @Override
        public final String getKey()
        {
            return formattedPrimaryKey;
        }

        @Override
        @JsonIgnore
        public DatabaseTable getDatabaseTable()
        {
            return GeneratedDatabaseTables.VOLUMES;
        }
    }

    @Version(GenCrdCurrent.VERSION)
    @Group(GenCrdCurrent.GROUP)
    @Plural("volumes")
    @Singular("volumes")
    public static class Volumes extends CustomResource<VolumesSpec, Void> implements LinstorCrd<VolumesSpec>
    {
        private static final long serialVersionUID = -978326389351866196L;

        public Volumes()
        {
            super();
        }

        public Volumes(VolumesSpec spec)
        {
            setMetadata(new ObjectMetaBuilder().withName(spec.getKey()).build());
            setSpec(spec);
        }

        @JsonIgnore
        public String getKey()
        {
            return spec.getKey();
        }
    }

    public static VolumeConnections createVolumeConnections(
        String uuid,
        String nodeNameSrc,
        String nodeNameDst,
        String resourceName,
        String snapshotName,
        int vlmNr
    )
    {
        return new VolumeConnections(
            new VolumeConnectionsSpec(
                uuid,
                nodeNameSrc,
                nodeNameDst,
                resourceName,
                snapshotName,
                vlmNr
            )
        );
    }

    public static class VolumeConnectionsSpec implements LinstorSpec
    {
        @JsonIgnore private static final long serialVersionUID = 5005490779644349444L;
        @JsonIgnore private static final String PK_FORMAT = "%s:%s:%s:%s:%d";

        @JsonIgnore private final String formattedPrimaryKey;

        @JsonProperty("uuid") public final String uuid;
        @JsonProperty("node_name_src") public final String nodeNameSrc; // PK
        @JsonProperty("node_name_dst") public final String nodeNameDst; // PK
        @JsonProperty("resource_name") public final String resourceName; // PK
        @JsonProperty("snapshot_name") public final String snapshotName; // PK
        @JsonProperty("vlm_nr") public final int vlmNr; // PK

        @JsonCreator
        public VolumeConnectionsSpec(
            @JsonProperty("uuid") String uuidRef,
            @JsonProperty("node_name_src") String nodeNameSrcRef,
            @JsonProperty("node_name_dst") String nodeNameDstRef,
            @JsonProperty("resource_name") String resourceNameRef,
            @JsonProperty("snapshot_name") String snapshotNameRef,
            @JsonProperty("vlm_nr") int vlmNrRef
        )
        {
            uuid = uuidRef;
            nodeNameSrc = nodeNameSrcRef;
            nodeNameDst = nodeNameDstRef;
            resourceName = resourceNameRef;
            snapshotName = snapshotNameRef;
            vlmNr = vlmNrRef;

            formattedPrimaryKey = base32Encode(String.format(
                VolumeConnectionsSpec.PK_FORMAT,
                nodeNameSrc,
                nodeNameDst,
                resourceName,
                snapshotName,
                vlmNr
            ));
        }

        public final <DATA> VolumeConnectionsSpec create(
            Map<Column, ExceptionThrowingFunction<DATA, Object, AccessDeniedException>> setters,
            DATA data
        )
            throws AccessDeniedException
        {
            return new VolumeConnectionsSpec(
                (String) setters.get(GeneratedDatabaseTables.VolumeConnections.UUID).accept(data),
                (String) setters.get(GeneratedDatabaseTables.VolumeConnections.NODE_NAME_SRC).accept(data),
                (String) setters.get(GeneratedDatabaseTables.VolumeConnections.NODE_NAME_DST).accept(data),
                (String) setters.get(GeneratedDatabaseTables.VolumeConnections.RESOURCE_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.VolumeConnections.SNAPSHOT_NAME).accept(data),
                (int) setters.get(GeneratedDatabaseTables.VolumeConnections.VLM_NR).accept(data)
            );
        }

        @JsonIgnore
        @Override
        public Map<String, Object> asRawParameters()
        {
            Map<String, Object> ret = new TreeMap<>();
            ret.put("UUID", uuid);
            ret.put("NODE_NAME_SRC", nodeNameSrc);
            ret.put("NODE_NAME_DST", nodeNameDst);
            ret.put("RESOURCE_NAME", resourceName);
            ret.put("SNAPSHOT_NAME", snapshotName);
            ret.put("VLM_NR", vlmNr);
            return ret;
        }

        @JsonIgnore
        @Override
        public Object getByColumn(Column clm)
        {
            switch(clm.getName())
            {
                case "UUID":
                    return uuid;
                case "NODE_NAME_SRC":
                    return nodeNameSrc;
                case "NODE_NAME_DST":
                    return nodeNameDst;
                case "RESOURCE_NAME":
                    return resourceName;
                case "SNAPSHOT_NAME":
                    return snapshotName;
                case "VLM_NR":
                    return vlmNr;
                default:
                    throw new ImplementationError("Unknown database column. Table: " + clm.getTable().getName() + ", Column: " + clm.getName());
            }
        }

        @JsonIgnore
        @Override
        public final String getKey()
        {
            return formattedPrimaryKey;
        }

        @Override
        @JsonIgnore
        public DatabaseTable getDatabaseTable()
        {
            return GeneratedDatabaseTables.VOLUME_CONNECTIONS;
        }
    }

    @Version(GenCrdCurrent.VERSION)
    @Group(GenCrdCurrent.GROUP)
    @Plural("volumeconnections")
    @Singular("volumeconnections")
    public static class VolumeConnections extends CustomResource<VolumeConnectionsSpec, Void> implements LinstorCrd<VolumeConnectionsSpec>
    {
        private static final long serialVersionUID = -3344982034186640641L;

        public VolumeConnections()
        {
            super();
        }

        public VolumeConnections(VolumeConnectionsSpec spec)
        {
            setMetadata(new ObjectMetaBuilder().withName(spec.getKey()).build());
            setSpec(spec);
        }

        @JsonIgnore
        public String getKey()
        {
            return spec.getKey();
        }
    }

    public static VolumeDefinitions createVolumeDefinitions(
        String uuid,
        String resourceName,
        String snapshotName,
        int vlmNr,
        long vlmSize,
        long vlmFlags
    )
    {
        return new VolumeDefinitions(
            new VolumeDefinitionsSpec(
                uuid,
                resourceName,
                snapshotName,
                vlmNr,
                vlmSize,
                vlmFlags
            )
        );
    }

    public static class VolumeDefinitionsSpec implements LinstorSpec
    {
        @JsonIgnore private static final long serialVersionUID = -7891841234159474860L;
        @JsonIgnore private static final String PK_FORMAT = "%s:%s:%d";

        @JsonIgnore private final String formattedPrimaryKey;

        @JsonProperty("uuid") public final String uuid;
        @JsonProperty("resource_name") public final String resourceName; // PK
        @JsonProperty("snapshot_name") public final String snapshotName; // PK
        @JsonProperty("vlm_nr") public final int vlmNr; // PK
        @JsonProperty("vlm_size") public final long vlmSize;
        @JsonProperty("vlm_flags") public final long vlmFlags;

        @JsonCreator
        public VolumeDefinitionsSpec(
            @JsonProperty("uuid") String uuidRef,
            @JsonProperty("resource_name") String resourceNameRef,
            @JsonProperty("snapshot_name") String snapshotNameRef,
            @JsonProperty("vlm_nr") int vlmNrRef,
            @JsonProperty("vlm_size") long vlmSizeRef,
            @JsonProperty("vlm_flags") long vlmFlagsRef
        )
        {
            uuid = uuidRef;
            resourceName = resourceNameRef;
            snapshotName = snapshotNameRef;
            vlmNr = vlmNrRef;
            vlmSize = vlmSizeRef;
            vlmFlags = vlmFlagsRef;

            formattedPrimaryKey = base32Encode(String.format(
                VolumeDefinitionsSpec.PK_FORMAT,
                resourceName,
                snapshotName,
                vlmNr
            ));
        }

        public final <DATA> VolumeDefinitionsSpec create(
            Map<Column, ExceptionThrowingFunction<DATA, Object, AccessDeniedException>> setters,
            DATA data
        )
            throws AccessDeniedException
        {
            return new VolumeDefinitionsSpec(
                (String) setters.get(GeneratedDatabaseTables.VolumeDefinitions.UUID).accept(data),
                (String) setters.get(GeneratedDatabaseTables.VolumeDefinitions.RESOURCE_NAME).accept(data),
                (String) setters.get(GeneratedDatabaseTables.VolumeDefinitions.SNAPSHOT_NAME).accept(data),
                (int) setters.get(GeneratedDatabaseTables.VolumeDefinitions.VLM_NR).accept(data),
                (long) setters.get(GeneratedDatabaseTables.VolumeDefinitions.VLM_SIZE).accept(data),
                (long) setters.get(GeneratedDatabaseTables.VolumeDefinitions.VLM_FLAGS).accept(data)
            );
        }

        @JsonIgnore
        @Override
        public Map<String, Object> asRawParameters()
        {
            Map<String, Object> ret = new TreeMap<>();
            ret.put("UUID", uuid);
            ret.put("RESOURCE_NAME", resourceName);
            ret.put("SNAPSHOT_NAME", snapshotName);
            ret.put("VLM_NR", vlmNr);
            ret.put("VLM_SIZE", vlmSize);
            ret.put("VLM_FLAGS", vlmFlags);
            return ret;
        }

        @JsonIgnore
        @Override
        public Object getByColumn(Column clm)
        {
            switch(clm.getName())
            {
                case "UUID":
                    return uuid;
                case "RESOURCE_NAME":
                    return resourceName;
                case "SNAPSHOT_NAME":
                    return snapshotName;
                case "VLM_NR":
                    return vlmNr;
                case "VLM_SIZE":
                    return vlmSize;
                case "VLM_FLAGS":
                    return vlmFlags;
                default:
                    throw new ImplementationError("Unknown database column. Table: " + clm.getTable().getName() + ", Column: " + clm.getName());
            }
        }

        @JsonIgnore
        @Override
        public final String getKey()
        {
            return formattedPrimaryKey;
        }

        @Override
        @JsonIgnore
        public DatabaseTable getDatabaseTable()
        {
            return GeneratedDatabaseTables.VOLUME_DEFINITIONS;
        }
    }

    @Version(GenCrdCurrent.VERSION)
    @Group(GenCrdCurrent.GROUP)
    @Plural("volumedefinitions")
    @Singular("volumedefinitions")
    public static class VolumeDefinitions extends CustomResource<VolumeDefinitionsSpec, Void> implements LinstorCrd<VolumeDefinitionsSpec>
    {
        private static final long serialVersionUID = -3333891334858164975L;

        public VolumeDefinitions()
        {
            super();
        }

        public VolumeDefinitions(VolumeDefinitionsSpec spec)
        {
            setMetadata(new ObjectMetaBuilder().withName(spec.getKey()).build());
            setSpec(spec);
        }

        @JsonIgnore
        public String getKey()
        {
            return spec.getKey();
        }
    }

    public static VolumeGroups createVolumeGroups(
        String uuid,
        String resourceGroupName,
        int vlmNr,
        long flags
    )
    {
        return new VolumeGroups(
            new VolumeGroupsSpec(
                uuid,
                resourceGroupName,
                vlmNr,
                flags
            )
        );
    }

    public static class VolumeGroupsSpec implements LinstorSpec
    {
        @JsonIgnore private static final long serialVersionUID = -5602493521231706961L;
        @JsonIgnore private static final String PK_FORMAT = "%s:%d";

        @JsonIgnore private final String formattedPrimaryKey;

        @JsonProperty("uuid") public final String uuid;
        @JsonProperty("resource_group_name") public final String resourceGroupName; // PK
        @JsonProperty("vlm_nr") public final int vlmNr; // PK
        @JsonProperty("flags") public final long flags;

        @JsonCreator
        public VolumeGroupsSpec(
            @JsonProperty("uuid") String uuidRef,
            @JsonProperty("resource_group_name") String resourceGroupNameRef,
            @JsonProperty("vlm_nr") int vlmNrRef,
            @JsonProperty("flags") long flagsRef
        )
        {
            uuid = uuidRef;
            resourceGroupName = resourceGroupNameRef;
            vlmNr = vlmNrRef;
            flags = flagsRef;

            formattedPrimaryKey = base32Encode(String.format(
                VolumeGroupsSpec.PK_FORMAT,
                resourceGroupName,
                vlmNr
            ));
        }

        public final <DATA> VolumeGroupsSpec create(
            Map<Column, ExceptionThrowingFunction<DATA, Object, AccessDeniedException>> setters,
            DATA data
        )
            throws AccessDeniedException
        {
            return new VolumeGroupsSpec(
                (String) setters.get(GeneratedDatabaseTables.VolumeGroups.UUID).accept(data),
                (String) setters.get(GeneratedDatabaseTables.VolumeGroups.RESOURCE_GROUP_NAME).accept(data),
                (int) setters.get(GeneratedDatabaseTables.VolumeGroups.VLM_NR).accept(data),
                (long) setters.get(GeneratedDatabaseTables.VolumeGroups.FLAGS).accept(data)
            );
        }

        @JsonIgnore
        @Override
        public Map<String, Object> asRawParameters()
        {
            Map<String, Object> ret = new TreeMap<>();
            ret.put("UUID", uuid);
            ret.put("RESOURCE_GROUP_NAME", resourceGroupName);
            ret.put("VLM_NR", vlmNr);
            ret.put("FLAGS", flags);
            return ret;
        }

        @JsonIgnore
        @Override
        public Object getByColumn(Column clm)
        {
            switch(clm.getName())
            {
                case "UUID":
                    return uuid;
                case "RESOURCE_GROUP_NAME":
                    return resourceGroupName;
                case "VLM_NR":
                    return vlmNr;
                case "FLAGS":
                    return flags;
                default:
                    throw new ImplementationError("Unknown database column. Table: " + clm.getTable().getName() + ", Column: " + clm.getName());
            }
        }

        @JsonIgnore
        @Override
        public final String getKey()
        {
            return formattedPrimaryKey;
        }

        @Override
        @JsonIgnore
        public DatabaseTable getDatabaseTable()
        {
            return GeneratedDatabaseTables.VOLUME_GROUPS;
        }
    }

    @Version(GenCrdCurrent.VERSION)
    @Group(GenCrdCurrent.GROUP)
    @Plural("volumegroups")
    @Singular("volumegroups")
    public static class VolumeGroups extends CustomResource<VolumeGroupsSpec, Void> implements LinstorCrd<VolumeGroupsSpec>
    {
        private static final long serialVersionUID = 1077436269860107015L;

        public VolumeGroups()
        {
            super();
        }

        public VolumeGroups(VolumeGroupsSpec spec)
        {
            setMetadata(new ObjectMetaBuilder().withName(spec.getKey()).build());
            setSpec(spec);
        }

        @JsonIgnore
        public String getKey()
        {
            return spec.getKey();
        }
    }

    private static final String base32Encode(String format, Object... args)
    {
        String stringToEncode = String.format(format, args);
        return new BigInteger(1, stringToEncode.getBytes(StandardCharsets.UTF_8)).toString(32);
    }
}