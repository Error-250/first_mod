package com.wxp.firstmod.recipe;

import com.wxp.firstmod.config.FirstModConfig;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/** @author wxp */
public abstract class AbstractShapedRecipe {
  Integer outputItemCount = 1;
  private String recipeFirstLine;
  private String recipeSecondLine;
  private String recipeThirdLine;
  private String recipeName;

  /**
   * 获取输出ItemStack, 包括输出什么Item, 输出多少
   *
   * @return ItemStack
   */
  public abstract ItemStack getOutputItemStack();

  /**
   * 获取用于注册Minecraft合成表的对象数组
   *
   * @return Object[]
   */
  public abstract Object[] getShapedRecipeObjectArray();

  public void setRecipeName(String recipeName) {
    this.recipeName = recipeName;
  }

  public ResourceLocation getCraftingResourceLocation() {
    return new ResourceLocation(FirstModConfig.MOD_ID + ":" + recipeName);
  }

  public void setOutputItemCount(Integer outputItemCount) {
    this.outputItemCount = outputItemCount;
  }

  public void setRecipeFirstLine(String recipeFirstLine) {
    this.recipeFirstLine = recipeFirstLine;
  }

  public void setRecipeSecondLine(String recipeSecondLine) {
    this.recipeSecondLine = recipeSecondLine;
  }

  public void setRecipeThirdLine(String recipeThirdLine) {
    this.recipeThirdLine = recipeThirdLine;
  }

  List<String> getRecipeLineList() {
    List<String> list = new ArrayList<>();
    if (!StringUtils.isEmpty(this.recipeFirstLine)) {
      list.add(this.recipeFirstLine);
    }
    if (!StringUtils.isEmpty(this.recipeSecondLine)) {
      list.add(this.recipeSecondLine);
    }
    if (!StringUtils.isEmpty(this.recipeThirdLine)) {
      list.add(this.recipeThirdLine);
    }
    return list;
  }

  static class AbstractRecipeInput {
    char alias;

    AbstractRecipeInput(char alias) {
      this.alias = alias;
    }

    char getAlias() {
      return alias;
    }
  }
}
