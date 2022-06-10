package fr.bobinho.roll.api.location;

import fr.bobinho.roll.api.validate.BValidate;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import javax.annotation.Nonnull;

/**
 * Bobinho location library
 */
public final class BLocation {

    /**
     * Unitilizable constructor (utility class)
     */
    private BLocation() {
    }

    /**
     * Serializes a location
     *
     * @param location the location
     * @return the serialized location
     */
    @Nonnull
    public static String getAsString(@Nonnull Location location) {
        BValidate.notNull(location);

        return location.getWorld().getName() + ":" +
                location.getX() + ":" +
                location.getY() + ":" +
                location.getZ() + ":" +
                location.getYaw() + ":" +
                location.getPitch();
    }

    /**
     * Deserializes a location
     *
     * @param locationString the location string
     * @return the deserialized location
     */
    @Nonnull
    public static Location getAsLocation(@Nonnull String locationString) {
        BValidate.notNull(locationString);

        String[] locationInformations = locationString.split(":");
        return new Location(
                Bukkit.getWorld(locationInformations[0]),
                Double.parseDouble(locationInformations[1]),
                Double.parseDouble(locationInformations[2]),
                Double.parseDouble(locationInformations[3]),
                Float.parseFloat(locationInformations[4]),
                Float.parseFloat(locationInformations[5])
        );
    }

    /**
     * Checks if the tested 1D coordinate is between the two others
     *
     * @param coordinates1 the first 1D coordinate
     * @param coordinates2 the second 1D coordinate
     * @param tested       the tested 1D coordinate
     * @return if the tested 1D coordinate is between the two others
     */
    public static boolean isBetweenTwo1DPoint(double coordinates1, double coordinates2, double tested) {
        return (tested >= coordinates1 && tested <= coordinates2) || (tested <= coordinates1 && tested >= coordinates2);
    }

    /**
     * Checks if the tested 2D coordinate is between the two others
     *
     * @param location1 the first 2D coordinate
     * @param location2 the second 2D coordinate
     * @param tested    the tested 0D coordinate
     * @return if the tested 2D coordinate is between the two others
     */
    public static boolean isBetweenTwo2DPoint(@Nonnull Location location1, @Nonnull Location location2, @Nonnull Location tested) {
        BValidate.notNull(location1);
        BValidate.notNull(location2);
        BValidate.notNull(tested);

        return location1.getWorld().equals(tested.getWorld()) && isBetweenTwo1DPoint(location1.getX(), location2.getX(), tested.getX()) && isBetweenTwo1DPoint(location1.getZ(), location2.getZ(), tested.getZ());
    }

}
