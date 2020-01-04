package com.wxp.firstmod.gui.guicontainer;

import com.wxp.firstmod.config.FirstModConfig;
import com.wxp.firstmod.gui.container.FirstGuiContainer;
import com.wxp.firstmod.item.GoldenEggItem;
import com.wxp.firstmod.manager.ItemManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;

/** @author wxp */
public class FirstGuiGuiContainer extends GuiContainer {
  private static final ResourceLocation background =
      new ResourceLocation(FirstModConfig.MOD_ID, "textures/gui/container/first_gui.png");

  public FirstGuiGuiContainer(FirstGuiContainer container) {
    super(container);
    this.xSize = 176;
    this.ySize = 133;
  }

  @Override
  protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
    GlStateManager.color(1.0F, 1.0F, 1.0F);

    this.mc.getTextureManager().bindTexture(background);
    int xOffset = (this.width - this.xSize) / 2;
    int yOffset = (this.height - this.ySize) / 2;

    this.drawTexturedModalRect(xOffset, yOffset, 0, 0, this.xSize, this.ySize);
  }

  @Override
  protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
    this.drawVerticalLine(30, 19, 36, 0xFF000000);
    this.drawHorizontalLine(8, 167, 43, 0xFF000000);

    TextComponentTranslation textComponents = new TextComponentTranslation("container.first_gui");
    String title = textComponents.getFormattedText();
    this.fontRenderer.drawString(
        title, (this.xSize - this.fontRenderer.getStringWidth(title)) / 2, 6, 0x404040);

    this.itemRender.renderItemAndEffectIntoGUI(
        new ItemStack(ItemManager.getItemByClass(GoldenEggItem.class)), 8, 20);
    super.drawGuiContainerForegroundLayer(mouseX, mouseY);
  }

  @Override
  public void initGui() {
    super.initGui();
    int xOffset = (this.width - this.xSize) / 2;
    int yOffset = (this.height - this.ySize) / 2;
    this.buttonList.add(
        new GuiButton(0, xOffset + 153, yOffset + 17, 15, 10, "测试") {
          @Override
          public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            if (this.visible) {
              GlStateManager.color(1.0F, 1.0F, 1.0F);

              mc.getTextureManager().bindTexture(background);
              int x = mouseX - this.x;
              int y = mouseY - this.y;

              if (x >= 0 && y >= 0 && x < this.width && y < this.height) {
                this.drawTexturedModalRect(this.x, this.y, 1, 146, this.width, this.height);
              } else {
                this.drawTexturedModalRect(this.x, this.y, 1, 134, this.width, this.height);
              }
            }
          }
        });

    this.buttonList.add(
        new GuiButton(1, xOffset + 153, yOffset + 29, 15, 10, "测试2") {
          @Override
          public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            if (this.visible) {
              GlStateManager.color(1.0F, 1.0F, 1.0F);

              mc.getTextureManager().bindTexture(background);
              int x = mouseX - this.x;
              int y = mouseY - this.y;

              if (x >= 0 && y >= 0 && x < this.width && y < this.height) {
                this.drawTexturedModalRect(this.x, this.y, 20, 146, this.width, this.height);
              } else {
                this.drawTexturedModalRect(this.x, this.y, 20, 134, this.width, this.height);
              }
            }
          }
        });
  }
}
