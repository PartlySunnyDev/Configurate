package me.partlysunny.configurate.api;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Represents a configuration file.
 */
public interface Config {

    /**
     * Gets the file.
     * @return The underlying file where this config will save to
     */
    File getFile();

    /**
     * Gets the file configuration.
     * @return The underlying file configuration
     */
    FileConfiguration getConfig();

    /**
     * Sets a value in the config.
     * @param key The key to set
     * @param object Object of type T to write
     * @param adapter The adapter to use to write the object
     * @param <T> The type of object to write
     * @param <U> A writable type that can be saved into the FileConfiguration
     */
    <T, U> void set(String key, T object, DataAdapter<T, U> adapter);

    /**
     * Sets a value in the config.
     * @param key The key to set
     * @param object Object to write
     */
    void set(String key, Object object);

    /**
     * Gets a value from the config.
     * @param key The key to get
     * @param adapter The adapter to use to read the object
     * @return The object of type T
     * @param <T> The type of object to read
     * @param <U> A readable type that can be read from the FileConfiguration
     */
    <T, U> T get(String key, DataAdapter<T, U> adapter);

    /**
     * Gets a value from the config.
     * @param key The key to get
     * @param def The default value to return if the key is not found
     * @param adapter The adapter to use to read the object
     * @return The object of type T
     * @param <T> The type of object to read
     * @param <U> A readable type that can be read from the FileConfiguration
     */
    <T, U> T get(String key, T def, DataAdapter<T, U> adapter);

    /**
     * Gets a value from the config.
     * @param key The key to get
     * @return The object related to the key
     */
    Object get(String key);

    /**
     * Gets a value from the config.
     * @param key The key to get
     * @param def The default value to return if the key is not found
     * @return The object related to the key or the default value
     */
    Object get(String key, Object def);

    /**
     * Saves the config to the specified file.
     * @throws IOException If an I/O error occurs
     */
    void save() throws IOException;

    /**
     * Loads the config from the specified file.
     * Can be used to reload config from the file
     * @throws IOException If an I/O error occurs
     * @throws InvalidConfigurationException If the loaded config is invalid
     */
    void load() throws IOException, InvalidConfigurationException;

    /**
     * Saves the default config to the specified file.
     * @throws IOException If an I/O error occurs
     * @throws InvalidConfigurationException If the loaded config from the default file is invalid
     */
    void saveDefault() throws IOException, InvalidConfigurationException;

}
