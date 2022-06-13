package fr.bobinho.roll.api.menu;

import fr.bobinho.roll.api.validate.BValidate;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import javax.annotation.Nonnull;

/**
 * Bobinho menu library
 */
public final class BMenu {

    /**
     * Creates a new menu
     *
     * @param holder the holder
     * @param size   the size
     * @param title  the title
     * @return the menu
     */
    @Nonnull
    public static Inventory createMenu(@Nonnull BHolder holder, int size, @Nonnull String title) {
        BValidate.notNull(holder);
        BValidate.notNull(title);

        return Bukkit.createInventory(holder, size, Component.text(title));
    }

}
