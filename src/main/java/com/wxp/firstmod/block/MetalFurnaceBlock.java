package com.wxp.firstmod.block;

import com.wxp.firstmod.manager.ItemManager;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Objects;
import java.util.Random;

/** @author wxp */
public class MetalFurnaceBlock extends AbstractMultiStateBlock {
  private static final PropertyDirection FACING =
      PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
  private static final PropertyBool BURNING = PropertyBool.create("burning");
  private static final PropertyEnum<MaterialEnum> MATERIAL =
      PropertyEnum.create("material", MaterialEnum.class);

  public MetalFurnaceBlock() {
    super(Material.IRON);
    setUnlocalizedName("metal_furnace");
    setHardness(2.5f);
    setSoundType(SoundType.METAL);
    setRegistryName("metal_furnace");
    setDefaultState(
        this.getBlockState()
            .getBaseState()
            .withProperty(FACING, EnumFacing.NORTH)
            .withProperty(BURNING, Boolean.FALSE)
            .withProperty(MATERIAL, MaterialEnum.IRON));
  }

  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, FACING, BURNING, MATERIAL);
  }

  @Override
  public AbstractItemMultiTextureBlock getItemBlock() {
    AbstractItemMultiTextureBlock itemBlock =
        new AbstractItemMultiTextureBlock(this) {
          @Override
          public String getRegistryName(ItemStack stack) {
            if ((stack.getMetadata() & 0b1000) == 0) {
              return this.block.getRegistryName() + "_iron";
            } else {
              return this.block.getRegistryName() + "_golden";
            }
          }

          @Override
          public String getUnlocalizedName(ItemStack stack) {
            if ((stack.getMetadata() & 0b1000) == 0) {
              return this.block.getUnlocalizedName() + "_iron";
            } else {
              return this.block.getUnlocalizedName() + "_golden";
            }
          }

          @Override
          public int getMetadata(int damage) {
            return damage;
          }
        };
    itemBlock.setRegistryName(Objects.requireNonNull(this.getRegistryName()));
    return itemBlock;
  }

  @Override
  public void onBlockPlacedBy(
      World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
    worldIn.setBlockState(
        pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()));
  }

  @Override
  public boolean onBlockActivated(
      World worldIn,
      BlockPos pos,
      IBlockState state,
      EntityPlayer playerIn,
      EnumHand hand,
      EnumFacing facing,
      float hitX,
      float hitY,
      float hitZ) {
    worldIn.setBlockState(pos, state.cycleProperty(BURNING));
    return Boolean.TRUE;
  }

  @Override
  public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
    if (stateIn.getValue(BURNING)) {
      EnumFacing enumfacing = stateIn.getValue(FACING);
      double d0 = (double) pos.getX() + 0.5D;
      double d1 = (double) pos.getY() + rand.nextDouble() * 6.0D / 16.0D;
      double d2 = (double) pos.getZ() + 0.5D;
      double d4 = rand.nextDouble() * 0.6D - 0.3D;

      if (rand.nextDouble() < 0.1D) {
        worldIn.playSound(
            (double) pos.getX() + 0.5D,
            (double) pos.getY(),
            (double) pos.getZ() + 0.5D,
            SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE,
            SoundCategory.BLOCKS,
            1.0F,
            1.0F,
            false);
      }

      switch (enumfacing) {
        case WEST:
          worldIn.spawnParticle(
              EnumParticleTypes.SMOKE_NORMAL, d0 - 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
          worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 - 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
          break;
        case EAST:
          worldIn.spawnParticle(
              EnumParticleTypes.SMOKE_NORMAL, d0 + 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
          worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
          break;
        case NORTH:
          worldIn.spawnParticle(
              EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 - 0.52D, 0.0D, 0.0D, 0.0D);
          worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 - 0.52D, 0.0D, 0.0D, 0.0D);
          break;
        case SOUTH:
          worldIn.spawnParticle(
              EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 + 0.52D, 0.0D, 0.0D, 0.0D);
          worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 + 0.52D, 0.0D, 0.0D, 0.0D);
      }
    }
  }

  @Override
  public int damageDropped(IBlockState state) {
    return getMetaFromState(state) & 0b1000;
  }

  @Override
  public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
    items.add(new ItemStack(ItemManager.getItemByClass(MetalFurnaceBlock.class), 1, 0b0000));
    items.add(new ItemStack(ItemManager.getItemByClass(MetalFurnaceBlock.class), 1, 0b1000));
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    int material = state.getValue(MATERIAL).equals(MaterialEnum.IRON) ? 0b0000 : 0b1000;
    int burning = state.getValue(BURNING) ? 0b0100 : 0b0000;
    int facing = state.getValue(FACING).getHorizontalIndex();
    return material | burning | facing;
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    MaterialEnum material = (meta & 0b1000) == 0 ? MaterialEnum.IRON : MaterialEnum.GOLD;
    boolean burning = (meta & 0b0100) != 0;
    EnumFacing facing = EnumFacing.getHorizontal(meta & 0b0011);
    return getDefaultState()
        .withProperty(MATERIAL, material)
        .withProperty(BURNING, burning)
        .withProperty(FACING, facing);
  }

  public enum MaterialEnum implements IStringSerializable {
    /** 铁 */
    IRON,
    /** 金 */
    GOLD;

    @Override
    public String getName() {
      return name().toLowerCase();
    }
  }
}