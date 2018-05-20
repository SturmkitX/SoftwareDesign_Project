package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    private String authors;

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

    public String getAuthors() {
        return authors;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public Set<Media> getSongs() {
        return songs;
    }

    public Album setId(int id) {
        this.id = id;
        return this;
    }

    public Album setTitle(String title) {
        this.title = title;
        return this;
    }

    public Album setAuthors(String authors) {
        this.authors = authors;
        return this;
    }

    public Album setSongs(Set<Media> songs) {
        this.songs = songs;
        return this;
    }

    public Album setCoverPath(String coverPath) {
        this.coverPath = coverPath;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return id == album.id;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
