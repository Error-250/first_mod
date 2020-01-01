package com.wxp.firstmod.manager;

import com.wxp.firstmod.config.FirstModConfig;
import com.wxp.firstmod.network.PositionHistoryMessage;
import com.wxp.firstmod.network.handler.PositionHistoryMessageHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

/** @author wxp */
public class NetworkManager {
  public static SimpleNetworkWrapper simpleNetworkWrapper =
      NetworkRegistry.INSTANCE.newSimpleChannel(FirstModConfig.MOD_ID);;

  public static void initNetwork() {
    simpleNetworkWrapper.registerMessage(
        PositionHistoryMessageHandler.class, PositionHistoryMessage.class, 0, Side.CLIENT);
  }
}
