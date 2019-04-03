package jazekOSK.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "instructors")
@PrimaryKeyJoinColumn(name = "INSTRUCTOR_ID", referencedColumnName = "USER_ID")
public class Instructor extends User {

    private String name;
    private String surname;
    private String telephone;

    @Column(name = "e_mail")
    private String eMail;

    private String pesel;

    @Column(name = "instructor_num")
    private String instNumber;

    @Column(name = "street")
    private String street;

    @Column(name = "postal_code")
    private String postCode;

    @Column(name = "city")
    private String city;

    @OneToMany(mappedBy = "instructor")
    private Set<Lesson> lessonList;

    @OneToMany(mappedBy = "mainInstructor")
    private Set<Student> studentList;

}
