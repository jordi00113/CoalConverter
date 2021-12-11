package be.jordiperreman.coalconverter.commands;

import be.jordiperreman.coalconverter.CoalConverter;
import be.jordiperreman.coalconverter.interfaces.ISubCommand;
import be.jordiperreman.coalconverter.util.ChatUtil;
import be.jordiperreman.coalconverter.util.Files;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CoalAuthor extends CoalConverterCmd implements ISubCommand {

    public CoalAuthor(CoalConverter plugin) {
        super(plugin);
    }

    @Override
    public boolean doCommand(CommandSender sender, Command command, String[] args) {

        if (!sender.hasPermission(getPermission())) {
            sender.sendMessage(ChatUtil.getString(Files.MESSAGES, "messages.general.no_perm"));
            return true;
        }
        sender.sendMessage(ChatUtil.getPrefix() + ChatUtil.color("&fThis plugin is developed by &c&ljordi00113&8."));
        return true;
    }

    @Override
    public String getPermission() {
        return "coalconverter.author";
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return null;
    }
}
