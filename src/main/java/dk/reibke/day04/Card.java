package dk.reibke.day04;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static dk.reibke.utils.Regexes.NUMBER_REGEX;

public record Card(int cardNumber, List<Integer> winningNumbers, List<Integer> numbers) {

    public static final Pattern CARD_SECTION_REGEX = Pattern.compile("Card\\s+(?<cardNumber>\\d+):(?<winningNumbers>.+)\\|(?<numbers>.+)");


    public static Card fromLine(String line) {
        Matcher matcher = CARD_SECTION_REGEX.matcher(line);

        if(!matcher.find()) {
            throw new RuntimeException(String.format("Unable to find the Card Section format in: [%s]", line));
        }

        int cardNumber = Integer.parseInt(matcher.group("cardNumber"));
        String winningNumbersAsString = matcher.group("winningNumbers");
        String numbersAsString = matcher.group("numbers");

        List<Integer> winningNumbers = fromString(winningNumbersAsString);
        List<Integer> numbers = fromString(numbersAsString);

        return new Card(cardNumber, winningNumbers, numbers);
    }

    private static List<Integer> fromString(String winningNumbersAsString) {
        return NUMBER_REGEX.matcher(winningNumbersAsString)
                .results()
                .map(MatchResult::group)
                .map(Integer::parseInt)
                .toList();
    }

    public long getMatchingNumbersScore() {
        long matches = countMatchingNumbers();

        return matches == 0 ? 0 : 1L << (matches - 1L);
    }

    public long countMatchingNumbers() {
        return numbers().stream()
                .filter(winningNumbers()::contains)
                .count();
    }
}
