package domain.participants;

import domain.deck.Card;
import domain.deck.Cards;

import java.util.List;

public class Player {
    private static final int BLACK_JACK = 21;
    private static final boolean Y_COMMAND = true;
    private final Name name;
    private final Cards cards;

    public Player(String name) {
        this.name = new Name(name);
        this.cards = new Cards();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getCardsSum() {
        return cards.getSum();
    }

    public boolean isOverPlayerBlackJack() {
        return getCardsSum() >= BLACK_JACK;
    }

    public boolean canDrawCard(boolean command) {
        return !isOverPlayerBlackJack() && isCommandYes(command);
    }

    public boolean isCommandYes(boolean command) {
        return command == Y_COMMAND;
    }

    public String getName() {
        return name.getName();
    }

    public List<Card> getPlayerCards() {
        return cards.getCards();
    }
}

