package dk.reibke.day06;

import dk.reibke.utils.FileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.stream.Stream;

public class Day06Runner {

    public static void run() throws URISyntaxException, IOException {
        //taskOne();
        taskTwo();
    }

    private static void taskTwo() throws URISyntaxException, IOException {
        Stream<String> lines = new FileReader().readLines("day06/input01.txt");
        Races races = RacerFactory.fromStrings(lines);
        Race mergedRaces = races.mergeRaces();
        int recordBeatingRaces = mergedRaces.findRaceRecordBeaters().size();
        System.out.printf("Result for day 06 Task 02 = [%s]%n", recordBeatingRaces);
    }

    private static void taskOne() throws URISyntaxException, IOException {
        Stream<String> lines = new FileReader().readLines("day06/input01.txt");
        Races races = RacerFactory.fromStrings(lines);
        int possibleWinnings = races.countPossibleWinnings();
        System.out.printf("Result for day 06 Task 01 = [%s]%n", possibleWinnings);
    }
}
