package com.example.addon;

import com.g4vrk.react.api.extension.ExtensionId;
import com.g4vrk.react.api.extension.bootstrap.ExtensionBootstrap;
import com.g4vrk.react.api.extension.bootstrap.ExtensionBootstrapContext;
import org.jetbrains.annotations.NotNull;

/**
 * Links the inference API when the official RCP is installed.
 */
public final class ExampleBootstrap implements ExtensionBootstrap {

    static final ExtensionId INFERENCE = ExtensionId.rcp("react-inference");

    @Override
    public void bootstrap(final @NotNull ExtensionBootstrapContext context) {

        context.identify(ExtensionId.addon("example"), 1);
        context.optional(INFERENCE, 1);

    }

}
