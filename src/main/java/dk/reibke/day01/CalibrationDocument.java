package dk.reibke.day01;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class CalibrationDocument {


    private static final Pattern DIGIT_REGEX = Pattern.compile("[0-9]");
    private static final Predicate<String> DIGIT_REGEX_PREDICATE = DIGIT_REGEX.asPredicate();
    private static final Pattern GENERIC_DIGIT_REGEX = Pattern.compile(
            String.join("|", Stream.of(Digit.values()).map(Digit::regex).toList())
    );

    public Long sumLines(Stream<String> lines, Function<String, Long> extractor) {
        return lines.map(extractor)
                .mapToLong(l -> l)
                .sum();
    }

    public Long findDigitInLine(String line) {
        List<String> digits = line.chars().mapToObj(i -> (char) i)
                .map(Object::toString)
                .filter(DIGIT_REGEX_PREDICATE)
                .toList();

        String firstDigit = digits.get(0);
        String lastDigit = digits.get(digits.size() - 1);

        return Long.parseLong(firstDigit + lastDigit);
    }

    public Long findGenericDigitsInLine(String line) {
        Matcher matcher = GENERIC_DIGIT_REGEX.matcher(line);
        Digit firstDigit = findFirstDigit(line, matcher);
        Digit lastDigit = findLastDigit(line, matcher);

        return firstDigit.combineWith(lastDigit);
    }

    private Digit findFirstDigit(String line, Matcher matcher) {
        if (matcher.find()) {
            return Digit.fromString(matcher.group());
        }
        throw new RuntimeException("Unable to find any digit in: " + line);
    }

    private Digit findLastDigit(String line, Matcher matcher) {
        for (int i = line.length() - 1; i >= 0; i--) {
            if (matcher.find(i)) {
                return Digit.fromString(matcher.group());
            }
        }
        throw new RuntimeException("Unable to find any digit in: " + line);
    }
}
