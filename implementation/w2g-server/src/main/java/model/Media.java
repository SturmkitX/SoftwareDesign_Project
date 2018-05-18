package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Media {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @NotNull
    private int id;

    @NotNull
    private String path;

    @NotNull
    private String title;

    @NotNull
    @ManyToMany(targetEntity = Album.class, cascade = { CascadeType.ALL })
    @JoinColumn(name = "id")
    private Set<Album> albums;     // singles are regarded as being on EP albums
    private int duration;

    public Media() {
    }

    public int getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public String getTitle() {
        return title;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public int getDuration() {
        return duration;
    }

}
