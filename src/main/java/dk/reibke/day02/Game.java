package dk.reibke.day02;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Game {

    private final static Pattern GAME_NUMBER_REGEX = Pattern.compile("Game ([1-9][0-9]*)");
    private final long gameNumber;
    private final List<CubeSet> cubeSets;

    public Game(String line) {
        String[] split = line.split(":");

        this.gameNumber = getGameNumber(split[0]);
        this.cubeSets = Stream.of(split[1].split(";"))
                .map(CubeSet::fromString)
                .toList();
    }

    private long getGameNumber(String gameInfo) {
        Matcher matcher = GAME_NUMBER_REGEX.matcher(gameInfo);
        if(!matcher.find()) {
            throw new RuntimeException(String.format("Unable to find Game number from: [%s]", gameInfo));
        }
        return Long.parseLong(matcher.group(1));
    }

    public long getGameNumber() {
        return gameNumber;
    }

    public List<CubeSet> getCubeSets() {
        return cubeSets;
    }

    public long getPowerOfLowestPossibleSetting() {
        CubeSet setting = cubeSets.stream()
                .reduce(CubeSet::mergeWithMax)
                .orElse(new CubeSet(0,0,0));

        return (long) setting.green() * setting.blue() * setting.red();
    }
}
