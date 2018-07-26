package tr.org.linux.kamp.memeapp.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.org.linux.kamp.memeapp.memes.Meme;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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

    @Size(min = 3)
    @NotBlank
    @Column(unique = true)
    private String username;

    @NotBlank
    @Column(unique = true)
    private String email;

    private String firstName;

    private String lastName;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<Meme> memes;

    public User(@Size(min = 3) @NotBlank String username, @NotBlank String email, String firstName, String lastName) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

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
