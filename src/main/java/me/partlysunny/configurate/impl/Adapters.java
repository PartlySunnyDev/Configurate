package me.partlysunny.configurate.impl;

import me.partlysunny.configurate.api.DataAdapter;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemoryConfiguration;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;
import java.util.UUID;

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

    public static final DataAdapter<Color, ConfigurationSection> COLOR = new DataAdapter<>() {
        @Override
        public ConfigurationSection serialize(Color object) {
            ConfigurationSection section = new MemoryConfiguration();
            section.set("red", object.getRed());
            section.set("green", object.getGreen());
            section.set("blue", object.getBlue());
            return section;
        }

        @Override
        public Color deserialize(ConfigurationSection object) {
            return Color.fromRGB(object.getInt("red"), object.getInt("green"), object.getInt("blue"));
        }
    };

    public static final DataAdapter<Component, String> COMPONENT = new DataAdapter<>() {
        @Override
        public String serialize(Component object) {
            return MiniMessage.miniMessage().serialize(object);
        }

        @Override
        public Component deserialize(String object) {
            return MiniMessage.miniMessage().deserialize(object);
        }
    };

    public static final DataAdapter<Sound, ConfigurationSection> SOUND = new DataAdapter<>() {
        @Override
        public ConfigurationSection serialize(Sound object) {
            ConfigurationSection section = new MemoryConfiguration();
            section.set("name", object.name());
            section.set("source", object.source().name());
            section.set("volume", object.volume());
            section.set("pitch", object.pitch());
            return section;
        }

        @Override
        public Sound deserialize(ConfigurationSection object) {
            return Sound.sound(
                    Key.key(object.getString("name")),
                    Sound.Source.valueOf(object.getString("source")),
                    (float) object.getDouble("volume"),
                    (float) object.getDouble("pitch")
            );
        }
    };

    public static final DataAdapter<Material, String> MATERIAL = new DataAdapter<>() {
        @Override
        public String serialize(Material object) {
            return object.name();
        }

        @Override
        public Material deserialize(String object) {
            return Material.valueOf(object);
        }
    };

    public static final DataAdapter<AttributeModifier, ConfigurationSection> ATTRIBUTE_MODIFIER = new DataAdapter<>() {
        @Override
        public ConfigurationSection serialize(AttributeModifier object) {
            ConfigurationSection section = new MemoryConfiguration();
            section.set("name", object.getName());
            section.set("amount", object.getAmount());
            section.set("operation", object.getOperation().name());
            EquipmentSlot slot = object.getSlot();
            if (slot != null) section.set("slot", slot.name());
            return section;
        }

        @Override
        public AttributeModifier deserialize(ConfigurationSection object) {
            String name = object.getString("name");
            return new AttributeModifier(
                    UUID.fromString(name),
                    name,
                    object.getDouble("amount"),
                    AttributeModifier.Operation.valueOf(object.getString("operation")),
                    EquipmentSlot.valueOf(object.getString("slot"))
            );
        }
    };

}
