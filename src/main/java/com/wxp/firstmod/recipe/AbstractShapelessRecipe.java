package com.wxp.firstmod.recipe;

import com.wxp.firstmod.config.FirstModConfig;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

/**
 * @author wxp
 */
public abstract class AbstractShapelessRecipe {
  Integer outputCount = 1;
  private String recipeName;

  /**
   * 获取输出ItemStack, 包括输出什么Item, 输出多少
   * @return ItemStack
   */
  public abstract ItemStack getOutputItemStack();

  /**
   * 获取输入Ingredient, 包括需要什么东西, 需要多少
   * @return Ingredient
   */
  public abstract Ingredient[] getInputIngredient();

  public void setRecipeName(String recipeName) {
    this.recipeName = recipeName;
  }

  public ResourceLocation getCraftingResourceLocation() {
    return new ResourceLocation(FirstModConfig.MOD_ID + ":" + recipeName);
  }

  public void setOutputCount(Integer outputCount) {
    this.outputCount = outputCount;
  }
}
