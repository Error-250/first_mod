package com.wxp.firstmod.eventhandler;

import com.wxp.firstmod.capability.PositionHistoryCap;
import com.wxp.firstmod.config.FirstModConfig;
import com.wxp.firstmod.key.ShowTimeKey;
import com.wxp.firstmod.manager.CapabilityManager;
import com.wxp.firstmod.manager.KeyManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;

/** @author wxp */
@Mod.EventBusSubscriber(modid = FirstModConfig.MOD_ID)
public class InputEventHandler {
  @SubscribeEvent
  public static void onKeyInput(InputEvent.KeyInputEvent event) {
    if (!Side.CLIENT.equals(FMLCommonHandler.instance().getSide())) {
      return;
    }
    if (KeyManager.getKeyByClass(ShowTimeKey.class).isPressed()) {
      EntityPlayer player = Minecraft.getMinecraft().player;
      World world = Minecraft.getMinecraft().world;
      player.sendMessage(
          new TextComponentTranslation(
              String.format("chat.%s.time", FirstModConfig.MOD_ID), world.getTotalWorldTime()));

      // 尝试获取用户存储的位置历史数据
      if (player.hasCapability(CapabilityManager.positionHistory, null)) {
        PositionHistoryCap positionHistoryCap =
            player.getCapability(CapabilityManager.positionHistory, null);
        for (Vec3d vector3d : positionHistoryCap.getHistoryPositions()) {
          if (vector3d == null) {
            continue;
          }
          player.sendMessage(new TextComponentString(vector3d.toString()));
        }
      }
    }
  }
}
