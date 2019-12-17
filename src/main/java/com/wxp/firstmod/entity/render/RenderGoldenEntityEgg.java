package com.wxp.firstmod.entity.render;

import com.wxp.firstmod.entity.GoldenEntityEgg;
import com.wxp.firstmod.item.GoldenEggItem;
import com.wxp.firstmod.manager.ItemManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.Item;

/** @author wxp */
public class RenderGoldenEntityEgg extends RenderSnowball<GoldenEntityEgg> {
  public RenderGoldenEntityEgg(RenderManager renderManagerIn) {
    super(
        renderManagerIn,
        ItemManager.getItemByClass(GoldenEggItem.class),
        Minecraft.getMinecraft().getRenderItem());
  }
}
