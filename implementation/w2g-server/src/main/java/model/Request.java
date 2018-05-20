package model;

public class Request {

    private String head;
    private float time;
    private String source;

    public Request() {
    }

    public Request(String head, float time, String source) {
        this.head = head;
        this.time = time;
        this.source = source;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public String getSource() {
        return source;
    }

    public Request setSource(String source) {
        this.source = source;
        return this;
    }
}
