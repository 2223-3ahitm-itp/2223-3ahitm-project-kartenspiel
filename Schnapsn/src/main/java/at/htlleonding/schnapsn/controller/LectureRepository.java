package at.htlleonding.schnapsn.controller;

import at.htlleonding.schnapsn.model.Lecture;

import javax.sql.DataSource;
import java.sql.*;

public class LectureRepository {
    private DataSource dataSource = Database.getDataSource();

    public void update(Lecture lecture) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "update lecture SET name=?, content=? where lectureId=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, lecture.getName());
            statement.setString(2, lecture.getContent());
            statement.setLong(3, lecture.getId());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of LECTURE failed, no rows affected");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
