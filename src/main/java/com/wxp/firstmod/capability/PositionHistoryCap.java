package com.wxp.firstmod.capability;

import net.minecraft.util.math.Vec3d;

/** @author wxp */
public interface PositionHistoryCap {
  /**
   * 获取历史保存位置
   *
   * @return 保存的位置
   */
  Vec3d[] getHistoryPositions();

  /**
   * 保存位置
   *
   * @param position 要保存的位置
   */
  void savePosition(Vec3d position);

  /**
   * 保存全部历史位置
   *
   * @param positions 多个位置数据
   */
  void saveAllPositions(Vec3d[] positions);
}
