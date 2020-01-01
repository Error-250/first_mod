package com.wxp.firstmod.capability.storage;

import com.wxp.firstmod.capability.PositionHistoryCap;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;
import java.util.Iterator;

/** @author wxp */
public class PositionHistoryStorage implements Capability.IStorage<PositionHistoryCap> {
  @Nullable
  @Override
  public NBTBase writeNBT(
      Capability<PositionHistoryCap> capability, PositionHistoryCap instance, EnumFacing side) {
    NBTTagList list = new NBTTagList();
    for (Vec3d position : instance.getHistoryPositions()) {
      if (position == null) {
        continue;
      }
      NBTTagCompound compound = new NBTTagCompound();
      compound.setDouble("x", position.x);
      compound.setDouble("y", position.y);
      compound.setDouble("z", position.z);
      list.appendTag(compound);
    }
    return list;
  }

  @Override
  public void readNBT(
      Capability<PositionHistoryCap> capability,
      PositionHistoryCap instance,
      EnumFacing side,
      NBTBase nbt) {
    if (nbt == null) {
      return;
    }
    NBTTagList list = (NBTTagList) nbt;
    for (NBTBase nbtBase : list) {
      NBTTagCompound compound = (NBTTagCompound) nbtBase;
      double x = compound.getDouble("x");
      double y = compound.getDouble("y");
      double z = compound.getDouble("z");
      Vec3d vector3d = new Vec3d(x, y, z);
      instance.savePosition(vector3d);
    }
  }
}
