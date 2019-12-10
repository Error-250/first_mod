package com.wxp.firstmod.manager;

import com.wxp.firstmod.block.MyGrassBlock;
import com.wxp.firstmod.item.GoldenEggItem;
import com.wxp.firstmod.item.RedStoneAppleItem;
import com.wxp.firstmod.item.RedStoneArmorItem;
import com.wxp.firstmod.item.RedStonePickaxeItem;
import com.wxp.firstmod.recipe.*;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/** @author wxp */
public class RecipeManager {
  private static List<AbstractShapedRecipe> shapedRecipes;
  private static List<AbstractShapelessRecipe> shapelessRecipes;

  public static void initRecipes() {
    shapedRecipes = new ArrayList<>();
    shapelessRecipes = new ArrayList<>();

    ShapedItemRecipe goldenEggRecipe = new ShapedItemRecipe();
    goldenEggRecipe.setRecipeName("golden_egg_crafting");
    goldenEggRecipe.setOutputItem(ItemManager.getItemByClass(GoldenEggItem.class));
    goldenEggRecipe.setRecipeFirstLine("###");
    goldenEggRecipe.setRecipeSecondLine("#*#");
    goldenEggRecipe.setRecipeThirdLine("###");
    goldenEggRecipe
        .addItemRecipeInput(new ShapedItemRecipe.ItemRecipeInput('#', Items.GOLD_INGOT))
        .addItemRecipeInput(new ShapedItemRecipe.ItemRecipeInput('*', Items.EGG));
    shapedRecipes.add(goldenEggRecipe);

    ShapedItemRecipe redStonePickaxeRecipe = new ShapedItemRecipe();
    redStonePickaxeRecipe.setRecipeName("red_stone_pickaxe_crafting");
    redStonePickaxeRecipe.setOutputItem(ItemManager.getItemByClass(RedStonePickaxeItem.class));
    redStonePickaxeRecipe.setRecipeFirstLine("###");
    redStonePickaxeRecipe.setRecipeSecondLine(" * ");
    redStonePickaxeRecipe.setRecipeThirdLine(" * ");
    redStonePickaxeRecipe
        .addItemRecipeInput(new ShapedItemRecipe.ItemRecipeInput('#', Items.REDSTONE))
        .addItemRecipeInput(new ShapedItemRecipe.ItemRecipeInput('*', Items.STICK));
    shapedRecipes.add(redStonePickaxeRecipe);

    ShapedItemRecipe redStoneAppleRecipe = new ShapedItemRecipe();
    redStoneAppleRecipe.setRecipeName("red_stone_apple_crafting");
    redStoneAppleRecipe.setOutputItem(ItemManager.getItemByClass(RedStoneAppleItem.class));
    redStoneAppleRecipe.setRecipeFirstLine("###");
    redStoneAppleRecipe.setRecipeSecondLine("#*#");
    redStoneAppleRecipe.setRecipeThirdLine("###");
    redStoneAppleRecipe
        .addItemRecipeInput(new ShapedItemRecipe.ItemRecipeInput('#', Items.REDSTONE))
        .addItemRecipeInput(new ShapedItemRecipe.ItemRecipeInput('*', Items.APPLE));
    shapedRecipes.add(redStoneAppleRecipe);

    ShapedItemRecipe redStoneHelmetRecipe = new ShapedItemRecipe();
    redStoneHelmetRecipe.setRecipeName("red_stone_helmet_crafting");
    redStoneHelmetRecipe.setOutputItem(
        ItemManager.getItemByClass(RedStoneArmorItem.RedStoneHelmetItem.class));
    redStoneHelmetRecipe.setRecipeFirstLine("###");
    redStoneHelmetRecipe.setRecipeSecondLine("# #");
    redStoneHelmetRecipe.addItemRecipeInput(
        new ShapedItemRecipe.ItemRecipeInput('#', Items.REDSTONE));
    shapedRecipes.add(redStoneHelmetRecipe);

    ShapedItemRecipe redStoneChestplateRecipe = new ShapedItemRecipe();
    redStoneChestplateRecipe.setRecipeName("red_stone_chestplate_crafting");
    redStoneChestplateRecipe.setOutputItem(
        ItemManager.getItemByClass(RedStoneArmorItem.RedStoneChestplateItem.class));
    redStoneChestplateRecipe.setRecipeFirstLine("# #");
    redStoneChestplateRecipe.setRecipeSecondLine("###");
    redStoneChestplateRecipe.setRecipeThirdLine("###");
    redStoneChestplateRecipe.addItemRecipeInput(
        new ShapedItemRecipe.ItemRecipeInput('#', Items.REDSTONE));
    shapedRecipes.add(redStoneChestplateRecipe);

    ShapedItemRecipe redStoneLeggingRecipe = new ShapedItemRecipe();
    redStoneLeggingRecipe.setRecipeName("red_stone_legging_crafting");
    redStoneLeggingRecipe.setOutputItem(ItemManager.getItemByClass(RedStoneArmorItem.RedStoneLeggingItem.class));
    redStoneLeggingRecipe.setRecipeFirstLine("###");
    redStoneLeggingRecipe.setRecipeSecondLine("# #");
    redStoneLeggingRecipe.setRecipeThirdLine("# #");
    redStoneLeggingRecipe.addItemRecipeInput(
            new ShapedItemRecipe.ItemRecipeInput('#', Items.REDSTONE));
    shapedRecipes.add(redStoneLeggingRecipe);

    ShapedItemRecipe redStoneBootsRecipe = new ShapedItemRecipe();
    redStoneBootsRecipe.setRecipeName("red_stone_boots_crafting");
    redStoneBootsRecipe.setOutputItem(ItemManager.getItemByClass(RedStoneArmorItem.RedStoneBootsItem.class));
    redStoneBootsRecipe.setRecipeFirstLine("# #");
    redStoneBootsRecipe.setRecipeSecondLine("# #");
    redStoneBootsRecipe.addItemRecipeInput(
            new ShapedItemRecipe.ItemRecipeInput('#', Items.REDSTONE));
    shapedRecipes.add(redStoneBootsRecipe);

    ShapedBlockRecipe myGrassRecipe = new ShapedBlockRecipe();
    myGrassRecipe.setRecipeName("my_grass_crafting");
    myGrassRecipe.setOutputBlock(BlockManager.getBlockByClass(MyGrassBlock.class));
    myGrassRecipe.setRecipeFirstLine("##");
    myGrassRecipe.setRecipeSecondLine("##");
    myGrassRecipe.addItemRecipeInput(new ShapedBlockRecipe.BlockRecipeInput('#', Blocks.VINE));
    shapedRecipes.add(myGrassRecipe);

    ShapelessBlockRecipe vineMyGrassRecipe = new ShapelessBlockRecipe();
    vineMyGrassRecipe.setOutputCount(4);
    vineMyGrassRecipe.setOutputBlock(Blocks.VINE);
    vineMyGrassRecipe
        .addInputBlock(BlockManager.getBlockByClass(MyGrassBlock.class))
        .addInputBlock(Blocks.STONE);
    shapelessRecipes.add(vineMyGrassRecipe);
  }

  public static Collection<AbstractShapedRecipe> getInitializedShapedRecipe() {
    return shapedRecipes;
  }

  public static Collection<AbstractShapelessRecipe> getInitializedShapelessRecipe() {
    return shapelessRecipes;
  }
}
