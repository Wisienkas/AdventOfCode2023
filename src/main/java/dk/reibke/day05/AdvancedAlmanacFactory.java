package dk.reibke.day05;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import static dk.reibke.day05.TranslatorFactory.IS_TRANSLATOR_LINE;

public class AdvancedAlmanacFactory {

    public static AdvancedAlmanac fromLines(Stream<String> lines) {
        Iterator<String> iterator = lines.iterator();
        List<ItemRange> initialSeedRanges = new SeedRangeFactory().findSeedRanges(iterator.next());
        List<Translator> translators = new ArrayList<>();

        while (iterator.hasNext()) {
            String line = iterator.next();
            if (line.isBlank()) {
                continue;
            }
            if (IS_TRANSLATOR_LINE.test(line)) {
                translators.add(TranslatorFactory.createTranslator(line, iterator));
            }
        }
        return new AdvancedAlmanac(translators, initialSeedRanges);
    }

}
