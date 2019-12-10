package com.wxp.firstmod.entity.factory;

import com.wxp.firstmod.entity.GoldenChickenEntity;
import com.wxp.firstmod.entity.render.GoldenChickRender;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

/** @author wxp */
public class GoldenChickenRenderFactory implements IRenderFactory<GoldenChickenEntity> {
  @Override
  public Render<? super GoldenChickenEntity> createRenderFor(RenderManager manager) {
    return new GoldenChickRender(manager);
  }
}
