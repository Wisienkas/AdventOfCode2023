package dk.reibke.day02;


import dk.reibke.utils.FileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.stream.Stream;

public class Day02Runner {

    public static void run() throws URISyntaxException, IOException {
        var gameRules = new GameConfiguration(new CubeSet(12, 14, 13));
        Stream<String> lines = new FileReader().readLines("day02/input01.txt");
        var scoreAggregator = new ScoreAggregator();

        long score = scoreAggregator.scoreByGameNumber(lines, gameRules);
        System.out.printf("Result for day 02, task 01 = [%s]%n", score);


        lines = new FileReader().readLines("day02/input01.txt");
        long totalPower = scoreAggregator.scoreByPowerOfLowestPossibleSetting(lines);
        System.out.printf("Result for day 02, task 02 = [%s]%n", totalPower);
    }
}
