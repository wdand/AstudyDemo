package com.bingkong.bknet.http.model;

public class UserInfoDeviceId {

    /**
     * userInfo : {"userId":"8eee1112-6be0-4b0c-a790-7b5b2afee2a2","username":"Dddd","firstName":"Sssss","lastName":"Aaa","mobile":"15840210488","email":"1@11.com","userType":0,"emailVerificationStatus":0,"avatarUrl":"https://unokiwidev.yotach.net/uploads/auto-generate-avatar/2019/09/25/24b5a46ccb2b4a0db24eba8547efa390.png","regState":true}
     */

    private UserInfoModel.UserInfoBean userInfo;

    public UserInfoModel.UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoModel.UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }


    @Override
    public String toString() {
        return "UserInfoDeviceId{" +
                "userInfo=" + userInfo +
                '}';
    }
    public UserInfoModel convertToUserInfoModel() {
        UserInfoModel model = new UserInfoModel();
        model.setUserInfo(userInfo);
        return model;

    }
}
