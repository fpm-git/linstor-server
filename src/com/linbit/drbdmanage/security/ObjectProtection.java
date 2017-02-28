package com.linbit.drbdmanage.security;

import com.linbit.ErrorCheck;
import com.linbit.ImplementationError;
import com.linbit.drbdmanage.InvalidNameException;

/**
 * Security protection for drbdmanageNG object
 *
 * @author Robert Altnoeder &lt;robert.altnoeder@linbit.com&gt;
 */
public final class ObjectProtection
{
    public static final String DEFAULT_SECTYPE_NAME = "default";

    // Identity that created the object
    //
    // The creator's identity may change if the
    // account that was used to create the object
    // is deleted
    private Identity objectCreator;

    // Role that has owner rights on the object
    private Role objectOwner;

    // Access control list for the object
    private final AccessControlList objectAcl;

    // Security type for the object
    private SecurityType objectType;

    // FIXME: Test constructor; probably unnecessary
    private ObjectProtection(Identity creator, Role owner)
    {
        objectCreator = creator;
        objectOwner = owner;
        objectAcl = new AccessControlList();
        try
        {
            objectType = new SecurityType(new SecTypeName(DEFAULT_SECTYPE_NAME));
        }
        catch (InvalidNameException invNameExc)
        {
            throw new ImplementationError(
                "Default security type name is invalid",
                null
            );
        }
    }

    /**
     * Creates an ObjectProtection instance for a newly created object
     *
     * @param accCtx The object creator's access context
     */
    public ObjectProtection(AccessContext accCtx)
    {
        ErrorCheck.ctorNotNull(ObjectProtection.class, AccessContext.class, accCtx);

        objectCreator = accCtx.subjectId;
        objectOwner = accCtx.subjectRole;
        objectAcl = new AccessControlList();
        objectType = accCtx.subjectDomain;
    }

    /**
     * Check whether a subject can be granted the requested level of access
     * to the object protected by this instance
     *
     * @param context The security context of the subject requesting access
     * @param requested The type of access requested by the subject
     * @throws AccessDeniedException If access is denied
     */
    public final void requireAccess(AccessContext context, AccessType requested)
        throws AccessDeniedException
    {
        objectType.requireAccess(context, requested);
        objectAcl.requireAccess(context, requested);
    }

    public Identity getCreator()
    {
        return objectCreator;
    }

    public void resetCreator(AccessContext context)
        throws AccessDeniedException
    {
        PrivilegeSet privs = context.getEffectivePrivs();
        privs.requirePrivileges(Privilege.PRIV_SYS_ALL);
        objectCreator = Identity.SYSTEM_ID;
    }

    public Role getOwner()
    {
        return objectOwner;
    }

    public void setOwner(AccessContext context, Role newOwner)
        throws AccessDeniedException
    {
        PrivilegeSet privs = context.getEffectivePrivs();
        privs.requirePrivileges(Privilege.PRIV_SYS_ALL);
        objectOwner = newOwner;
    }

    public AccessControlList getAcl()
    {
        return objectAcl;
    }

    public SecurityType getSecurityType()
    {
        return objectType;
    }

    public void setSecurityType(AccessContext context, SecurityType newSecType)
        throws AccessDeniedException
    {
        PrivilegeSet privs = context.getEffectivePrivs();
        privs.requirePrivileges(Privilege.PRIV_SYS_ALL);
        objectType = newSecType;
    }

    public void addAclEntry(AccessContext context, Role entryRole, AccessType grantedAccess)
        throws AccessDeniedException
    {
        objectType.requireAccess(context, AccessType.CONTROL);
        if (context.subjectRole != objectOwner)
        {
            objectAcl.requireAccess(context, AccessType.CONTROL);
            // Only object owners or privileged users may change the access controls for the
            // role that is being used to change the entry
            if (context.subjectRole == entryRole &&
                !context.getEffectivePrivs().hasPrivileges(Privilege.PRIV_OBJ_CONTROL)
            )
            {
                throw new AccessDeniedException(
                    "Changing the access control entry for the role performing the change was denied"
                );
            }
        }
        objectAcl.addEntry(entryRole, grantedAccess);
    }

    public void delAclEntry(AccessContext context, Role entryRole)
        throws AccessDeniedException
    {
        objectType.requireAccess(context, AccessType.CONTROL);
        if (context.subjectRole != objectOwner)
        {
            objectAcl.requireAccess(context, AccessType.CONTROL);
            if (context.subjectRole == entryRole &&
                !context.getEffectivePrivs().hasPrivileges(Privilege.PRIV_OBJ_CONTROL)
            )
            {
                throw new AccessDeniedException(
                    "Deleting the access control entry for the role performing the change was denied"
                );
            }
        }
        objectAcl.delEntry(entryRole);
    }
}
