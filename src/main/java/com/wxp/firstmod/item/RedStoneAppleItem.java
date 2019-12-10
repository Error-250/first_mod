package com.wxp.firstmod.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/** @author wxp */
public class RedStoneAppleItem extends ItemFood {
  public RedStoneAppleItem() {
    super(4, 6.0f, false);
    setAlwaysEdible();
    setRegistryName("red_stone_apple");
    setUnlocalizedName("red_stone_apple");
    setPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 200, 1), 1.0f);
  }

  @Override
  protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
    // 判断是否在server
    if (!worldIn.isRemote) {
      player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 200, 1));
      player.addExperienceLevel(10);
    }
    super.onFoodEaten(stack, worldIn, player);
  }
}
