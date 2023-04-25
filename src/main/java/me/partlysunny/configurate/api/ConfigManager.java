package me.partlysunny.configurate.api;

import org.bukkit.configuration.InvalidConfigurationException;

import java.io.IOException;

/**
 * Management class for configs
 */
public interface ConfigManager {

    /**
     * Adds a config to the manager
     * @param key the key to add the config under
     * @param config the config to add
     */
    void addConfig(String key, Config config);

    /**
     * Removes a config from the manager
     * @param key the key to remove the config from
     */
    void removeConfig(String key);

    /**
     * Gets a config from the manager
     * @param key the key to get the config from
     * @return the config
     */
    Config getConfig(String key);

    /**
     * Saves an empty config to the manager
     * @param key the key to save the config under
     */
    void saveEmptyConfig(String key);

    /**
     * Saves all configs in the manager
     * @throws IOException if an I/O error occurs
     */
    void saveAll() throws IOException;

    /**
     * Reloads all configs in the manager
     * @throws IOException if an I/O error occurs
     * @throws InvalidConfigurationException if an invalid configuration is found
     */
    void reloadAll() throws IOException, InvalidConfigurationException;

    /**
     * Saves all default configs in the manager
     * @throws IOException if an I/O error occurs
     * @throws InvalidConfigurationException if an invalid default configuration is found
     */
    void saveDefaultAll() throws IOException, InvalidConfigurationException;

}
