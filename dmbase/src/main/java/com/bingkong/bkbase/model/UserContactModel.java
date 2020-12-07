package com.bingkong.bkbase.model;

public class UserContactModel {
    public static String STORENAME_getContactInfo = "getContactInfo";

    /**
     * Block
     */
    public static class reqUserBlock {
        public String getBlockUnokiwi() {
            return blockUnokiwi;
        }

        public void setBlockUnokiwi(String blockUnokiwi) {
            this.blockUnokiwi = blockUnokiwi;
        }

        private String blockUnokiwi;
    }

    public static class ContactInfo {
        /**
         * "detail": {
         * CIDetail
         * }
         */
        CIDetail detail;

        public void setDetail(CIDetail detail) {
            this.detail = detail;
        }

        public CIDetail getDetail() {
            return detail;
        }

    }

    public static class CIDetail {
        /*
        *          "contactId": "220c9e19-8548-48ad-85b9-518f14210fa9",
         "username": "uk",
         "firstName": "Tom",
         "lastName": "James",
         "mobile": "",
         "email": "ukeee@uk.com",
         "avatarUrl": "https:\/\/unokiwidev.yotach.net\/uploads\/2018\/12\/05\/57b4dbbae783446da2c16b38782d964b.jpg",
         "ignoreMessage": 0,
         "blockMessage": 0,
         "blockUnokiwi": 0,
         "contactType": 3
        * */
        String contactId;
        String username;
        String firstName;
        String lastName;
        String mobile;
        String email;
        String avatarUrl;
        int ignoreMessage;
        int blockMessage;
        int blockUnokiwi;
        int contactType;

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public String getUsername() {
            return username;
        }

        public String getLastName() {
            return lastName;
        }

        public int getBlockMessage() {
            return blockMessage;
        }

        public int getBlockUnokiwi() {
            return blockUnokiwi;
        }

        public int getIgnoreMessage() {
            return ignoreMessage;
        }

        public void setBlockMessage(int blockMessage) {
            this.blockMessage = blockMessage;
        }

        public void setBlockUnokiwi(int blockUnokiwi) {
            this.blockUnokiwi = blockUnokiwi;
        }

        public void setContactId(String contactId) {
            this.contactId = contactId;
        }

        public void setIgnoreMessage(int ignoreMessage) {
            this.ignoreMessage = ignoreMessage;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getFirstName() {
            return firstName;
        }

        public int getContactType() {
            return contactType;
        }

        public String getContactId() {
            return contactId;
        }

        public String getEmail() {
            return email;
        }

        public String getMobile() {
            return mobile;
        }

        public void setContactType(int contactType) {
            this.contactType = contactType;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
