package com.example.addon;

import com.g4vrk.react.api.extension.AddonEnvironment;
import com.g4vrk.react.inference.api.InferenceApi;
import org.jetbrains.annotations.NotNull;

/**
 * Uses classes from the inference RCP after React links its exported API.
 */
final class InferenceIntegration {

    static void logModels(final @NotNull AddonEnvironment environment) {

        final InferenceApi inference = environment.services().require(ExampleBootstrap.INFERENCE, InferenceApi.class);
        environment.logger().info("Inference models: {}", inference.modelIds());

    }

}
