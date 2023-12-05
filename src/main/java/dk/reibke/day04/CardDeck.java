package dk.reibke.day04;

import java.util.List;

import java.util.stream.Stream;

public record CardDeck(List<Card> cards) {

    public static CardDeck fromLine(Stream<String> lines) {
        return new CardDeck(lines.map(Card::fromLine).toList());
    }

    public Long getCardScore() {
        return cards().stream()
                .mapToLong(Card::getMatchingNumbersScore)
                .sum();
    }

}
