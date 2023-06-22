package at.htlleonding.schnapsn.model;

import org.junit.jupiter.api.Test;

public class CardTest {
    @Test
    public void createCard() {
        Card card = new Card("Unter", CardType.EICHEL, 2);

        org.assertj.core.api.Assertions.assertThat(card.getName()).isEqualTo("Unter");
        org.assertj.core.api.Assertions.assertThat(card.getValue()).isEqualTo(2);
        org.assertj.core.api.Assertions.assertThat(card.getType()).isEqualTo(CardType.EICHEL);

    }
}
