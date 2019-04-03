package jazekOSK.entity;

import lombok.Data;
import jazekOSK.type.Role;
import javax.persistence.*;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "USER_ID", unique = true)
    private Integer userId;

    @Column(name = "login",unique = true)
    private String login;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_user")
    private Role role;
}
