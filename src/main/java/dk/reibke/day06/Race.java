package dk.reibke.day06;

import java.util.List;
import java.util.stream.LongStream;

public record Race(long raceTime, long recordDistance) {

    public List<RaceRecordBeaters> findRaceRecordBeaters() {
        return LongStream.rangeClosed(0, raceTime)
                .mapToObj(buttonPressTime -> RaceRecordBeaters.from(this, buttonPressTime))
                .filter(raceRecordBeaters -> raceRecordBeaters.distance() > recordDistance())
                .toList();
    }

    public Race merge(Race other) {
        long raceTime = combineAsStrings(raceTime(), other.raceTime());
        long distance = combineAsStrings(recordDistance(), other.recordDistance());
        return new Race(raceTime, distance);
    }

    public long combineAsStrings(long first, long second) {
        String asString = "%d%d".formatted(first, second);
        return Long.parseLong(asString);
    }
}
