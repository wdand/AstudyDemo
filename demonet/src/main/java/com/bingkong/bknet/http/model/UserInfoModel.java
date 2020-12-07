package com.bingkong.bknet.http.model;

public class UserInfoModel {

    /**
     * userInfo : {"userId":"220c9e19-8548-48ad-85b9-518f14210fa9","username":"uk","mobile":"","email":"ukeee@uk.com","userType":0,"emailVerificationStatus":0,"avatarUrl":""}
     */

    private UserInfoBean userInfo;

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public static class UserInfoBean {
        /**
         * id : 7d581be481d6403a881d9b959653f7f9
         * name : 撒啊旦
         * userName : as
         * password : c1704a35e704d53e628c86946d77dc39
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

        private String id = "";
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

        public String getUserId() {
            return id;
        }

        public void setUserId(String id) {
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

    public static class SSOBound {
        private String GOOGLE;
        private String FACEBOOK;

        public String getGOOGLE() {
            return GOOGLE;
        }

        public void setGOOGLE(String GOOGLE) {
            this.GOOGLE = GOOGLE;
        }

        public String getFACEBOOK() {
            return FACEBOOK;
        }

        public void setFACEBOOK(String FACEBOOK) {
            this.FACEBOOK = FACEBOOK;
        }
    }
}
