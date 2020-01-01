package com.wxp.firstmod.capability.impl;

import com.wxp.firstmod.capability.PositionHistoryCap;
import net.minecraft.util.math.Vec3d;

/** @author wxp */
public class PositionHistoryCapImpl implements PositionHistoryCap {
  private Vec3d[] positions = new Vec3d[5];
  private int currentNum = 0;

  @Override
  public Vec3d[] getHistoryPositions() {
    return positions;
  }

  @Override
  public void savePosition(Vec3d position) {
    if (currentNum + 1 >= 5) {
      for (int i = 1; i < 5; i++) {
        positions[i - 1] = positions[i];
      }
    }
    positions[currentNum] = position;
    if (currentNum + 1 < 5) {
      currentNum++;
    }
  }

  @Override
  public void saveAllPositions(Vec3d[] positions) {}
}
