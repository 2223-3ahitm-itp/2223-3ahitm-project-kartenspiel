package at.htlleonding.schnapsn.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.assertj.core.api.Assertions.*;

class PlayerTest {
    private static Connection connection;
    @BeforeAll
    public static void setup() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/derbydb-1");
            System.out.println("Connection Established");
        } catch (SQLException e) {
            System.err.println("Connection couldn't be established: " + e.getMessage());
        }
    }

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

    @Test
    void testAddPlayerIntoDatabase() {
        Player player = new Player("JaneSmith", "93n4j32n59", "smith.jane@gmail.com");
        String statement = "INSERT INTO PLAYER(USERNAME, PASSWORD, EMAIL, GAMES_PLAYED, WINS, LOOSES) VALUES (?, ?, ?, ?, ?, ?)";

        // prepare statement
        try (PreparedStatement preparedStatement = connection.prepareStatement(statement, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, player.getUsername());
            preparedStatement.setString(2, player.getPassword());
            preparedStatement.setString(3, player.getEmail());
            preparedStatement.setInt(4, player.getGames_played());
            preparedStatement.setInt(5, player.getWins());
            preparedStatement.setInt(6, player.getLosses());

            // execute statement
            int affectedRows = preparedStatement.executeUpdate();
            assertThat(affectedRows).isEqualTo(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testGetDataFromDatabase() {
        Player player = new Player("JaneSmith", "93n4j32n59", "smith.jane@gmail.com");
        String statement = "SELECT * FROM PLAYER WHERE USERNAME = ?";

        // prepare statement
        try (PreparedStatement preparedStatement = connection.prepareStatement(statement)) {
            preparedStatement.setString(1, player.getUsername());

            // execute statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // test database output
            String username = resultSet.getString(1);
            String password = resultSet.getString(2);
            String email = resultSet.getString(3);
            int games_played = resultSet.getInt(4);
            int wins = resultSet.getInt(5);
            int losses = resultSet.getInt(6);

            assertThat(player.getUsername()).isEqualTo(username);
            assertThat(player.getPassword()).isEqualTo(password);
            assertThat(player.getEmail()).isEqualTo(email);
            assertThat(player.getGames_played()).isEqualTo(games_played);
            assertThat(player.getWins()).isEqualTo(wins);
            assertThat(player.getLosses()).isEqualTo(losses);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testUpdatePlayerInDatabase() {
        Player player = new Player("JaneSmith", "93n4j32n59", "smith.jane@gmail.com");
        Player updatedPlayer = new Player("SmithJane", "lkjdsf90", "j.smith@gmail.com");
        String statement = "UPDATE PLAYER SET USERNAME = ?, PASSWORD = ?, EMAIL = ?, GAMES_PLAYED = ?, WINS = ?, LOOSES = ? WHERE USERNAME = ?";

        // prepare statement
        try (PreparedStatement preparedStatement = connection.prepareStatement(statement)) {
            preparedStatement.setString(1, updatedPlayer.getUsername());
            preparedStatement.setString(2, updatedPlayer.getPassword());
            preparedStatement.setString(3, updatedPlayer.getEmail());
            preparedStatement.setInt(4, updatedPlayer.getGames_played());
            preparedStatement.setInt(5, updatedPlayer.getWins());
            preparedStatement.setInt(6, updatedPlayer.getLosses());
            preparedStatement.setString(7, player.getUsername());

            int affectedRows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testGetUpdatedDataFromDatabase() {
        Player updatedPlayer = new Player("SmithJane", "lkjdsf90", "j.smith@gmail.com");
        String statement = "SELECT * FROM PLAYER WHERE USERNAME = ?";

        // prepare statement
        try (PreparedStatement preparedStatement = connection.prepareStatement(statement)) {
            preparedStatement.setString(1, updatedPlayer.getUsername());

            // execute statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // test database output
            String username = resultSet.getString(1);
            String password = resultSet.getString(2);
            String email = resultSet.getString(3);
            int games_played = resultSet.getInt(4);
            int wins = resultSet.getInt(5);
            int losses = resultSet.getInt(6);

            assertThat(updatedPlayer.getUsername()).isEqualTo(username);
            assertThat(updatedPlayer.getPassword()).isEqualTo(password);
            assertThat(updatedPlayer.getEmail()).isEqualTo(email);
            assertThat(updatedPlayer.getGames_played()).isEqualTo(games_played);
            assertThat(updatedPlayer.getWins()).isEqualTo(wins);
            assertThat(updatedPlayer.getLosses()).isEqualTo(losses);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testRemoveFromDatabase() {
        Player updatedPlayer = new Player("SmithJane", "lkjdsf90", "j.smith@gmail.com");
        String statement = "DELETE FROM PLAYER WHERE USERNAME = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(statement)) {
            preparedStatement.setString(1, updatedPlayer.getUsername());

            int affectedRows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}