package com.wxp.firstmod.damagesource;

import net.minecraft.util.DamageSource;

/** @author wxp */
public class PowerDamageSource extends DamageSource {

  public PowerDamageSource() {
    super("power");
    setDamageBypassesArmor();
    setDifficultyScaled();
  }
}
