package com.example.studydemo.datas;

public class User {
    //头像
    private int icon;
    //昵称
    private String nick;
    //签名
    private String sign;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public User(int icon, String nick, String sign) {
        this.icon = icon;
        this.nick = nick;
        this.sign = sign;
    }
}