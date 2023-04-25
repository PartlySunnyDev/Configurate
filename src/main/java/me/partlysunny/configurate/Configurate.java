package me.partlysunny.configurate;

import me.partlysunny.configurate.api.ConfigManager;
import me.partlysunny.configurate.impl.ConfigManagerImpl;
import org.bukkit.plugin.java.JavaPlugin;

import static java.util.logging.Level.*;

public final class Configurate {

    public static boolean LOG_MODE = true;

    private static Configurate INSTANCE;

    public static Configurate instance() {
        if (INSTANCE == null) {
            throw new IllegalStateException("Configurate has not been initialized yet!");
        }
        return INSTANCE;
    }

    public static void initialize(JavaPlugin plugin) {
        if (INSTANCE != null) {
            throw new IllegalStateException("Configurate has already been initialized!");
        }
        INSTANCE = new Configurate(plugin);
        if (LOG_MODE) plugin.getLogger().log(INFO, "Configurate was initialized -> " + plugin.getName() + "!");
    }

    public Configurate(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    private final JavaPlugin plugin;
    private final ConfigManager configManager = new ConfigManagerImpl();

    public JavaPlugin plugin() {
        return plugin;
    }

    public ConfigManager configManager() {
        return configManager;
    }
}
