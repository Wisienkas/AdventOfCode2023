package dk.reibke.day02;

import java.util.stream.Stream;

public class ScoreAggregator {

    public long scoreByGameNumber(Stream<String> lines, GameConfiguration gameConfiguration) {
        return lines.map(Game::new)
                .filter(gameConfiguration::isValidGame)
                .mapToLong(Game::getGameNumber)
                .sum();
    }

    public long scoreByPowerOfLowestPossibleSetting(Stream<String> lines) {
        return lines.map(Game::new)
                .mapToLong(Game::getPowerOfLowestPossibleSetting)
                .sum();
    }
}
