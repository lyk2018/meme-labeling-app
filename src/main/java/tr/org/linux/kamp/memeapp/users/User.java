package tr.org.linux.kamp.memeapp.users;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "APP_USER")
class User implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String email;

    private String firstName;

    private String lastName;

}
