package com.wxp.firstmod.recipe;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/** @author wxp */
public class ShapelessBlockRecipe extends AbstractShapelessRecipe {
  private Block outputBlock;
  private List<Block> inputBlocks;

  @Override
  public ItemStack getOutputItemStack() {
    return new ItemStack(outputBlock, outputCount);
  }

  @Override
  public Ingredient[] getInputIngredient() {
    return inputBlocks.stream()
        .map(block -> Ingredient.fromStacks(new ItemStack(block)))
        .collect(Collectors.toList())
        .toArray(new Ingredient[inputBlocks.size()]);
  }

  public void setOutputBlock(Block outputBlock) {
    this.outputBlock = outputBlock;
  }

  public ShapelessBlockRecipe addInputBlock(Block block) {
    if (inputBlocks == null) {
      inputBlocks = new ArrayList<>();
    }
    inputBlocks.add(block);
    return this;
  }
}
