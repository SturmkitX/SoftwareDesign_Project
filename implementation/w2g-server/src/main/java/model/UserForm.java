package model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserForm {

    private int id;

    private String role;

    @NotBlank
    private String username;

    @NotBlank
    @Pattern(regexp = "\\S+@\\S+\\.\\S+")
    private String email;

    @NotBlank
    @Size(min = 8)
    private String password;

    public UserForm() {
    }

    public String getEmail() {
        return email;
    }

    public UserForm setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserForm{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
