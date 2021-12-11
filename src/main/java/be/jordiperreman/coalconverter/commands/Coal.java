package be.jordiperreman.coalconverter.commands;

import be.jordiperreman.coalconverter.CoalConverter;
import be.jordiperreman.coalconverter.util.ChatUtil;
import be.jordiperreman.coalconverter.util.Files;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Coal extends CommandBase implements CommandExecutor, TabCompleter {
    protected CommandSender sender;
    protected Command command;
    protected String[] args;


    public Coal(CoalConverter plugin) {
        super(plugin);
    }

    @Override
    public boolean doCommand(CommandSender sender, Command command, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatUtil.getString(Files.MESSAGES, "messages.general.player_only_cmd"));
            return true;
        }
        final Player player = (Player) sender;
        if (args.length != 0) {
            if (args.length > 1) {
                sender.sendMessage(ChatUtil.getString(Files.MESSAGES, "messages.convert_coal.only_one_arg_req"));
                return true;
            }
            int numberToConvert;
            try {
                numberToConvert = Integer.parseInt(args[0]);
            } catch (Exception e) {
                sender.sendMessage(ChatUtil.getString(Files.MESSAGES, "messages.convert_coal.not_an_integer"));
                return true;
            }
            if (numberToConvert <= 0) {
                sender.sendMessage(ChatUtil.getString(Files.MESSAGES, "messages.convert_coal.greather_zero"));
                return true;
            }
            int coalInInventory = itemCount(player);
            if (coalInInventory >= numberToConvert) {
                replaceCharcoal(player, numberToConvert);
                sender.sendMessage(String.format(ChatUtil.getString(Files.MESSAGES, "messages.convert_coal.succesfully_converted"), numberToConvert));

            } else {
                sender.sendMessage(ChatUtil.getString(Files.MESSAGES, "messages.convert_coal.not_enough"));
                return true;
            }
        } else {
            final int coalInInventory = itemCount(player);
            replaceCharcoal(player, coalInInventory);
            sender.sendMessage(String.format(ChatUtil.getString(Files.MESSAGES, "messages.convert_coal.succesfully_converted"), coalInInventory));
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        final String string = "Number of items to convert";
        List<String> strings = new ArrayList<>();
        strings.add(string);
        return strings;
    }

    @Override
    public void registerSubCommands() {
    }

    public String getPermission() {
        return "coalconverter.coal";
    }

    private int itemCount(Player player) {
        int count = 0;
        final PlayerInventory inv = player.getInventory();
        for (ItemStack is : inv.all(Material.CHARCOAL).values()) {
            if (is != null && is.getType() == Material.CHARCOAL) {
                count = count + is.getAmount();
            }
        }
        return count;
    }

    private void replaceCharcoal(Player player, int count) {
        final ItemStack toRemove = new ItemStack(Material.CHARCOAL, count);
        player.getInventory().removeItem(toRemove);

        final ItemStack toAdd = new ItemStack(Material.COAL, count);
        player.getInventory().addItem(toAdd);
    }

}

