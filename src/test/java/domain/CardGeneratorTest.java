package domain;

import domain.deck.Card;
import domain.generator.CardGenerator;
import domain.strategy.NoShuffleCardsStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

public class CardGeneratorTest {
    private CardGenerator cardGenerator;
    private Queue<Card> cards;

    @BeforeEach
    void beforeEach() {
        cardGenerator = new CardGenerator();
        cards = cardGenerator.generate(new NoShuffleCardsStrategy());
    }

    @Test
    @DisplayName("52개의 카드를 생성한다.")
    void createCard() {
        assertThat(cards.size()).isEqualTo(52);
    }

}
