package dk.reibke.day04;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Dealer {

    private final Map<Integer, Integer> stateOfCards;
    private final List<Card> cards;

    public Dealer(CardDeck cardDeck) {
        this.stateOfCards = cardDeck.cards().stream()
                .collect(Collectors.toMap(
                        Card::cardNumber,
                        card -> 1));
        this.cards = cardDeck.cards();
    }

    public Round dealAndCountCard(Card card) {
        long matchingNumbersScore = card.countMatchingNumbers();
        int multiplier = stateOfCards.get(card.cardNumber());
        for (int i = 1; i <= matchingNumbersScore; i++) {
            stateOfCards.compute(
                    i + card.cardNumber(),
                    (key, value) -> value == null ? multiplier : value + multiplier);
        }
        return new Round(matchingNumbersScore, multiplier);
    }

    public DealerGameResult playScrath() {
        List<Round> rounds = cards.stream()
                .map(this::dealAndCountCard)
                .toList();
        return new DealerGameResult(rounds);
    }
}
