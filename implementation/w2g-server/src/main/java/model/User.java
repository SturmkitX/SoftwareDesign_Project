package model;

import javax.persistence.*;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String name;
    private String password;
    private String email;

    @Column(name = "isadmin")
    private boolean admin;

    public User() {
    }


}
