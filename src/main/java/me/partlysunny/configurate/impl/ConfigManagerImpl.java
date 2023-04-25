package me.partlysunny.configurate.impl;

import me.partlysunny.configurate.api.ConfigManager;
import me.partlysunny.configurate.api.Config;
import org.bukkit.configuration.InvalidConfigurationException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConfigManagerImpl implements ConfigManager {

    private final Map<String, Config> configs = new HashMap<>();

    @Override
    public void addConfig(String key, Config config) {
        configs.put(key, config);
    }

    @Override
    public void removeConfig(String key) {
        configs.remove(key);
    }

    @Override
    public Config getConfig(String key) {
        return configs.get(key);
    }

    @Override
    public void saveEmptyConfig(String key) {
        Config empty = new SpigotConfig(key);
        addConfig(key, empty);
    }

    @Override
    public void saveAll() throws IOException {
        for (Config config : configs.values()) {
            config.save();
        }
    }

    @Override
    public void reloadAll() throws IOException, InvalidConfigurationException {
        for (Config config : configs.values()) {
            config.reload();
        }
    }

    @Override
    public void saveDefaultAll() throws IOException, InvalidConfigurationException {
        for (Config config : configs.values()) {
            config.saveDefault();
        }
    }
}
