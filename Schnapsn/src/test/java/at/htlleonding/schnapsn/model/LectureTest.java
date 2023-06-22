package at.htlleonding.schnapsn.model;

import org.junit.jupiter.api.Test;

public class LectureTest {
    @Test
    public void createLecture() {
        Lecture lecture = new Lecture("Doppeldeutsche Karten", "Es gibt 20 Karten. Jeweils 5 von einer Farbe. Es gibt die Farben: PIK, HERZ, SCHELLE und EICHEL. Ausserdem gibt es die Typen: Unter, Ober, König, Zehn und Sau, diese haben die Werte: 2, 3, 4, 10 und 11");

        org.assertj.core.api.Assertions.assertThat(lecture.getName()).isEqualTo("Doppeldeutsche Karten");
        org.assertj.core.api.Assertions.assertThat(lecture.getContent()).isEqualTo("Es gibt 20 Karten. Jeweils 5 von einer Farbe. Es gibt die Farben: PIK, HERZ, SCHELLE und EICHEL. Ausserdem gibt es die Typen: Unter, Ober, König, Zehn und Sau, diese haben die Werte: 2, 3, 4, 10 und 11");
    }
}
