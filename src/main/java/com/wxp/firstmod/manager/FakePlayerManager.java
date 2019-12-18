package com.wxp.firstmod.manager;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayerFactory;

import java.lang.ref.WeakReference;
import java.util.Objects;
import java.util.UUID;

/** @author wxp */
public class FakePlayerManager {
  private static GameProfile gameProfile;
  private static WeakReference<EntityPlayerMP> fakePlayer;

  public static void initFakePlayer() {
    gameProfile = new GameProfile(UUID.randomUUID(), "[FirstMod]");
    fakePlayer = new WeakReference<>(null);
  }

  public static WeakReference<EntityPlayerMP> getFakePlayer(WorldServer server) {
    if (fakePlayer.get() == null) {
      fakePlayer = new WeakReference<>(FakePlayerFactory.get(server, gameProfile));
    } else {
      Objects.requireNonNull(fakePlayer.get()).world = server;
    }
    return fakePlayer;
  }
}
