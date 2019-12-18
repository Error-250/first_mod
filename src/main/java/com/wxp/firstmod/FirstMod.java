package com.wxp.firstmod;

import com.wxp.firstmod.config.FirstModConfig;
import com.wxp.firstmod.manager.*;
import com.wxp.firstmod.proxy.ModProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Logger;

/** @author wxp */
@Mod(
    modid = FirstModConfig.MOD_ID,
    name = FirstModConfig.MON_NAME,
    version = FirstModConfig.VERSION)
public class FirstMod {
  private static Logger logger;

  @SidedProxy(
      clientSide = "com.wxp.firstmod.proxy.ClientProxy",
      serverSide = "com.wxp.firstmod.proxy.ServerProxy",
      modId = FirstModConfig.MOD_ID)
  public static ModProxy modProxy;

  @EventHandler
  public void preInit(FMLPreInitializationEvent event) {
    logger = event.getModLog();
    FluidManager.initFluid();
    modProxy.preInit(event);
    CreativeTabManager.initCreativeTab();
    BlockManager.initBlock();
    BlockManager.initFluidBlock();
    SoundEventManager.initSoundEvent();
    ItemManager.initItem();
    RecipeManager.initRecipes();
    EnchantmentManager.initEnchantment();
    PotionManager.initPotion();
    KeyManager.initKey();
    CommandManager.initCommand();
    GeneratorManager.initGenerator();
    EntityManager.initEntity();
    DamageSourceManager.initDamageSource();
    FakePlayerManager.initFakePlayer();
  }

  @EventHandler
  public void serverStarting(FMLServerStartingEvent event) {
    logger.info("Server start");
    modProxy.serverStarting(event);
  }

  @EventHandler
  public void init(FMLInitializationEvent event) {
    logger.info("Init");
    modProxy.init(event);
  }

  public static Logger getLogger() {
    return logger;
  }
}
