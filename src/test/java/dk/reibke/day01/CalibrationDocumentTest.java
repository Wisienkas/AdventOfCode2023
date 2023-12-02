package dk.reibke.day01;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CalibrationDocumentTest {

    @Test
    void sumLines() {
        String testInput = lineFeed()
                .map(arguments -> (String) arguments.get()[0]).collect(Collectors.joining("\n"));

        var calibrationDocument = new CalibrationDocument();
        var lines = Stream.of(testInput.split("\n"));
        Long sum = calibrationDocument.sumLines(lines, calibrationDocument::findDigitInLine);

        assertEquals(142, sum);
    }

    @Test
    void sumFancyLines() {
        String testInput = lineFeedFancy()
                .map(arguments -> (String) arguments.get()[0]).collect(Collectors.joining("\n"));

        var calibrationDocument = new CalibrationDocument();
        var lines = Stream.of(testInput.split("\n"));
        Long sum = calibrationDocument.sumLines(lines, calibrationDocument::findGenericDigitsInLine);

        assertEquals(281, sum);
    }

    @ParameterizedTest
    @MethodSource("lineFeedFancy")
    void lineValueFancy(String line, Long expectedValue) {
        var calibrationDocument = new CalibrationDocument();
        Long digit = calibrationDocument.findGenericDigitsInLine(line);

        assertEquals(expectedValue, digit);
    }

    @ParameterizedTest
    @MethodSource("lineFeedFancyOverlaps")
    void lineValueFancyOverlapTest(String line, Long expectedValue) {
        var calibrationDocument = new CalibrationDocument();
        Long digit = calibrationDocument.findGenericDigitsInLine(line);

        assertEquals(expectedValue, digit);
    }

    @ParameterizedTest
    @MethodSource("lineFeedFancySingleDigit")
    void lineValueFancySingleDigitTest(String line, Long expectedValue) {
        var calibrationDocument = new CalibrationDocument();
        Long digit = calibrationDocument.findGenericDigitsInLine(line);

        assertEquals(expectedValue, digit);
    }


    @ParameterizedTest
    @MethodSource("lineFeed")
    void lineValue(String line, Long expectedValue) {
        var calibrationDocument = new CalibrationDocument();
        Long digit = calibrationDocument.findDigitInLine(line);

        assertEquals(expectedValue, digit);
    }

    private static Stream<Arguments> lineFeedFancy(){
        return Stream.of(
                Arguments.of("two1nine", 29L),
                Arguments.of("eightwothree", 83L),
                Arguments.of("abcone2threexyz", 13L),
                Arguments.of("xtwone3four", 24L),
                Arguments.of("4nineeightseven2", 42L),
                Arguments.of("zoneight234", 14L),
                Arguments.of("7pqrstsixteen", 76L)
        );
    }

    private static Stream<Arguments> lineFeedFancyOverlaps(){
        return Stream.of(
                Arguments.of("nine671seventwotwonejkf", 91L),
                Arguments.of("xfbcskone12jvvthreeightfn", 18L)
        );
    }

    private static Stream<Arguments> lineFeedFancySingleDigit(){
        return Stream.of(
                Arguments.of("ninesevjdfasjd", 99L),
                Arguments.of("2askdhcvqwueoasdio", 22L),
                Arguments.of("nnesevjthreedfasjd", 33L),
                Arguments.of("askdhcvqw3ueoasdio", 33L),
                Arguments.of("nnesevjdfasjdtwo", 22L),
                Arguments.of("askdhcvqwueoasdio7", 77L)

                );
    }

    private static Stream<Arguments> lineFeed(){
        return Stream.of(
                Arguments.of("1abc2", 12L),
                Arguments.of("pqr3stu8vwx", 38L),
                Arguments.of("a1b2c3d4e5f", 15L),
                Arguments.of("treb7uchet", 77L)
        );
    }
}