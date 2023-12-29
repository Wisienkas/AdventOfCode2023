package dk.reibke.day05;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TranslationTest {

    public static Stream<Arguments> coverScenarios() {
        return Stream.of(
                Arguments.of("Range Left window, partial cover",
                        new ItemRange(MapType.SOIL, 4,8), new Translation(6, 0,8)),
                Arguments.of("translation cover range",
                        new ItemRange(MapType.SOIL, 4,8), new Translation(3, 0,8)),
                Arguments.of("Range Right window, partial cover",
                        new ItemRange(MapType.SOIL, 4,8), new Translation(2, 0,4)),
                Arguments.of("Range cover translation",
                        new ItemRange(MapType.SOIL, 0,8), new Translation(2, 10,3))
        );
    }

    @ParameterizedTest
    @MethodSource("coverScenarios")
    void overLap(String scenario, ItemRange itemRange, Translation translation) {
        if (!translation.overLap(itemRange)) {
            fail(() -> String.format("scenario: [%s] failed", scenario));
        }
    }

    @Test
    void testSplit() {
        // Given
        ItemRange itemRange = new ItemRange(MapType.SOIL, 0, 8);
        Translation translation = new Translation(2, 10, 3);

        // Act
        ItemRange.TranslatedRange actual = translation.split(itemRange, MapType.HUMIDITY);

        // Assert
        ItemRange.TranslatedRange expected = new ItemRange.TranslatedRange(
                itemRange,
                List.of(new ItemRange(MapType.SOIL, 0, 2), new ItemRange(MapType.SOIL, 5, 8)),
                List.of(new ItemRange(MapType.HUMIDITY, 10, 13)));
        assertEquals(expected, actual);
    }

    @Test
    void testSorting() {
        Translation a = new Translation(3, 7, 3);
        Translation b = new Translation(12, 95, 3);

        List<Translation> list = Stream.of(b, a).sorted().toList();

        assertAll(
                () -> assertEquals(a, list.get(0)),
                () -> assertEquals(b, list.get(1))
        );
    }
}