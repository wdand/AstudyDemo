package com.example.studydemo;

public class YFWLoginInfo {

    /**
     * code : 1
     * result : {"id":1,"account_name":"wulu","pwd":null,"dict_sex":1,"dict_account_type":1,"birthday":"2019-06-27 00:00:00","real_name":"张三","email":"","fax":"021-56498745","mobile":"15800545481","dict_bool_mobile_audit":1,"phone":"021-3356889","qq":"845097502","address":"上海市浦东新区上海浦东新区商城路297号2304","intro_image":"/19/3183/b47e09a029ad98d4945e5f5f4d611066.jpg","last_visit_time":"2020-12-09 15:54:15","regionid":523,"dict_bool_confirmation_email":0,"dict_idcard_type":0,"idcard_no":"31022619940115291X","cancel_reason":"","last_loginip":"192.168.1.53","salt":null,"login_count":9807,"dict_mobile_type":"W1","active":1,"update_time":"2020-12-08 17:51:54","create_time":"2009-12-24 15:59:41","dict_bool_certification":1,"iswhole":null}
     * msg : null
     */

    private int code;
    private ResultBean result;
    private Object msg;

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

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public static class ResultBean {
        /**
         * id : 1
         * account_name : wulu
         * pwd : null
         * dict_sex : 1
         * dict_account_type : 1
         * birthday : 2019-06-27 00:00:00
         * real_name : 张三
         * email :
         * fax : 021-56498745
         * mobile : 15800545481
         * dict_bool_mobile_audit : 1
         * phone : 021-3356889
         * qq : 845097502
         * address : 上海市浦东新区上海浦东新区商城路297号2304
         * intro_image : /19/3183/b47e09a029ad98d4945e5f5f4d611066.jpg
         * last_visit_time : 2020-12-09 15:54:15
         * regionid : 523
         * dict_bool_confirmation_email : 0
         * dict_idcard_type : 0
         * idcard_no : 31022619940115291X
         * cancel_reason :
         * last_loginip : 192.168.1.53
         * salt : null
         * login_count : 9807
         * dict_mobile_type : W1
         * active : 1
         * update_time : 2020-12-08 17:51:54
         * create_time : 2009-12-24 15:59:41
         * dict_bool_certification : 1
         * iswhole : null
         */

        private int id;
        private String account_name;
        private Object pwd;
        private int dict_sex;
        private int dict_account_type;
        private String birthday;
        private String real_name;
        private String email;
        private String fax;
        private String mobile;
        private int dict_bool_mobile_audit;
        private String phone;
        private String qq;
        private String address;
        private String intro_image;
        private String last_visit_time;
        private int regionid;
        private int dict_bool_confirmation_email;
        private int dict_idcard_type;
        private String idcard_no;
        private String cancel_reason;
        private String last_loginip;
        private Object salt;
        private int login_count;
        private String dict_mobile_type;
        private int active;
        private String update_time;
        private String create_time;
        private int dict_bool_certification;
        private Object iswhole;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAccount_name() {
            return account_name;
        }

        public void setAccount_name(String account_name) {
            this.account_name = account_name;
        }

        public Object getPwd() {
            return pwd;
        }

        public void setPwd(Object pwd) {
            this.pwd = pwd;
        }

        public int getDict_sex() {
            return dict_sex;
        }

        public void setDict_sex(int dict_sex) {
            this.dict_sex = dict_sex;
        }

        public int getDict_account_type() {
            return dict_account_type;
        }

        public void setDict_account_type(int dict_account_type) {
            this.dict_account_type = dict_account_type;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFax() {
            return fax;
        }

        public void setFax(String fax) {
            this.fax = fax;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getDict_bool_mobile_audit() {
            return dict_bool_mobile_audit;
        }

        public void setDict_bool_mobile_audit(int dict_bool_mobile_audit) {
            this.dict_bool_mobile_audit = dict_bool_mobile_audit;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getIntro_image() {
            return intro_image;
        }

        public void setIntro_image(String intro_image) {
            this.intro_image = intro_image;
        }

        public String getLast_visit_time() {
            return last_visit_time;
        }

        public void setLast_visit_time(String last_visit_time) {
            this.last_visit_time = last_visit_time;
        }

        public int getRegionid() {
            return regionid;
        }

        public void setRegionid(int regionid) {
            this.regionid = regionid;
        }

        public int getDict_bool_confirmation_email() {
            return dict_bool_confirmation_email;
        }

        public void setDict_bool_confirmation_email(int dict_bool_confirmation_email) {
            this.dict_bool_confirmation_email = dict_bool_confirmation_email;
        }

        public int getDict_idcard_type() {
            return dict_idcard_type;
        }

        public void setDict_idcard_type(int dict_idcard_type) {
            this.dict_idcard_type = dict_idcard_type;
        }

        public String getIdcard_no() {
            return idcard_no;
        }

        public void setIdcard_no(String idcard_no) {
            this.idcard_no = idcard_no;
        }

        public String getCancel_reason() {
            return cancel_reason;
        }

        public void setCancel_reason(String cancel_reason) {
            this.cancel_reason = cancel_reason;
        }

        public String getLast_loginip() {
            return last_loginip;
        }

        public void setLast_loginip(String last_loginip) {
            this.last_loginip = last_loginip;
        }

        public Object getSalt() {
            return salt;
        }

        public void setSalt(Object salt) {
            this.salt = salt;
        }

        public int getLogin_count() {
            return login_count;
        }

        public void setLogin_count(int login_count) {
            this.login_count = login_count;
        }

        public String getDict_mobile_type() {
            return dict_mobile_type;
        }

        public void setDict_mobile_type(String dict_mobile_type) {
            this.dict_mobile_type = dict_mobile_type;
        }

        public int getActive() {
            return active;
        }

        public void setActive(int active) {
            this.active = active;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public int getDict_bool_certification() {
            return dict_bool_certification;
        }

        public void setDict_bool_certification(int dict_bool_certification) {
            this.dict_bool_certification = dict_bool_certification;
        }

        public Object getIswhole() {
            return iswhole;
        }

        public void setIswhole(Object iswhole) {
            this.iswhole = iswhole;
        }
    }
}
