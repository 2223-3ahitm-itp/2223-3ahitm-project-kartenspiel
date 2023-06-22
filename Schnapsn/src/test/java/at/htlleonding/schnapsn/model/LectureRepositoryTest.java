package at.htlleonding.schnapsn.model;

import at.htlleonding.schnapsn.controller.Database;
import at.htlleonding.schnapsn.controller.LectureRepository;
import at.htlleonding.schnapsn.controller.PlayerRepository;
import at.htlleonding.schnapsn.database.SqlRunner;
import org.assertj.db.api.Assertions;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class LectureRepositoryTest {

    LectureRepository lectureRepository = new LectureRepository();
    @BeforeAll
    private static void init(){
        SqlRunner.dropAndCreateTablesWithExampleData();
    }

    @Test
    @Order(1)
    void save() {
        Lecture lecture = new Lecture("Test", "Test");

        lectureRepository.save(lecture);

        Table table = new Table(Database.getDataSource(), "LECTURE");

        Assertions.assertThat(table).row(0).value("NAME").isEqualTo(lecture.getName());
        Assertions.assertThat(table).row(0).value("CONTENT").isEqualTo(lecture.getContent());
    }

    @Test
    @Order(2)
    void insert() {
        Lecture lecture = new Lecture("Test", "Test");
        Table table = new Table(Database.getDataSource(), "PLAYER");
        int rowsBefore = table.getRowsList().size();
        lectureRepository.insert(lecture);
        int rowsAfter = table.getRowsList().size();

        org.assertj.core.api.Assertions.assertThat(rowsBefore).isEqualTo(rowsAfter);
    }

    @Test
    @Order(3)
    void delete() {
        Lecture lecture = new Lecture("Test", "Test");
        lectureRepository.insert(lecture);
        Table table = new Table(Database.getDataSource(), "PLAYER");

        int rowsBefore = table.getRowsList().size();
        lectureRepository.insert(lecture);
        int rowsAfter = table.getRowsList().size();

        org.assertj.core.api.Assertions.assertThat(rowsBefore).isEqualTo(rowsAfter);
    }
}
