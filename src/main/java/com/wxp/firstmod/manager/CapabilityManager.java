package com.wxp.firstmod.manager;

import com.wxp.firstmod.capability.PositionHistoryCap;
import com.wxp.firstmod.capability.factory.PositionHistoryFactory;
import com.wxp.firstmod.capability.storage.PositionHistoryStorage;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

/** @author wxp */
public class CapabilityManager {
  @CapabilityInject(PositionHistoryCap.class)
  public static Capability<PositionHistoryCap> positionHistory;

  public static void initCapability() {
    net.minecraftforge.common.capabilities.CapabilityManager.INSTANCE.register(
        PositionHistoryCap.class, new PositionHistoryStorage(), new PositionHistoryFactory());
  }
}
