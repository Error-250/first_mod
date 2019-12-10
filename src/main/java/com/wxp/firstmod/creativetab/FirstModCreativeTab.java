package com.wxp.firstmod.creativetab;

import com.wxp.firstmod.config.FirstModConfig;
import com.wxp.firstmod.item.GoldenEggItem;
import com.wxp.firstmod.manager.ItemManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** @author wxp */
public class FirstModCreativeTab extends CreativeTabs {
  public FirstModCreativeTab() {
    super(FirstModConfig.MOD_ID);
  }

  @SideOnly(Side.CLIENT)
  @Override
  public ItemStack getTabIconItem() {
    return new ItemStack(ItemManager.getItemByClass(GoldenEggItem.class));
  }

  @Override
  public boolean hasSearchBar() {
    return Boolean.TRUE;
  }
}
