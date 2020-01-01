package com.wxp.firstmod.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

/** @author wxp */
public class PositionHistoryMessage implements IMessage {
  private NBTTagCompound nbt = null;

  public PositionHistoryMessage() {}

  public PositionHistoryMessage(NBTTagCompound nbt) {
    this.nbt = nbt;
  }

  @Override
  public void fromBytes(ByteBuf buf) {
    nbt = ByteBufUtils.readTag(buf);
  }

  @Override
  public void toBytes(ByteBuf buf) {
    if (nbt == null) {
      return;
    }
    ByteBufUtils.writeTag(buf, nbt);
  }

  public NBTTagCompound getNbt() {
    return nbt;
  }
}
