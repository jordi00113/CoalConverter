package be.jordiperreman.coalconverter;

import be.jordiperreman.coalconverter.commands.Coal;
import be.jordiperreman.coalconverter.commands.CoalConverterCmd;
import be.jordiperreman.coalconverter.interfaces.IConfigService;
import be.jordiperreman.coalconverter.recipes.CharcoalRecipe;
import be.jordiperreman.coalconverter.services.ConfigService;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginLogger;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class CoalConverter extends JavaPlugin {

    public final static Logger LOGGER = PluginLogger.getLogger("CoalConverter");
    private final IConfigService configService = new ConfigService(this);

    public static CoalConverter getCoalConverter() {
        return (CoalConverter) Bukkit.getPluginManager().getPlugin("CoalConverter");
    }

    @Override
    public void onEnable() {
        // Init recept
        new CharcoalRecipe(this).createCharcoalRecipes();

        //Register  coal command
        Coal coalcommand = new Coal(this);
        getCommand("coal").setExecutor(coalcommand);
        getCommand("coal").setTabCompleter(coalcommand);

        //Register coalconverter command & subcommands
        CoalConverterCmd coalConverterCmd = new CoalConverterCmd(this);
        getCommand("coalconverter").setExecutor(coalConverterCmd);
        getCommand("coalconverter").setTabCompleter(coalConverterCmd);
        coalConverterCmd.registerSubCommands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public IConfigService getConfigService() {
        return this.configService;
    }
}