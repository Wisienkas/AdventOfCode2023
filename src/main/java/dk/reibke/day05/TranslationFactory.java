package dk.reibke.day05;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.MatchResult;

import static dk.reibke.utils.Regexes.NUMBER_REGEX;

public class TranslationFactory {

    public static final int SOURCE_START_RANGE_INDEX = 1;
    public static final int DESTINATION_START_RANGE_INDEX = 0;
    private static final int RANGE_INDEX = 2;
    static List<Translation> createTranslationList(Iterator<String> iterator) {
        List<Translation> translationList = new ArrayList<>();
        while (iterator.hasNext()) {
            String line = iterator.next();
            if (line.isBlank()) {
                return translationList;
            }

            List<Long> list = NUMBER_REGEX.matcher(line).results()
                    .map(MatchResult::group)
                    .map(Long::parseLong)
                    .toList();
            if (list.size() != 3) {
                throw new RuntimeException(String.format("Line didn't contain exactly 3 values: [%s]", line));
            }

            translationList.add(new Translation(
                    list.get(SOURCE_START_RANGE_INDEX),
                    list.get(DESTINATION_START_RANGE_INDEX),
                    list.get(RANGE_INDEX)));
        }
        return translationList;
    }
}
