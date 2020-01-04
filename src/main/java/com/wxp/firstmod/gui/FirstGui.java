package com.wxp.firstmod.gui;

import com.wxp.firstmod.gui.container.FirstGuiContainer;
import com.wxp.firstmod.gui.guicontainer.FirstGuiGuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

/** @author wxp */
public class FirstGui implements IGuiHandler {
  private static final int DEMO = 1;

  @Nullable
  @Override
  public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    if (ID == DEMO) {
      return new FirstGuiContainer(player);
    }
    return null;
  }

  @Nullable
  @Override
  public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    if (ID == DEMO) {
      return new FirstGuiGuiContainer(new FirstGuiContainer(player));
    }
    return null;
  }
}
