package me.partlysunny.configurate.impl;

import me.partlysunny.configurate.Configurate;
import me.partlysunny.configurate.api.Config;
import me.partlysunny.configurate.api.DataAdapter;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SpigotConfig implements Config {

    private final File file;
    private final FileConfiguration config;
    @Nullable
    private final InputStream defaultFileStream;

    public SpigotConfig(File file, FileConfiguration config, @Nullable InputStream defaultFileStream) {
        this.file = file;
        this.config = config;
        this.defaultFileStream = defaultFileStream;
    }

    public SpigotConfig(File file, FileConfiguration config) {
        this(file, config, null);
    }

    public SpigotConfig(File file, @Nullable InputStream defaultFileStream) {
        this(file, YamlConfiguration.loadConfiguration(file), defaultFileStream);
    }

    public SpigotConfig(File file) {
        this(file, YamlConfiguration.loadConfiguration(file));
    }

    public SpigotConfig(String name) {
        this(new File(Configurate.instance().plugin().getDataFolder(), name));
    }

    public SpigotConfig(String name, String defaultFileName) {
        this(new File(Configurate.instance().plugin().getDataFolder(), name), Configurate.instance().plugin().getResource(defaultFileName));
    }

    @Override
    public File getFile() {
        return file;
    }

    @Override
    public FileConfiguration getConfig() {
        return config;
    }

    @Override
    public <T, U> void set(String key, T object, DataAdapter<T, U> adapter) {
        config.set(key, adapter.serialize(object));
    }

    @Override
    public void set(String key, Object object) {
        config.set(key, object);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T, U> T get(String key, DataAdapter<T, U> adapter) {
        return adapter.deserialize((U) config.get(key));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T, U> T get(String key, T def, DataAdapter<T, U> adapter) {
        if (!config.contains(key)) {
            return def;
        }
        return adapter.deserialize((U) config.get(key));
    }

    @Override
    public Object get(String key) {
        return config.get(key);
    }

    @Override
    public Object get(String key, Object def) {
        return config.get(key, def);
    }

    @Override
    public <T> T get(String key, Class<T> clazz) throws ClassCastException {
        return clazz.cast(get(key));
    }

    @Override
    public <T> T get(String key, T def, Class<T> clazz) throws ClassCastException {
        return clazz.cast(get(key, def));
    }

    @Override
    public void save() throws IOException {
        config.save(file);
    }

    @Override
    public void load() throws IOException, InvalidConfigurationException {
        if (!file.exists()) {
            if (defaultFileStream != null) {
                saveDefault();
            } else {
                if (!file.createNewFile()) {
                    throw new IOException("Could not create file " + file.getName());
                }
            }
        }
        config.load(file);
    }

    @Override
    public void saveDefault() throws IOException, InvalidConfigurationException {
        if (defaultFileStream != null) {
            YamlConfiguration defaults = new YamlConfiguration();
            defaults.load(new InputStreamReader(defaultFileStream));
            config.setDefaults(defaults);
            config.options().copyDefaults(true);
            config.save(file);
        }
    }
}
