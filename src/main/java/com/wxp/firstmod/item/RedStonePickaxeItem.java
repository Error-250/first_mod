package com.wxp.firstmod.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraftforge.common.util.EnumHelper;

/** @author wxp */
public class RedStonePickaxeItem extends ItemPickaxe {
  /** 定义工具使用的材料 */
  private static final Item.ToolMaterial RED_STONE =
      EnumHelper.addToolMaterial("RED_STONE", 3, 16, 16.0F, 0.0F, 10);

  public RedStonePickaxeItem() {
    super(RED_STONE);
    setRegistryName("red_stone_pickaxe");
    setUnlocalizedName("red_stone_pickaxe");
  }
}
