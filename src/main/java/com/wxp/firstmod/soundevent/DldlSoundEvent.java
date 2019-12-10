package com.wxp.firstmod.soundevent;

import com.wxp.firstmod.config.FirstModConfig;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

/**
 * @author wxp
 */
public class DldlSoundEvent extends SoundEvent {
  public DldlSoundEvent() {
    super(new ResourceLocation(FirstModConfig.MOD_ID, "first_mod.dldl"));
    setRegistryName("first_mod.dldl");
  }
}
