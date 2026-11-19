package com.example.addon;

import com.g4vrk.react.api.addon.JavaAddon;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;

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

        if (environment().dependencyAvailable(ExampleBootstrap.INFERENCE)) {
            InferenceIntegration.logModels(environment());
        }
        final String language = "en".equalsIgnoreCase(System.getProperty("user.language")) ? "en" : "ru";
        try {
            final ConfigurationNode config = environment().configs().file("config.yml", ConfigurationNode.class)
                    .defaults(getClass().getClassLoader(), language + "/config.yml")
                    .build()
                    .loadNode();
            environment().listeners().register(new FailListener(
                    environment().text(),
                    environment().scheduler(),
                    config.node("permission").getString("react.example.notify"),
                    config.node("message").getString("")
            ));
        } catch (final ConfigurateException exception) {
            throw new IllegalStateException("Could not read the example addon config", exception);
        }

    }

    @Override
    public void onDisable() {

        logger().info("Example addon disabled");

    }

}
