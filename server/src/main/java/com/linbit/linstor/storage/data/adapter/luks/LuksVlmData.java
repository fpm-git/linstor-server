package com.linbit.linstor.storage.data.adapter.luks;

import com.linbit.linstor.api.pojo.LuksRscPojo.LuksVlmPojo;
import com.linbit.linstor.core.objects.AbsResource;
import com.linbit.linstor.core.objects.AbsVolume;
import com.linbit.linstor.dbdrivers.DatabaseException;
import com.linbit.linstor.dbdrivers.interfaces.LuksLayerDatabaseDriver;
import com.linbit.linstor.security.AccessContext;
import com.linbit.linstor.storage.data.AbsVlmData;
import com.linbit.linstor.storage.interfaces.categories.resource.VlmDfnLayerObject;
import com.linbit.linstor.storage.interfaces.categories.resource.VlmLayerObject;
import com.linbit.linstor.storage.interfaces.layers.State;
import com.linbit.linstor.storage.interfaces.layers.luks.LuksVlmObject;
import com.linbit.linstor.storage.kinds.DeviceLayerKind;
import com.linbit.linstor.transaction.TransactionObjectFactory;
import com.linbit.linstor.transaction.TransactionSimpleObject;
import com.linbit.linstor.transaction.manager.TransactionMgr;

import javax.annotation.Nullable;
import javax.inject.Provider;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LuksVlmData<RSC extends AbsResource<RSC>>
    extends AbsVlmData<RSC, LuksRscData<RSC>>
    implements LuksVlmObject<RSC>, VlmLayerObject<RSC>
{
    // persisted, serialized, ctrl and stlt
    private final TransactionSimpleObject<LuksVlmData<?>, byte[]> encryptedPassword;

    // not persisted, serialized, ctrl and stlt
    private @Nullable String devicePath;
    private String backingDevice;
    private String diskState;

    // not persisted, not serialized, stlt only
    private boolean opened;
    private String identifier;
    private byte[] decryptedPassword = null;
    private List<? extends State> unmodStates;
    private Size sizeState;

    // TODO maybe introduce States like "OPEN", "CLOSED", "UNINITIALIZED" or something...

    public LuksVlmData(
        AbsVolume<RSC> vlmRef,
        LuksRscData<RSC> rscDataRef,
        byte[] encryptedPasswordRef,
        LuksLayerDatabaseDriver dbDriver,
        TransactionObjectFactory transObjFactory,
        Provider<? extends TransactionMgr> transMgrProvider
    )
    {
        super(vlmRef, rscDataRef, transObjFactory, transMgrProvider);

        unmodStates = Collections.emptyList();

        encryptedPassword = transObjFactory.createTransactionSimpleObject(
            this,
            encryptedPasswordRef,
            dbDriver.getVlmEncryptedPasswordDriver()
        );

        transObjs = Arrays.asList(
            vlm,
            rscData,
            encryptedPassword
        );
    }

    @Override
    public boolean exists()
    {
        return exists.get();
    }

    public void setExists(boolean existsRef) throws DatabaseException
    {
        exists.set(existsRef);
    }

    public boolean isOpened()
    {
        return opened;
    }

    public void setOpened(boolean openedRef)
    {
        opened = openedRef;
    }

    @Override
    public boolean hasFailed()
    {
        return failed.get();
    }

    public void setFailed(boolean failedRef) throws DatabaseException
    {
        failed.set(failedRef);
    }

    @Override
    public long getAllocatedSize()
    {
        return allocatedSize.get();
    }

    @Override
    public void setAllocatedSize(long allocatedSizeRef) throws DatabaseException
    {
        allocatedSize.set(allocatedSizeRef);
    }

    @Override
    public long getUsableSize()
    {
        return usableSize.get();
    }

    @Override
    public long getOriginalSize()
    {
        return originalSize;
    }

    @Override
    public void setOriginalSize(long originalSizeRef)
    {
        originalSize = originalSizeRef;
    }

    @Override
    public void setUsableSize(long usableSizeRef) throws DatabaseException
    {
        if (usableSizeRef != usableSize.get())
        {
            if (usableSize.get() < usableSizeRef)
            {
                sizeState = Size.TOO_SMALL;
            }
            else
            {
                sizeState = Size.TOO_LARGE;
            }
        }
        else
        {
            sizeState = Size.AS_EXPECTED;
        }
        usableSize.set(usableSizeRef);
    }

    @Override
    public @Nullable String getDevicePath()
    {
        return devicePath;
    }

    public void setDevicePath(String devicePathRef)
    {
        devicePath = devicePathRef;
    }

    @Override
    public String getBackingDevice()
    {
        return backingDevice;
    }

    public void setBackingDevice(String backingDeviceRef)
    {
        backingDevice = backingDeviceRef;
    }

    @Override
    public Size getSizeState()
    {
        return sizeState;
    }

    public void setSizeState(Size sizeStateRef)
    {
        sizeState = sizeStateRef;
    }

    public String getDiskState()
    {
        return diskState;
    }

    public void setDiskState(String diskStateRef)
    {
        diskState = diskStateRef;
    }

    @Override
    public List<? extends State> getStates()
    {
        return unmodStates;
    }

    @Override
    public DeviceLayerKind getLayerKind()
    {
        return DeviceLayerKind.LUKS;
    }

    @Override
    public byte[] getEncryptedKey()
    {
        return encryptedPassword.get();
    }

    public void setEncryptedKey(byte[] encryptedKeyRef) throws DatabaseException
    {
        encryptedPassword.set(encryptedKeyRef);
    }

    @Override
    public @Nullable VlmDfnLayerObject getVlmDfnLayerObject()
    {
        return null;
    }

    public byte[] getDecryptedPassword()
    {
        return decryptedPassword;
    }

    public void setDecryptedPassword(byte[] decryptedPasswordRef)
    {
        decryptedPassword = decryptedPasswordRef;
    }

    @Override
    public String getIdentifier()
    {
        return identifier;
    }

    public void setIdentifier(String identifierRef)
    {
        identifier = identifierRef;
    }

    @Override
    public LuksVlmPojo asPojo(AccessContext accCtxRef)
    {
        return new LuksVlmPojo(
            getVlmNr().value,
            encryptedPassword.get(),
            devicePath,
            backingDevice,
            allocatedSize.get(),
            usableSize.get(),
            opened,
            diskState
        );
    }
}
