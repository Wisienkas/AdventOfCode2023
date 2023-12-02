package dk.reibke.day02;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GameConfigurationTest {

    @ParameterizedTest
    @MethodSource("gameData")
    void fromStringTest(String data, boolean expectedValidGame) {
        var gameRules = new GameConfiguration(new CubeSet(12, 14, 13));
        var game = new Game(data);

        boolean isValidGame = gameRules.isValidGame(game);

        assertEquals(expectedValidGame, isValidGame);
    }

    public static Stream<Arguments> gameData() {
        return Stream.of(
                Arguments.of("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green", true),
                Arguments.of("Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue", true),
                Arguments.of("Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red", false),
                Arguments.of("Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red", false),
                Arguments.of("Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green", true)
        );
    }
}