package fr.bobinho.roll.api.menu;

import fr.bobinho.roll.api.validate.BValidate;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import javax.annotation.Nonnull;

/**
 * Bobinho holder library
 */
public class BHolder implements InventoryHolder {

    /**
     * Fields
     */
    private Inventory inventory;

    /**
     * Sets the holder inventory
     *
     * @param inventory the inventory
     */
    public void setInventory(@Nonnull Inventory inventory) {
        BValidate.notNull(inventory);

        this.inventory = inventory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nonnull
    public Inventory getInventory() {
        return inventory;
    }

}
