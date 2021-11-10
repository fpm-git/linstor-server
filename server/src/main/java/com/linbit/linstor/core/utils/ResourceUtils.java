package com.linbit.linstor.core.utils;

import com.linbit.linstor.core.objects.Resource;
import com.linbit.linstor.core.objects.ResourceDefinition;
import com.linbit.linstor.security.AccessContext;
import com.linbit.linstor.security.AccessDeniedException;
import com.linbit.utils.ExceptionThrowingPredicate;

import java.util.HashSet;
import java.util.Iterator;

public class ResourceUtils
{
    public static HashSet<Resource> filterResourcesDiskless(ResourceDefinition rscDfn, AccessContext accCtx)
        throws AccessDeniedException
    {
        return filterResources(rscDfn, accCtx, rsc -> rsc.getStateFlags().isSet(accCtx, Resource.Flags.DISKLESS));
    }

    public static HashSet<Resource> filterResourcesDrbdDiskless(ResourceDefinition rscDfn, AccessContext accCtx)
        throws AccessDeniedException
    {
        return filterResources(rscDfn, accCtx, rsc -> rsc.getStateFlags().isSet(accCtx, Resource.Flags.DRBD_DISKLESS));
    }

    public static HashSet<Resource> filterResources(
        ResourceDefinition rscDfn,
        AccessContext accCtx,
        ExceptionThrowingPredicate<Resource, AccessDeniedException> filter
    )
        throws AccessDeniedException
    {
        HashSet<Resource> ret = new HashSet<>();

        Iterator<Resource> rscIt = rscDfn.iterateResource(accCtx);
        while (rscIt.hasNext())
        {
            Resource rsc = rscIt.next();
            if (filter.test(rsc))
            {
                ret.add(rsc);
            }
        }

        return ret;
    }
}
