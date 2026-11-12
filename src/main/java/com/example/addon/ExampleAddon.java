package com.example.addon;

import com.g4vrk.react.api.addon.JavaAddon;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * Entry point named by {@code mainClass} in {@code react-addon.yml}.
 *
 * <p>The plugin loads addons from {@code plugins/React/addons/}, calls {@link #onLoad()}
 * first and {@link #onEnable()} once React itself is enabled. Listeners go through
 * {@code environment().listeners()}, which takes them down again when the addon is disabled.</p>
 */
public final class ExampleAddon extends JavaAddon {

    @Override
    public void onLoad() {

        logger().info("Example addon loaded");

    }

    @Override
    public void onEnable() {

        saveResource("config.yml", false);
        final YamlConfiguration config = YamlConfiguration.loadConfiguration(directory().resolve("config.yml").toFile());
        environment().listeners().register(new FailListener(
                environment().text(),
                environment().scheduler(),
                config.getString("permission", ""),
                config.getString("message", "")
        ));

    }

    @Override
    public void onDisable() {

        logger().info("Example addon disabled");

    }

}
