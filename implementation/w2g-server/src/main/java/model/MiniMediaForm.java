package model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class MiniMediaForm {

    @Min(1)
    private int id;

    @NotBlank
    private String title;

    @Min(0)
    private int duration;

    public MiniMediaForm() {
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
