package com.wxp.firstmod.potion;

import com.wxp.firstmod.config.FirstModConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;

/** @author wxp */
public class FallProtectPotion extends AbstractModPotion {
  public FallProtectPotion() {
    super(Boolean.FALSE, 0x00f000);
    setPotionName("effect.fall_protect");
    setRegistryName(FirstModConfig.MOD_ID, "fall_protect");
  }

  @Override
  public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
    mc.getTextureManager().bindTexture(new ResourceLocation(FirstModConfig.MOD_ID, "textures/gui/potion.png"));
    Objects.requireNonNull(mc.currentScreen).drawTexturedModalRect(x + 6, y + 7, 0, 0, 18, 18);
  }

  @Override
  public PotionType getPotionType() {
    return new FallProtectPotionType(this);
  }

  public static class FallProtectPotionType extends PotionType {
    FallProtectPotionType(FallProtectPotion fallProtectPotion) {
      super(new PotionEffect(fallProtectPotion, 2000));
      setRegistryName("fall_protect");
    }
  }
}
