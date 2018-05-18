package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @NotNull
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String password;

    @NotNull
    private String email;

    @NotNull
    private int role;   // can be either regular user, admin or singer

    @NotNull
    @OneToMany(targetEntity = Album.class, cascade = { CascadeType.ALL }, mappedBy = "author")
    private Set<Album> albums;

    public User() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getRole() {
        return role;
    }

    public Set<Album> getAlbums() {
        return albums;
    }
}
