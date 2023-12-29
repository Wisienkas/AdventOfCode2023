package dk.reibke.day05;


import java.util.ArrayList;
import java.util.List;

public record ItemRange(MapType mapType, long start, long endExclusive) {

    public long endInclusive() {
        return endExclusive - 1L;
    }

    public record TranslatedRange(ItemRange original, List<ItemRange> nonTranslated, List<ItemRange> translated) {

        public TranslatedRange updateWith(TranslatedRange translatedRange2) {
            List<ItemRange> updatedTranslated = new ArrayList<>(translated());
            updatedTranslated.addAll(translatedRange2.translated());

            return new TranslatedRange(
                    original(),
                    translatedRange2.nonTranslated(),
                    updatedTranslated
            );
        }
    }
}
