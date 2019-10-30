package com.linbit.linstor.utils.layer;

import com.linbit.linstor.api.ApiConsts;
import com.linbit.linstor.core.apicallhandler.response.ApiAccessDeniedException;
import com.linbit.linstor.core.identifier.VolumeNumber;
import com.linbit.linstor.core.objects.StorPool;
import com.linbit.linstor.core.objects.Volume;
import com.linbit.linstor.security.AccessContext;
import com.linbit.linstor.security.AccessDeniedException;
import com.linbit.linstor.storage.interfaces.categories.resource.RscLayerObject;
import com.linbit.linstor.storage.interfaces.categories.resource.VlmProviderObject;
import com.linbit.linstor.storage.kinds.DeviceLayerKind;
import com.linbit.linstor.storage.utils.LayerUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Function;

public class LayerVlmUtils
{
    public static Set<StorPool> getStorPoolSet(Volume vlm, AccessContext accCtx)
        throws AccessDeniedException
    {

        VolumeNumber vlmNr = vlm.getVolumeDefinition().getVolumeNumber();

        Set<RscLayerObject> storageRscDataSet = LayerRscUtils.getRscDataByProvider(
            vlm.getResource().getLayerData(accCtx),
            DeviceLayerKind.STORAGE
        );
        return getStoragePools(vlmNr, storageRscDataSet);
    }

    private static Set<StorPool> getStoragePools(VolumeNumber vlmNr, Set<RscLayerObject> storageRscDataSet)
    {
        Set<StorPool> storPools = new TreeSet<>();
        for (RscLayerObject rscData : storageRscDataSet)
        {
            VlmProviderObject vlmProviderObject = rscData.getVlmProviderObject(vlmNr);
            if (vlmProviderObject != null)
            {
                /*
                 *  vlmProviderObject is null in the following usecase:
                 *
                 *  DRBD with 2 volumes,
                 *      one has external meta-data, the other has internal
                 *
                 *  this will create 2 STORAGE resources ("", and ".meta")
                 *      "" will have 2 vlmProviderObjects (as usual
                 *      ".meta" will only have 1 vlmProviderObject, as the other has internal metadata
                 */
                storPools.add(vlmProviderObject.getStorPool());
            }
        }
        return storPools;
    }

    public static Set<StorPool> getStorPoolSet(VlmProviderObject vlmData, AccessContext accCtx)
    {
        return getStoragePools(
            vlmData.getVlmNr(), LayerRscUtils.getRscDataByProvider(
            vlmData.getRscLayerObject(),
                DeviceLayerKind.STORAGE
            )
        );
    }

    public static Map<String, StorPool> getStorPoolMap(
        Volume vlm,
        AccessContext accCtx,
        Function<Volume, String> vlmToString
    )
    {
        Map<String, StorPool> storPoolMap = new TreeMap<>();
        try
        {
            List<RscLayerObject> storageRscList = LayerUtils.getChildLayerDataByKind(
                vlm.getResource().getLayerData(accCtx),
                DeviceLayerKind.STORAGE
            );
            VolumeNumber vlmNr = vlm.getVolumeDefinition().getVolumeNumber();
            for (RscLayerObject storageRsc : storageRscList)
            {
                storPoolMap.put(
                    storageRsc.getResourceNameSuffix(),
                    storageRsc.getVlmProviderObject(vlmNr).getStorPool()
                );
            }
        }
        catch (AccessDeniedException accDeniedExc)
        {
            throw new ApiAccessDeniedException(
                accDeniedExc,
                "get storage pool of " + vlmToString.apply(vlm),
                ApiConsts.FAIL_ACC_DENIED_VLM
            );
        }
        return storPoolMap;
    }

    private LayerVlmUtils()
    {
    }

}
