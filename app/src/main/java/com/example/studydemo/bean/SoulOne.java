package com.example.studydemo.bean;

public class SoulOne {

    /**
     * code : 200
     * msg : success
     * data : {"title":"大家喜欢和你一起，是为了，显示他们的好看。"}
     * author : {"name":"Alone88","desc":"由Alone88提供的免费API 服务，官方文档：www.alapi.cn"}
     */

    private int code;
    private String msg;
    private DataBean data;
    private AuthorBean author;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public AuthorBean getAuthor() {
        return author;
    }

    public void setAuthor(AuthorBean author) {
        this.author = author;
    }

    public static class DataBean {
        /**
         * title : 大家喜欢和你一起，是为了，显示他们的好看。
         */

        private String title;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class AuthorBean {
        /**
         * name : Alone88
         * desc : 由Alone88提供的免费API 服务，官方文档：www.alapi.cn
         */

        private String name;
        private String desc;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
