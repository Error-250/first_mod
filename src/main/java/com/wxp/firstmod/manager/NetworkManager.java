package com.wxp.firstmod.manager;

import com.wxp.firstmod.config.FirstModConfig;
import com.wxp.firstmod.gui.FirstGui;
import com.wxp.firstmod.network.PositionHistoryMessage;
import com.wxp.firstmod.network.handler.PositionHistoryMessageHandler;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/** @author wxp */
public class NetworkManager {
  private static List<IGuiHandler> handlerList;
  public static SimpleNetworkWrapper simpleNetworkWrapper =
      NetworkRegistry.INSTANCE.newSimpleChannel(FirstModConfig.MOD_ID);

  public static void initNetwork() {
    simpleNetworkWrapper.registerMessage(
        PositionHistoryMessageHandler.class, PositionHistoryMessage.class, 0, Side.CLIENT);

    handlerList = new ArrayList<>();
    FirstGui firstGui = new FirstGui();
    handlerList.add(firstGui);
  }

  public static Collection<IGuiHandler> getInitializedGui() {
    return handlerList;
  }
}
