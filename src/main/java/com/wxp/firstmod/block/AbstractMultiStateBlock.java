package com.wxp.firstmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/** @author wxp */
public abstract class AbstractMultiStateBlock extends AbstractMyBlock {
  AbstractMultiStateBlock(Material blockMaterialIn) {
    super(blockMaterialIn);
  }

  /**
   * 获取方块的物品形态
   *
   * @return AbstractItemMultiTextureBlock
   */
  @Override
  public abstract AbstractItemMultiTextureBlock getItemBlock();

  public abstract static class AbstractItemMultiTextureBlock extends ItemBlock {

    public AbstractItemMultiTextureBlock(Block block) {
      super(block);
      setMaxDamage(0);
      setHasSubtypes(true);
    }

    /**
     * 获取不同状态物品的注册名
     *
     * @param stack 不同状态的物品
     * @return 注册名
     */
    public abstract String getRegistryName(ItemStack stack);

    /**
     * 获取不同状态物品的国际化名
     *
     * @param stack 不同状态的物品
     * @return 国际化名
     */
    @Override
    public abstract String getUnlocalizedName(ItemStack stack);

    @Override
    public int getMetadata(int damage) {
      return damage;
    }
  }
}
