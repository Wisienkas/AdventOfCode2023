package dk.reibke.day06;

import dk.reibke.utils.Pair;
import dk.reibke.utils.Regexes;
import dk.reibke.utils.StreamUtil;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.stream.Stream;

public class RacerFactory {

    public static Stream<Pair<Long, Long>> matchRaceAndDistance(String raceTimeLine, String recordDistanceLine) {
        Matcher raceTimeMatcher = Regexes.NUMBER_REGEX.matcher(raceTimeLine);
        Matcher recordDistanceMatcher = Regexes.NUMBER_REGEX.matcher(recordDistanceLine);

        List<Long> raceTimes = raceTimeMatcher.results()
                .map(MatchResult::group)
                .map(Long::parseLong)
                .toList();

        List<Long> recordDistances = recordDistanceMatcher.results()
                .map(MatchResult::group)
                .map(Long::parseLong)
                .toList();

        if (raceTimes.isEmpty() || raceTimes.size() != recordDistances.size()) {
            throw new RuntimeException(
                    String.format("Unexpected sizes of raceTimes:[%s], recordDistances:[%s]",
                            raceTimes.size(), recordDistances.size()));
        }

        return StreamUtil.zipStream(raceTimes, recordDistances);
    }

    public static Races fromStrings(Stream<String> lineStream) {
        List<String> lines = lineStream.toList();

        String raceTimeLine = lines.stream()
                .filter(line -> line.startsWith("Time:"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Unable to find any lines starting with 'Time:'"));
        String recordDistanceLine = lines.stream()
                .filter(line -> line.startsWith("Distance:"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Unable to find any lines starting with 'Distance:'"));

        List<Race> raceList = matchRaceAndDistance(raceTimeLine, recordDistanceLine)
                .map(pair -> new Race(pair.first(), pair.second()))
                .toList();

        return new Races(raceList);
    }

}
