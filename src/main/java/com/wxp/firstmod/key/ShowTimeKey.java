package com.wxp.firstmod.key;

import com.wxp.firstmod.config.FirstModConfig;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

/** @author wxp */
public class ShowTimeKey extends KeyBinding {
  public ShowTimeKey() {
    super(
        String.format("key.%s.show_time", FirstModConfig.MOD_ID),
        Keyboard.KEY_H,
        String.format("key.categories.%s", FirstModConfig.MOD_ID));
  }
}
