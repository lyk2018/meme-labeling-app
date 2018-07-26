package tr.org.linux.kamp.memeapp.memes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.org.linux.kamp.memeapp.users.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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

    @NotBlank
    private String name;

    private String description;

    private String artist;

    @Pattern(regexp = "^http.*")
    private String url;

    @NotNull
    @ManyToOne
    private User owner;

    @ManyToMany
    private Set<Label> labels = new HashSet<>();

}
