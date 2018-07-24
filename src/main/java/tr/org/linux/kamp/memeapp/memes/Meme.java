package tr.org.linux.kamp.memeapp.memes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.org.linux.kamp.memeapp.users.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany
    private Set<Label> labels = new HashSet<>();

}
