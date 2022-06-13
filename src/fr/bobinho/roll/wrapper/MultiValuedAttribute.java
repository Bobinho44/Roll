package fr.bobinho.roll.wrapper;

import fr.bobinho.roll.api.validate.BValidate;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper of multi-valued attribute
 */
public class MultiValuedAttribute<T> extends ReadOnlyMultiValuedAttribute<T> {

    /**
     * Creates a new multi-valued attribute wrapper with initial values
     *
     * @param values the initial values
     */
    public MultiValuedAttribute(@NotNull List<T> values) {
        super(values);
    }

    /**
     * Creates an empty new multi-valued attribute wrapper
     */
    public MultiValuedAttribute() {
        super(new ArrayList<>());
    }

    /**
     * Replaces the index-th value of the list
     *
     * @param index the index
     * @param value the new value
     */
    public void set(int index, @Nonnull T value) {
        BValidate.notNull(value);

        values.set(index, value);
    }

    /**
     * Adds a new value to the multi-valued attribute
     *
     * @param value the value
     */
    public void add(@Nonnull T value) {
        BValidate.notNull(value);

        values.add(value);
    }

    /**
     * Removes a value from the multi-valued attribute
     *
     * @param value the value
     */
    public void remove(@Nonnull T value) {
        BValidate.notNull(value);

        values.remove(value);
    }

}
