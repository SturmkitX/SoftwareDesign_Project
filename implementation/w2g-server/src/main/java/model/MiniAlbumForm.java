package model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class MiniAlbumForm {

    @Min(1)
    private int id;

    @NotBlank
    private String title;

    @NotBlank
    private String authors;

    public MiniAlbumForm() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }
}
