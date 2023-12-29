package dk.reibke.day05;

import dk.reibke.Verifier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TranslatorTest {



    @Test
    void testFactory() {
        Iterator<String> iterator = Stream.of(
                """
                        soil-to-fertilizer map:
                        0 15 37
                        37 52 2
                        39 0 15""".split("\n")
        ).iterator();
        Translator translator = TranslatorFactory.createTranslator(iterator.next(), iterator);

        assertEquals(new Translator(
                MapType.SOIL, MapType.FERTILIZER, List.of(
                    new Translation(15, 0, 37),
                    new Translation(52, 37, 2),
                    new Translation(0, 39, 15)
                )),
                translator);
    }

    @ParameterizedTest
    @MethodSource("translations")
    void testTranslations(Translator translator, Item item, Item expectedItem) {
        Item actualItem = translator.translate(item);

        assertEquals(expectedItem, actualItem);
    }

    public static Stream<Arguments> translations() {
        Translator translator = new Translator(
                MapType.SOIL, MapType.FERTILIZER, List.of(
                new Translation(15, 0, 37),
                new Translation(52, 37, 2),
                new Translation(0, 39, 15)
        ));

        return Stream.of(
                Arguments.of(translator, new Item(MapType.SOIL, 14), new Item(MapType.FERTILIZER, 39 + 14)),
                Arguments.of(translator, new Item(MapType.SOIL, 16), new Item(MapType.FERTILIZER, 1)),
                Arguments.of(translator, new Item(MapType.SOIL, 1000), new Item(MapType.FERTILIZER, 1000))
        );
    }

    @Test
    void testTranslateItemRangeCovered() {
        // Given
        ItemRange itemRange = new ItemRange(MapType.SEED, 2, 5);
        Translator translator = new Translator(MapType.SEED, MapType.SOIL, List.of(
                new Translation(1, 3, 7)
        ));

        // Act
        List<ItemRange> translate = translator.translate(itemRange);

        // Assert
        assertIterableEquals(List.of(new ItemRange(MapType.SOIL, 4, 7)), translate);
    }

    @Test
    void testTranslateItemRangeCoversTranslation() {
        // Given
        ItemRange itemRange = new ItemRange(MapType.SEED, 0, 8);
        Translator translator = new Translator(MapType.SEED, MapType.SOIL, List.of(
                new Translation(2, 10, 3)
        ));

        // Act
        List<ItemRange> translate = translator.translate(itemRange);

        // Assert
        Verifier.assertCollection(List.of(
                new ItemRange(MapType.SOIL, 0, 2),
                new ItemRange(MapType.SOIL, 5, 8),
                new ItemRange(MapType.SOIL, 10, 13)
        ), new HashSet<>(translate));
    }


}