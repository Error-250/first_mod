package com.wxp.firstmod.manager;

import com.wxp.firstmod.block.AbstractMyBlock;
import com.wxp.firstmod.item.*;
import net.minecraft.item.Item;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/** @author wxp */
public class ItemManager {
  private static Map<Class, Item> items;

  public static void initItem() {
    items = new LinkedHashMap<>();
    for (AbstractMyBlock abstractMyBlock : BlockManager.getInitializedBlocks()) {
      if (abstractMyBlock.hasItemBlock()) {
        items.put(
            abstractMyBlock.getItemBlock().getBlock().getClass(), abstractMyBlock.getItemBlock());
      }
    }
    GoldenEggItem goldenEggItem = new GoldenEggItem();
    goldenEggItem.setCreativeTab(CreativeTabManager.firstModCreativeTab);
    items.put(goldenEggItem.getClass(), goldenEggItem);
    RedStonePickaxeItem redStonePickaxeItem = new RedStonePickaxeItem();
    redStonePickaxeItem.setCreativeTab(CreativeTabManager.firstModCreativeTab);
    items.put(redStonePickaxeItem.getClass(), redStonePickaxeItem);
    RedStoneAppleItem redStoneAppleItem = new RedStoneAppleItem();
    redStoneAppleItem.setCreativeTab(CreativeTabManager.firstModCreativeTab);
    items.put(redStoneAppleItem.getClass(), redStoneAppleItem);
    RedStoneArmorItem.RedStoneHelmetItem redStoneHelmetItem =
        new RedStoneArmorItem.RedStoneHelmetItem();
    redStoneHelmetItem.setCreativeTab(CreativeTabManager.firstModCreativeTab);
    items.put(redStoneHelmetItem.getClass(), redStoneHelmetItem);
    RedStoneArmorItem.RedStoneChestplateItem redStoneChestplateItem =
        new RedStoneArmorItem.RedStoneChestplateItem();
    redStoneChestplateItem.setCreativeTab(CreativeTabManager.firstModCreativeTab);
    items.put(redStoneChestplateItem.getClass(), redStoneChestplateItem);
    RedStoneArmorItem.RedStoneLeggingItem redStoneLeggingItem =
        new RedStoneArmorItem.RedStoneLeggingItem();
    redStoneLeggingItem.setCreativeTab(CreativeTabManager.firstModCreativeTab);
    items.put(redStoneLeggingItem.getClass(), redStoneLeggingItem);
    RedStoneArmorItem.RedStoneBootsItem redStoneBootsItem =
        new RedStoneArmorItem.RedStoneBootsItem();
    redStoneBootsItem.setCreativeTab(CreativeTabManager.firstModCreativeTab);
    items.put(redStoneBootsItem.getClass(), redStoneBootsItem);
    DldlRecordItem dldlRecordItem = new DldlRecordItem();
    dldlRecordItem.setCreativeTab(CreativeTabManager.firstModCreativeTab);
    items.put(dldlRecordItem.getClass(), dldlRecordItem);
    MercuryBucketItem mercuryBucketItem = new MercuryBucketItem();
    mercuryBucketItem.setCreativeTab(CreativeTabManager.firstModCreativeTab);
    items.put(mercuryBucketItem.getClass(), mercuryBucketItem);
  }

  public static Collection<Item> getInitializedItems() {
    return items.values();
  }

  public static Item getItemByClass(Class itemClass) {
    return items.get(itemClass);
  }
}
