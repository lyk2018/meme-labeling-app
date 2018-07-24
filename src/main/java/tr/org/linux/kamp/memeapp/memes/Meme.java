package tr.org.linux.kamp.memeapp.memes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.org.linux.kamp.memeapp.users.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Meme implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    private String artist;

    private String url;

    @ManyToOne
    private User owner;

}
