package dk.reibke.day03;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SchematicLineAnalyzerTest {

    @ParameterizedTest
    @MethodSource("lineDataForNumbers")
    void findSchematicNumbers(String line, int lineNumber, List<SchematicNumber> expectedSchematicNumbers) {
        var schematicLineAnalyzer = new SchematicLineAnalyzer(line, lineNumber);
        List<SchematicNumber> schematicNumbers = schematicLineAnalyzer.findSchematicNumbers();

        assertIterableEquals(expectedSchematicNumbers, schematicNumbers);
    }

    @ParameterizedTest
    @MethodSource("lineDataForSymbols")
    void findSchematicSymbols(String line, int lineNumber, List<SchematicSymbol> expectedSchematicNumbers) {
        var schematicLineAnalyzer = new SchematicLineAnalyzer(line, lineNumber);
        List<SchematicSymbol> schematicSymbols = schematicLineAnalyzer.findSchematicSymbols();

        assertIterableEquals(expectedSchematicNumbers, schematicSymbols);
    }

    static Stream<Arguments> lineDataForNumbers() {
        return Stream.of(
            Arguments.of("467..114..", 0, List.of(
                        new SchematicNumber(467,0,3, 0),
                        new SchematicNumber(114,5,8, 0)
                    )),
                Arguments.of("...*......", 1, List.of()),
                Arguments.of("..35..633.", 2, List.of(
                        new SchematicNumber(35, 2, 4, 2),
                        new SchematicNumber(633, 6, 9, 2)
                        )),
                Arguments.of("......#...", 3, List.of()),
                Arguments.of("617*......", 4, List.of(new SchematicNumber(617, 0, 3, 4))),
                Arguments.of(".....+.58.", 5, List.of(new SchematicNumber(58, 7, 9, 5))),
                Arguments.of("..592.....", 6, List.of(new SchematicNumber(592, 2,5, 6))),
                Arguments.of("......755.", 7, List.of(new SchematicNumber(755,6,9, 7))),
                Arguments.of("...$.*....", 8, List.of()),
                Arguments.of(".664.598..", 9, List.of(
                        new SchematicNumber(664, 1, 4, 9),
                        new SchematicNumber(598, 5, 8, 9)
                ))
        );
    }

    static Stream<Arguments> lineDataForSymbols() {
        return Stream.of(
                Arguments.of("467..114..", 0, List.of()),
                Arguments.of("...*......", 1, List.of(new SchematicSymbol("*", 3, 1))),
                Arguments.of("..35..633.", 2, List.of()),
                Arguments.of("......#...", 3, List.of(new SchematicSymbol("#", 6, 3))),
                Arguments.of("617*......", 4, List.of(new SchematicSymbol("*", 3, 4))),
                Arguments.of(".....+.58.", 5, List.of(new SchematicSymbol("+", 5, 5))),
                Arguments.of("..592.....", 6, List.of()),
                Arguments.of("......755.", 7, List.of()),
                Arguments.of("...$.*....", 8, List.of(
                        new SchematicSymbol("$", 3, 8),
                        new SchematicSymbol("*", 5, 8))),
                Arguments.of(".664.598..", 9, List.of())
        );
    }
}