package com.wxp.firstmod.manager;

import com.wxp.firstmod.creativetab.FirstModCreativeTab;

/**
 * @author wxp
 */
public class CreativeTabManager {
  static FirstModCreativeTab firstModCreativeTab;

  public static void initCreativeTab() {
    firstModCreativeTab = new FirstModCreativeTab();
  }
}
