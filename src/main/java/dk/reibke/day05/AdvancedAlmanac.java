package dk.reibke.day05;

import java.util.List;
import java.util.Optional;

public record AdvancedAlmanac(List<Translator> translators, List<ItemRange> initialSeedRanges) {

    public List<ItemRange> transLate(ItemRange itemRange) {
        Optional<Translator> anyTranslator = translators.stream()
                .filter(translator -> translator.from().equals(itemRange.mapType()))
                .findAny();

        return anyTranslator.map(translator -> translator.translate(itemRange)
                .stream()
                .map(this::transLate)
                .flatMap(List::stream)
                .toList()).orElseGet(() -> List.of(itemRange));
    }

    public long findLowestLocationOfSeeds() {
        return initialSeedRanges.stream()
                .map(this::transLate)
                .flatMap(List::stream)
                .mapToLong(ItemRange::start)
                .min()
                .orElseThrow(() -> new RuntimeException("Unable to find any lowest seeds!"));
    }
}
