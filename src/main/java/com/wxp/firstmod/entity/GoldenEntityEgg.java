package com.wxp.firstmod.entity;

import com.wxp.firstmod.config.FirstModConfig;
import com.wxp.firstmod.manager.EntityManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

/** @author wxp */
public class GoldenEntityEgg extends EntityThrowable {
  public GoldenEntityEgg(World worldIn) {
    super(worldIn);
  }

  public GoldenEntityEgg(World worldIn, EntityLivingBase throwerIn) {
    super(worldIn, throwerIn);
  }

  public GoldenEntityEgg(World worldIn, double x, double y, double z) {
    super(worldIn, x, y, z);
  }

  @Override
  protected void onImpact(RayTraceResult result) {
    if (result.entityHit != null) {
      result.entityHit.attackEntityFrom(
          DamageSource.causeThrownDamage(this, this.getThrower()), 1.0f);
    }
    if (!this.world.isRemote) {
      GoldenChickenEntity goldenChickenEntity = new GoldenChickenEntity(this.world);
      goldenChickenEntity.setGrowingAge(0);
      goldenChickenEntity.setLocationAndAngles(
          this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
      this.world.spawnEntity(goldenChickenEntity);

      this.world.setEntityState(this, (byte) 3);
      this.setDead();
    }
  }

  public static EntityEntry getEntity() {
    return EntityEntryBuilder.create()
        .id(
            new ResourceLocation(FirstModConfig.MOD_ID, "golden_egg"),
            EntityManager.getIdByClass(GoldenEntityEgg.class))
        .name("golden_egg")
        .tracker(64, 10, true)
        .entity(GoldenEntityEgg.class)
        .build();
  }
}
