package com.wxp.firstmod.gui.container;

import com.wxp.firstmod.item.GoldenEggItem;
import com.wxp.firstmod.manager.ItemManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

/** @author wxp */
public class FirstGuiContainer extends Container {
  private EntityPlayer player;
  private ItemStackHandler items = new ItemStackHandler(4);

  private Slot goldSlot;
  private Slot diamondSlot;
  private Slot emeraldSlot;
  private Slot ironSlot;

  public FirstGuiContainer(EntityPlayer player) {
    this.player = player;
    // 顶层4个物品槽
    goldSlot =
        this.addSlotToContainer(
            new SlotItemHandler(items, 0, 38, 20) {
              @Override
              public boolean isItemValid(@Nonnull ItemStack stack) {
                return !stack.isEmpty()
                    && stack.getItem().equals(Items.GOLD_INGOT)
                    && super.isItemValid(stack);
              }

              @Override
              public int getItemStackLimit(@Nonnull ItemStack stack) {
                return 16;
              }
            });
    diamondSlot =
        this.addSlotToContainer(
            new SlotItemHandler(items, 1, 38 + 32, 20) {
              {
                this.putStack(new ItemStack(Items.DIAMOND, 64));
              }

              @Override
              public boolean canTakeStack(EntityPlayer playerIn) {
                return Boolean.FALSE;
              }
            });
    emeraldSlot =
        this.addSlotToContainer(
            new SlotItemHandler(items, 2, 38 + 2 * 32, 20) {
              @Override
              public boolean isItemValid(@Nonnull ItemStack stack) {
                return !stack.isEmpty()
                    && stack.getItem().equals(Items.EMERALD)
                    && super.isItemValid(stack);
              }

              @Override
              public void onSlotChanged() {
                ItemStack itemStack = this.getStack();
                int remain = itemStack.isEmpty() ? 64 : 64 - itemStack.getCount();
                FirstGuiContainer.this.diamondSlot.putStack(new ItemStack(Items.DIAMOND, remain));
                super.onSlotChanged();
              }
            });
    ironSlot =
        this.addSlotToContainer(
            new SlotItemHandler(items, 3, 38 + 3 * 32, 20) {
              {
                putStack(new ItemStack(Items.IRON_INGOT, 64));
              }

              @Override
              public boolean canTakeStack(EntityPlayer playerIn) {
                return Boolean.FALSE;
              }
            });
    // 玩家27个物品槽
    for (int i = 0; i < 3; ++i) {
      for (int j = 0; j < 9; ++j) {
        this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 51 + i * 18));
      }
    }

    // 9个快捷物品槽
    for (int i = 0; i < 9; ++i) {
      this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 109));
    }
  }

  @Override
  public boolean canInteractWith(EntityPlayer playerIn) {
    return playerIn
        .getHeldItemMainhand()
        .getItem()
        .equals(ItemManager.getItemByClass(GoldenEggItem.class));
  }

  @Override
  public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
    Slot slot = inventorySlots.get(index);

    if (slot == null || !slot.getHasStack()) {
      return ItemStack.EMPTY;
    }

    ItemStack newStack = slot.getStack();
    ItemStack oldStack = newStack.copy();

    boolean isMerged = false;

    if (index == 0 || index == 2) {
      isMerged = mergeItemStack(newStack, 4, 40, true);
    } else if (index >= 4 && index < 31) {
      isMerged =
          (!goldSlot.getHasStack()
                  && newStack.getCount() <= 16
                  && mergeItemStack(newStack, 0, 1, false))
              || (!emeraldSlot.getHasStack() && mergeItemStack(newStack, 2, 3, false))
              || mergeItemStack(newStack, 31, 40, false);
    } else if (index >= 31 && index < 40) {
      isMerged =
          (!goldSlot.getHasStack()
                  && newStack.getCount() <= 16
                  && mergeItemStack(newStack, 0, 1, false))
              || (!emeraldSlot.getHasStack() && mergeItemStack(newStack, 2, 3, false))
              || mergeItemStack(newStack, 4, 31, false);
    }

    if (!isMerged) {
      return ItemStack.EMPTY;
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
  public void onContainerClosed(EntityPlayer playerIn) {
    super.onContainerClosed(playerIn);

    if (playerIn.isServerWorld()) {
      ItemStack goldStack = this.goldSlot.getStack();
      if (!goldStack.isEmpty()) {
        playerIn.dropItem(goldStack, false);
        this.goldSlot.putStack(ItemStack.EMPTY);
      }
      ItemStack emeraldStack = this.emeraldSlot.getStack();
      if (!emeraldStack.isEmpty()) {
        playerIn.dropItem(emeraldStack, false);
        this.emeraldSlot.putStack(ItemStack.EMPTY);
      }
    }
  }

  public Slot getIronSlot() {
    return ironSlot;
  }
}
