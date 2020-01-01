package com.wxp.firstmod.command;

import com.google.common.collect.Lists;
import com.wxp.firstmod.FirstMod;
import com.wxp.firstmod.capability.PositionHistoryCap;
import com.wxp.firstmod.capability.storage.PositionHistoryStorage;
import com.wxp.firstmod.manager.CapabilityManager;
import com.wxp.firstmod.manager.NetworkManager;
import com.wxp.firstmod.network.PositionHistoryMessage;
import com.wxp.firstmod.worldsave.PositionHistoryWorldSaved;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/** @author wxp */
public class PotionCommand extends CommandBase {
  @Override
  public String getName() {
    return "potion";
  }

  @Override
  public String getUsage(ICommandSender sender) {
    FirstMod.getLogger().info("Sender:{}", sender.getClass());
    return "commands.position.usage";
  }

  @Override
  public void execute(MinecraftServer server, ICommandSender sender, String[] args)
      throws CommandException {
    if (args.length == 2) {
      if ("world".equalsIgnoreCase(args[0])) {
        EntityPlayerMP entityPlayerMP = CommandBase.getCommandSenderAsPlayer(sender);
        if ("list".equalsIgnoreCase(args[1])) {
          sender.sendMessage(new TextComponentString("Global: "));
          PositionHistoryWorldSaved globalData =
              PositionHistoryWorldSaved.getGlobal(entityPlayerMP.world);
          listPositionData(entityPlayerMP, globalData, sender);
          sender.sendMessage(new TextComponentString("Dimension: "));
          PositionHistoryWorldSaved data = PositionHistoryWorldSaved.get(entityPlayerMP.world);
          listPositionData(entityPlayerMP, data, sender);
        } else if ("global".equalsIgnoreCase(args[1])) {
          Vec3d pos = entityPlayerMP.getPositionVector();
          UUID uuid = entityPlayerMP.getUniqueID();
          PositionHistoryWorldSaved globalData =
              PositionHistoryWorldSaved.getGlobal(entityPlayerMP.world);
          globalData.savePosition(uuid, pos);
          sender.sendMessage(new TextComponentTranslation("commands.position.save", pos));
        } else if ("dimension".equalsIgnoreCase(args[1])) {
          Vec3d pos = entityPlayerMP.getPositionVector();
          UUID uuid = entityPlayerMP.getUniqueID();
          PositionHistoryWorldSaved globalData =
              PositionHistoryWorldSaved.get(entityPlayerMP.world);
          globalData.savePosition(uuid, pos);
          sender.sendMessage(new TextComponentTranslation("commands.position.save", pos));
        }
      }
    }
    if (args.length != 1) {
      throw new WrongUsageException("commands.position.usage");
    }
    EntityPlayerMP playerMP = CommandBase.getPlayer(server, sender, args[0]);
    Vec3d vec3d = playerMP.getPositionVector();
    if (playerMP == sender && playerMP.hasCapability(CapabilityManager.positionHistory, null)) {
      sender.sendMessage(new TextComponentTranslation("commands.position.history"));
      PositionHistoryCap positionHistoryCap =
          playerMP.getCapability(CapabilityManager.positionHistory, null);
      for (Vec3d vector3d : positionHistoryCap.getHistoryPositions()) {
        if (vector3d == null) {
          continue;
        }
        sender.sendMessage(new TextComponentString(vector3d.toString()));
      }
      positionHistoryCap.savePosition(vec3d);

      PositionHistoryStorage storage =
          (PositionHistoryStorage) CapabilityManager.positionHistory.getStorage();
      NBTBase base = storage.writeNBT(CapabilityManager.positionHistory, positionHistoryCap, null);
      NBTTagCompound nbtTagCompound = new NBTTagCompound();
      nbtTagCompound.setTag("histories", base);
      PositionHistoryMessage message = new PositionHistoryMessage(nbtTagCompound);
      NetworkManager.simpleNetworkWrapper.sendTo(message, playerMP);
    }
    sender.sendMessage(
        new TextComponentTranslation(
            "commands.position.success",
            playerMP.getName(),
            vec3d,
            playerMP.world.getWorldInfo().getWorldName()));
  }

  @Override
  public List<String> getTabCompletions(
      MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
    if (args.length == 1) {
      List<String> params = Lists.newArrayList(server.getOnlinePlayerNames());
      params.add("world");
      return CommandBase.getListOfStringsMatchingLastWord(args, params);
    } else if (args.length == 2 && "world".equalsIgnoreCase(args[0])) {
      return CommandBase.getListOfStringsMatchingLastWord(
          args, Lists.newArrayList("list", "global", "dimension"));
    }
    return null;
  }

  private void listPositionData(
      EntityPlayerMP playerMP, PositionHistoryWorldSaved data, ICommandSender sender) {
    for (Map.Entry<UUID, List<Vec3d>> entry : data.getPlayerPositions().entrySet()) {
      String positionStr =
          entry.getValue().stream().map(Vec3d::toString).collect(Collectors.joining(","));
      sender.sendMessage(
          new TextComponentTranslation(
              "commands.position.list",
              positionStr,
              playerMP.world.getPlayerEntityByUUID(entry.getKey()).getName()));
    }
  }
}
