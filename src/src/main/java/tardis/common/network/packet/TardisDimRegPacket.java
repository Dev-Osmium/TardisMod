package tardis.common.network.packet;

import java.io.IOException;

import net.minecraft.nbt.NBTTagCompound;
import tardis.TardisMod;
import tardis.common.core.Helper;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;

public class TardisDimRegPacket extends TardisAbstractPacket
{

	public TardisDimRegPacket(ByteBuf payload, NBTTagCompound nbt)
	{
		super(payload, nbt, (byte) PacketType.DIMREG.ordinal());
	}
	
	public TardisDimRegPacket(ByteBuf payload)
	{
		super(payload);
	}

	public void registerDims()
	{
		NBTTagCompound nbt = getNBT();
		if(nbt != null && !Helper.isServer())
			TardisMod.dimReg.readFromNBT(nbt);
	}

}