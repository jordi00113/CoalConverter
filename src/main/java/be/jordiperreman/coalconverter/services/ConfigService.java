package be.jordiperreman.coalconverter.services;

import be.jordiperreman.coalconverter.CoalConverter;
import be.jordiperreman.coalconverter.interfaces.IConfigService;
import be.jordiperreman.coalconverter.util.Files;

public class ConfigService implements IConfigService {

    private final CoalConverter main;

    public ConfigService(CoalConverter main) {
        this.main = main;
    }

    @Override
    public void loadConfig() {
        main.getConfig().options().copyDefaults(true);

        // For all files declared in Files enum.
        for (Files f : Files.values()) {
            f.getFile().options().copyDefaults(true);
            if (!f.getFileFile().exists()) {
                main.saveResource(f.getFilepath(), false);
            }
            f.load();
        }
    }
}
