package com.example.addon;

import com.g4vrk.react.api.addon.JavaAddon;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;

import java.io.File;

/**
 * Entry point named by {@code mainClass} in {@code react-addon.yml}.
 *
 * <p>The plugin loads addons from {@code plugins/React/addons/}, calls {@link #onLoad()}
 * first and {@link #onEnable()} once React itself is enabled. Bukkit listeners are
 * registered on behalf of the React plugin, so the addon unregisters them on disable.</p>
 */
public final class ExampleAddon extends JavaAddon {

    private FailListener listener;

    @Override
    public void onLoad() {
        slf4jLogger().info("Example addon loaded");
    }

    @Override
    public void onEnable() {
        final File file = new File(directory(), "config.yml");
        if (!file.exists()) {
            saveResource("config.yml", false);
        }
        final YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

        final Plugin react = Bukkit.getPluginManager().getPlugin("React");
        if (react == null) {
            slf4jLogger().error("React is not loaded, the example addon stays idle");
            return;
        }
        listener = new FailListener(config.getString("permission", "react.example.notify"),
                config.getString("message", "%player% failed %check%"));
        Bukkit.getPluginManager().registerEvents(listener, react);
    }

    @Override
    public void onDisable() {
        if (listener != null) {
            HandlerList.unregisterAll(listener);
            listener = null;
        }
    }

}
