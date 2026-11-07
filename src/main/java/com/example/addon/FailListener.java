package com.example.addon;

import com.g4vrk.react.api.check.Check;
import com.g4vrk.react.api.event.EventPriority;
import com.g4vrk.react.api.event.Subscribe;
import com.g4vrk.react.api.event.check.CheckFlagEvent;
import com.g4vrk.react.api.text.TextFormatter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

/**
 * Sends staff a line for every flag that goes through.
 *
 * <p>{@link CheckFlagEvent} is posted on the React event bus before a check raises its
 * violation level, for React's own checks and for every result an RCP hands to the plugin.
 * The handler runs last and skips cancelled flags, so it only sees flags that count.</p>
 */
final class FailListener {

    private final TextFormatter text;
    private final String permission;
    private final String message;

    FailListener(
            final @NotNull TextFormatter text,
            final @NotNull String permission,
            final @NotNull String message
    ) {
        this.text = text;
        this.permission = permission;
        this.message = message;
    }

    @Subscribe(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onFlag(final @NotNull CheckFlagEvent event) {
        final Check check = event.check();
        final Component line = text.format(message,
                Placeholder.unparsed("player", check.player().name()),
                Placeholder.unparsed("check", check.name()),
                Placeholder.unparsed("violations",
                        String.format(Locale.ROOT, "%.1f", check.violations() + event.amount())));
        Bukkit.getOnlinePlayers().stream()
                .filter(player -> player.hasPermission(permission))
                .forEach(player -> player.sendMessage(line));
    }

}
