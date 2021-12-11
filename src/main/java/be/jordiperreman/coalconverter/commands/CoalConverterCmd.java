package be.jordiperreman.coalconverter.commands;

import be.jordiperreman.coalconverter.CoalConverter;
import be.jordiperreman.coalconverter.interfaces.ISubCommand;
import be.jordiperreman.coalconverter.util.ChatUtil;
import be.jordiperreman.coalconverter.util.Files;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoalConverterCmd extends CommandBase implements CommandExecutor, TabCompleter {
    protected CommandSender sender;
    protected Command command;
    protected String[] args;
    protected Map<String, ISubCommand> commands = new HashMap<>();


    public CoalConverterCmd(CoalConverter plugin) {
        super(plugin);
    }

    @Override
    public boolean doCommand(CommandSender sender, Command command, String[] args) {
        if (args.length != 0) {
            if (!commands.containsKey(args[0].toLowerCase())) {
                // No subcommand like that found
                sender.sendMessage(ChatUtil.getString(Files.MESSAGES, "messages.general.sub_cmd_not_exist"));
                return true;
            }
            return commands.get(args[0]).doCommand(sender, command, args);
        } else {

            // When no subcommand or param is found
            if (!sender.hasPermission(getPermission())) {
                sender.sendMessage(ChatUtil.getString(Files.MESSAGES, "messages.general.no_perm"));
                return true;
            }
            sender.sendMessage(ChatUtil.getPrefix() + ChatUtil.color("&fMain goal of the plugin is to convert charcoal to coal. Developed by &c&ljordi00113&8."));
            return true;
        }
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return new ArrayList<>(commands.keySet());
    }

    @Override
    public void registerSubCommands() {
        commands.put("author", new CoalAuthor(this.plugin));
        commands.put("reload", new CoalReload(this.plugin));
        commands.put("version", new CoalVersion(this.plugin));
        commands.put("v", new CoalVersion(this.plugin));


    }

    public String getPermission() {
        return "coalconverter.coalconverter";
    }

}
