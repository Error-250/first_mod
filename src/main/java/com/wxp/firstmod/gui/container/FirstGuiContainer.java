package com.wxp.firstmod.gui.container;

import com.wxp.firstmod.item.GoldenEggItem;
import com.wxp.firstmod.manager.ItemManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

/** @author wxp */
public class FirstGuiContainer extends Container {
  private EntityPlayer player;
  private ItemStackHandler items = new ItemStackHandler(4);

  public FirstGuiContainer(EntityPlayer player) {
    this.player = player;
    // 顶层4个物品槽
    for (int index = 0; index < 4; index++) {
      this.addSlotToContainer(new SlotItemHandler(items, index, 38 + index * 32, 20));
    }

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
    return super.transferStackInSlot(playerIn, index);
  }
}
