package com.example.addon;

import com.g4vrk.react.api.check.Check;
import com.g4vrk.react.api.event.EventPriority;
import com.g4vrk.react.api.event.Subscribe;
import com.g4vrk.react.api.event.check.CheckFlagEvent;
import com.g4vrk.react.api.text.TagMapper;
import com.g4vrk.react.api.text.TextFormatter;
import com.g4vrk.schedula.task.TickSchedule;
import com.g4vrk.schedula.task.scheduler.Scheduler;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

/**
 * Sends staff a line for every flag that goes through.
 *
 * <p>{@link CheckFlagEvent} is posted on the React event bus before a check raises its
 * violation level, for React's own checks and for every result an RCP hands to the plugin.
 * The handler runs last and skips cancelled flags, so it only sees flags that count.</p>
 */
final class FailListener {

    private static final TickSchedule SEND_SCHEDULE = TickSchedule.instant();

    private final TextFormatter text;
    private final Scheduler scheduler;
    private final String permission;
    private final String message;

    FailListener(
            final @NotNull TextFormatter text,
            final @NotNull Scheduler scheduler,
            final @NotNull String permission,
            final @NotNull String message
    ) {

        this.text = text;
        this.scheduler = scheduler;
        this.permission = permission;
        this.message = message;

    }

    @Subscribe(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onFlag(final @NotNull CheckFlagEvent event) {

        final Check check = event.check();
        final FlagTags tags = new FlagTags(
                escape(check.player().name()),
                escape(check.name()),
                String.format(Locale.ROOT, "%.1f", check.violations() + event.amount())
        );
        scheduler.schedule(() -> send(tags), SEND_SCHEDULE);

    }

    private void send(final @NotNull FlagTags tags) {

        final Component line = text.formatMapped(message, tags);
        for (final Player player : Bukkit.getOnlinePlayers()) {
            scheduler.scheduleEntity(player, () -> {
                if (player.isOnline() && player.hasPermission(permission)) {
                    player.sendMessage(line);
                }
            }, SEND_SCHEDULE);
        }

    }

    private static @NotNull String escape(final @NotNull String value) {

        return value.replace("\\", "\\\\").replace("<", "\\<");

    }

    /**
     * Literal tag values captured when the flag is posted.
     */
    private record FlagTags(@NotNull String player, @NotNull String check, @NotNull String violations) implements TagMapper {

        @Override
        public @Nullable String map(final @NotNull String name, final @NotNull String args) {

            return switch (name) {
                case "player" -> player;
                case "check" -> check;
                case "violations" -> violations;
                default -> null;
            };

        }

        @Override
        public boolean has(final @NotNull String name) {

            return map(name, "") != null;

        }

    }

}
