package com.wxp.firstmod.item;

import com.wxp.firstmod.config.FirstModConfig;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

/** @author wxp */
public class RedStoneArmorItem extends ItemArmor {
  private static final ArmorMaterial RED_STONE_MATERIAL =
      EnumHelper.addArmorMaterial(
          String.format("%s:red_stone_armor", FirstModConfig.MOD_ID),
          String.format("%s:red_stone_armor", FirstModConfig.MOD_ID),
          10,
          new int[] {2, 6, 4, 2},
          10,
          SoundEvents.ITEM_ARMOR_EQUIP_GOLD,
          1.0f);

  RedStoneArmorItem(EntityEquipmentSlot equipmentSlotIn) {
    super(RED_STONE_MATERIAL, RED_STONE_MATERIAL.ordinal(), equipmentSlotIn);
  }

  public static class RedStoneHelmetItem extends RedStoneArmorItem {
    public RedStoneHelmetItem() {
      super(EntityEquipmentSlot.HEAD);
      setRegistryName("red_stone_helmet");
      setUnlocalizedName("red_stone_helmet");
    }
  }

  public static class RedStoneChestplateItem extends RedStoneArmorItem {
    public RedStoneChestplateItem() {
      super(EntityEquipmentSlot.CHEST);
      setRegistryName("red_stone_chestplate");
      setUnlocalizedName("red_stone_chestplate");
    }
  }

  public static class RedStoneLeggingItem extends RedStoneArmorItem {
    public RedStoneLeggingItem() {
      super(EntityEquipmentSlot.LEGS);
      setRegistryName("red_stone_legging");
      setUnlocalizedName("red_stone_legging");
    }
  }

  public static class RedStoneBootsItem extends RedStoneArmorItem {
    public RedStoneBootsItem() {
      super(EntityEquipmentSlot.FEET);
      setRegistryName("red_stone_boots");
      setUnlocalizedName("red_stone_boots");
    }
  }
}
