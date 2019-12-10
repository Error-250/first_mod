package com.wxp.firstmod.recipe;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/** @author wxp */
public class ShapedItemRecipe extends AbstractShapedRecipe {
  private Item outputItem;
  private List<ItemRecipeInput> inputItems;

  public void setOutputItem(Item outputItem) {
    this.outputItem = outputItem;
  }

  @Override
  public ItemStack getOutputItemStack() {
    return new ItemStack(outputItem, outputItemCount);
  }

  public ShapedItemRecipe addItemRecipeInput(ItemRecipeInput inputItem) {
    if (this.inputItems == null) {
      this.inputItems = new ArrayList<>();
    }
    this.inputItems.add(inputItem);
    return this;
  }

  @Override
  public Object[] getShapedRecipeObjectArray() {
    List<Object> objects = new ArrayList<>(getRecipeLineList());
    for (ItemRecipeInput inputItem : this.inputItems) {
      objects.add(inputItem.getAlias());
      objects.add(inputItem.getItem());
    }
    return objects.toArray();
  }

  public static class ItemRecipeInput extends AbstractRecipeInput {
    private Item item;

    public ItemRecipeInput(char alias, Item item) {
      super(alias);
      this.item = item;
    }

    Item getItem() {
      return item;
    }
  }
}
