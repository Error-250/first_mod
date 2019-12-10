package com.wxp.firstmod.entity;

import com.wxp.firstmod.config.FirstModConfig;
import com.wxp.firstmod.manager.EntityManager;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

import javax.annotation.Nullable;

/** @author wxp */
public class GoldenChickenEntity extends EntityChicken {
  private static String entityName = "golden_chicken";

  public GoldenChickenEntity(World worldIn) {
    super(worldIn);
  }

  @Override
  public void onLivingUpdate() {
    super.onLivingUpdate();
  }

  @Nullable
  @Override
  protected ResourceLocation getLootTable() {
    return new ResourceLocation(FirstModConfig.MOD_ID, entityName);
  }

  public static EntityEntry getEntity() {
    return EntityEntryBuilder.create()
        .id(
            new ResourceLocation(FirstModConfig.MOD_ID, entityName),
            EntityManager.getIdByClass(GoldenChickenEntity.class))
        .name(entityName)
        .tracker(80, 3, true)
        .entity(GoldenChickenEntity.class)
        .egg(0xffff66, 0x660000)
        .build();
  }
}
