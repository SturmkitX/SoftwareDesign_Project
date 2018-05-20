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

    /*
    Getters for the view
     */
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

    /*
    Builder setters for faster building of objects
     */
    public Media setId(int id) {
        this.id = id;
        return this;
    }

    public Media setPath(String path) {
        this.path = path;
        return this;
    }

    public Media setTitle(String title) {
        this.title = title;
        return this;
    }

    public Media setAlbums(Set<Album> albums) {
        this.albums = albums;
        return this;
    }

    public Media setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Media media = (Media) o;
        return id == media.id;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
