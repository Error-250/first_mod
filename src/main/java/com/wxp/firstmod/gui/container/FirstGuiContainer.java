package com.wxp.firstmod.gui.container;

import com.wxp.firstmod.item.GoldenEggItem;
import com.wxp.firstmod.manager.ItemManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

/** @author wxp */
public class FirstGuiContainer extends Container {
  @Override
  public boolean canInteractWith(EntityPlayer playerIn) {
    return playerIn
        .getHeldItemMainhand()
        .getItem()
        .equals(ItemManager.getItemByClass(GoldenEggItem.class));
  }
}
