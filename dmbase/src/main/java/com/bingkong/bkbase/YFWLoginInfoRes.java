package com.bingkong.bkbase;



import java.util.List;

public class YFWLoginInfoRes {
    /**
     * code : 1
     * result : {"loginResult":1,"MedicalRecordNo":"F002CMW","Age":26,"info":{"UserId":1251,"AccountType":0,"Privilege":["wxapp"],"LoginId":"18217433824","Password":null,"UserName":"韦冬","IdCardType":0,"IdCardNo":"410804199411180015","LoginMobile":"18217433824","BindEmail":null,"Avatar":"https://dev.yizong.cn/18/1001/343aa05698dba81ed3de668c78c935bd.png","FaceImageFile":null,"Sex":1,"DoctorStatus":0,"PharmacistStatus":0,"NurseStatus":0,"CertStatus":1,"ClinicId":186,"ClinicName":"医纵互联网医院","ClinicLatitude":0,"ClinicLongitude":0,"ClinicStatus":0,"ClinicScheduleStatus":0,"CAUserId":0,"DepartmentId":0,"ShopId":0,"RoleStr":null,"SuperAdmin":false,"RoleCodes":[],"PermissionCodes":[],"ClinicIdNames":{},"ClinicNameUserProperties":{},"CreateTime":null,"LoginMethod":0,"IsMobileLogin":false,"IsFaceLogin":false,"IsOrgLogin":false,"OwnerClinicId":0,"OwnerClinicName":null,"OwnerStatusCode":1,"OfflineBizStatus":0,"OnlineBizStatus":0,"DoctorPermission":false,"PhamacistPermission":false,"Weight":11,"Token":null,"TransactionId":null,"EncKey":null,"MedicineTypes":null,"ChineseHerbalMedicine":3,"NotIncludedFeatures":null,"NotIncludedOnlineFeatures":null,"InitLoginMethod":false}}
     * msg : null
     */

