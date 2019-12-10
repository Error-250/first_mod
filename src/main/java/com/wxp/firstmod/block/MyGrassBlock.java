package com.wxp.firstmod.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

import java.util.Objects;

/** @author wxp */
public class MyGrassBlock extends AbstractMyBlock {
  public MyGrassBlock() {
    super(Material.ROCK);
    // dirt is o.5 stone is 1.5
    setHardness(0.5f);
    setSoundType(SoundType.GROUND);
    setRegistryName("my_grass");
    setUnlocalizedName("my_grass");
  }

  @Override
  public ItemBlock getItemBlock() {
    ItemBlock itemBlock = new ItemBlock(this);
    itemBlock.setRegistryName(Objects.requireNonNull(this.getRegistryName()));
    return itemBlock;
  }
}
