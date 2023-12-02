package dk.reibke.day01;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public enum Digit {
    ONE(1L),
    TWO(2L),
    THREE(3L),
    FOUR(4L),
    FIVE(5L),
    SIX(6L),
    SEVEN(7L),
    EIGHT(8L),
    NINE(9L);

    private static final Map<String, Digit> lookup = new HashMap<>();

    static {
        Map<String, Digit> digitLookup = Arrays.stream(Digit.values())
                .collect(Collectors.toMap(
                        digit -> Long.toString(digit.value),
                        digit -> digit));
        Map<String, Digit> textLookup = Arrays.stream(Digit.values())
                .collect(Collectors.toMap(
                        digit -> digit.representation,
                        digit -> digit));
        lookup.putAll(textLookup);
        lookup.putAll(digitLookup);
    }




    public final long value;
    public final String representation;

    Digit(long value) {
        this.value = value;
        this.representation = name().toLowerCase();
    }

    public String regex() {
        return String.format("(%s|%s)", representation, value);
    }

    public static Digit fromString(String digit) {
        return lookup.get(digit);
    }

    public Long combineWith(Digit digit) {
        return (this.value * 10L) + digit.value;
    }
}
