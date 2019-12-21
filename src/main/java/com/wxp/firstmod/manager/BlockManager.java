package com.wxp.firstmod.manager;

import com.wxp.firstmod.block.AbstractMyBlock;
import com.wxp.firstmod.block.MercuryFluidBlock;
import com.wxp.firstmod.block.MetalFurnaceBlock;
import com.wxp.firstmod.block.MyGrassBlock;
import com.wxp.firstmod.block.tileentity.MetalFurnaceTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.BlockFluidClassic;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/** @author wxp */
public class BlockManager {
  private static Map<Class, AbstractMyBlock> blocks;
  private static Map<Class, BlockFluidClassic> fluidBlocks;
  public static Map<Class<? extends TileEntity>, String> tileEntities;

  public static void initBlock() {
    blocks = new LinkedHashMap<>();
    MyGrassBlock myGrassBlock = new MyGrassBlock();
    myGrassBlock.setCreativeTab(CreativeTabManager.firstModCreativeTab);
    blocks.put(myGrassBlock.getClass(), myGrassBlock);

    MetalFurnaceBlock metalFurnaceBlock = new MetalFurnaceBlock();
    metalFurnaceBlock.setCreativeTab(CreativeTabManager.firstModCreativeTab);
    blocks.put(metalFurnaceBlock.getClass(), metalFurnaceBlock);

    tileEntities = new LinkedHashMap<>();
    tileEntities.put(MetalFurnaceTileEntity.class, "metal_furnace_tile_entity");
  }

  public static void initFluidBlock() {
    fluidBlocks = new LinkedHashMap<>();
    MercuryFluidBlock mercuryFluidBlock = new MercuryFluidBlock();
    mercuryFluidBlock.setCreativeTab(CreativeTabManager.firstModCreativeTab);
    fluidBlocks.put(mercuryFluidBlock.getClass(), mercuryFluidBlock);
  }

  public static Collection<AbstractMyBlock> getInitializedBlocks() {
    return blocks.values();
  }

  public static Collection<BlockFluidClassic> getInitializedFluidBlocks() {
    return fluidBlocks.values();
  }

  public static AbstractMyBlock getBlockByClass(Class blockClass) {
    return blocks.get(blockClass);
  }

  public static BlockFluidClassic getFluidBlockByClass(Class fluidBlockClass) {
    return fluidBlocks.get(fluidBlockClass);
  }
}
