package dk.reibke.day05;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class SimpleAlmanacFactory {



    public static SimpleAlmanac fromLines(Stream<String> lines) {
        Iterator<String> iterator = lines.iterator();
        List<Item> initialSeeds = new SeedFactory().findInitialSeeds(iterator.next());
        List<Translator> translators = new ArrayList<>();

        while (iterator.hasNext()) {
            String line = iterator.next();
            if (line.isBlank()) {
                continue;
            }
            if (TranslatorFactory.IS_TRANSLATOR_LINE.test(line)) {
                translators.add(TranslatorFactory.createTranslator(line, iterator));
            }
        }
        return new SimpleAlmanac(translators, initialSeeds);
    }

}
