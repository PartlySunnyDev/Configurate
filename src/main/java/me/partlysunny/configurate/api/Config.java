package me.partlysunny.configurate.api;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;

public interface Config {

    File getFile();

    FileConfiguration getConfig();

    <T, U> void set(String key, T object, DataAdapter<T, U> adapter);

    void set(String key, Object object);

    <T, U> T get(String key, DataAdapter<T, U> adapter);

    <T, U> T get(String key, T def, DataAdapter<T, U> adapter);

    Object get(String key);

    void save() throws IOException;

    void load() throws IOException, InvalidConfigurationException;

    void saveDefault() throws IOException, InvalidConfigurationException;

}
