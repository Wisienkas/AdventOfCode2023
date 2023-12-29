package dk.reibke.day05;

import dk.reibke.utils.FileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.stream.Stream;

public class Day05Runner {

    public static void run() throws URISyntaxException, IOException {

        //taskOne();
        taskTwo();
    }

    private static void taskTwo() throws URISyntaxException, IOException {
        Stream<String> lines = new FileReader().readLines("day05/input01.txt");
        AdvancedAlmanac almanac = AdvancedAlmanacFactory.fromLines(lines);
        long lowestLocationOfSeeds = almanac.findLowestLocationOfSeeds();
        System.out.printf("Result for day 05 Task 02 = [%s]%n", lowestLocationOfSeeds);
    }

    private static void taskOne() throws URISyntaxException, IOException {
        Stream<String> lines = new FileReader().readLines("day05/input01.txt");
        SimpleAlmanac almanac = SimpleAlmanacFactory.fromLines(lines);
        long lowestLocationOfSeeds = almanac.findLowestLocationOfSeeds();
        System.out.printf("Result for day 05 Task 01 = [%s]%n", lowestLocationOfSeeds);
    }

}
