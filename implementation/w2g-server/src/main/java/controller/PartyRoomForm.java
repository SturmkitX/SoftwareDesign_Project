package controller;

import javax.validation.constraints.NotNull;

public class PartyRoomForm {

    private String id;

    @NotNull
    private String hidden;
    private String name;

    public PartyRoomForm() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHidden() {
        return hidden;
    }

    public void setHidden(String hidden) {
        this.hidden = hidden;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
