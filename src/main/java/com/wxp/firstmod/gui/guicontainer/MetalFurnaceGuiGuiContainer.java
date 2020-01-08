package com.wxp.firstmod.gui.guicontainer;

import com.wxp.firstmod.config.FirstModConfig;
import com.wxp.firstmod.gui.container.MetalFurnaceGuiContainer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

/** @author wxp */
public class MetalFurnaceGuiGuiContainer extends GuiContainer {
  private static final ResourceLocation TEXTURE =
      new ResourceLocation(FirstModConfig.MOD_ID, "textures/gui/container/metal_furnace_gui.png");

  public MetalFurnaceGuiGuiContainer(MetalFurnaceGuiContainer inventorySlotsIn) {
    super(inventorySlotsIn);
    this.xSize = 176;
    this.ySize = 156;
  }

  @Override
  protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
    GlStateManager.color(1.0F, 1.0F, 1.0F);

    this.mc.getTextureManager().bindTexture(TEXTURE);
    int xOffset = (this.width - this.xSize) / 2;
    int yOffset = (this.height - this.ySize) / 2;

    this.drawTexturedModalRect(xOffset, yOffset, 0, 0, this.xSize, this.ySize);

    MetalFurnaceGuiContainer metalFurnaceGuiContainer =
        (MetalFurnaceGuiContainer) this.inventorySlots;
    int textureWidth =
        1
            + (int)
                Math.ceil(
                    22.0
                        * metalFurnaceGuiContainer.getBurnTime()
                        / metalFurnaceGuiContainer.getTotalBurnTime());
    this.drawTexturedModalRect(xOffset + 79, yOffset + 30, 0, 156, textureWidth, 17);
  }
}
