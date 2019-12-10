package com.wxp.firstmod.manager;

import com.wxp.firstmod.enchantment.FireBurnEnchantment;
import net.minecraft.enchantment.Enchantment;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/** @author wxp */
public class EnchantmentManager {
  private static Map<Class, Enchantment> enchantments;

  public static void initEnchantment() {
    enchantments = new LinkedHashMap<>();
    FireBurnEnchantment fireBurnEnchantment = new FireBurnEnchantment();
    enchantments.put(fireBurnEnchantment.getClass(), fireBurnEnchantment);
  }

  public static Collection<Enchantment> getInitializedEnchantment() {
    return enchantments.values();
  }

  public static Enchantment getEnchantmentByClass(Class enchantmentClass) {
    return enchantments.get(enchantmentClass);
  }
}
