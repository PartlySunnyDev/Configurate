package me.partlysunny.configurate.api;

import org.bukkit.configuration.InvalidConfigurationException;

import java.io.IOException;

public interface ConfigManager {

    void addConfig(String key, Config config);

    void removeConfig(String key);

    Config getConfig(String key);

    void saveEmptyConfig(String key);

    void saveAll() throws IOException;

    void reloadAll() throws IOException, InvalidConfigurationException;

    void saveDefaultAll() throws IOException, InvalidConfigurationException;

}
