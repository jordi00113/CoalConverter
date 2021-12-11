package be.jordiperreman.coalconverter.recipes;

import be.jordiperreman.coalconverter.CoalConverter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class CharcoalRecipe {

    public Logger LOGGER;
    public JavaPlugin plugin;

    public CharcoalRecipe(JavaPlugin plugin) {
        this.plugin = plugin;
        LOGGER = CoalConverter.LOGGER;
    }

    public void createCharcoalRecipes() {
        // Charcoal => Coal
        ItemStack item = new ItemStack(Material.COAL);

        NamespacedKey key = new NamespacedKey(this.plugin, "charcoal-to-coal");

        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape("C");

        recipe.setIngredient('C', Material.CHARCOAL);

        Bukkit.addRecipe(recipe);

        // Coal => Charcoal
        item = new ItemStack(Material.CHARCOAL);

        key = new NamespacedKey(this.plugin, "coal-to-charcoal");

        recipe = new ShapedRecipe(key, item);

        recipe.shape("C");

        recipe.setIngredient('C', Material.COAL);

        Bukkit.addRecipe(recipe);
    }

}
