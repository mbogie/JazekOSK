package jazekOSK.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "admins")
@PrimaryKeyJoinColumn(name = "ADMIN_ID", referencedColumnName = "USER_ID")
public class Admin extends User {

    private String name;
    private String surname;
    private String telephone;

    @Column(name = "e_mail")
    private String eMail;
}
