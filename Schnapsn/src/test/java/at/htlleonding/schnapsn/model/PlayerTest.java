package at.htlleonding.schnapsn.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;

class PlayerTest {
    @Test
    void testGetterAndSetter() {
        Player player = new Player("JonnoDoe", "1jkdfe23", "john.doe@gmail.com");
        String username = "JonnoDoe";
        String password = "1jkdfe23";
        String email = "john.doe@gmail.com";
        final int BASE_STATISTICS = 0;

        // test if constructor works fine
        assertThat(player.getUsername()).isEqualTo(username);
        assertThat(player.getPassword()).isEqualTo(password);
        assertThat(player.getEmail()).isEqualTo(email);
        assertThat(player.getWins()).isEqualTo(BASE_STATISTICS);
        assertThat(player.getGames_played()).isEqualTo(BASE_STATISTICS);
        assertThat(player.getLosses()).isEqualTo(BASE_STATISTICS);

        // change testing values
        username = "DoeJonny";
        password = "abc876th";
        email = "doe.j@gmail.com";

        // change values
        player.setUsername(username);
        player.setPassword(password);
        player.setEmail(email);
        player.setWins(2);
        player.setLosses(1);
        player.setGames_played(3);

        // test setter
        assertThat(player.getUsername()).isEqualTo(username);
        assertThat(player.getPassword()).isEqualTo(password);
        assertThat(player.getEmail()).isEqualTo(email);
        assertThat(player.getWins()).isEqualTo(2);
        assertThat(player.getGames_played()).isEqualTo(3);
        assertThat(player.getLosses()).isEqualTo(1);
    }
}