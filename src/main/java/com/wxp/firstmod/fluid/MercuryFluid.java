package com.wxp.firstmod.fluid;

import com.wxp.firstmod.config.FirstModConfig;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

/** @author wxp */
public class MercuryFluid extends Fluid {
  private static ResourceLocation still =
      new ResourceLocation(FirstModConfig.MOD_ID, "fluid/mercury_still");
  private static ResourceLocation flowing =
      new ResourceLocation(FirstModConfig.MOD_ID, "fluid/mercury_flow");

  public MercuryFluid() {
    super("mercury", still, flowing);
    setUnlocalizedName("mercury");
    this.setDensity(13600);
    this.setViscosity(750);
    this.setLuminosity(0);
    this.setTemperature(300);
  }
}
