package com.wxp.firstmod.manager;

import com.wxp.firstmod.soundevent.DldlSoundEvent;
import net.minecraft.util.SoundEvent;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/** @author wxp */
public class SoundEventManager {
  private static Map<Class, SoundEvent> soundEvents;

  public static void initSoundEvent() {
    soundEvents = new LinkedHashMap<>();

    DldlSoundEvent dldlSoundEvent = new DldlSoundEvent();
    soundEvents.put(dldlSoundEvent.getClass(), dldlSoundEvent);
  }

  public static Collection<SoundEvent> getInitializedSoundEvent() {
    return soundEvents.values();
  }

  public static SoundEvent getSoundEventByClass(Class soundClass) {
    return soundEvents.get(soundClass);
  }
}
