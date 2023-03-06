package domain.strategy;

import domain.deck.Card;
import strategy.ShuffleStrategy;

import java.util.List;

public class NoShuffleCardsStrategy implements ShuffleStrategy {
    @Override
    public void shuffle(final List<Card> cards) {
    }
}
