package com.wxp.firstmod.potion;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionType;

/**
 * @author wxp
 */
public abstract class AbstractModPotion extends Potion {
  protected AbstractModPotion(boolean isBadEffectIn, int liquidColorIn) {
    super(isBadEffectIn, liquidColorIn);
  }

  /**
   * 获取potionType
   * @return PotionType
   */
  public abstract PotionType getPotionType();
}
