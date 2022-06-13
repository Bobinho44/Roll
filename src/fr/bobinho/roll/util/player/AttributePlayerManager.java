package fr.bobinho.roll.util.player;

import fr.bobinho.roll.RollCore;
import fr.bobinho.roll.api.event.BEvent;
import fr.bobinho.roll.api.scheduler.BScheduler;
import fr.bobinho.roll.api.validate.BValidate;
import fr.bobinho.roll.wrapper.MultiValuedAttribute;
import org.bukkit.Bukkit;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Manager class of players
 */
public final class AttributePlayerManager {

    /**
     * Fields
     */
    private static final MultiValuedAttribute<AttributePlayer> attributePlayers = new MultiValuedAttribute<>();

    /**
     * Initialize the manager class of players
     */
    public static void initialize() {

        //Registers the player when he join
        BEvent.registerEvent(PlayerJoinEvent.class, EventPriority.LOWEST).consume(event ->
                registerAttributePlayer(event.getPlayer().getUniqueId())
        );

        //Unregister the player when he quit
        BEvent.registerEvent(PlayerQuitEvent.class, EventPriority.MONITOR).consume(event ->
                unregisterAttributePlayer(event.getPlayer().getUniqueId())
        );

        //Saves the player attribute every 10 minutes
        BScheduler.asyncScheduler().every(10, TimeUnit.MINUTES).run(() -> {
            Bukkit.getOnlinePlayers().forEach(onlinePlayer ->
                    getAttributePlayer(onlinePlayer.getUniqueId()).ifPresent(player -> {
                        RollCore.getPlayersSetting().getConfiguration().set(player.uuid().get() + ".points", player.points().get());
                        RollCore.getPlayersSetting().getConfiguration().set(player.uuid().get() + ".attributes", player.attributePoints().get());
                    }));

            RollCore.getPlayersSetting().save();
        });
    }

    /**
     * Gets the attribute player associated with the uuid
     *
     * @param uuid the uuid
     * @return the attribute player associated with the uuid
     */
    @Nonnull
    public static Optional<AttributePlayer> getAttributePlayer(@Nonnull UUID uuid) {
        BValidate.notNull(uuid);

        return attributePlayers.get().stream().filter(player -> player.uuid().get().equals(uuid)).findFirst();
    }

    /**
     * Registers the attribute player
     *
     * @param uuid the attribute player uuid
     */
    public static void registerAttributePlayer(@Nonnull UUID uuid) {
        BValidate.notNull(uuid);

        //Loads the attribute player and register him
        int points = RollCore.getPlayersSetting().getConfiguration().getInt(uuid + ".points", 10);
        List<Integer> attributePoints = RollCore.getPlayersSetting().getConfiguration().getIntegerList(uuid + ".attributes");

        attributePlayers.add(new AttributePlayer(uuid, points, attributePoints.isEmpty() ? new ArrayList<>(Collections.nCopies(16, 0)) : attributePoints));
    }

    /**
     * Unregisters the attribute player
     *
     * @param uuid the attribute player uuid
     */
    public static void unregisterAttributePlayer(@Nonnull UUID uuid) {
        BValidate.notNull(uuid);

        //Saves the attribute player and unregister him
        getAttributePlayer(uuid).ifPresent(player -> {
            RollCore.getPlayersSetting().getConfiguration().set(uuid + ".points", player.points().get());
            RollCore.getPlayersSetting().getConfiguration().set(uuid + ".attributes", player.attributePoints().get());
            RollCore.getPlayersSetting().save();
            attributePlayers.remove(player);
        });
    }

}
