package com.wxp.firstmod.gui.container;

import com.wxp.firstmod.block.tileentity.MetalFurnaceTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

/** @author wxp */
public class MetalFurnaceGuiContainer extends Container {
  private IItemHandler upItems;
  private IItemHandler downItems;
  private EntityPlayer entityPlayer;
  private MetalFurnaceTileEntity tileEntity;
  private int burnTime = 0;

  public MetalFurnaceGuiContainer(EntityPlayer entityPlayer, MetalFurnaceTileEntity tileEntity) {
    this.entityPlayer = entityPlayer;
    this.tileEntity = tileEntity;

    this.upItems =
        tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
    this.downItems =
        tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN);

    this.addSlotToContainer(new SlotItemHandler(this.upItems, 0, 56, 30));
    this.addSlotToContainer(
        new SlotItemHandler(this.downItems, 0, 110, 30) {
          @Override
          public boolean isItemValid(ItemStack stack) {
            return false;
          }
        });

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 9; j++) {
        this.addSlotToContainer(
            new Slot(entityPlayer.inventory, j + i * 9 + 9, 8 + j * 18, 74 + i * 18));
      }
    }

    for (int i = 0; i < 9; i++) {
      this.addSlotToContainer(new Slot(entityPlayer.inventory, i, 8 + i * 18, 132));
    }
  }

  @Override
  public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
    Slot slot = inventorySlots.get(index);

    if (slot == null || !slot.getHasStack()) {
      return null;
    }

    ItemStack newStack = slot.getStack(), oldStack = newStack.copy();

    boolean isMerged = false;

    if (index == 0 || index == 1) {
      isMerged = mergeItemStack(newStack, 2, 38, true);
    } else if (index >= 2 && index < 29) {
      isMerged = mergeItemStack(newStack, 0, 1, false) || mergeItemStack(newStack, 29, 38, false);
    } else if (index >= 29 && index < 38) {
      isMerged = mergeItemStack(newStack, 0, 1, false) || mergeItemStack(newStack, 2, 29, false);
    }

    if (!isMerged) {
      return null;
    }

    if (newStack.getCount() == 0) {
      slot.putStack(ItemStack.EMPTY);
    } else {
      slot.onSlotChanged();
    }

    slot.onTake(playerIn, newStack);

    return oldStack;
  }

  @Override
  public void detectAndSendChanges() {
    super.detectAndSendChanges();

    this.burnTime = tileEntity.getBurnTime();
    for (IContainerListener listener : this.listeners) {
      listener.sendWindowProperty(this, 0, burnTime);
    }
  }

  @Override
  public void updateProgressBar(int id, int data) {
    super.updateProgressBar(id, data);

    if (id == 0) {
      this.burnTime = data;
    }
  }

  @Override
  public boolean canInteractWith(EntityPlayer playerIn) {
    return playerIn.getDistanceSq(this.tileEntity.getPos()) <= 64;
  }

  public int getBurnTime() {
    return burnTime;
  }

  public int getTotalBurnTime() {
    return tileEntity.getTotalBurnTime();
  }
}
