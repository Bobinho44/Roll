package fr.bobinho.roll.util.notification;

import fr.bobinho.roll.RollCore;
import fr.bobinho.roll.api.notification.BNotification;
import fr.bobinho.roll.api.notification.BPlaceHolder;
import org.bukkit.ChatColor;

/**
 * Enum of admin notifications
 */
public enum AdminNotification implements BNotification {
    NO_PLAYER_FOUND(),
    ATTRIBUTE_GIVE_SENDER(),
    ATTRIBUTE_GIVE_TARGET(),
    ATTRIBUTE_REMOVE_SENDER(),
    ATTRIBUTE_REMOVE_TARGET(),
    ATTRIBUTE_RESET_SENDER(),
    ATTRIBUTE_RESET_TARGET();

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNotification() {
        return ChatColor.translateAlternateColorCodes('&', RollCore.getMessagesSetting().getConfiguration().getString(this.name(), ""));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNotification(BPlaceHolder... placeholders) {
        String notification = ChatColor.translateAlternateColorCodes('&', RollCore.getMessagesSetting().getConfiguration().getString(this.name(), ""));

        for (BPlaceHolder placeHolder : placeholders) {
            notification = notification.replaceAll(placeHolder.getOldValue(), placeHolder.getReplacement());
        }

        return notification;
    }

}
