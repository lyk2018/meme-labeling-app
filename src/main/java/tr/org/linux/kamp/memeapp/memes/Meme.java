package tr.org.linux.kamp.memeapp.memes;

import lombok.Data;
import lombok.NoArgsConstructor;
import tr.org.linux.kamp.memeapp.users.User;

import javax.persistence.*;
import java.io.Serializable;

@Data
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
