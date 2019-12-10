package com.wxp.firstmod.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

/**
 * @author wxp
 * when mod init, ModProxy will be use.
 * In Server, ServerProxy will be instanced.
 * In client, ClientProxy will be instanced.
 * */
public interface ModProxy {
  void preInit(FMLPreInitializationEvent event);

  void init(FMLInitializationEvent event);

  void serverStarting(FMLServerStartingEvent event);
}
