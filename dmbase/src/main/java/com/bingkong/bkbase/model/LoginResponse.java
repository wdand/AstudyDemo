package com.bingkong.bkbase.model;

public class LoginResponse {


    /**
     * data : {"result":"true","msg":"登录成功","loginTime":"1597937994636","user":{"id":"7d581be481d6403a881d9b959653f7f9","name":"撒啊旦","userName":"as","password":"c502585b9c48177fe540a468fdc623b1","passwordSalt":"fd23726ddcda49299f672d786c804a66","isAvailable":0,"createTime":1597746293000,"createUser":null,"updateTime":null,"updateUser":null,"email":"251242152@163.com","userType":0,"emailVerificationStatus":1,"deviceId":"123456789","referUserId":"123456","phoneNumber":"17630212591","vipStart":null,"vipEnd":null,"vipStatus":0,"creatorAvatarUrl":null},"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCIsImV4cCI6MTU5ODU0Mjc5NCwidXNlcklkIjoiN2Q1ODFiZTQ4MWQ2NDAzYTg4MWQ5Yjk1OTY1M2Y3ZjkiLCJ1c2VybmFtZSI6ImFzIn0.o4WnQQB8D6jfqDEGD0TYc_GnOy6j-HvX5tkdW5G-59I"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * result : true
         * msg : 登录成功
         * loginTime : 1597937994636
         * user : {"id":"7d581be481d6403a881d9b959653f7f9","name":"撒啊旦","userName":"as","password":"c502585b9c48177fe540a468fdc623b1","passwordSalt":"fd23726ddcda49299f672d786c804a66","isAvailable":0,"createTime":1597746293000,"createUser":null,"updateTime":null,"updateUser":null,"email":"251242152@163.com","userType":0,"emailVerificationStatus":1,"deviceId":"123456789","referUserId":"123456","phoneNumber":"17630212591","vipStart":null,"vipEnd":null,"vipStatus":0,"creatorAvatarUrl":null}
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCIsImV4cCI6MTU5ODU0Mjc5NCwidXNlcklkIjoiN2Q1ODFiZTQ4MWQ2NDAzYTg4MWQ5Yjk1OTY1M2Y3ZjkiLCJ1c2VybmFtZSI6ImFzIn0.o4WnQQB8D6jfqDEGD0TYc_GnOy6j-HvX5tkdW5G-59I
         */

        private String result;
        private String msg;
        private String loginTime;
        private UserBean user;
        private String token;

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getLoginTime() {
            return loginTime;
        }

        public void setLoginTime(String loginTime) {
            this.loginTime = loginTime;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public static class UserBean {
            /**
             * id : 7d581be481d6403a881d9b959653f7f9
             * name : 撒啊旦
             * userName : as
             * password : c502585b9c48177fe540a468fdc623b1
             * passwordSalt : fd23726ddcda49299f672d786c804a66
             * isAvailable : 0
             * createTime : 1597746293000
             * createUser : null
             * updateTime : null
             * updateUser : null
             * email : 251242152@163.com
             * userType : 0
             * emailVerificationStatus : 1
             * deviceId : 123456789
             * referUserId : 123456
             * phoneNumber : 17630212591
             * vipStart : null
             * vipEnd : null
             * vipStatus : 0
             * creatorAvatarUrl : null
             */

            private String id;
            private String name;
            private String userName;
            private String password;
            private String passwordSalt;
            private int isAvailable;
            private long createTime;
            private Object createUser;
            private Object updateTime;
            private Object updateUser;
            private String email;
            private int userType;
            private int emailVerificationStatus;
            private String deviceId;
            private String referUserId;
            private String phoneNumber;
            private Object vipStart;
            private Object vipEnd;
            private int vipStatus;
            private Object creatorAvatarUrl;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getPasswordSalt() {
                return passwordSalt;
            }

            public void setPasswordSalt(String passwordSalt) {
                this.passwordSalt = passwordSalt;
            }

            public int getIsAvailable() {
                return isAvailable;
            }

            public void setIsAvailable(int isAvailable) {
                this.isAvailable = isAvailable;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public Object getCreateUser() {
                return createUser;
            }

            public void setCreateUser(Object createUser) {
                this.createUser = createUser;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }

            public Object getUpdateUser() {
                return updateUser;
            }

            public void setUpdateUser(Object updateUser) {
                this.updateUser = updateUser;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public int getUserType() {
                return userType;
            }

            public void setUserType(int userType) {
                this.userType = userType;
            }

            public int getEmailVerificationStatus() {
                return emailVerificationStatus;
            }

            public void setEmailVerificationStatus(int emailVerificationStatus) {
                this.emailVerificationStatus = emailVerificationStatus;
            }

            public String getDeviceId() {
                return deviceId;
            }

            public void setDeviceId(String deviceId) {
                this.deviceId = deviceId;
            }

            public String getReferUserId() {
                return referUserId;
            }

            public void setReferUserId(String referUserId) {
                this.referUserId = referUserId;
            }

            public String getPhoneNumber() {
                return phoneNumber;
            }

            public void setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
            }

            public Object getVipStart() {
                return vipStart;
            }

            public void setVipStart(Object vipStart) {
                this.vipStart = vipStart;
            }

            public Object getVipEnd() {
                return vipEnd;
            }

            public void setVipEnd(Object vipEnd) {
                this.vipEnd = vipEnd;
            }

            public int getVipStatus() {
                return vipStatus;
            }

            public void setVipStatus(int vipStatus) {
                this.vipStatus = vipStatus;
            }

            public Object getCreatorAvatarUrl() {
                return creatorAvatarUrl;
            }

            public void setCreatorAvatarUrl(Object creatorAvatarUrl) {
                this.creatorAvatarUrl = creatorAvatarUrl;
            }
        }
    }
}
