package be.jordiperreman.coalconverter.util;

import be.jordiperreman.coalconverter.CoalConverter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public enum Files {
    MESSAGES("messages", "messages.yml");

    private final String filename;
    private final String filepath;
    private final File filefile;
    private FileConfiguration config;

    /**
     * @param name name of the file
     * @param path path of the file within datafolder
     */
    Files(String name, String path) {
        filename = name;
        filepath = path;
        filefile = new File(CoalConverter.getCoalConverter().getDataFolder(), filepath);
        config = YamlConfiguration.loadConfiguration(filefile);
    }

    /**
     * @return file
     */
    public FileConfiguration getFile() {
        return config;
    }

    /**
     * @return file name
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @return file path in dataFolder
     */
    public String getFilepath() {
        return filepath;
    }

    /**
     * @return file the config is saved at
     */
    public File getFileFile() {
        return filefile;
    }

    /**
     * Saves the config
     */
    public void save() {
        try {
            config.save(filefile);
        } catch (IOException e) {
            CoalConverter.LOGGER.warning("Unable to save file:" + filename);
            e.printStackTrace();
        }
    }

    /**
     * Loads the config
     */
    public void load() {
        this.config = YamlConfiguration.loadConfiguration(filefile);
    }

    /**
     * @param msg String for getting config.
     * @return String with untranslated config code.
     */
    public String getString(String msg) {
        return getFile().getString(msg);
    }

    /**
     * @param msg String for getting config.
     * @return Bool with untranslated config code.
     */
    public boolean getBool(String msg) {
        return getFile().getBoolean(msg);
    }
}
