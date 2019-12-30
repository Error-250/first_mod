package com.wxp.firstmod.block;

import com.wxp.firstmod.FirstMod;
import com.wxp.firstmod.block.tileentity.ObjMetalFurnaceTileEntity;
import com.wxp.firstmod.config.FirstModConfig;
import com.wxp.firstmod.manager.ItemManager;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.common.model.TRSRTransformation;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.Properties;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nullable;
import javax.vecmath.Matrix4f;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;

/** @author wxp */
public class ObjMetalFurnaceBlock extends AbstractMultiStateBlock implements ITileEntityProvider {
  private static final PropertyDirection FACING =
      PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
  public static final PropertyBool BURNING = PropertyBool.create("burning");
  public static final PropertyEnum<MaterialEnum> MATERIAL =
      PropertyEnum.create("material", MaterialEnum.class);
  private final String name = "obj_metal_furnace";

  public ObjMetalFurnaceBlock() {
    super(Material.IRON);
    setUnlocalizedName(name);
    setHardness(2.5f);
    setSoundType(SoundType.METAL);
    setRegistryName(name);
    setDefaultState(
        this.getBlockState()
            .getBaseState()
            .withProperty(FACING, EnumFacing.NORTH)
            .withProperty(BURNING, Boolean.FALSE)
            .withProperty(MATERIAL, MaterialEnum.IRON));
  }

  @Override
  public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos) {
    IExtendedBlockState extendedBlockState = (IExtendedBlockState) state;
    TRSRTransformation trsrTransformation = TRSRTransformation.from(state.getValue(FACING));
    TileEntity tileEntity = world.getTileEntity(pos);
    if (tileEntity instanceof ObjMetalFurnaceTileEntity) {
      ObjMetalFurnaceTileEntity objMetalFurnaceTileEntity = (ObjMetalFurnaceTileEntity) tileEntity;
      Matrix4f matrix = new Matrix4f();
      matrix.rotY(objMetalFurnaceTileEntity.getRotationDegree().floatValue());
      trsrTransformation =
          TRSRTransformation.blockCenterToCorner(new TRSRTransformation(matrix))
              .compose(trsrTransformation);
    }
    OBJModel.OBJState objState =
        new OBJModel.OBJState(
            Collections.singletonList(OBJModel.Group.ALL), true, trsrTransformation);
    if (state.getValue(BURNING)) {
      FirstMod.getLogger().info("state:{}", objState);
    }
    return extendedBlockState.withProperty(Properties.AnimationProperty, objState);
  }

  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer.Builder(this)
        .add(FACING, BURNING, MATERIAL)
        .add(Properties.AnimationProperty)
        .build();
  }

  @Override
  public boolean isFullCube(IBlockState state) {
    return false;
  }

  @Override
  public boolean isOpaqueCube(IBlockState state) {
    return false;
  }

  @Override
  public AbstractItemMultiTextureBlock getItemBlock() {
    AbstractItemMultiTextureBlock itemBlock =
        new AbstractItemMultiTextureBlock(this) {
          @Override
          public String getRegistryName(ItemStack stack) {
            if ((stack.getMetadata() & 0b1000) == 0) {
              return new ResourceLocation(FirstModConfig.MOD_ID, "iron_" + name).toString();
            } else {
              return new ResourceLocation(FirstModConfig.MOD_ID, "golden_" + name).toString();
            }
          }

          @Override
          public String getUnlocalizedName(ItemStack stack) {
            if ((stack.getMetadata() & 0b1000) == 0) {
              return "tile.iron_" + name;
            } else {
              return "tile.golden_" + name;
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
    if (!worldIn.isRemote) {
      ObjMetalFurnaceTileEntity tileEntity = (ObjMetalFurnaceTileEntity) worldIn.getTileEntity(pos);
      if (tileEntity == null) {
        return Boolean.TRUE;
      }
      IItemHandler up =
          tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
      IItemHandler down =
          tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN);
      String msg = String.format("Up: %s, Down: %s", up.getStackInSlot(0), down.getStackInSlot(0));
      playerIn.sendMessage(new TextComponentString(msg));
    }
    return Boolean.TRUE;
  }

  @Override
  public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
    ObjMetalFurnaceTileEntity tileEntity = (ObjMetalFurnaceTileEntity) worldIn.getTileEntity(pos);

    if (tileEntity == null) {
      return;
    }
    IItemHandler up =
        tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
    IItemHandler down =
        tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN);

    for (int i = up.getSlots() - 1; i >= 0; i--) {
      if (!up.getStackInSlot(i).isEmpty()) {
        Block.spawnAsEntity(worldIn, pos, up.getStackInSlot(i));
        ((IItemHandlerModifiable) up).setStackInSlot(i, ItemStack.EMPTY);
      }
    }

    for (int i = down.getSlots() - 1; i >= 0; i--) {
      if (!down.getStackInSlot(i).isEmpty()) {
        Block.spawnAsEntity(worldIn, pos, down.getStackInSlot(i));
        ((IItemHandlerModifiable) down).setStackInSlot(i, ItemStack.EMPTY);
      }
    }

    super.breakBlock(worldIn, pos, state);
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
    items.add(new ItemStack(ItemManager.getItemByClass(ObjMetalFurnaceBlock.class), 1, 0b0000));
    items.add(new ItemStack(ItemManager.getItemByClass(ObjMetalFurnaceBlock.class), 1, 0b1000));
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
    MaterialEnum material = (meta & 0b1000) == 0 ? MaterialEnum.IRON : MaterialEnum.GOLDEN;
    boolean burning = (meta & 0b0100) != 0;
    EnumFacing facing = EnumFacing.getHorizontal(meta & 0b0011);
    return getDefaultState()
        .withProperty(MATERIAL, material)
        .withProperty(BURNING, burning)
        .withProperty(FACING, facing);
  }

  @Override
  public boolean hasMultiModel() {
    return Boolean.TRUE;
  }

  @Override
  public IStateMapper getBlockStateMapper() {
    return new StateMap.Builder()
        .withName(MATERIAL)
        .withSuffix("_obj_metal_furnace")
        .ignore(FACING)
        .build();
  }

  @Nullable
  @Override
  public TileEntity createNewTileEntity(World worldIn, int meta) {
    return new ObjMetalFurnaceTileEntity();
  }

  @Override
  public boolean hasTileEntity(IBlockState state) {
    return true;
  }

  public enum MaterialEnum implements IStringSerializable {
    /** 铁 */
    IRON,
    /** 金 */
    GOLDEN;

    @Override
    public String getName() {
      return name().toLowerCase();
    }
  }
}
