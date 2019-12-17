package com.wxp.firstmod.entity;

import com.wxp.firstmod.FirstMod;
import com.wxp.firstmod.config.FirstModConfig;
import com.wxp.firstmod.manager.EntityManager;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

import javax.annotation.Nullable;

/** @author wxp */
public class GoldenChickenEntity extends EntityChicken {
  private static String entityName = "golden_chicken";
  private static IAttribute wingSpeed = new RangedAttribute(null, "wing_speed", 1.5, 0, 4);
  private static DataParameter<Float> wingSpeedData =
      EntityDataManager.createKey(GoldenChickenEntity.class, DataSerializers.FLOAT);

  public GoldenChickenEntity(World worldIn) {
    super(worldIn);
    setSize(1.2F, 1.8F);
  }

  @Override
  protected void entityInit() {
    super.entityInit();
    dataManager.register(wingSpeedData, 0f);
  }

  @Override
  public void readEntityFromNBT(NBTTagCompound compound) {
    super.readEntityFromNBT(compound);
    dataManager.set(wingSpeedData, compound.getFloat("wingSpeed"));
  }

  @Override
  public void writeEntityToNBT(NBTTagCompound compound) {
    super.writeEntityToNBT(compound);
    compound.setFloat("wingSpeed", dataManager.get(wingSpeedData));
  }

  @Override
  public boolean processInteract(EntityPlayer player, EnumHand hand) {
    if (!super.processInteract(player, hand)) {
      float currentWingSpeed = dataManager.get(wingSpeedData);
      dataManager.set(wingSpeedData, (currentWingSpeed + 1) % 5);
    }
    return true;
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

  @Override
  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    this.getAttributeMap().registerAttribute(wingSpeed);

    this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0);
    this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5);
    this.getEntityAttribute(wingSpeed).setBaseValue(1 + this.rand.nextDouble());
  }

  public double getWingSpeed() {
    double wingSpeedAttr = getEntityAttribute(wingSpeed).getAttributeValue();
    float data = dataManager.get(wingSpeedData);
    if (data > 1) {
      return wingSpeedAttr / data;
    }
    return wingSpeedAttr;
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
        .spawn(EnumCreatureType.CREATURE, 8, 2, 4, Biomes.PLAINS, Biomes.DESERT)
        .build();
  }
}
