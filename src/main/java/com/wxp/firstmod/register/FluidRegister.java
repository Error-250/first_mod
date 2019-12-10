package com.wxp.firstmod.register;

import com.wxp.firstmod.manager.FluidManager;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

/** @author wxp */
public class FluidRegister {
  public static void registerFluid() {
    // event 顺序 2
    for (Fluid fluid : FluidManager.getInitializedFluid()) {
      if (FluidRegistry.isFluidRegistered(fluid)) {
        continue;
      }
      FluidRegistry.registerFluid(fluid);
    }
  }
}
