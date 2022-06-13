package fr.bobinho.roll.util.notification;

import fr.bobinho.roll.RollCore;
import fr.bobinho.roll.api.notification.BNotification;
import fr.bobinho.roll.api.notification.BPlaceHolder;
import org.bukkit.ChatColor;

/**
 * Enum of roll notifications
 */
public enum RollNotification implements BNotification {
    ROLL(),
    ROLL_ATTACK_LESS_10(),
    ROLL_ATTACK_20(),
    ROLL_ATTACK_MORE_10(),
    ROLL_DEFEND_LESS_10(),
    ROLL_DEFEND_20(),
    ROLL_DEFEND_MORE_10(),
    ROLL_RANGE_LESS_10(),
    ROLL_RANGE_20(),
    ROLL_RANGE_MORE_10(),
    ROLL_RUN_NOT_20(),
    ROLL_RUN_20(),
    ROLL_GRAB_NOT_20(),
    ROLL_GRAB_20(),
    ROLL_RESIST_NOT_20(),
    ROLL_RESIST_20(),
    ROLL_PICKPOCKET_LESS_10(),
    ROLL_PICKPOCKET_20(),
    ROLL_PICKPOCKET_MORE_10(),
    ROLL_STEALTH_LESS_10(),
    ROLL_STEALTH_MORE_10(),
    ROLL_ACROBATICS_LESS_10(),
    ROLL_ACROBATICS_MORE_10(),
    ROLL_ATHLETICS_LESS_10(),
    ROLL_ATHLETICS_MORE_10(),
    ROLL_REFLEX_LESS_10(),
    ROLL_REFLEX_MORE_10(),
    ROLL_CHARISMA_LESS_10(),
    ROLL_CHARISMA_MORE_10(),
    ROLL_PERSUASION_LESS_10(),
    ROLL_PERSUASION_MORE_10(),
    ROLL_DECEPTION_LESS_10(),
    ROLL_DECEPTION_MORE_10(),
    ROLL_INTIMIDATION_LESS_10(),
    ROLL_INTIMIDATION_MORE_10(),
    ROLL_RELIGION_LESS_10(),
    ROLL_RELIGION_MORE_10(),
    ROLL_PERCEPTION_LESS_10(),
    ROLL_PERCEPTION_MORE_10(),
    ROLL_NATURE_LESS_10(),
    ROLL_NATURE_MORE_10(),
    ROLL_ANIMAL_HANDLING_LESS_10(),
    ROLL_ANIMAL_HANDLING_MORE_10(),
    ROLL_MEDICINE_LESS_10(),
    ROLL_MEDICINE_MORE_10(),
    ROLL_PERFORMANCE_LESS_10(),
    ROLL_PERFORMANCE_MORE_10(),
    ROLL_LOCKPICK_LESS_10(),
    ROLL_LOCKPICK_MORE_10;

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
