package controller;

public class PartyRoom {

    private String id;
    private boolean hidden;
    private String name;

    public PartyRoom() {
    }

    public PartyRoom(String id, boolean hidden, String name) {
        this.id = id;
        this.hidden = hidden;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public boolean isHidden() {
        return hidden;
    }

    public String getName() {
        return name;
    }

    public PartyRoom setId(String id) {
        this.id = id;
        return this;
    }

    public PartyRoom setHidden(boolean hidden) {
        this.hidden = hidden;
        return this;
    }

    public PartyRoom setName(String name) {
        this.name = name;
        return this;
    }
}
