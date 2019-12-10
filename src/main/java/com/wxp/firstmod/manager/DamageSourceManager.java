package com.wxp.firstmod.manager;

import com.wxp.firstmod.damagesource.PowerDamageSource;
import net.minecraft.util.DamageSource;

import java.util.LinkedHashMap;
import java.util.Map;

/** @author wxp */
public class DamageSourceManager {
  private static Map<Class, DamageSource> damageSources;

  public static void initDamageSource() {
    damageSources = new LinkedHashMap<>();
    PowerDamageSource powerDamageSource = new PowerDamageSource();
    damageSources.put(powerDamageSource.getClass(), powerDamageSource);
  }

  public static DamageSource getDamageSourceByClass(Class damageClass) {
    return damageSources.get(damageClass);
  }
}
