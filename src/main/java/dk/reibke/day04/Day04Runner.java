package dk.reibke.day04;

import dk.reibke.utils.FileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.stream.Stream;

public class Day04Runner {

    public static void run() throws URISyntaxException, IOException {

        Stream<String> lines = new FileReader().readLines("day04/input01.txt");
        CardDeck cardDeck = CardDeck.fromLine(lines);
        Long cardScore = cardDeck.getCardScore();

        System.out.printf("Result for day 04, task 01 = [%s]%n", cardScore);

        Dealer dealer = new Dealer(cardDeck);
        DealerGameResult dealerGameResult = dealer.playScrath();
        System.out.printf("Result for day 04, task 02 = [%s]%n", dealerGameResult.countCards());
    }
}
