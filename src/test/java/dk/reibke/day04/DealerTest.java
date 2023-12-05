package dk.reibke.day04;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DealerTest {

    @Test
    void testDealerGame() {
        CardDeck cardDeck = CardDeck.fromLine(getLines());
        Dealer dealer = new Dealer(cardDeck);
        DealerGameResult dealerGameResult = dealer.playScrath();

        long countedCards = dealerGameResult.countCards();

        assertEquals(30, countedCards);
    }

    @Test
    void testEachRoundOfCountingScrathCards() {
        CardDeck cardDeck = CardDeck.fromLine(getLines());
        Dealer dealer = new Dealer(cardDeck);
        List<Card> cards = cardDeck.cards();

        assertEquals(new Round(4, 1), dealer.dealAndCountCard(cards.get(0)));
        assertEquals(new Round(2, 2), dealer.dealAndCountCard(cards.get(1)));
        assertEquals(new Round(2, 4), dealer.dealAndCountCard(cards.get(2)));
        assertEquals(new Round(1,8), dealer.dealAndCountCard(cards.get(3)));
        assertEquals(new Round(0, 14), dealer.dealAndCountCard(cards.get(4)));
        assertEquals(new Round(0, 1), dealer.dealAndCountCard(cards.get(5)));
    }


    private static Stream<String> getLines() {
        Stream<String> lines = Stream.of(
                """
                        Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
                        Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
                        Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
                        Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
                        Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
                        Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11""".split("\n")
        );
        return lines;
    }
}