package at.htlleonding.schnapsn.model;

import at.htlleonding.schnapsn.controller.Database;
import at.htlleonding.schnapsn.controller.PlayerRepository;
import at.htlleonding.schnapsn.database.SqlRunner;
import org.assertj.db.api.Assertions;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.assertj.core.api.Assertions.*;

class PlayerRepositoryTest {
    PlayerRepository playerRepository = new PlayerRepository();
    @BeforeAll
    public static void setup() throws SQLException {
        SqlRunner.dropAndCreateTablesWithExampleData();
    }

    @Test
    @Order(1)
    void testSave() {
        Player player = new Player("JaneSmith", "93n4j32n59", "smith.jane@gmail.com");

        playerRepository.save(player);

        Table table = new Table(Database.getDataSource(), "PLAYER");

        Assertions.assertThat(table).row(0).value("USERNAME").isEqualTo(player.getUsername());
        Assertions.assertThat(table).row(0).value("PASSWORD").isEqualTo(player.getPassword());
        Assertions.assertThat(table).row(0).value("EMAIL").isEqualTo(player.getEmail());
        Assertions.assertThat(table).row(0).value("GAMES_PLAYED").isEqualTo(player.getGames_played());
        Assertions.assertThat(table).row(0).value("WINS").isEqualTo(player.getWins());
        Assertions.assertThat(table).row(0).value("LOSSES").isEqualTo(player.getLosses());
    }

    @Test
    @Order(2)
    void testInsertIntoPlayer() {
        Player player = new Player("JohnDoe", "98grih2ho5i4pn5", "john.doe@gmail.com");
        Table table = new Table(Database.getDataSource(), "PLAYER");
        int rowsBefore = table.getRowsList().size();
        playerRepository.insert(player);
        int rowsAfter = table.getRowsList().size();

        org.assertj.core.api.Assertions.assertThat(rowsBefore).isEqualTo(rowsAfter);
    }

    @Test
    @Order(3)
    void testDeleteFromPlayer() {
        Player player = new Player("JohnDoe", "98grih2ho5i4pn5", "john.doe@gmail.com");
        playerRepository.insert(player);
        Table table = new Table(Database.getDataSource(), "PLAYER");

        int rowsBefore = table.getRowsList().size();
        playerRepository.delete(player.getPlayerId());
        int rowsAfter = table.getRowsList().size();

        org.assertj.core.api.Assertions.assertThat(rowsBefore).isEqualTo(rowsAfter);
    }
}