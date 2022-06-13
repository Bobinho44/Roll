package fr.bobinho.roll.util.attribute;

import fr.bobinho.roll.api.validate.BValidate;
import fr.bobinho.roll.util.notification.AttributeNotification;
import fr.bobinho.roll.wrapper.ReadOnlyMonoValuedAttribute;
import fr.bobinho.roll.wrapper.ReadOnlyMultiValuedAttribute;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Enum of attributes
 */
public enum Attribute {
    ATHLETICS(0, AttributeNotification.ATHLETICS_TITLE.getNotification(),
            List.of(AttributeNotification.ATHLETICS_DESCRIPTION_1.getNotification(), AttributeNotification.ATHLETICS_DESCRIPTION_2.getNotification())),
    ACROBATICS(1, AttributeNotification.ACROBATICS_TITLE.getNotification(),
            List.of(AttributeNotification.ACROBATICS_DESCRIPTION_1.getNotification(), AttributeNotification.ACROBATICS_DESCRIPTION_2.getNotification())),
    REFLEX(2, AttributeNotification.REFLEX_TITLE.getNotification(),
            List.of(AttributeNotification.REFLEX_DESCRIPTION_1.getNotification(), AttributeNotification.REFLEX_DESCRIPTION_2.getNotification())),
    CHARISMA(3, AttributeNotification.CHARISMA_TITLE.getNotification(),
            List.of(AttributeNotification.CHARISMA_DESCRIPTION_1.getNotification(), AttributeNotification.CHARISMA_DESCRIPTION_2.getNotification())),
    PERSUASION(4, AttributeNotification.PERSUASION_TITLE.getNotification(),
            List.of(AttributeNotification.PERSUASION_DESCRIPTION_1.getNotification(), AttributeNotification.PERSUASION_DESCRIPTION_2.getNotification())),
    DECEPTION(5, AttributeNotification.DECEPTION_TITLE.getNotification(),
            List.of(AttributeNotification.DECEPTION_DESCRIPTION_1.getNotification(), AttributeNotification.DECEPTION_DESCRIPTION_2.getNotification())),
    INTIMIDATION(6, AttributeNotification.INTIMIDATION_TITLE.getNotification(),
            List.of(AttributeNotification.INTIMIDATION_DESCRIPTION_1.getNotification(), AttributeNotification.INTIMIDATION_DESCRIPTION_2.getNotification())),
    PERCEPTION(7, AttributeNotification.PERCEPTION_TITLE.getNotification(),
            List.of(AttributeNotification.PERCEPTION_DESCRIPTION_1.getNotification(), AttributeNotification.PERCEPTION_DESCRIPTION_2.getNotification())),
    PERFORMANCE(8, AttributeNotification.PERFORMANCE_TITLE.getNotification(),
            List.of(AttributeNotification.PERFORMANCE_DESCRIPTION_1.getNotification(), AttributeNotification.PERFORMANCE_DESCRIPTION_2.getNotification())),
    RELIGION(9, AttributeNotification.RELIGION_TITLE.getNotification(),
            List.of(AttributeNotification.RELIGION_DESCRIPTION_1.getNotification(), AttributeNotification.RELIGION_DESCRIPTION_2.getNotification())),
    MEDICINE(10, AttributeNotification.MEDICINE_TITLE.getNotification(),
            List.of(AttributeNotification.MEDICINE_DESCRIPTION_1.getNotification(), AttributeNotification.MEDICINE_DESCRIPTION_2.getNotification())),
    NATURE(11, AttributeNotification.NATURE_TITLE.getNotification(),
            List.of(AttributeNotification.NATURE_DESCRIPTION_1.getNotification(), AttributeNotification.NATURE_DESCRIPTION_2.getNotification())),
    ANIMAL_HANDLING(12, AttributeNotification.ANIMAL_HANDLING_TITLE.getNotification(),
            List.of(AttributeNotification.ANIMAL_HANDLING_DESCRIPTION_1.getNotification(), AttributeNotification.ANIMAL_HANDLING_DESCRIPTION_2.getNotification())),
    STEALTH(13, AttributeNotification.STEALTH_TITLE.getNotification(),
            List.of(AttributeNotification.STEALTH_DESCRIPTION_1.getNotification(), AttributeNotification.STEALTH_DESCRIPTION_2.getNotification())),
    LOCKPIKING(14, AttributeNotification.LOCKPIKING_TITLE.getNotification(),
            List.of(AttributeNotification.LOCKPIKING_DESCRIPTION_1.getNotification())),
    PICKPOCKETING(15, AttributeNotification.PICKPOCKETING_TITLE.getNotification(),
            List.of(AttributeNotification.PICKPOCKETING_DESCRIPTION_1.getNotification(), AttributeNotification.PICKPOCKETING_DESCRIPTION_2.getNotification()));

    /**
     * Fields
     */
    private final ReadOnlyMonoValuedAttribute<Integer> id;
    private final ReadOnlyMonoValuedAttribute<String> title;
    private final ReadOnlyMultiValuedAttribute<String> description;

    /**
     * Creates a new attribute
     *
     * @param id          the attribute id
     * @param title       the attribute title
     * @param description the attribute description
     */
    Attribute(int id, @Nonnull String title, @Nonnull List<String> description) {
        BValidate.notNull(title);
        BValidate.notNull(description);

        this.id = new ReadOnlyMonoValuedAttribute<>(id);
        this.title = new ReadOnlyMonoValuedAttribute<>(title);
        this.description = new ReadOnlyMultiValuedAttribute<>(description);
    }

    /**
     * Gets the wrapper of the id
     *
     * @return the wrapper of the id
     */
    @Nonnull
    public ReadOnlyMonoValuedAttribute<Integer> id() {
        return id;
    }

    /**
     * Gets the wrapper of the title
     *
     * @return the wrapper of the title
     */
    @Nonnull
    public ReadOnlyMonoValuedAttribute<String> title() {
        return title;
    }

    /**
     * Gets the wrapper of the description
     *
     * @return the wrapper of the description
     */
    @Nonnull
    public ReadOnlyMultiValuedAttribute<String> description() {
        return description;
    }

    /**
     * Gets an attribute from his slot number
     *
     * @param slot the slot number
     * @return the attribute associated with the slot number
     */
    @Nonnull
    public static Optional<Attribute> getFromSlot(int slot) {
        return Arrays.stream(values()).filter(attribute -> 37 + 2 * attribute.id.get() - attribute.id.get() / 4 * 17 == slot).findFirst();
    }

}
