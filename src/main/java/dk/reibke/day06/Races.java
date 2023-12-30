package dk.reibke.day06;

import java.util.List;

public record Races(List<Race> races) {

    public int countPossibleWinnings() {
        return races.stream()
                .map(Race::findRaceRecordBeaters)
                .map(List::size)
                .reduce((a, b) -> a * b)
                .orElseThrow(() -> new RuntimeException("No races found to count winnings"));
    }

    public Race mergeRaces() {
        return races.stream()
                .reduce(Race::merge)
                .orElseThrow(() -> new RuntimeException("No Races found to be merged!"));
    }
}
