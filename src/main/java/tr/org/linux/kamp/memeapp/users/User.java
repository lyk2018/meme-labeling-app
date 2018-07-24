package tr.org.linux.kamp.memeapp.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.org.linux.kamp.memeapp.memes.Meme;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "APP_USER")
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String email;

    private String firstName;

    private String lastName;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<Meme> memes;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
