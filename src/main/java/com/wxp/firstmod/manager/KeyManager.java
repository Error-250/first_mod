package com.wxp.firstmod.manager;

import com.wxp.firstmod.key.ShowTimeKey;
import net.minecraft.client.settings.KeyBinding;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author wxp
 */
public class KeyManager {
  private static Map<Class, KeyBinding> keyBindings;

  public static void initKey() {
    keyBindings = new LinkedHashMap<>();

    ShowTimeKey showTimeKey = new ShowTimeKey();
    keyBindings.put(showTimeKey.getClass(), showTimeKey);
  }

  public static Collection<KeyBinding> getInitializedKeys() {
    return keyBindings.values();
  }

  public static KeyBinding getKeyByClass(Class keyClass) {
    return keyBindings.get(keyClass);
  }
}
