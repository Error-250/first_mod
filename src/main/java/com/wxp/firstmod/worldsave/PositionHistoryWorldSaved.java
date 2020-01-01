package com.wxp.firstmod.worldsave;

import com.google.common.collect.Lists;
import com.wxp.firstmod.config.FirstModConfig;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

import java.util.*;

/** @author wxp */
public class PositionHistoryWorldSaved extends WorldSavedData {
  private static final String name = FirstModConfig.MOD_ID + "_position_history";

  private Map<UUID, List<Vec3d>> playerPositions;

  public PositionHistoryWorldSaved() {
    this(name);
  }

  public PositionHistoryWorldSaved(String name) {
    super(name);
    playerPositions = new LinkedHashMap<>();
  }

  public void savePosition(UUID player, Vec3d position) {
    if (playerPositions.containsKey(player)) {
      playerPositions.get(player).add(position);
    } else {
      playerPositions.put(player, Lists.newArrayList(position));
    }
    markDirty();
  }

  public List<Vec3d> getPositions(UUID player) {
    if (playerPositions.containsKey(player)) {
      return playerPositions.get(player);
    }
    return Collections.emptyList();
  }

  public Map<UUID, List<Vec3d>> getPlayerPositions() {
    return playerPositions;
  }

  @Override
  public void readFromNBT(NBTTagCompound nbt) {
    playerPositions.clear();
    NBTTagList list = (NBTTagList) nbt.getTag("position_history");
    for (NBTBase base : list) {
      NBTTagCompound compound = (NBTTagCompound) base;
      String player = compound.getString("player");
      List<Vec3d> positions = new ArrayList<>();
      NBTTagList positionList = (NBTTagList) compound.getTag("positions");
      for (NBTBase positionBase : positionList) {
        NBTTagCompound position = (NBTTagCompound) positionBase;
        double x = position.getDouble("x");
        double y = position.getDouble("y");
        double z = position.getDouble("z");
        Vec3d vector3d = new Vec3d(x, y, z);
        positions.add(vector3d);
      }
      playerPositions.put(UUID.fromString(player), positions);
    }
  }

  @Override
  public NBTTagCompound writeToNBT(NBTTagCompound compound) {
    NBTTagList list = new NBTTagList();
    for (Map.Entry<UUID, List<Vec3d>> entry : playerPositions.entrySet()) {
      NBTTagList positionList = new NBTTagList();
      for (Vec3d vec3d : entry.getValue()) {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setDouble("x", vec3d.x);
        nbt.setDouble("y", vec3d.y);
        nbt.setDouble("z", vec3d.z);
        positionList.appendTag(nbt);
      }
      NBTTagCompound playerCompound = new NBTTagCompound();
      playerCompound.setString("player", entry.getKey().toString());
      playerCompound.setTag("positions", positionList);
      list.appendTag(playerCompound);
    }
    compound.setTag("position_history", list);
    return compound;
  }

  public static PositionHistoryWorldSaved get(World world) {
    MapStorage mapStorage = world.getPerWorldStorage();
    PositionHistoryWorldSaved worldSaved =
        (PositionHistoryWorldSaved) mapStorage.getOrLoadData(PositionHistoryWorldSaved.class, name);
    if (worldSaved == null) {
      worldSaved = new PositionHistoryWorldSaved();
      mapStorage.setData(name, worldSaved);
    }
    return worldSaved;
  }

  public static PositionHistoryWorldSaved getGlobal(World world) {
    MapStorage mapStorage = world.getMapStorage();
    PositionHistoryWorldSaved worldSaved =
        (PositionHistoryWorldSaved)
            mapStorage.getOrLoadData(PositionHistoryWorldSaved.class, name + "_global");
    if (worldSaved == null) {
      worldSaved = new PositionHistoryWorldSaved(name + "_global");
      mapStorage.setData(name + "_global", worldSaved);
    }
    return worldSaved;
  }
}
