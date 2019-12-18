package com.wxp.firstmod.item;

import com.wxp.firstmod.entity.GoldenEntityEgg;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import java.util.Objects;

/** @author wxp */
public class GoldenEggItem extends Item {
  public GoldenEggItem() {
    setRegistryName("golden_egg");
    setUnlocalizedName("golden_egg");
  }

  @Override
  public int getItemBurnTime(ItemStack itemStack) {
    return 200;
  }

  @Override
  public ActionResult<ItemStack> onItemRightClick(
      World worldIn, EntityPlayer playerIn, EnumHand handIn) {
    ItemStack itemstack = playerIn.getHeldItem(handIn);

    if (!playerIn.capabilities.isCreativeMode) {
      itemstack.shrink(1);
    }

    worldIn.playSound(
        null,
        playerIn.posX,
        playerIn.posY,
        playerIn.posZ,
        SoundEvents.ENTITY_EGG_THROW,
        SoundCategory.PLAYERS,
        0.5F,
        0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

    if (!worldIn.isRemote) {
      GoldenEntityEgg entityEgg = new GoldenEntityEgg(worldIn, playerIn);
      entityEgg.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
      worldIn.spawnEntity(entityEgg);
    }

    playerIn.addStat(Objects.requireNonNull(StatList.getObjectUseStats(this)));
    return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
  }
}
