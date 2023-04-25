package me.partlysunny.configurate.impl;

import me.partlysunny.configurate.api.DataAdapter;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemoryConfiguration;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

public class Adapters {

    public static final DataAdapter<Location, ConfigurationSection> LOCATION = new DataAdapter<>() {
        @Override
        public Location deserialize(ConfigurationSection object) {
            return new Location(
                    null,
                    object.getDouble("x"),
                    object.getDouble("y"),
                    object.getDouble("z"),
                    (float) object.getDouble("yaw"),
                    (float) object.getDouble("pitch")
            );
        }

        @Override
        public ConfigurationSection serialize(Location object) {
            ConfigurationSection section = new MemoryConfiguration();
            section.set("x", object.getX());
            section.set("y", object.getY());
            section.set("z", object.getZ());
            section.set("yaw", object.getYaw());
            section.set("pitch", object.getPitch());
            return section;
        }
    };

    public static final DataAdapter<PotionEffect, ConfigurationSection> POTION_EFFECT = new DataAdapter<>() {
        @Override
        public PotionEffect deserialize(ConfigurationSection object) {
            return new PotionEffect(
                    Objects.requireNonNull(PotionEffectType.getByName(object.getString("type", "NULL"))),
                    object.getInt("duration"),
                    object.getInt("amplifier"),
                    object.getBoolean("ambient"),
                    object.getBoolean("particles"),
                    object.getBoolean("icon")
            );
        }

        @Override
        public ConfigurationSection serialize(PotionEffect object) {
            ConfigurationSection section = new MemoryConfiguration();
            section.set("type", object.getType().getName());
            section.set("duration", object.getDuration());
            section.set("amplifier", object.getAmplifier());
            section.set("ambient", object.isAmbient());
            section.set("particles", object.hasParticles());
            section.set("icon", object.hasIcon());
            return section;
        }
    };

}
