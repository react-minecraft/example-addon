package com.example.addon;

import com.g4vrk.react.api.event.PlayerCheckFailEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

/**
 * Sends staff a line for every failed check. {@link PlayerCheckFailEvent} is fired by React
 * for its own checks and for every result an RCP hands to the plugin.
 */
final class FailListener implements Listener {

    private final String permission;
    private final String message;

    FailListener(final @NotNull String permission, final @NotNull String message) {
        this.permission = permission;
        this.message = ChatColor.translateAlternateColorCodes('&', message);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onFail(final @NotNull PlayerCheckFailEvent event) {
        final String line = message
                .replace("%player%", event.player().name())
                .replace("%check%", event.check().getName())
                .replace("%violations%", String.format(Locale.ROOT, "%.1f", event.check().getViolations()));
        Bukkit.getOnlinePlayers().stream()
                .filter(player -> player.hasPermission(permission))
                .forEach(player -> player.sendMessage(line));
    }

}
