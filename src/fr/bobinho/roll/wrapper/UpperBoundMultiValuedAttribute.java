package fr.bobinho.roll.wrapper;

import fr.bobinho.roll.api.validate.BValidate;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Wrapper of upper bound multi-valued attribute
 */
public class UpperBoundMultiValuedAttribute<T> extends MultiValuedAttribute<T> {

    /**
     * Fields
     */
    private final int maxSize;

    /**
     * Creates a new upper bound multi-valued attribute wrapper with initial values
     *
     * @param values  the initial values
     * @param maxSize the max size
     */
    public UpperBoundMultiValuedAttribute(@Nonnull List<T> values, int maxSize) {
        super(values);

        BValidate.notNull(values);
        BValidate.isTrue(values.size() <= maxSize);

        this.maxSize = maxSize;
    }

    /**
     * Creates a new empty upper bound multi-valued attribute wrapper
     *
     * @param maxSize the max size
     */
    public UpperBoundMultiValuedAttribute(int maxSize) {
        super();

        this.maxSize = maxSize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(@Nonnull T value) {
        BValidate.notNull(value);
        BValidate.isTrue(values.size() < maxSize);

        values.add(value);
    }

}
