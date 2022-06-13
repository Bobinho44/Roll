package fr.bobinho.roll.api.number;

import fr.bobinho.roll.api.validate.BValidate;

import javax.annotation.Nonnull;
import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Bobinho number library
 */
public final class BNumber {

    /**
     * Fields
     */
    private static final DecimalFormat baseDecimalFormat = new DecimalFormat("###,###,###,###,###,###,###,###.#");

    /**
     * Creates a random number maximum of given number
     *
     * @param <T>         the number type
     * @param maximum     the maximum number
     * @param numberClass the number class
     * @return the random number
     */
    @SuppressWarnings("unchecked")
    @Nonnull
    public static <T extends Number> T random(@Nonnull T maximum, @Nonnull Class<T> numberClass) {
        BValidate.notNull(maximum);
        BValidate.notNull(numberClass);

        //Gets the random
        if (numberClass == int.class || numberClass == Integer.class)
            return (T) (Object) random(maximum.intValue());
        else if (numberClass == double.class || numberClass == Double.class)
            return (T) (Object) random(maximum.doubleValue());
        else if (numberClass == long.class || numberClass == Long.class)
            return (T) (Object) random(maximum.longValue());
        return (T) (Object) 0;
    }

    /**
     * Creates a random number in range of minimum and maximum number
     *
     * @param <T>         the number type
     * @param minimum     the minimum number
     * @param maximum     the maximum number
     * @param numberClass the number class
     * @return the random number
     */
    @SuppressWarnings("unchecked")
    @Nonnull
    public static <T extends Number> T random(@Nonnull T minimum, @Nonnull T maximum, @Nonnull Class<T> numberClass) {
        BValidate.notNull(minimum);
        BValidate.notNull(maximum);
        BValidate.notNull(numberClass);

        //Gets the random
        if (numberClass == int.class || numberClass == Integer.class)
            return (T) (Object) random(minimum.intValue(), maximum.intValue());
        else if (numberClass == double.class || numberClass == Double.class)
            return (T) (Object) random(minimum.doubleValue(), maximum.doubleValue());
        else if (numberClass == long.class || numberClass == Long.class)
            return (T) (Object) random(minimum.longValue(), maximum.longValue());
        return (T) (Object) 0;
    }

    /**
     * Creates a random integer maximum of given number
     *
     * @param maximum the maximum integer
     * @return the random integer
     */
    public static int random(int maximum) {
        return ThreadLocalRandom.current().ints(0, maximum).findFirst().orElse(0);
    }

    /**
     * Creates a random double maximum of given number
     *
     * @param maximum the maximum double
     * @return the random double
     */
    public static double random(double maximum) {
        return ThreadLocalRandom.current().doubles(0.0D, maximum).findFirst().orElse(0.0D);
    }

    /**
     * Creates a random long maximum of given number
     *
     * @param maximum the maximum long
     * @return the random long
     */
    public static long random(long maximum) {
        return ThreadLocalRandom.current().longs(0L, maximum).findFirst().orElse(0L);
    }

    /**
     * Creates a random integer in range of minimum and maximum number
     *
     * @param minimum the minimum integer
     * @param maximum the maximum integer
     * @return the random integer
     */
    public static int random(int minimum, int maximum) {
        return ThreadLocalRandom.current().ints(minimum, maximum).findFirst().orElse(0);
    }

    /**
     * Creates a random double in range of minimum and maximum number
     *
     * @param minimum the minimum double
     * @param maximum the maximum double
     * @return the random double
     */
    public static double random(double minimum, double maximum) {
        return ThreadLocalRandom.current().doubles(minimum, maximum).findFirst().orElse(0.0D);
    }

    /**
     * Creates a random long in range of minimum and maximum number
     *
     * @param minimum the minimum long
     * @param maximum the maximum long
     * @return the random long
     */
    public static long random(long minimum, long maximum) {
        return ThreadLocalRandom.current().longs(minimum, maximum).findFirst().orElse(0L);
    }

    /**
     * Generates a random number between 0 and 100 then compare with the probability
     *
     * @param probability the probability
     * @return the pass it or not statue
     */
    public static boolean chance(int probability) {
        return random(0, 100, int.class) <= probability;
    }

    /**
     * Formats a integer as readable text
     *
     * @param number the integer number
     * @return the readable text
     */
    @Nonnull
    public static String format(int number) {
        return baseDecimalFormat.format(number);
    }

    /**
     * Formats a double as readable text
     *
     * @param number the double number
     * @return the readable text
     */
    @Nonnull
    public static String format(double number) {
        return baseDecimalFormat.format(number);
    }

    /**
     * Formats a long as readable text.
     *
     * @param number the long number
     * @return the readable text
     */
    @Nonnull
    public static String format(long number) {
        return baseDecimalFormat.format(number);
    }

}
