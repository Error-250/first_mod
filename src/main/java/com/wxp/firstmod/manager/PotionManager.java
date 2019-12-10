package com.wxp.firstmod.manager;

import com.wxp.firstmod.potion.FallProtectPotion;
import net.minecraft.potion.Potion;

import java.util.*;

/** @author wxp */
public class PotionManager {
  private static Map<Class, Potion> potions;

  public static void initPotion() {
    potions = new LinkedHashMap<>();

    FallProtectPotion fallProtectPotion = new FallProtectPotion();
    potions.put(fallProtectPotion.getClass(), fallProtectPotion);
  }

  public static Collection<Potion> getInitializedPotion() {
    return potions.values();
  }

  public static Potion getPotionByClass(Class potionClass) {
    return potions.get(potionClass);
  }
}
