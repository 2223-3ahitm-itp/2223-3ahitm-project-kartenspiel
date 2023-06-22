package at.htlleonding.schnapsn.controller;

import at.htlleonding.schnapsn.model.Lecture;

import javax.sql.DataSource;
import java.sql.*;

public class LectureRepository implements Persistent<Lecture> {
    private DataSource dataSource = Database.getDataSource();

    @Override
    public void save(Lecture lecture) {
        if (lecture.getId() == null) {
            insert(lecture);
        } else {
            update(lecture);
        }
    }

    public void update(Lecture lecture) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "update lecture SET name=?, content=? where lectureId=?;";

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

    @Override
    public void insert(Lecture lecture) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "insert into lecture (name, content) values  (?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, lecture.getName());
            statement.setString(2, lecture.getContent());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of LECTURE failed, no rows affected");
            }

            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    lecture.setId(keys.getInt(1));
                } else {
                    throw new SQLException("Insert into LECTURE failed, no ID obtained");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "delete from lecture where lectureId=?;";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of LECTURE failed, no rows affected");
            }

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Delete from answer_option failed, no rows affected");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
