package com.wxp.firstmod.proxy;

import com.wxp.firstmod.entity.GoldenChickenEntity;
import com.wxp.firstmod.entity.factory.GoldenChickenRenderFactory;
import com.wxp.firstmod.eventhandler.OreGenEventHandler;
import com.wxp.firstmod.manager.CommandManager;
import com.wxp.firstmod.register.CraftingRegister;
import com.wxp.firstmod.register.FluidRegister;
import com.wxp.firstmod.register.KeyRegister;
import net.minecraft.command.CommandBase;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

/** @author wxp */
public class ClientProxy implements ModProxy {
  @Override
  public void preInit(FMLPreInitializationEvent event) {
    FluidRegister.registerFluid();
    RenderingRegistry.registerEntityRenderingHandler(
        GoldenChickenEntity.class, new GoldenChickenRenderFactory());
  }

  @Override
  public void init(FMLInitializationEvent event) {
    MinecraftForge.ORE_GEN_BUS.register(OreGenEventHandler.class);
    CraftingRegister.registerRecipe();
    KeyRegister.registerKey();
  }

  @Override
  public void serverStarting(FMLServerStartingEvent event) {
    for (CommandBase commandBase : CommandManager.getInitializedCommand()) {
      event.registerServerCommand(commandBase);
    }
  }
}
