package com.wxp.firstmod.proxy;

import com.wxp.firstmod.manager.CommandManager;
import com.wxp.firstmod.register.CraftingRegister;
import com.wxp.firstmod.register.FluidRegister;
import net.minecraft.command.CommandBase;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

/**
 * @author wxp
 * when you need regist a block/item or other thing in game will user this.
 * But do not do any operate that should do in ClientProxy, for example GUI,sound.
 */
public class ServerProxy implements ModProxy {
  @Override
  public void preInit(FMLPreInitializationEvent event) {
    FluidRegister.registerFluid();
  }

  @Override
  public void init(FMLInitializationEvent event) {
    CraftingRegister.registerRecipe();
  }

  @Override
  public void serverStarting(FMLServerStartingEvent event) {
    for(CommandBase commandBase: CommandManager.getInitializedCommand()) {
      event.registerServerCommand(commandBase);
    }
  }
}
