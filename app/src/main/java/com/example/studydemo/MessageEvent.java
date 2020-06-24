package com.example.studydemo;

public class MessageEvent {
    private String msg;
    private String level;

    public MessageEvent(String msg, String level) {
        this.msg = msg;
        this.level = level;
    }

    public MessageEvent() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "MessageEvent{" +
                "msg='" + msg + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}
