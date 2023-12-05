package dk.reibke.day04;

import java.util.List;

public record DealerGameResult(List<Round> rounds) {

    public long countCards() {
        return rounds.stream()
                .mapToLong(Round::countOfCards)
                .sum();
    }
}
