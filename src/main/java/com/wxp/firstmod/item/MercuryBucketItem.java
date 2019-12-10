package com.wxp.firstmod.item;

import com.wxp.firstmod.block.MercuryFluidBlock;
import com.wxp.firstmod.manager.BlockManager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;

/** @author wxp */
public class MercuryBucketItem extends ItemBucket {
  public MercuryBucketItem() {
    super(BlockManager.getFluidBlockByClass(MercuryFluidBlock.class));
    setContainerItem(Items.BUCKET);
    setRegistryName("mercury_bucket");
    setUnlocalizedName("mercury_bucket");
  }
}
