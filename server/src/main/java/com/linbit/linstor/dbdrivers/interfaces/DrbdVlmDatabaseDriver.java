package com.linbit.linstor.dbdrivers.interfaces;

import com.linbit.SingleColumnDatabaseDriver;
import com.linbit.linstor.storage.layer.data.DrbdVlmData;

public interface DrbdVlmDatabaseDriver
{
    SingleColumnDatabaseDriver<DrbdVlmData, String> getMetaDiskDriver();

}
