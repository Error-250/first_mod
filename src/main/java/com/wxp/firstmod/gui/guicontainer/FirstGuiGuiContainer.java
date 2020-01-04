package com.wxp.firstmod.gui.guicontainer;

import com.wxp.firstmod.gui.container.FirstGuiContainer;
import net.minecraft.client.gui.inventory.GuiContainer;

/** @author wxp */
public class FirstGuiGuiContainer extends GuiContainer {
  public FirstGuiGuiContainer(FirstGuiContainer container) {
    super(container);
  }

  @Override
  protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
    //
  }

  @Override
  protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
    super.drawGuiContainerForegroundLayer(mouseX, mouseY);
  }
}
