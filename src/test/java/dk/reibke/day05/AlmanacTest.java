package dk.reibke.day05;

import dk.reibke.utils.FileReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class AlmanacTest {

    @Test
    void testSimpleAlmanacSeeds() throws URISyntaxException, IOException {
        Stream<String> lines = new FileReader().readLines("day05/testInput01.txt");
        SimpleAlmanac almanac = SimpleAlmanacFactory.fromLines(lines);

        long lowestLocation = almanac.findLowestLocationOfSeeds();

        assertEquals(35, lowestLocation);
    }

    @Test
    void testAdvancedAlmanacSeeds() throws URISyntaxException, IOException {
        Stream<String> lines = new FileReader().readLines("day05/testInput01.txt");
        AdvancedAlmanac almanac = AdvancedAlmanacFactory.fromLines(lines);

        long lowestLocation = almanac.findLowestLocationOfSeeds();

        assertEquals(46, lowestLocation);
    }
}