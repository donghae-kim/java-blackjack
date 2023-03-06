package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {
    private static final int DECREASE_ACE_VALUE = -10;
    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int getSum() {
        int sum = cards.stream()
                .map(Card::getCardValue).mapToInt(Integer::intValue).sum();
        return calculateAceValue(sum);
    }

    private int calculateAceValue(int sum) {
        long countAce = countAce();
        while (countAce-- > 0 && sum > BlackjackGame.BLACK_JACK) {
            sum += DECREASE_ACE_VALUE;
        }
        return sum;
    }

    private long countAce() {
        return cards.stream().filter(Card::isAce).count();
    }

    public List<String> getCards() {
        return cards.stream()
                .map(Card::getCard)
                .collect(Collectors.toList());
    }
}
