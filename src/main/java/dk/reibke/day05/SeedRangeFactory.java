package dk.reibke.day05;

import java.util.ArrayList;
import java.util.List;

import static dk.reibke.utils.Regexes.NUMBER_REGEX;

public class SeedRangeFactory {

    public List<ItemRange> findSeedRanges(String line) {
        if (!line.startsWith("seeds:")) {
            throw new RuntimeException(String.format("Line for initial seeds, started unexpectedly: [%s]", line));
        }

        List<Long> ItemRangeInfo = NUMBER_REGEX.matcher(line)
                .results()
                .map(result -> Long.parseLong(result.group()))
                .toList();

        return toItemRanges(ItemRangeInfo);
    }

    private List<ItemRange> toItemRanges(List<Long> seedRangeInfo) {
        List<ItemRange> seedRanges = new ArrayList<>();
        for (int i = 0; i < seedRangeInfo.size(); i = i + 2) {
            Long start = seedRangeInfo.get(i);
            Long range = seedRangeInfo.get(i + 1);
            seedRanges.add(new ItemRange(MapType.SEED, start, start + range));
        }
        return seedRanges;
    }
}
