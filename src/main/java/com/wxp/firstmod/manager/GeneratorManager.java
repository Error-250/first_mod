package com.wxp.firstmod.manager;

import com.wxp.firstmod.generator.GlowStoneWorldGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/** @author wxp */
public class GeneratorManager {
  private static List<WorldGenerator> generators;

  public static void initGenerator() {
    generators = new ArrayList<>();

    GlowStoneWorldGenerator glowStoneWorldGenerator = new GlowStoneWorldGenerator();
    generators.add(glowStoneWorldGenerator);
  }

  public static Collection<WorldGenerator> getInitializedGenerator() {
    return generators;
  }
}
