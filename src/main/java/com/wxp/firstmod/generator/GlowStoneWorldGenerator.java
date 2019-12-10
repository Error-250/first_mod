package com.wxp.firstmod.generator;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

import java.util.Random;

/** @author wxp */
public class GlowStoneWorldGenerator extends WorldGenerator {
  private final WorldGenerator glowStoneGenerator =
      new WorldGenMinable(Blocks.GLOWSTONE.getDefaultState(), 16);

  /**
   * 生成矿物
   *
   * @param worldIn 生成矿物的世界
   * @param rand 随机数
   * @param position 一个chunk中, y = 0的平面上的x与z值都最小的一个点. 即表示 position.x ~ position.x+16,-无穷 ~ +无穷,
   *     position.z ~ position.z+16的柱形区域
   * @return 是否生成成功
   */
  @Override
  public boolean generate(World worldIn, Random rand, BlockPos position) {
    if (TerrainGen.generateOre(
        worldIn, rand, this, position, OreGenEvent.GenerateMinable.EventType.CUSTOM)) {
      for (int i = 0; i < 4; ++i) {
        int posX = position.getX() + rand.nextInt(16);
        int posY = 64 + rand.nextInt(16);
        int posZ = position.getZ() + rand.nextInt(16);
        BlockPos blockpos = new BlockPos(posX, posY, posZ);
        Biome biomeGenBase = worldIn.getBiomeForCoordsBody(blockpos);
        if (biomeGenBase.getRainfall() < rand.nextInt(65536)) {
          glowStoneGenerator.generate(worldIn, rand, blockpos);
        }
      }
    }
    return true;
  }
}
