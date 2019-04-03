package jazekOSK.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;


@Data
@Entity
@Table(name = "students")
@PrimaryKeyJoinColumn(name = "STUDENT_ID", referencedColumnName = "USER_ID")
public class Student extends User {

    private String name;
    private String surname;
    private String telephone;

    private String pesel;

    @Column(name = "reg_num")
    private String regNum;

    @Column(name = "pk_num")
    private String pkNum;

    @Column(name = "i_doc_num")
    private String idNumber;

    @Column(name = "street")
    private String street;

    @Column(name = "postal_code")
    private String postCode;

    @Column(name = "city")
    private String city;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INSTRUCTOR_ID")
    private Instructor mainInstructor;

    @OneToMany(mappedBy = "student")
    private Set<Payment> paymentList;

    @OneToMany(mappedBy = "student")
    private Set<Lesson> lessonList;
}
