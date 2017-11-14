package com.linbit.drbdmanage.api.protobuf.controller.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import com.google.protobuf.ByteString;
import com.linbit.ImplementationError;
import com.linbit.drbdmanage.api.interfaces.Serializer;
import com.linbit.drbdmanage.api.protobuf.BaseProtoApiCall;
import com.linbit.drbdmanage.logging.ErrorReporter;
import com.linbit.drbdmanage.propscon.Props;
import com.linbit.drbdmanage.proto.LinStorMapEntryOuterClass.LinStorMapEntry;
import com.linbit.drbdmanage.proto.MsgHeaderOuterClass.MsgHeader;
import com.linbit.drbdmanage.proto.javainternal.MsgIntObjectIdOuterClass.MsgIntObjectId;
import com.linbit.drbdmanage.security.AccessContext;
import com.linbit.drbdmanage.security.AccessDeniedException;
import com.linbit.utils.UuidUtils;

abstract class AbsSerializerProto<TYPE> implements Serializer<TYPE>
{
    protected final AccessContext serializerCtx;
    protected final ErrorReporter errorReporter;

    private final String apiCallChanged;
    private final String apiCallData;

    public AbsSerializerProto(
        AccessContext serializerCtxRef,
        ErrorReporter errorReporterRef,
        String apiCallChangedRef,
        String apiCallDataRef
    )
    {
        serializerCtx = serializerCtxRef;
        errorReporter = errorReporterRef;
        apiCallChanged = apiCallChangedRef;
        apiCallData = apiCallDataRef;
    }

    protected ByteString asByteString(UUID uuid)
    {
        return ByteString.copyFrom(UuidUtils.asByteArray(uuid));
    }

    @Override
    public byte[] getChangedMessage(TYPE data)
    {
        byte[] toSend = null;
        try
        {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            MsgHeader.newBuilder()
                .setApiCall(apiCallChanged)
                .setMsgId(0) // TODO: change to something that defines this message as a protobuf msg
                .build()
                .writeDelimitedTo(baos);

            MsgIntObjectId.newBuilder()
                .setName(getName(data))
                .setUuid(asByteString(getUuid(data)))
                .build()
                .writeDelimitedTo(baos);

            toSend = baos.toByteArray();
        }
        catch (IOException ioExc)
        {
            errorReporter.reportError(ioExc);
            toSend = new byte[0];
        }

        return toSend;
    }

    @Override
    public byte[] getDataMessage(int msgId, TYPE data)
    {
        byte[] toSend = null;
        try
        {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            MsgHeader.newBuilder()
                .setApiCall(apiCallData)
                .setMsgId(msgId)
                .build()
                .writeDelimitedTo(baos);

            writeData(data, baos);

            toSend = baos.toByteArray();
        }
        catch (IOException ioExc)
        {
            errorReporter.reportError(ioExc);
            toSend = new byte[0];
        }
        catch (AccessDeniedException accDeniedExc)
        {
            errorReporter.reportError(
                new ImplementationError(
                    "Access denied for ResourceDataSerializer",
                    accDeniedExc
                )
            );
            toSend = new byte[0];
        }

        return toSend;
    }

    protected List<LinStorMapEntry> asLinStorList(Props props)
    {
        return BaseProtoApiCall.fromMap(props.map());
    }

    protected abstract void writeData(TYPE data, ByteArrayOutputStream baos)
        throws IOException, AccessDeniedException;

    protected abstract String getName(TYPE data);

    protected abstract UUID getUuid(TYPE data);
}
