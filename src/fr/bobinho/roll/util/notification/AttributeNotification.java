package fr.bobinho.roll.util.notification;

import fr.bobinho.roll.RollCore;
import fr.bobinho.roll.api.notification.BNotification;
import fr.bobinho.roll.api.notification.BPlaceHolder;

/**
 * Enum of attribute notifications
 */
public enum AttributeNotification implements BNotification {
    ATHLETICS_TITLE(),
    ATHLETICS_DESCRIPTION_1(),
    ATHLETICS_DESCRIPTION_2(),
    ACROBATICS_TITLE(),
    ACROBATICS_DESCRIPTION_1(),
    ACROBATICS_DESCRIPTION_2(),
    REFLEX_TITLE(),
    REFLEX_DESCRIPTION_1(),
    REFLEX_DESCRIPTION_2(),
    CHARISMA_TITLE(),
    CHARISMA_DESCRIPTION_1(),
    CHARISMA_DESCRIPTION_2(),
    PERSUASION_TITLE(),
    PERSUASION_DESCRIPTION_1(),
    PERSUASION_DESCRIPTION_2(),
    DECEPTION_TITLE(),
    DECEPTION_DESCRIPTION_1(),
    DECEPTION_DESCRIPTION_2(),
    INTIMIDATION_TITLE(),
    INTIMIDATION_DESCRIPTION_1(),
    INTIMIDATION_DESCRIPTION_2(),
    PERCEPTION_TITLE(),
    PERCEPTION_DESCRIPTION_1(),
    PERCEPTION_DESCRIPTION_2(),
    PERFORMANCE_TITLE(),
    PERFORMANCE_DESCRIPTION_1(),
    PERFORMANCE_DESCRIPTION_2(),
    RELIGION_TITLE(),
    RELIGION_DESCRIPTION_1(),
    RELIGION_DESCRIPTION_2(),
    MEDICINE_TITLE(),
    MEDICINE_DESCRIPTION_1(),
    MEDICINE_DESCRIPTION_2(),
    NATURE_TITLE(),
    NATURE_DESCRIPTION_1(),
    NATURE_DESCRIPTION_2(),
    ANIMAL_HANDLING_TITLE(),
    ANIMAL_HANDLING_DESCRIPTION_1(),
    ANIMAL_HANDLING_DESCRIPTION_2(),
    STEALTH_TITLE(),
    STEALTH_DESCRIPTION_1(),
    STEALTH_DESCRIPTION_2(),
    LOCKPIKING_TITLE(),
    LOCKPIKING_DESCRIPTION_1(),
    PICKPOCKETING_TITLE(),
    PICKPOCKETING_DESCRIPTION_1(),
    PICKPOCKETING_DESCRIPTION_2();

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNotification() {
        return RollCore.getMessagesSetting().getConfiguration().getString(this.name(), "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNotification(BPlaceHolder... placeholders) {
        return getNotification();
    }
}
