package com.wxp.firstmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

/**
 * @author wxp
 */
public abstract class AbstractMyBlock extends Block {
  AbstractMyBlock(Material blockMaterialIn) {
    super(blockMaterialIn);
  }

  /**
   * 获取方块的物品形态
   * @return ItemBlock
   */
  public abstract ItemBlock getItemBlock();

  public boolean hasItemBlock() {
    return true;
  }
}
