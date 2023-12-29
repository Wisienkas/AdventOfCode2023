package dk.reibke.day05;

import java.util.ArrayList;
import java.util.List;

public record Translator(MapType from, MapType to, List<Translation> translationList) {

    public Item translate(Item item) {
        Long newLocation = translationList.stream()
                .filter(translation -> translation.inRange(item.location()))
                .findAny()
                .map(translation -> translation.translate(item.location()))
                .orElse(item.location());

        return new Item(to, newLocation);
    }

    public List<ItemRange> translate(ItemRange sourceItemRange) {
        List<Translation> overlappingTranslations = translationList().stream()
                .filter(translation -> translation.overLap(sourceItemRange))
                .sorted()
                .toList();

        if (overlappingTranslations.isEmpty()) {
            return List.of(
                    new ItemRange(to, sourceItemRange.start(), sourceItemRange.endExclusive())
            );
        }

        ItemRange.TranslatedRange translatedRange = overlappingTranslations.get(0).split(sourceItemRange, to);
        for (Translation translation : overlappingTranslations.stream().skip(1).toList()) {
            translatedRange = translatedRange.nonTranslated()
                    .stream()
                    .filter(translation::overLap)
                    .map(itemRange -> translation.split(itemRange, to))
                    .reduce(translatedRange, ItemRange.TranslatedRange::updateWith);
        }

        List<ItemRange> result = new ArrayList<>(translatedRange.nonTranslated().stream()
                .map(itemRange -> new ItemRange(to, itemRange.start(), itemRange.endExclusive()))
                .toList());
        result.addAll(translatedRange.translated());

        return result;
    }
}
