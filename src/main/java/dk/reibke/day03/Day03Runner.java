package dk.reibke.day03;

import dk.reibke.utils.FileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.stream.Stream;

public class Day03Runner {

    public static void run() throws URISyntaxException, IOException {

        Stream<String> lines = new FileReader().readLines("day03/input01.txt");
        EngineSchematic engineSchematic = new EngineSchematic(lines);
        Long sumOfSchematicNumbers = engineSchematic.sumOfSchematicNumbers();
        System.out.printf("Result for day 03, task 01 = [%s]%n", sumOfSchematicNumbers);

        Long sumGearRatios = engineSchematic.sumGearRatios();
        System.out.printf("Result for day 03, task 02 = [%s]%n", sumGearRatios);
    }
}
