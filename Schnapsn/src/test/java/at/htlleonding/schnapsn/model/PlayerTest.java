package at.htlleonding.schnapsn.model;

import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    public void createPlayer() {
        Player player = new Player("Maxi", "Maxi1234", "maxi@gmail.com");

        org.assertj.core.api.Assertions.assertThat(player.getUsername()).isEqualTo("Maxi");
        org.assertj.core.api.Assertions.assertThat(player.getPassword()).isEqualTo("Maxi1234");
        org.assertj.core.api.Assertions.assertThat(player.getEmail()).isEqualTo("maxi@gmail.com");
    }
}
