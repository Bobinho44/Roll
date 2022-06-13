package fr.bobinho.roll.util.menu;

import fr.bobinho.roll.api.event.BEvent;
import fr.bobinho.roll.api.item.BItemBuilder;
import fr.bobinho.roll.api.menu.BMenu;
import fr.bobinho.roll.api.validate.BValidate;
import fr.bobinho.roll.util.attribute.Attribute;
import fr.bobinho.roll.util.player.AttributePlayer;
import fr.bobinho.roll.util.player.AttributePlayerManager;
import org.bukkit.Material;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * Manager class of menus
 */
public final class AttributeMenuManager {

    /**
     * Initialize the manager class of menus
     */
    public static void initialize() {

        //Manages the inventory click
        BEvent.registerEvent(InventoryClickEvent.class, EventPriority.LOWEST)
                .filter(event -> event.getClickedInventory() != null)
                .filter(event -> Objects.requireNonNull(event.getClickedInventory()).getHolder() != null)
                .filter(event -> Objects.requireNonNull(event.getClickedInventory()).getHolder() instanceof AttributeHolder)
                .filter(event -> event.getCurrentItem() != null)
                .consume(event -> {
                            event.setCancelled(true);

                            //Gets the attribute player who clicked
                            AttributePlayerManager.getAttributePlayer(event.getWhoClicked().getUniqueId()).ifPresent(player -> {

                                //Checks if the attribute player has clicked on an attribute item
                                if (Objects.requireNonNull(event.getCurrentItem()).getType().name().matches(".*STAINED_GLASS")) {

                                    //Checks if the attribute player have points
                                    if (player.points().get() == 0) {
                                        event.getWhoClicked().sendMessage("You have already spent all your attribute points.");
                                        return;
                                    }

                                    //Gets the clicked attribute
                                    Attribute.getFromSlot(event.getSlot()).ifPresent(attribute -> {

                                        //Checks if the attribute player does not already have 10 points in this attribute
                                        if (player.attributePoints().get(attribute.id().get()) == 10) {
                                            event.getWhoClicked().sendMessage("You have already reached the maximum level for this attribute.");
                                            return;
                                        }

                                        //Uses a point in this attribute
                                        player.usePoint(attribute);
                                    });

                                    //Updates the menu
                                    openAttributeMenu(player);
                                }
                            });
                        }
                );
    }

    /**
     * Opens the attribute menu
     * @param roller the attribute player
     */
    public static void openAttributeMenu(@Nonnull AttributePlayer roller) {
        BValidate.notNull(roller);

        //Creates the menu
        Inventory menu = BMenu.createMenu(new AttributeHolder(), 54, "Attributes : " + roller.points().get() + " point(s)");

        //Adds the design menu
        for (int i = 0; i < 54; i++) {
            menu.setItem(i, new BItemBuilder(Material.WHITE_STAINED_GLASS_PANE).name("").build());
        }

        //Removes item from the menu center
        for (int i = 0; i < 28; i++) {
            menu.setItem(10 + i + i / 7 * 2, new BItemBuilder(Material.AIR).build());
        }

        //Adds attributes items
        for (Attribute attribute : Attribute.values()) {
            int rollerPoint = roller.attributePoints().get(attribute.id().get());

            menu.setItem(37 + 2 * attribute.id().get() - attribute.id().get() / 4 * 17, new BItemBuilder(rollerPoint > 0 ? Material.GREEN_STAINED_GLASS : Material.RED_STAINED_GLASS)
                    .name(attribute.title().get())
                    .lore(attribute.description().get())
                    .amount(Math.max(1, rollerPoint))
                    .build());
        }

        //Opens the menu
        roller.getPlayer().ifPresent(player -> player.openInventory(menu));
    }

}
