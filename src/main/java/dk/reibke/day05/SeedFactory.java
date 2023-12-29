package dk.reibke.day05;

import java.util.List;

import static dk.reibke.utils.Regexes.NUMBER_REGEX;

public class SeedFactory {

    public List<Item> findInitialSeeds(String line) {
        if (!line.startsWith("seeds:")) {
            throw new RuntimeException(String.format("Line for initial seeds, started unexpectedly: [%s]", line));
        }

        return NUMBER_REGEX.matcher(line)
                .results()
                .map(result -> new Item(MapType.SEED, Long.parseLong(result.group())))
                .toList();
    }
}
