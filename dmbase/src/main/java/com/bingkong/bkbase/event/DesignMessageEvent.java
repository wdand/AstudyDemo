package com.bingkong.bkbase.event;

public class DesignMessageEvent {
    public DesignMessageEvent(int type, String data) {
        this.type = type;
        this.data = data;
        ModuleMessage moduleMessage;
    }

    /* Additional fields if needed */
    private int type;
    private String data;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}