package jazekOSK.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "lessons")
public class Lesson {

    @Id
    @GeneratedValue
    @Column(name = "LESSON_ID")
    private Integer lessonId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INSTRUCTOR_ID")
    private Instructor instructor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDENT_ID")
    private Student student;

    private LocalDate date;

    @Column(name = "start_hour")
    private Integer startHour;

    @Column(name = "finish_hour")
    private Integer finishHour;

}
