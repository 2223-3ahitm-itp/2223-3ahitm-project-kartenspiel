package at.htlleonding.schnapsn.controller;

import at.htlleonding.schnapsn.model.Player;
import org.apache.ibatis.metadata.Table;

import javax.sql.DataSource;
import java.sql.*;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerRepository implements Persistent<Player> {
    private DataSource dataSource = Database.getDataSource();

    @Override
    public void save(Player player) {
        if (player.getPlayerId() == null) {
            insert(player);
        } else {
            update(player);
        }
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
            preparedStatement.setInt(7, player.getPlayerId());

            if(preparedStatement.executeUpdate() == 0) {
                throw new SQLException("Update of PLAYER failed, no rows affected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Player player) {
        // prepare statement
        try (Connection connection = dataSource.getConnection()) {
            String statement = "INSERT INTO PLAYER(USERNAME, PASSWORD, EMAIL, GAMES_PLAYED, WINS, LOOSES) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(statement, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, player.getUsername());
            preparedStatement.setString(2, player.getPassword());
            preparedStatement.setString(3, player.getEmail());
            preparedStatement.setInt(4, player.getGames_played());
            preparedStatement.setInt(5, player.getWins());
            preparedStatement.setInt(6, player.getLosses());

            if(preparedStatement.executeUpdate() == 0) {
                throw new SQLException("Update of PLAYER failed, no rows affected");
            }
            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                if (keys.next()) {
                    player.setPlayerId(keys.getInt(1));
                } else {
                    throw new SQLException("Insert into ANSWER failed, no ID obtained");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        // prepare statement
        try (Connection connection = dataSource.getConnection()) {
            String statement = "DELETE FROM PLAYER WHERE PLAYERID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setInt(1, id);

            if(preparedStatement.executeUpdate() == 0) {
                throw new SQLException("Delete from PLAYER failed, no rows affected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
