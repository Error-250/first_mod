package com.wxp.firstmod.eventhandler;

import com.wxp.firstmod.FirstMod;
import com.wxp.firstmod.block.MercuryFluidBlock;
import com.wxp.firstmod.config.FirstModConfig;
import com.wxp.firstmod.enchantment.FireBurnEnchantment;
import com.wxp.firstmod.item.MercuryBucketItem;
import com.wxp.firstmod.manager.BlockManager;
import com.wxp.firstmod.manager.EnchantmentManager;
import com.wxp.firstmod.manager.FluidManager;
import com.wxp.firstmod.manager.ItemManager;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Objects;

/** @author wxp */
@Mod.EventBusSubscriber(modid = FirstModConfig.MOD_ID)
public class BlockEventHandler {
  @SubscribeEvent
  public static void onBlockHarvestDrops(BlockEvent.HarvestDropsEvent event) {
    if (!event.getWorld().isRemote && event.getHarvester() != null) {
      ItemStack heldItem = event.getHarvester().getHeldItemMainhand();
      if (EnchantmentHelper.getEnchantmentLevel(
              EnchantmentManager.getEnchantmentByClass(FireBurnEnchantment.class), heldItem)
          > 0) {
        for (int index = 0; index < event.getDrops().size(); index++) {
          ItemStack itemStack = event.getDrops().get(index);
          if (itemStack.isEmpty()) {
            continue;
          }
          ItemStack burnedItem = FurnaceRecipes.instance().getSmeltingResult(itemStack);
          if (!burnedItem.isEmpty()) {
            event.getDrops().set(index, burnedItem);
          }
        }
      }
    }
  }

  @SubscribeEvent
  public static void onBucketFill(FillBucketEvent event) {
    BlockPos position = Objects.requireNonNull(event.getTarget()).getBlockPos();
    Block block = event.getWorld().getBlockState(position).getBlock();
    if (BlockManager.getFluidBlockByClass(MercuryFluidBlock.class).equals(block)) {
      event.getWorld().setBlockToAir(position);
      event.setFilledBucket(new ItemStack(ItemManager.getItemByClass(MercuryBucketItem.class)));
      event.setResult(Event.Result.ALLOW);
    }
  }
}
