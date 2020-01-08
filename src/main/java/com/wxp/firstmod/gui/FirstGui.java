package com.wxp.firstmod.gui;

import com.wxp.firstmod.block.tileentity.MetalFurnaceTileEntity;
import com.wxp.firstmod.gui.container.FirstGuiContainer;
import com.wxp.firstmod.gui.container.MetalFurnaceGuiContainer;
import com.wxp.firstmod.gui.guicontainer.FirstGuiGuiContainer;
import com.wxp.firstmod.gui.guicontainer.MetalFurnaceGuiGuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

/** @author wxp */
public class FirstGui implements IGuiHandler {
  private static final int DEMO = 1;
  private static final int METAL_FURNACE = 2;

  @Nullable
  @Override
  public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    switch (ID) {
      case DEMO:
        return new FirstGuiContainer(player);
      case METAL_FURNACE:
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
        if (tileEntity instanceof MetalFurnaceTileEntity) {
          return new MetalFurnaceGuiContainer(player, (MetalFurnaceTileEntity) tileEntity);
        }
        return null;
      default:
        return null;
    }
  }

  @Nullable
  @Override
  public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    switch (ID) {
      case DEMO:
        return new FirstGuiGuiContainer(new FirstGuiContainer(player));
      case METAL_FURNACE:
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
        if (tileEntity instanceof MetalFurnaceTileEntity) {
          return new MetalFurnaceGuiGuiContainer(
              new MetalFurnaceGuiContainer(player, (MetalFurnaceTileEntity) tileEntity));
        }
        return null;
      default:
        return null;
    }
  }
}
