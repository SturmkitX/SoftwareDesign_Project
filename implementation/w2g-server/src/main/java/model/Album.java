package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
public class Album {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @NotNull
    private int id;

    @NotNull
    private String title;

    @NotNull
    @ManyToOne(cascade = { CascadeType.ALL }, targetEntity = User.class)
    private User author;

    @NotNull
    @ManyToMany(targetEntity = Media.class, cascade = { CascadeType.ALL })
    @JoinColumn(name = "id")
    private Set<Media> songs;

    private String coverPath;

    public Album() {
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public User getAuthor() {
        return author;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public Set<Media> getSongs() {
        return songs;
    }
}
