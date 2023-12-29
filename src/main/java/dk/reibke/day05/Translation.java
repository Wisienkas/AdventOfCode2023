package dk.reibke.day05;

import java.util.List;

public record Translation(long sourceStart, long destinationStart, long range) implements Comparable<Translation> {

    public long translate(long location) {
        long change = destinationStart() - sourceStart();
        return location + change;
    }

    public boolean inRange(long location) {
        return location >= sourceStart && location <= sourceStart + range;
    }

    public boolean overLap(ItemRange itemRange) {
        boolean rangeStartOrEndingWithinScope = inRange(itemRange.start()) || inRange(itemRange.endInclusive());
        boolean itemRangeFullyCoverTransition = itemRange.start() <= sourceStart() && itemRange.endInclusive() >= sourceEnd();

        return rangeStartOrEndingWithinScope || itemRangeFullyCoverTransition;
    }

    public long sourceEnd() {
        return sourceStart() + range();
    }

    public ItemRange.TranslatedRange split(ItemRange itemRange, MapType newType) {
        if (overLap(itemRange)) {

            if (fullyContains(itemRange)) {
                return new ItemRange.TranslatedRange(
                        itemRange,
                        List.of(),
                        translate(List.of(itemRange), newType));
            }

            if (isCoveredBy(itemRange)) {
                return new ItemRange.TranslatedRange(
                        itemRange,
                        List.of(
                                unchangedLeftItemRange(itemRange),
                                unchangedRightItemRange(itemRange)
                        ),
                        translate(
                                List.of(unchangedFullTranslationRangeItemRange(itemRange)),
                                newType
                        )
                );
            }

            if (leftSplit(itemRange)) {
                return new ItemRange.TranslatedRange(
                        itemRange,
                        List.of(
                                unchangedLeftItemRange(itemRange)
                        ),
                        translate(
                                List.of(new ItemRange(itemRange.mapType(), sourceStart(), itemRange.endExclusive())),
                                newType
                        )
                );
            }

            if (rightSplit(itemRange)) {
                return new ItemRange.TranslatedRange(
                        itemRange,
                        List.of(unchangedRightItemRange(itemRange)),
                        translate(
                                List.of(new ItemRange(itemRange.mapType(), itemRange.start(), sourceEnd())),
                                newType
                        )
                );
            }

            throw new RuntimeException(String.format("Should never each here for pair of {%s, %s}", itemRange, this));
        }
        return new ItemRange.TranslatedRange(itemRange, List.of(itemRange), List.of());
    }

    boolean rightSplit(ItemRange itemRange) {
        return inRange(itemRange.start()) && !inRange(itemRange.endInclusive());
    }

    boolean leftSplit(ItemRange itemRange) {
        return !inRange(itemRange.start()) && inRange(itemRange.endInclusive());
    }

    boolean isCoveredBy(ItemRange itemRange) {
        return itemRange.start() <= sourceStart() && itemRange.endInclusive() >= sourceEnd();
    }

    List<ItemRange> translate(List<ItemRange> itemRanges, MapType newType) {
        return itemRanges.stream()
                .map(itemRange -> new ItemRange(
                        newType,
                        translate(itemRange.start()),
                        translate(itemRange.endExclusive()))
                )
                .toList();
    }

    ItemRange unchangedFullTranslationRangeItemRange(ItemRange itemRange) {
        return new ItemRange(itemRange.mapType(), sourceStart(), sourceEnd());
    }

    ItemRange unchangedRightItemRange(ItemRange itemRange) {
        return new ItemRange(itemRange.mapType(), sourceEnd(), itemRange.endExclusive());
    }

    ItemRange unchangedLeftItemRange(ItemRange itemRange) {
        return new ItemRange(itemRange.mapType(), itemRange.start(), sourceStart());
    }

    boolean fullyContains(ItemRange itemRange) {
        return inRange(itemRange.start()) && inRange(itemRange.endExclusive() - 1);
    }

    @Override
    public int compareTo(Translation o) {
        return Long.compare(sourceStart, o.sourceStart);
    }
}
