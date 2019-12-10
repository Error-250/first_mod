package com.wxp.firstmod.item;

import com.wxp.firstmod.entity.GoldenChickenEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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
  public EnumActionResult onItemUse(
      EntityPlayer player,
      World worldIn,
      BlockPos pos,
      EnumHand hand,
      EnumFacing facing,
      float hitX,
      float hitY,
      float hitZ) {
    if (!worldIn.isRemote) {
      GoldenChickenEntity goldenChickenEntity = new GoldenChickenEntity(worldIn);
      goldenChickenEntity.setPositionAndUpdate(
          pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
      worldIn.spawnEntity(goldenChickenEntity);
    }
    return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
  }
}
