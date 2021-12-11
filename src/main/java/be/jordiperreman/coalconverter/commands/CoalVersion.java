package be.jordiperreman.coalconverter.commands;

import be.jordiperreman.coalconverter.CoalConverter;
import be.jordiperreman.coalconverter.interfaces.ISubCommand;
import be.jordiperreman.coalconverter.util.ChatUtil;
import be.jordiperreman.coalconverter.util.Files;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CoalVersion extends CoalConverterCmd implements ISubCommand {

    public CoalVersion(CoalConverter plugin) {
        super(plugin);
    }

    @Override
    public boolean doCommand(CommandSender sender, Command command, String[] args) {
        if (!sender.hasPermission(getPermission())) {
            sender.sendMessage(ChatUtil.getString(Files.MESSAGES, "messages.general.no_perm"));
            return true;
        }
        sender.sendMessage(String.format(ChatUtil.getString(Files.MESSAGES, "messages.general.version"), plugin.getDescription().getVersion()));
        return true;
    }

    @Override
    public String getPermission() {
        return "coalconverter.version";
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return null;
    }
}