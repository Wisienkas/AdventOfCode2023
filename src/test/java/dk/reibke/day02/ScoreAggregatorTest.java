package dk.reibke.day02;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoreAggregatorTest {

    @Test
    void scoreTest() {
        List<String> data = List.of(
                "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
                "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
                "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
                "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
                "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"
        );

        var gameRules = new GameConfiguration(new CubeSet(12, 14, 13));
        var scoreAggregator = new ScoreAggregator();

        long score = scoreAggregator.scoreByGameNumber(data.stream(), gameRules);

        assertEquals(8,score);
    }

    @Test
    void totalPowerTest() {
        List<String> data = List.of(
                "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
                "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
                "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
                "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
                "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"
        );

        var scoreAggregator = new ScoreAggregator();

        long totalPower = scoreAggregator.scoreByPowerOfLowestPossibleSetting(data.stream());

        assertEquals(2286L,totalPower);
    }
}