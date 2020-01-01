package com.wxp.firstmod.capability.provider;

import com.wxp.firstmod.FirstMod;
import com.wxp.firstmod.capability.PositionHistoryCap;
import com.wxp.firstmod.capability.impl.PositionHistoryCapImpl;
import com.wxp.firstmod.capability.storage.PositionHistoryStorage;
import com.wxp.firstmod.manager.CapabilityManager;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/** @author wxp */
public class PositionHistoryProvider implements ICapabilitySerializable {
  private PositionHistoryCap positionHistoryCap = new PositionHistoryCapImpl();
  private PositionHistoryStorage storage =
      (PositionHistoryStorage) CapabilityManager.positionHistory.getStorage();

  @Override
  public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
    return CapabilityManager.positionHistory.equals(capability);
  }

  @Nullable
  @Override
  public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
    if (CapabilityManager.positionHistory.equals(capability)) {
      return (T) positionHistoryCap;
    }
    return null;
  }

  @Override
  public NBTBase serializeNBT() {
    NBTTagCompound compound = new NBTTagCompound();
    compound.setTag(
        "position_history",
        storage.writeNBT(CapabilityManager.positionHistory, positionHistoryCap, null));
    return compound;
  }

  @Override
  public void deserializeNBT(NBTBase nbt) {
    NBTTagCompound compound = (NBTTagCompound) nbt;
    NBTTagList list = (NBTTagList) compound.getTag("position_history");
    FirstMod.getLogger().info("deserialize {}", list);
    storage.readNBT(CapabilityManager.positionHistory, positionHistoryCap, null, list);
  }
}
