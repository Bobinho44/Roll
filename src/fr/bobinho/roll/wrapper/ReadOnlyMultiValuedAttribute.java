package fr.bobinho.roll.wrapper;

import fr.bobinho.roll.api.validate.BValidate;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Wrapper of read only multi-valued attribute
 */
public class ReadOnlyMultiValuedAttribute<T> {

    /**
     * Fields
     */
    protected final List<T> values;

    /**
     * Creates a new read only multi-valued attribute wrapper with initial values
     *
     * @param values the initial values
     */
    public ReadOnlyMultiValuedAttribute(@Nonnull List<T> values) {
        BValidate.notNull(values);

        this.values = values;
    }

    /**
     * Gets a copy of values
     *
     * @return the copy of values
     */
    @Nonnull
    public List<T> get() {
        return List.copyOf(values);
    }

    /**
     * Gets the value at index i of the multi-valued attribute
     *
     * @param i the index of the value
     * @return the value at index i of the multi-valued attribute
     */
    @Nonnull
    public T get(int i) {
        return values.get(i);
    }

}
