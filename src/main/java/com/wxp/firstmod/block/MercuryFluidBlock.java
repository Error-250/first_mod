package com.wxp.firstmod.block;

import com.wxp.firstmod.fluid.MercuryFluid;
import com.wxp.firstmod.manager.FluidManager;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidClassic;

/**
 * @author wxp
 */
public class MercuryFluidBlock extends BlockFluidClassic {
  public MercuryFluidBlock() {
    super(FluidManager.getFluidByClass(MercuryFluid.class), Material.WATER);
    setRegistryName("mercury_block");
    setUnlocalizedName("mercury_block");
  }
}
