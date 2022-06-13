package fr.bobinho.roll.util.player;

import fr.bobinho.roll.api.validate.BValidate;
import fr.bobinho.roll.util.attribute.Attribute;
import fr.bobinho.roll.wrapper.MonoValuedAttribute;
import fr.bobinho.roll.wrapper.ReadOnlyMonoValuedAttribute;
import fr.bobinho.roll.wrapper.UpperBoundMultiValuedAttribute;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The player class
 */
public class AttributePlayer {

    /**
     * Fields
     */
    private final ReadOnlyMonoValuedAttribute<UUID> uuid;
    private final MonoValuedAttribute<Integer> points;
    private final UpperBoundMultiValuedAttribute<Integer> attributePoints;

    /**
     * Creates a new attribute player
     * @param uuid the attribute player uuid
     * @param points the attribute player points
     * @param attributePoints the attribute player point associated with each attribute
     */
    public AttributePlayer(@Nonnull UUID uuid, int points, @Nonnull List<Integer> attributePoints) {
        BValidate.notNull(uuid);
        BValidate.notNull(attributePoints);

        this.uuid = new ReadOnlyMonoValuedAttribute<>(uuid);
        this.points = new MonoValuedAttribute<>(points);
        this.attributePoints = new UpperBoundMultiValuedAttribute<>(attributePoints, 16);
    }

    /**
     * Gets the attribute player uuid wrapper
     * @return the attribute player uuid wrapper
     */
    @Nonnull
    public ReadOnlyMonoValuedAttribute<UUID> uuid() {
        return uuid;
    }

    /**
     * Gets the attribute player points wrapper
     * @return the attribute player points wrapper
     */
    @Nonnull
    public MonoValuedAttribute<Integer> points() {
        return points;
    }

    /**
     * Gets the attribute player point associated with each attribute wrapper
     * @return the attribute player point associated with each attribute wrapper
     */
    @Nonnull
    public UpperBoundMultiValuedAttribute<Integer> attributePoints() {
        return attributePoints;
    }

    /**
     * Gets the spigot attribute player
     * @return the spigot attribute player
     */
    @Nonnull
    public Optional<Player> getPlayer() {
        return Optional.ofNullable(Bukkit.getPlayer(uuid.get()));
    }

    /**
     * Uses an attribute player point in the attribute
     * @param attribute the attribute
     */
    public void usePoint(@Nonnull Attribute attribute) {
        BValidate.notNull(attribute);

        points.set(points.get() - 1);
        attributePoints.set(attribute.id().get(), attributePoints.get(attribute.id().get()) + 1);
    }

    /**
     * Gives a point to the attribute player
     */
    public void givePoint() {
        points.set(points.get() + 1);
    }

    /**
     * Removes a point from the attribute player
     */
    public void removePoint() {
        points.set(Math.max(0, points.get() - 1));
    }

    /**
     * Resets all attribute of the attribute player
     */
    public void reset() {
        for (int i = 0; i < 16; i++) {
            points.set(points.get() + attributePoints.get(i));
            attributePoints.set(i, 0);
        }
    }

}
