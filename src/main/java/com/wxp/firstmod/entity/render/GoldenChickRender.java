package com.wxp.firstmod.entity.render;

import com.wxp.firstmod.config.FirstModConfig;
import com.wxp.firstmod.entity.GoldenChickenEntity;
import com.wxp.firstmod.entity.model.GoldenChickenModel;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

/** @author wxp */
public class GoldenChickRender extends RenderLiving<GoldenChickenEntity> {
  public GoldenChickRender(RenderManager renderManager) {
    super(renderManager, new GoldenChickenModel(), 0.5f);
  }

  @Override
  protected void preRenderCallback(GoldenChickenEntity goldenChickenEntity, float partialTickTime) {
//    GlStateManager.scale(2.5f, 2.5f, 2.5f);
  }

  @Nullable
  @Override
  protected ResourceLocation getEntityTexture(GoldenChickenEntity entity) {
    return new ResourceLocation(FirstModConfig.MOD_ID, "textures/entity/golden_chicken.png");
  }

  @Override
  public void doRender(GoldenChickenEntity entity, double x, double y, double z, float entityYaw, float partialTicks) {
    super.doRender(entity, x, y, z, entityYaw, partialTicks);
  }
}
