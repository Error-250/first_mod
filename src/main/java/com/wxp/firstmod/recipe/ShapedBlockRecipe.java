package com.wxp.firstmod.recipe;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/** @author wxp */
public class ShapedBlockRecipe extends AbstractShapedRecipe {
  private Block outputBlock;
  private List<BlockRecipeInput> inputBlocks;

  public void setOutputBlock(Block outputBlock) {
    this.outputBlock = outputBlock;
  }

  @Override
  public ItemStack getOutputItemStack() {
    return new ItemStack(outputBlock, outputItemCount);
  }

  public ShapedBlockRecipe addItemRecipeInput(ShapedBlockRecipe.BlockRecipeInput inputBlock) {
    if (this.inputBlocks == null) {
      this.inputBlocks = new ArrayList<>();
    }
    this.inputBlocks.add(inputBlock);
    return this;
  }

  @Override
  public Object[] getShapedRecipeObjectArray() {
    List<Object> objects = new ArrayList<>(getRecipeLineList());
    for (ShapedBlockRecipe.BlockRecipeInput inputBlock : this.inputBlocks) {
      objects.add(inputBlock.getAlias());
      objects.add(inputBlock.getBlock());
    }
    return objects.toArray();
  }

  public static class BlockRecipeInput extends AbstractRecipeInput {
    private Block block;

    public BlockRecipeInput(char alias, Block block) {
      super(alias);
      this.block = block;
    }

    Block getBlock() {
      return block;
    }
  }
}
