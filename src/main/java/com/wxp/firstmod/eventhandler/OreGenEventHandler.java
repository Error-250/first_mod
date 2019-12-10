package com.wxp.firstmod.eventhandler;

import com.wxp.firstmod.manager.GeneratorManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/** @author wxp */
public class OreGenEventHandler {
  private static BlockPos lastPos;

  @SubscribeEvent
  public static void onOreGenGenerate(OreGenEvent.GenerateMinable event) {
    if (OreGenEvent.GenerateMinable.EventType.DIRT.equals(event.getType())) {
      event.setResult(Event.Result.DENY);
    }
  }

  @SubscribeEvent
  public static void onOreGenPost(OreGenEvent.Post event) {
    if (event.getPos().equals(lastPos)) {
      return;
    }
    for (WorldGenerator worldGenerator : GeneratorManager.getInitializedGenerator()) {
      worldGenerator.generate(event.getWorld(), event.getRand(), event.getPos());
    }
    lastPos = event.getPos();
  }
}
