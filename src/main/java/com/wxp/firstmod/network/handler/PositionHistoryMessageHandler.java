package com.wxp.firstmod.network.handler;

import com.wxp.firstmod.capability.PositionHistoryCap;
import com.wxp.firstmod.capability.storage.PositionHistoryStorage;
import com.wxp.firstmod.manager.CapabilityManager;
import com.wxp.firstmod.network.PositionHistoryMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

/** @author wxp */
public class PositionHistoryMessageHandler
    implements IMessageHandler<PositionHistoryMessage, IMessage> {
  @Override
  public IMessage onMessage(PositionHistoryMessage message, MessageContext ctx) {
    if (ctx.side == Side.CLIENT) {
      final NBTBase nbt = message.getNbt().getTag("histories");
      Minecraft.getMinecraft()
          .addScheduledTask(
              () -> {
                EntityPlayer player = Minecraft.getMinecraft().player;
                if (player.hasCapability(CapabilityManager.positionHistory, null)) {
                  PositionHistoryCap positionHistoryCap =
                      player.getCapability(CapabilityManager.positionHistory, null);
                  PositionHistoryStorage storage =
                      (PositionHistoryStorage) CapabilityManager.positionHistory.getStorage();
                  storage.readNBT(CapabilityManager.positionHistory, positionHistoryCap, null, nbt);
                }
              });
    }
    return null;
  }
}
