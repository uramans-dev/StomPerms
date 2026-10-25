package me.lucko.luckperms.minestom.context;

import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import net.minestom.server.entity.Player;
import net.minestom.server.event.Event;
import net.minestom.server.event.EventNode;
import org.jetbrains.annotations.NotNull;

public interface ContextProvider {

    @NotNull String key();

    @NotNull Optional<String> query(@NotNull Player subject);

    default @NotNull Set<String> potentialValues() {
        return Set.of();
    }

    default void register(@NotNull Consumer<Player> contextUpdateSignaller, @NotNull EventNode<Event> eventNode) {
        // do nothing by default
    }

}
