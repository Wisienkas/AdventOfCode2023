package dk.reibke.day03;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class EngineSchematicTest {

    @Test
    void sumOfSchematicNumbers() {
        Stream<String> lines = Stream.of(
                ("""
                        467..114..
                        ...*......
                        ..35..633.
                        ......#...
                        617*......
                        .....+.58.
                        ..592.....
                        ......755.
                        ...$.*....
                        .664.598..""").split("\n")
        );

        EngineSchematic engineSchematic = new EngineSchematic(lines);
        Long sumOfSchematicNumbers = engineSchematic.sumOfSchematicNumbers();

        assertEquals(4361, sumOfSchematicNumbers);
    }

    @Test
    void sumGearRatios() {
        Stream<String> lines = Stream.of(
                ("""
                        467..114..
                        ...*......
                        ..35..633.
                        ......#...
                        617*......
                        .....+.58.
                        ..592.....
                        ......755.
                        ...$.*....
                        .664.598..""").split("\n")
        );

        EngineSchematic engineSchematic = new EngineSchematic(lines);
        Long sumGearRatios = engineSchematic.sumGearRatios();

        assertEquals(467835, sumGearRatios);
    }
}