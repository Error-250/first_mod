package com.wxp.firstmod.manager;

import com.wxp.firstmod.fluid.MercuryFluid;
import net.minecraftforge.fluids.Fluid;

import java.util.*;

/** @author wxp */
public class FluidManager {
  private static Map<Class, Fluid> fluids;

  public static void initFluid() {
    fluids = new LinkedHashMap<>();

    MercuryFluid mercuryFluid = new MercuryFluid();
    fluids.put(mercuryFluid.getClass(), mercuryFluid);
  }

  public static Collection<Fluid> getInitializedFluid() {
    return fluids.values();
  }

  public static Fluid getFluidByClass(Class fluidClass) {
    return fluids.get(fluidClass);
  }
}
