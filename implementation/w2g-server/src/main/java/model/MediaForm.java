package model;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MediaForm {

    @Min(1)
    private int id;

    @NotBlank
    private String title;

    @NotBlank
    private String albums;

    @Min(0)
    private int duration;

    @NotNull
    private MultipartFile upFile;

    public MediaForm() {
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

    public String getAlbums() {
        return albums;
    }

    public void setAlbums(String albums) {
        this.albums = albums;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public MultipartFile getUpFile() {
        return upFile;
    }

    public void setUpFile(MultipartFile file) {
        this.upFile = file;
    }

    @Override
    public String toString() {
        return "MediaForm{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", albums='" + albums + '\'' +
                ", duration=" + duration +
                ", file=" + upFile +
                '}';
    }
}
