package com.wxp.firstmod.enchantment;

import com.wxp.firstmod.config.FirstModConfig;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;

/** @author wxp */
public class FireBurnEnchantment extends Enchantment {
  public FireBurnEnchantment() {
    super(
        Rarity.COMMON,
        EnumEnchantmentType.DIGGER,
        new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
    setName("fire_burn");
    setRegistryName(FirstModConfig.MOD_ID, "fire_burn");
  }

  @Override
  public int getMinEnchantability(int enchantmentLevel) {
    return 7 * enchantmentLevel;
  }

  @Override
  public int getMaxEnchantability(int enchantmentLevel) {
    return getMinEnchantability(enchantmentLevel) + 50;
  }

  @Override
  public int getMaxLevel() {
    return 2;
  }
}
