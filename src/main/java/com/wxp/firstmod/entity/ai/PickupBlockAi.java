package com.wxp.firstmod.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

/** @author wxp */
public class PickupBlockAi extends EntityAIBase {
  private final EntityLiving entity;

  public PickupBlockAi(EntityLiving entity) {
    this.entity = entity;
  }

  @Override
  public boolean shouldExecute() {
    boolean canMobGriefing = this.entity.world.getGameRules().getBoolean("mobGriefing");
    BlockPos blockPos = new BlockPos(entity);
    boolean isOnGround = this.entity.world.isAirBlock(blockPos);
    return canMobGriefing && isOnGround;
  }

  @Override
  public boolean isInterruptible() {
    return super.isInterruptible();
  }

  @Override
  public void updateTask() {
    BlockPos blockPos = new BlockPos(entity);
    for (EntityItem entityItem :
        entity.world.getEntitiesWithinAABB(EntityItem.class, this.entity.getEntityBoundingBox())) {
      ItemStack stack = entityItem.getItem();
      Block block = Block.getBlockFromItem(stack.getItem());
      if (Blocks.AIR.equals(block)) {
        continue;
      }
      entity.setLocationAndAngles(
          entity.posX, entity.posY + 1, entity.posZ, entity.rotationYaw, entity.rotationPitch);
      entity.world.setBlockState(blockPos, block.getDefaultState());
      stack.shrink(1);
      entityItem.setDead();
    }
  }
}
