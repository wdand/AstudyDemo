package com.bingkong.bkbase.model;

public class RegisteredInfo {

    /**
     * phoneNumber : 17630212591
     * phoneCode : 821628
     * registrationTime : 2020-09-07  16:13:12
     * password : 123456
     * email : wupenghuis@163.com
     * referUserId : 123456
     */

    private String phoneNumber;
    private String phoneCode;
    private String registrationTime;
    private String password;
    private String email;
    private String introducerPhone;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(String registrationTime) {
        this.registrationTime = registrationTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReferUserId() {
        return introducerPhone;
    }

    public void setReferUserId(String referUserId) {
        this.introducerPhone = referUserId;
    }
}
