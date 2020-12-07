package com.bingkong.bkbase.model;

import java.util.List;

public class ReqUserInfoModle {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
    /*
    *  "code": 0,
    "data": {
        "list": [
            {
                "userId": "147d01b2-bd38-41f3-bc61-fc51612f5e31",
                "username": "Jasmine",
                "firstName": "",
                "lastName": "Jasmine",
                "mobile": "13188888188",
                "email": "Jasmine@iftony.com",
                "avatarUrl": ""
            }
        ]
    },
    "message": "success"
    }
    * */

        private String userId;
        private String username;
        private String firstName;
        private String lastName;
        private String mobile;
        private String email;
        private String avatarUrl;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }
    }
}
