package com.wxp.firstmod.register;

import com.wxp.firstmod.block.MyGrassBlock;
import com.wxp.firstmod.manager.BlockManager;
import com.wxp.firstmod.manager.RecipeManager;
import com.wxp.firstmod.recipe.AbstractShapedRecipe;
import com.wxp.firstmod.recipe.AbstractShapelessRecipe;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/** @author wxp */
public class CraftingRegister {
  /** 注册合成表 */
  public static void registerRecipe() {
    for (AbstractShapedRecipe shapedRecipe : RecipeManager.getInitializedShapedRecipe()) {
      GameRegistry.addShapedRecipe(
          shapedRecipe.getCraftingResourceLocation(),
          null,
          shapedRecipe.getOutputItemStack(),
          shapedRecipe.getShapedRecipeObjectArray());
    }

    for (AbstractShapelessRecipe shapelessRecipe : RecipeManager.getInitializedShapelessRecipe()) {
      GameRegistry.addShapelessRecipe(
          shapelessRecipe.getCraftingResourceLocation(),
          null,
          shapelessRecipe.getOutputItemStack(),
          shapelessRecipe.getInputIngredient());
    }

    // 烧炼
    GameRegistry.addSmelting(
        BlockManager.getBlockByClass(MyGrassBlock.class), new ItemStack(Items.COAL, 2), 1f);
  }
}
