package at.htlleonding.schnapsn.controller;

import at.htlleonding.schnapsn.model.Player;
import org.apache.ibatis.metadata.Table;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerRepository implements Persistent<Player> {
    private DataSource dataSource = Database.getDataSource();




    void testAddPlayerIntoDatabase() {
        Player player = new Player("JaneSmith", "93n4j32n59", "smith.jane@gmail.com");

        Table table = new Table(Database.getDataSource(), "player");


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

    @Override
    public void save(Player entity) {

    }

    public void update(Player player) {
        // prepare statement
        try (Connection connection = dataSource.getConnection()) {
            String statement = "UPDATE PLAYER SET USERNAME = ?, PASSWORD = ?, EMAIL = ?, GAMES_PLAYED = ?, WINS = ?, LOOSES = ? WHERE PLAYERID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, player.getUsername());
            preparedStatement.setString(2, player.getPassword());
            preparedStatement.setString(3, player.getEmail());
            preparedStatement.setInt(4, player.getGames_played());
            preparedStatement.setInt(5, player.getWins());
            preparedStatement.setInt(6, player.getLosses());
            preparedStatement.setString(7, player.getPlayerId()+"");

            if(preparedStatement.executeUpdate() == 0) {
                throw new SQLException("Update of PLAYER failed, no rows affected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Player entity) {

    }

    @Override
    public void delete(long id) {

    }
}
