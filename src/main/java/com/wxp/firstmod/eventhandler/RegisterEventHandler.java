package com.wxp.firstmod.eventhandler;

import com.wxp.firstmod.FirstMod;
import com.wxp.firstmod.block.AbstractMultiStateBlock;
import com.wxp.firstmod.block.AbstractMyBlock;
import com.wxp.firstmod.block.MetalFurnaceBlock;
import com.wxp.firstmod.config.FirstModConfig;
import com.wxp.firstmod.manager.*;
import com.wxp.firstmod.potion.AbstractModPotion;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

import java.util.Map;
import java.util.Objects;

/** @author wxp before init after preInit */
@Mod.EventBusSubscriber(modid = FirstModConfig.MOD_ID)
public class RegisterEventHandler {

  @SubscribeEvent
  public static void registerBlocks(RegistryEvent.Register<Block> event) {
    FirstMod.getLogger().info("Register blocks");
    OBJLoader.INSTANCE.addDomain(FirstModConfig.MOD_ID);
    for (AbstractMyBlock block : BlockManager.getInitializedBlocks()) {
      event.getRegistry().register(block);
    }
    registerTileEntity();
    for (BlockFluidClassic blockFluidClassic : BlockManager.getInitializedFluidBlocks()) {
      event.getRegistry().register(blockFluidClassic);
    }
    registerFluidModel();
  }

  @SubscribeEvent
  public static void registerItem(RegistryEvent.Register<Item> event) {
    FirstMod.getLogger().info("Register items");
    for (Item item : ItemManager.getInitializedItems()) {
      event.getRegistry().register(item);
    }
    registerItemModel();
  }

  @SubscribeEvent
  public static void registerEnchantment(RegistryEvent.Register<Enchantment> event) {
    FirstMod.getLogger().info("Register enchantment");
    for (Enchantment enchantment : EnchantmentManager.getInitializedEnchantment()) {
      event.getRegistry().register(enchantment);
    }
  }

  @SubscribeEvent
  public static void registerPotion(RegistryEvent.Register<Potion> event) {
    FirstMod.getLogger().info("Register potion");
    for (Potion potion : PotionManager.getInitializedPotion()) {
      event.getRegistry().register(potion);
    }
  }

  @SubscribeEvent
  public static void registerPotionType(RegistryEvent.Register<PotionType> event) {
    FirstMod.getLogger().info("Register potionType");
    for (Potion potion : PotionManager.getInitializedPotion()) {
      event.getRegistry().register(((AbstractModPotion) potion).getPotionType());
    }
  }

  @SubscribeEvent
  public static void registerSoundEvent(RegistryEvent.Register<SoundEvent> event) {
    FirstMod.getLogger().info("Register soundEvent");
    for (SoundEvent soundEvent : SoundEventManager.getInitializedSoundEvent()) {
      event.getRegistry().register(soundEvent);
    }
  }

  @SubscribeEvent
  public static void registerEntity(RegistryEvent.Register<EntityEntry> event) {
    FirstMod.getLogger().info("Register entity");
    for (EntityEntry entityEntry : EntityManager.getInitializedEntity()) {
      event.getRegistry().register(entityEntry);
    }
  }

  private static void registerTileEntity() {
    FirstMod.getLogger().info("Register tile entity");
    for (Map.Entry<Class<? extends TileEntity>, String> entry :
        BlockManager.tileEntities.entrySet()) {
      GameRegistry.registerTileEntity(
          entry.getKey(), new ResourceLocation(FirstModConfig.MOD_ID, entry.getValue()));
    }
  }

  private static void registerFluidModel() {
    if (Side.CLIENT.equals(FMLCommonHandler.instance().getSide())) {
      FirstMod.getLogger().info("Register fluid model");
      for (BlockFluidClassic blockFluidClassic : BlockManager.getInitializedFluidBlocks()) {
        Item itemFluid = Item.getItemFromBlock(blockFluidClassic);
        ModelResourceLocation resourceLocation =
            new ModelResourceLocation(
                new ResourceLocation(FirstModConfig.MOD_ID, "mercury_block"), "fluid");
        ModelLoader.setCustomMeshDefinition(itemFluid, stack -> resourceLocation);
        ModelLoader.setCustomStateMapper(
            blockFluidClassic,
            new StateMapperBase() {
              @Override
              protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return resourceLocation;
              }
            });
      }
    }
  }

  private static void registerItemModel() {
    if (Side.CLIENT.equals(FMLCommonHandler.instance().getSide())) {
      FirstMod.getLogger().info("Register models");
      NonNullList<ItemStack> subItems = NonNullList.create();
      for (Item item : ItemManager.getInitializedItems()) {
        if (item instanceof AbstractMultiStateBlock.AbstractItemMultiTextureBlock) {
          AbstractMultiStateBlock.AbstractItemMultiTextureBlock itemBlock =
              (AbstractMultiStateBlock.AbstractItemMultiTextureBlock) item;
          if(itemBlock.getBlock() instanceof AbstractMultiStateBlock) {
            AbstractMultiStateBlock block = (AbstractMultiStateBlock) itemBlock.getBlock();
            ModelLoader.setCustomStateMapper(block, block.getBlockStateMapper());
          }
          itemBlock.getSubItems(CreativeTabManager.firstModCreativeTab, subItems);
          for (ItemStack itemStack : subItems) {
            ModelLoader.setCustomModelResourceLocation(
                itemStack.getItem(),
                itemStack.getMetadata(),
                new ModelResourceLocation(
                    Objects.requireNonNull(itemBlock.getRegistryName(itemStack)), "inventory"));
          }
          subItems.clear();
        } else {
          ModelLoader.setCustomModelResourceLocation(
              item,
              0,
              new ModelResourceLocation(
                  Objects.requireNonNull(item.getRegistryName()), "inventory"));
        }
      }
    } else {
      FirstMod.getLogger().info("Skip. Not run in server");
    }
  }
}
