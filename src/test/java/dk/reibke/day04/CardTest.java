package dk.reibke.day04;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @ParameterizedTest
    @MethodSource("lines")
    void getMatchingNumbersScore(String line, long expectedScore) {
        Card card = Card.fromLine(line);
        long matchingNumbersScore = card.getMatchingNumbersScore();

        assertEquals(expectedScore, matchingNumbersScore);
    }


    public static Stream<Arguments> lines() {
        return Stream.of(
                Arguments.of("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53", 8L),
                Arguments.of("Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19", 2L),
                Arguments.of("Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1", 2L),
                Arguments.of("Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83", 1L),
                Arguments.of("Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36", 0L),
                Arguments.of("Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11", 0L)
        );
    }
}