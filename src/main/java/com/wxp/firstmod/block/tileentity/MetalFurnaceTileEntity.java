package com.wxp.firstmod.block.tileentity;

import com.wxp.firstmod.block.MetalFurnaceBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

/** @author wxp */
public class MetalFurnaceTileEntity extends TileEntity implements ITickable {
  private int burnTime = 0;
  private ItemStackHandler upInventory = new ItemStackHandler();
  private ItemStackHandler downInventory = new ItemStackHandler();
  private final String keyUpInventory = "up_inventory";
  private final String keyDownInventory = "down_inventory";
  private final String keyBurnTime = "burn_time";

  @Override
  public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
    if (CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.equals(capability)) {
      return true;
    }
    return super.hasCapability(capability, facing);
  }

  @Nullable
  @Override
  public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
    if (CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.equals(capability)) {
      // return your IItemHandler
      return (T) (EnumFacing.DOWN.equals(facing) ? downInventory : upInventory);
    }
    return super.getCapability(capability, facing);
  }

  @Override
  public void readFromNBT(NBTTagCompound compound) {
    super.readFromNBT(compound);
    upInventory.deserializeNBT(compound.getCompoundTag(keyUpInventory));
    downInventory.deserializeNBT(compound.getCompoundTag(keyDownInventory));
    burnTime = compound.getInteger(keyBurnTime);
  }

  @Override
  public NBTTagCompound writeToNBT(NBTTagCompound compound) {
    super.writeToNBT(compound);
    compound.setTag(keyUpInventory, upInventory.serializeNBT());
    compound.setTag(keyDownInventory, downInventory.serializeNBT());
    compound.setInteger(keyBurnTime, burnTime);
    return compound;
  }

  @Override
  public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
    return oldState.getBlock() != newSate.getBlock();
  }

  @Override
  public void update() {
    if (!this.world.isRemote) {
      ItemStack itemStack = upInventory.extractItem(0, 1, true);
      IBlockState state = this.world.getBlockState(pos);

      if (!itemStack.isEmpty() && downInventory.insertItem(0, itemStack, true).isEmpty()) {
        this.world.setBlockState(pos, state.withProperty(MetalFurnaceBlock.BURNING, Boolean.TRUE));

        int burnTotalTime;
        switch (state.getValue(MetalFurnaceBlock.MATERIAL)) {
          case IRON:
            burnTotalTime = 150;
            break;
          case GOLDEN:
            burnTotalTime = 100;
            break;
          default:
            burnTotalTime = 200;
        }

        burnTime++;
        if (this.burnTime >= burnTotalTime) {
          this.burnTime = 0;
          itemStack = upInventory.extractItem(0, 1, false);
          downInventory.insertItem(0, itemStack, false);
          this.markDirty();
        }
      } else {
        this.world.setBlockState(pos, state.withProperty(MetalFurnaceBlock.BURNING, Boolean.FALSE));
      }
    }
  }
}
