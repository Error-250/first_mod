package com.wxp.firstmod.eventhandler;

import com.wxp.firstmod.capability.provider.PositionHistoryProvider;
import com.wxp.firstmod.config.FirstModConfig;
import com.wxp.firstmod.damagesource.PowerDamageSource;
import com.wxp.firstmod.item.RedStoneArmorItem;
import com.wxp.firstmod.manager.DamageSourceManager;
import com.wxp.firstmod.manager.PotionManager;
import com.wxp.firstmod.potion.FallProtectPotion;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/** @author wxp */
@Mod.EventBusSubscriber(modid = FirstModConfig.MOD_ID)
public class PlayerEventHandler {
  private static final String FALL_DAMAGE_TYPE = "fall";

  @SubscribeEvent
  public static void onPlayerAttackEntity(AttackEntityEvent event) {
    EntityPlayer player = event.getEntityPlayer();
    if (player.isServerWorld()) {
      int redStoneArmorCount = 0;
      if (player.getHeldItemMainhand().getItem() instanceof ItemSword) {
        for (ItemStack itemStack : player.getArmorInventoryList()) {
          if (itemStack.getItem() instanceof RedStoneArmorItem) {
            redStoneArmorCount++;
          }
        }
      }
      if (redStoneArmorCount > 0) {
        event
            .getTarget()
            .attackEntityFrom(
                DamageSourceManager.getDamageSourceByClass(PowerDamageSource.class),
                redStoneArmorCount * 1.5f);
      }
    }
  }

  @SubscribeEvent
  public static void onLivingHurt(LivingHurtEvent event) {
    if (FALL_DAMAGE_TYPE.equals(event.getSource().getDamageType())) {
      PotionEffect effect =
          event
              .getEntityLiving()
              .getActivePotionEffect(PotionManager.getPotionByClass(FallProtectPotion.class));
      if (effect != null) {
        if (effect.getAmplifier() == 0) {
          event.setAmount(event.getAmount() / 2);
        } else {
          event.setAmount(0f);
        }
      }
    }
  }

  @SubscribeEvent
  public static void onAttachCapabilitiesEntity(AttachCapabilitiesEvent<Entity> event) {
    Entity entity = event.getObject();
    if (entity instanceof EntityPlayer) {
      PositionHistoryProvider positionHistoryProvider = new PositionHistoryProvider();
      event.addCapability(
          new ResourceLocation(FirstModConfig.MOD_ID, "position_history"), positionHistoryProvider);
    }
  }
}
