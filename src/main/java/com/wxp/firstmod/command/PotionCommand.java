package com.wxp.firstmod.command;

import com.wxp.firstmod.FirstMod;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;

import javax.annotation.Nullable;
import java.util.List;

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
    if (args.length != 1) {
      throw new WrongUsageException("commands.position.usage");
    }
    EntityPlayerMP playerMP = CommandBase.getPlayer(server, sender, args[0]);
    Vec3d vec3d = playerMP.getPositionVector();
    sender.sendMessage(
        new TextComponentTranslation(
            "commands.position.success",
            playerMP.getName(),
            vec3d,
            playerMP.world.getWorldInfo().getWorldName()));
  }

  @Override
  public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
    if (args.length == 1)
    {
      String[] names = server.getOnlinePlayerNames();
      return CommandBase.getListOfStringsMatchingLastWord(args, names);
    }
    return null;
  }
}
