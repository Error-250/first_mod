package com.wxp.firstmod.register;

import com.wxp.firstmod.manager.KeyManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

/** @author wxp */
public class KeyRegister {
  public static void registerKey() {
    for (KeyBinding keyBinding : KeyManager.getInitializedKeys()) {
      ClientRegistry.registerKeyBinding(keyBinding);
    }
  }
}
