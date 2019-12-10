package com.wxp.firstmod.manager;

import com.wxp.firstmod.command.PotionCommand;
import net.minecraft.command.CommandBase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author wxp
 */
public class CommandManager {
  private static List<CommandBase> commands;

  public static void initCommand() {
    commands = new ArrayList<>();

    PotionCommand potionCommand = new PotionCommand();
    commands.add(potionCommand);
  }

  public static Collection<CommandBase> getInitializedCommand() {
    return commands;
  }
}
