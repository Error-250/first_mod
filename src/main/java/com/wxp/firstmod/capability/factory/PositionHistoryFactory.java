package com.wxp.firstmod.capability.factory;

import com.wxp.firstmod.capability.PositionHistoryCap;
import com.wxp.firstmod.capability.impl.PositionHistoryCapImpl;

import java.util.concurrent.Callable;

/** @author wxp */
public class PositionHistoryFactory implements Callable<PositionHistoryCap> {
  @Override
  public PositionHistoryCap call() throws Exception {
    return new PositionHistoryCapImpl();
  }
}