    private int code;
    private ResultBean result;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class ResultBean {
        /**
         * loginResult : 1
         * MedicalRecordNo : F002CMW
         * Age : 26
         * info : {"UserId":1251,"AccountType":0,"Privilege":["wxapp"],"LoginId":"18217433824","Password":null,"UserName":"韦冬","IdCardType":0,"IdCardNo":"410804199411180015","LoginMobile":"18217433824","BindEmail":null,"Avatar":"https://dev.yizong.cn/18/1001/343aa05698dba81ed3de668c78c935bd.png","FaceImageFile":null,"Sex":1,"DoctorStatus":0,"PharmacistStatus":0,"NurseStatus":0,"CertStatus":1,"ClinicId":186,"ClinicName":"医纵互联网医院","ClinicLatitude":0,"ClinicLongitude":0,"ClinicStatus":0,"ClinicScheduleStatus":0,"CAUserId":0,"DepartmentId":0,"ShopId":0,"RoleStr":null,"SuperAdmin":false,"RoleCodes":[],"PermissionCodes":[],"ClinicIdNames":{},"ClinicNameUserProperties":{},"CreateTime":null,"LoginMethod":0,"IsMobileLogin":false,"IsFaceLogin":false,"IsOrgLogin":false,"OwnerClinicId":0,"OwnerClinicName":null,"OwnerStatusCode":1,"OfflineBizStatus":0,"OnlineBizStatus":0,"DoctorPermission":false,"PhamacistPermission":false,"Weight":11,"Token":null,"TransactionId":null,"EncKey":null,"MedicineTypes":null,"ChineseHerbalMedicine":3,"NotIncludedFeatures":null,"NotIncludedOnlineFeatures":null,"InitLoginMethod":false}
         */

        private int loginResult;
        private String MedicalRecordNo;
        private int Age;
        private ResultBean.InfoBean info;

        public int getLoginResult() {
            return loginResult;
        }

        public void setLoginResult(int loginResult) {
            this.loginResult = loginResult;
        }

        public String getMedicalRecordNo() {
            return MedicalRecordNo;
        }

        public void setMedicalRecordNo(String MedicalRecordNo) {
            this.MedicalRecordNo = MedicalRecordNo;
        }

        public int getAge() {
            return Age;
        }

        public void setAge(int Age) {
            this.Age = Age;
        }

        public ResultBean.InfoBean getInfo() {
            return info;
        }

        public void setInfo(ResultBean.InfoBean info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * UserId : 1251
             * AccountType : 0
             * Privilege : ["wxapp"]
             * LoginId : 18217433824
             * Password : null
             * UserName : 韦冬
             * IdCardType : 0
             * IdCardNo : 410804199411180015
             * LoginMobile : 18217433824
             * BindEmail : null
             * Avatar : https://dev.yizong.cn/18/1001/343aa05698dba81ed3de668c78c935bd.png
             * FaceImageFile : null
             * Sex : 1
             * DoctorStatus : 0
             * PharmacistStatus : 0
             * NurseStatus : 0
             * CertStatus : 1
             * ClinicId : 186
             * ClinicName : 医纵互联网医院
             * ClinicLatitude : 0.0
             * ClinicLongitude : 0.0
             * ClinicStatus : 0
             * ClinicScheduleStatus : 0
             * CAUserId : 0
             * DepartmentId : 0
             * ShopId : 0
             * RoleStr : null
             * SuperAdmin : false
             * RoleCodes : []
             * PermissionCodes : []
             * ClinicIdNames : {}
             * ClinicNameUserProperties : {}
             * CreateTime : null
             * LoginMethod : 0
             * IsMobileLogin : false
             * IsFaceLogin : false
             * IsOrgLogin : false
             * OwnerClinicId : 0
             * OwnerClinicName : null
             * OwnerStatusCode : 1
             * OfflineBizStatus : 0
             * OnlineBizStatus : 0
             * DoctorPermission : false
             * PhamacistPermission : false
             * Weight : 11.0
             * Token : null
             * TransactionId : null
             * EncKey : null
             * MedicineTypes : null
             * ChineseHerbalMedicine : 3
             * NotIncludedFeatures : null
             * NotIncludedOnlineFeatures : null
             * InitLoginMethod : false
             */

            private int UserId;
            private int AccountType;
            private String LoginId;
            private Object Password;
            private String UserName;
            private int IdCardType;
            private String IdCardNo;
            private String LoginMobile;
            private Object BindEmail;
            private String Avatar;
            private Object FaceImageFile;
            private int Sex;
            private int DoctorStatus;
            private int PharmacistStatus;
            private int NurseStatus;
            private int CertStatus;
            private int ClinicId;
            private String ClinicName;
            private double ClinicLatitude;
            private double ClinicLongitude;
            private int ClinicStatus;
            private int ClinicScheduleStatus;
            private int CAUserId;
            private int DepartmentId;
            private int ShopId;
            private Object RoleStr;
            private boolean SuperAdmin;
            private ResultBean.InfoBean.ClinicIdNamesBean ClinicIdNames;
            private ResultBean.InfoBean.ClinicNameUserPropertiesBean ClinicNameUserProperties;
            private Object CreateTime;
            private int LoginMethod;
            private boolean IsMobileLogin;
            private boolean IsFaceLogin;
            private boolean IsOrgLogin;
            private int OwnerClinicId;
            private Object OwnerClinicName;
            private int OwnerStatusCode;
            private int OfflineBizStatus;
            private int OnlineBizStatus;
            private boolean DoctorPermission;
            private boolean PhamacistPermission;
            private double Weight;
            private Object Token;
            private Object TransactionId;
            private Object EncKey;
            private Object MedicineTypes;
            private int ChineseHerbalMedicine;
            private Object NotIncludedFeatures;
            private Object NotIncludedOnlineFeatures;
            private boolean InitLoginMethod;
            private List<String> Privilege;
            private List<?> RoleCodes;
            private List<?> PermissionCodes;

            public int getUserId() {
                return UserId;
            }

            public void setUserId(int UserId) {
                this.UserId = UserId;
            }

            public int getAccountType() {
                return AccountType;
            }

            public void setAccountType(int AccountType) {
                this.AccountType = AccountType;
            }

            public String getLoginId() {
                return LoginId;
            }

            public void setLoginId(String LoginId) {
                this.LoginId = LoginId;
            }

            public Object getPassword() {
                return Password;
            }

            public void setPassword(Object Password) {
                this.Password = Password;
            }

            public String getUserName() {
                return UserName;
            }

            public void setUserName(String UserName) {
                this.UserName = UserName;
            }

            public int getIdCardType() {
                return IdCardType;
            }

            public void setIdCardType(int IdCardType) {
                this.IdCardType = IdCardType;
            }

            public String getIdCardNo() {
                return IdCardNo;
            }

            public void setIdCardNo(String IdCardNo) {
                this.IdCardNo = IdCardNo;
            }

            public String getLoginMobile() {
                return LoginMobile;
            }

            public void setLoginMobile(String LoginMobile) {
                this.LoginMobile = LoginMobile;
            }

            public Object getBindEmail() {
                return BindEmail;
            }

            public void setBindEmail(Object BindEmail) {
                this.BindEmail = BindEmail;
            }

            public String getAvatar() {
                return Avatar;
            }

            public void setAvatar(String Avatar) {
                this.Avatar = Avatar;
            }

            public Object getFaceImageFile() {
                return FaceImageFile;
            }

            public void setFaceImageFile(Object FaceImageFile) {
                this.FaceImageFile = FaceImageFile;
            }

            public int getSex() {
                return Sex;
            }

            public void setSex(int Sex) {
                this.Sex = Sex;
            }

            public int getDoctorStatus() {
                return DoctorStatus;
            }

            public void setDoctorStatus(int DoctorStatus) {
                this.DoctorStatus = DoctorStatus;
            }

            public int getPharmacistStatus() {
                return PharmacistStatus;
            }

            public void setPharmacistStatus(int PharmacistStatus) {
                this.PharmacistStatus = PharmacistStatus;
            }

            public int getNurseStatus() {
                return NurseStatus;
            }

            public void setNurseStatus(int NurseStatus) {
                this.NurseStatus = NurseStatus;
            }

            public int getCertStatus() {
                return CertStatus;
            }

            public void setCertStatus(int CertStatus) {
                this.CertStatus = CertStatus;
            }

            public int getClinicId() {
                return ClinicId;
            }

            public void setClinicId(int ClinicId) {
                this.ClinicId = ClinicId;
            }

            public String getClinicName() {
                return ClinicName;
            }

            public void setClinicName(String ClinicName) {
                this.ClinicName = ClinicName;
            }

            public double getClinicLatitude() {
                return ClinicLatitude;
            }

            public void setClinicLatitude(double ClinicLatitude) {
                this.ClinicLatitude = ClinicLatitude;
            }

            public double getClinicLongitude() {
                return ClinicLongitude;
            }

            public void setClinicLongitude(double ClinicLongitude) {
                this.ClinicLongitude = ClinicLongitude;
            }

            public int getClinicStatus() {
                return ClinicStatus;
            }

            public void setClinicStatus(int ClinicStatus) {
                this.ClinicStatus = ClinicStatus;
            }

            public int getClinicScheduleStatus() {
                return ClinicScheduleStatus;
            }

            public void setClinicScheduleStatus(int ClinicScheduleStatus) {
                this.ClinicScheduleStatus = ClinicScheduleStatus;
            }

            public int getCAUserId() {
                return CAUserId;
            }

            public void setCAUserId(int CAUserId) {
                this.CAUserId = CAUserId;
            }

            public int getDepartmentId() {
                return DepartmentId;
            }

            public void setDepartmentId(int DepartmentId) {
                this.DepartmentId = DepartmentId;
            }

            public int getShopId() {
                return ShopId;
            }

            public void setShopId(int ShopId) {
                this.ShopId = ShopId;
            }

            public Object getRoleStr() {
                return RoleStr;
            }

            public void setRoleStr(Object RoleStr) {
                this.RoleStr = RoleStr;
            }

            public boolean isSuperAdmin() {
                return SuperAdmin;
            }

            public void setSuperAdmin(boolean SuperAdmin) {
                this.SuperAdmin = SuperAdmin;
            }

            public ResultBean.InfoBean.ClinicIdNamesBean getClinicIdNames() {
                return ClinicIdNames;
            }

            public void setClinicIdNames(ResultBean.InfoBean.ClinicIdNamesBean ClinicIdNames) {
                this.ClinicIdNames = ClinicIdNames;
            }

            public ResultBean.InfoBean.ClinicNameUserPropertiesBean getClinicNameUserProperties() {
                return ClinicNameUserProperties;
            }

            public void setClinicNameUserProperties(ResultBean.InfoBean.ClinicNameUserPropertiesBean ClinicNameUserProperties) {
                this.ClinicNameUserProperties = ClinicNameUserProperties;
            }

            public Object getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(Object CreateTime) {
                this.CreateTime = CreateTime;
            }

            public int getLoginMethod() {
                return LoginMethod;
            }

            public void setLoginMethod(int LoginMethod) {
                this.LoginMethod = LoginMethod;
            }

            public boolean isIsMobileLogin() {
                return IsMobileLogin;
            }

            public void setIsMobileLogin(boolean IsMobileLogin) {
                this.IsMobileLogin = IsMobileLogin;
            }

            public boolean isIsFaceLogin() {
                return IsFaceLogin;
            }

            public void setIsFaceLogin(boolean IsFaceLogin) {
                this.IsFaceLogin = IsFaceLogin;
            }

            public boolean isIsOrgLogin() {
                return IsOrgLogin;
            }

            public void setIsOrgLogin(boolean IsOrgLogin) {
                this.IsOrgLogin = IsOrgLogin;
            }

            public int getOwnerClinicId() {
                return OwnerClinicId;
            }

            public void setOwnerClinicId(int OwnerClinicId) {
                this.OwnerClinicId = OwnerClinicId;
            }

            public Object getOwnerClinicName() {
                return OwnerClinicName;
            }

            public void setOwnerClinicName(Object OwnerClinicName) {
                this.OwnerClinicName = OwnerClinicName;
            }

            public int getOwnerStatusCode() {
                return OwnerStatusCode;
            }

            public void setOwnerStatusCode(int OwnerStatusCode) {
                this.OwnerStatusCode = OwnerStatusCode;
            }

            public int getOfflineBizStatus() {
                return OfflineBizStatus;
            }

            public void setOfflineBizStatus(int OfflineBizStatus) {
                this.OfflineBizStatus = OfflineBizStatus;
            }

            public int getOnlineBizStatus() {
                return OnlineBizStatus;
            }

            public void setOnlineBizStatus(int OnlineBizStatus) {
                this.OnlineBizStatus = OnlineBizStatus;
            }

            public boolean isDoctorPermission() {
                return DoctorPermission;
            }

            public void setDoctorPermission(boolean DoctorPermission) {
                this.DoctorPermission = DoctorPermission;
            }

            public boolean isPhamacistPermission() {
                return PhamacistPermission;
            }

            public void setPhamacistPermission(boolean PhamacistPermission) {
                this.PhamacistPermission = PhamacistPermission;
            }

            public double getWeight() {
                return Weight;
            }

            public void setWeight(double Weight) {
                this.Weight = Weight;
            }

            public Object getToken() {
                return Token;
            }

            public void setToken(Object Token) {
                this.Token = Token;
            }

            public Object getTransactionId() {
                return TransactionId;
            }

            public void setTransactionId(Object TransactionId) {
                this.TransactionId = TransactionId;
            }

            public Object getEncKey() {
                return EncKey;
            }

            public void setEncKey(Object EncKey) {
                this.EncKey = EncKey;
            }

            public Object getMedicineTypes() {
                return MedicineTypes;
            }

            public void setMedicineTypes(Object MedicineTypes) {
                this.MedicineTypes = MedicineTypes;
            }

            public int getChineseHerbalMedicine() {
                return ChineseHerbalMedicine;
            }

            public void setChineseHerbalMedicine(int ChineseHerbalMedicine) {
                this.ChineseHerbalMedicine = ChineseHerbalMedicine;
            }

            public Object getNotIncludedFeatures() {
                return NotIncludedFeatures;
            }

            public void setNotIncludedFeatures(Object NotIncludedFeatures) {
                this.NotIncludedFeatures = NotIncludedFeatures;
            }

            public Object getNotIncludedOnlineFeatures() {
                return NotIncludedOnlineFeatures;
            }

            public void setNotIncludedOnlineFeatures(Object NotIncludedOnlineFeatures) {
                this.NotIncludedOnlineFeatures = NotIncludedOnlineFeatures;
            }

            public boolean isInitLoginMethod() {
                return InitLoginMethod;
            }

            public void setInitLoginMethod(boolean InitLoginMethod) {
                this.InitLoginMethod = InitLoginMethod;
            }

            public List<String> getPrivilege() {
                return Privilege;
            }

            public void setPrivilege(List<String> Privilege) {
                this.Privilege = Privilege;
            }

            public List<?> getRoleCodes() {
                return RoleCodes;
            }

            public void setRoleCodes(List<?> RoleCodes) {
                this.RoleCodes = RoleCodes;
            }

            public List<?> getPermissionCodes() {
                return PermissionCodes;
            }

            public void setPermissionCodes(List<?> PermissionCodes) {
                this.PermissionCodes = PermissionCodes;
            }

            public static class ClinicIdNamesBean {
            }

            public static class ClinicNameUserPropertiesBean {
            }
        }
    }
}
