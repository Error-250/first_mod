package com.wxp.firstmod.item;

import com.wxp.firstmod.manager.SoundEventManager;
import com.wxp.firstmod.soundevent.DldlSoundEvent;
import net.minecraft.item.ItemRecord;

/** @author wxp */
public class DldlRecordItem extends ItemRecord {
  public DldlRecordItem() {
    super("dldl", SoundEventManager.getSoundEventByClass(DldlSoundEvent.class));
    setRegistryName("dldl_record");
    setUnlocalizedName("dldl_record");
  }
}
