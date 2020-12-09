package com.example.studydemo;

import java.util.List;

public class CaseDemoBean {

    /**
     * code : 1
     * result : {"DataList":[{"Id":17487,"DoctorTitle":"副主任医师","ChiefComplaint":"2222222222222222222","StatusCode":11,"DoctorId":2429,"OrderStartTime":"2020-12-04 18:00:00","OrderEndTime":"2020-12-04 18:30:00","DoctorName":"韦冬","Avatar":"https://dev.yizong.cn/1/image/wxapp_doctor_avatar_default.png","DepartmentId":2835,"PatientInfoId":18073,"PatientName":"杨春霞","PeriodEndTime":"2020-12-04 21:00:00","Diagnosis":"","isAppraised":0,"Department":"神经外科","LeftOrderSec":-417273,"LeftPaySec":-406473},{"Id":17486,"DoctorTitle":"主任医师","ChiefComplaint":"23333333333333333","StatusCode":15,"DoctorId":1803,"OrderStartTime":"2020-12-04 17:30:00","OrderEndTime":"2020-12-04 18:00:00","DoctorName":"俞超","Avatar":"https://dev.yizong.cn/18/2257/1060b8b2b22a0f45a1b0d25a53a38925.jpg","DepartmentId":526,"PatientInfoId":18072,"PatientName":"杨春霞","PeriodEndTime":"2020-12-04 18:00:00","Diagnosis":"","isAppraised":0,"Department":"内科","LeftOrderSec":-419073,"LeftPaySec":-417273},{"Id":17429,"DoctorTitle":"副主任医师","ChiefComplaint":"","StatusCode":15,"DoctorId":2429,"OrderStartTime":"2020-12-03 18:30:00","OrderEndTime":"2020-12-03 19:00:00","DoctorName":"韦冬","Avatar":"https://dev.yizong.cn/1/image/wxapp_doctor_avatar_default.png","DepartmentId":2835,"PatientInfoId":18015,"PatientName":"韦冬","PeriodEndTime":"2020-12-03 21:00:00","Diagnosis":"","isAppraised":0,"Department":"神经外科","LeftOrderSec":-501873,"LeftPaySec":-492873},{"Id":17415,"DoctorTitle":"副主任医师","ChiefComplaint":"","StatusCode":11,"DoctorId":2429,"OrderStartTime":"2020-12-03 16:30:00","OrderEndTime":"2020-12-03 17:00:00","DoctorName":"韦冬","Avatar":"https://dev.yizong.cn/1/image/wxapp_doctor_avatar_default.png","DepartmentId":2835,"PatientInfoId":18001,"PatientName":"韦冬","PeriodEndTime":"2020-12-03 18:00:00","Diagnosis":"","isAppraised":0,"Department":"神经外科","LeftOrderSec":-509073,"LeftPaySec":-503673},{"Id":17414,"DoctorTitle":"副主任医师","ChiefComplaint":"","StatusCode":9,"DoctorId":2421,"OrderStartTime":"2020-12-03 16:00:00","OrderEndTime":"2020-12-03 16:30:00","DoctorName":"张鹏兴","Avatar":"https://dev.yizong.cn/2/2629/49d8f72e979e364a362d56973ea92fbc.jpg","DepartmentId":527,"PatientInfoId":18000,"PatientName":"韦冬","PeriodEndTime":"2020-12-03 18:00:00","Diagnosis":"","isAppraised":0,"Department":"五官科","LeftOrderSec":-510873,"LeftPaySec":-503673},{"Id":17410,"DoctorTitle":"副主任医师","ChiefComplaint":"","StatusCode":11,"DoctorId":2429,"OrderStartTime":"2020-12-03 15:00:00","OrderEndTime":"2020-12-03 15:30:00","DoctorName":"韦冬","Avatar":"https://dev.yizong.cn/1/image/wxapp_doctor_avatar_default.png","DepartmentId":2835,"PatientInfoId":17996,"PatientName":"韦冬","PeriodEndTime":"2020-12-03 18:00:00","Diagnosis":"","isAppraised":0,"Department":"神经外科","LeftOrderSec":-514473,"LeftPaySec":-503673},{"Id":17412,"DoctorTitle":"副主任医师","ChiefComplaint":"","StatusCode":5,"DoctorId":2429,"OrderStartTime":"2020-12-03 15:00:00","OrderEndTime":"2020-12-03 15:30:00","DoctorName":"韦冬","Avatar":"https://dev.yizong.cn/1/image/wxapp_doctor_avatar_default.png","DepartmentId":2835,"PatientInfoId":17998,"PatientName":"韦冬","PeriodEndTime":"2020-12-03 18:00:00","Diagnosis":"","isAppraised":0,"Department":"神经外科","LeftOrderSec":-514473,"LeftPaySec":-503673},{"Id":17404,"DoctorTitle":"副主任医师","ChiefComplaint":"","StatusCode":11,"DoctorId":2429,"OrderStartTime":"2020-12-03 14:30:00","OrderEndTime":"2020-12-03 15:00:00","DoctorName":"韦冬","Avatar":"https://dev.yizong.cn/1/image/wxapp_doctor_avatar_default.png","DepartmentId":2835,"PatientInfoId":17990,"PatientName":"韦冬","PeriodEndTime":"2020-12-03 18:00:00","Diagnosis":"","isAppraised":0,"Department":"神经外科","LeftOrderSec":-516273,"LeftPaySec":-503673},{"Id":17407,"DoctorTitle":"副主任医师","ChiefComplaint":"22222222222222","StatusCode":5,"DoctorId":2429,"OrderStartTime":"2020-12-03 14:30:00","OrderEndTime":"2020-12-03 15:00:00","DoctorName":"韦冬","Avatar":"https://dev.yizong.cn/1/image/wxapp_doctor_avatar_default.png","DepartmentId":2835,"PatientInfoId":17993,"PatientName":"韦冬","PeriodEndTime":"2020-12-03 18:00:00","Diagnosis":"","isAppraised":0,"Department":"神经外科","LeftOrderSec":-516273,"LeftPaySec":-503673},{"Id":17339,"DoctorTitle":"副主任医师","ChiefComplaint":"222222222","StatusCode":5,"DoctorId":2429,"OrderStartTime":"2020-12-01 18:00:00","OrderEndTime":"2020-12-01 18:30:00","DoctorName":"韦冬","Avatar":"https://dev.yizong.cn/1/image/wxapp_doctor_avatar_default.png","DepartmentId":2835,"PatientInfoId":17925,"PatientName":"韦冬","PeriodEndTime":"2020-12-01 21:00:00","Diagnosis":"","isAppraised":0,"Department":"神经外科","LeftOrderSec":-676473,"LeftPaySec":-665673}],"TotalCount":1004}
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
         * DataList : [{"Id":17487,"DoctorTitle":"副主任医师","ChiefComplaint":"2222222222222222222","StatusCode":11,"DoctorId":2429,"OrderStartTime":"2020-12-04 18:00:00","OrderEndTime":"2020-12-04 18:30:00","DoctorName":"韦冬","Avatar":"https://dev.yizong.cn/1/image/wxapp_doctor_avatar_default.png","DepartmentId":2835,"PatientInfoId":18073,"PatientName":"杨春霞","PeriodEndTime":"2020-12-04 21:00:00","Diagnosis":"","isAppraised":0,"Department":"神经外科","LeftOrderSec":-417273,"LeftPaySec":-406473},{"Id":17486,"DoctorTitle":"主任医师","ChiefComplaint":"23333333333333333","StatusCode":15,"DoctorId":1803,"OrderStartTime":"2020-12-04 17:30:00","OrderEndTime":"2020-12-04 18:00:00","DoctorName":"俞超","Avatar":"https://dev.yizong.cn/18/2257/1060b8b2b22a0f45a1b0d25a53a38925.jpg","DepartmentId":526,"PatientInfoId":18072,"PatientName":"杨春霞","PeriodEndTime":"2020-12-04 18:00:00","Diagnosis":"","isAppraised":0,"Department":"内科","LeftOrderSec":-419073,"LeftPaySec":-417273},{"Id":17429,"DoctorTitle":"副主任医师","ChiefComplaint":"","StatusCode":15,"DoctorId":2429,"OrderStartTime":"2020-12-03 18:30:00","OrderEndTime":"2020-12-03 19:00:00","DoctorName":"韦冬","Avatar":"https://dev.yizong.cn/1/image/wxapp_doctor_avatar_default.png","DepartmentId":2835,"PatientInfoId":18015,"PatientName":"韦冬","PeriodEndTime":"2020-12-03 21:00:00","Diagnosis":"","isAppraised":0,"Department":"神经外科","LeftOrderSec":-501873,"LeftPaySec":-492873},{"Id":17415,"DoctorTitle":"副主任医师","ChiefComplaint":"","StatusCode":11,"DoctorId":2429,"OrderStartTime":"2020-12-03 16:30:00","OrderEndTime":"2020-12-03 17:00:00","DoctorName":"韦冬","Avatar":"https://dev.yizong.cn/1/image/wxapp_doctor_avatar_default.png","DepartmentId":2835,"PatientInfoId":18001,"PatientName":"韦冬","PeriodEndTime":"2020-12-03 18:00:00","Diagnosis":"","isAppraised":0,"Department":"神经外科","LeftOrderSec":-509073,"LeftPaySec":-503673},{"Id":17414,"DoctorTitle":"副主任医师","ChiefComplaint":"","StatusCode":9,"DoctorId":2421,"OrderStartTime":"2020-12-03 16:00:00","OrderEndTime":"2020-12-03 16:30:00","DoctorName":"张鹏兴","Avatar":"https://dev.yizong.cn/2/2629/49d8f72e979e364a362d56973ea92fbc.jpg","DepartmentId":527,"PatientInfoId":18000,"PatientName":"韦冬","PeriodEndTime":"2020-12-03 18:00:00","Diagnosis":"","isAppraised":0,"Department":"五官科","LeftOrderSec":-510873,"LeftPaySec":-503673},{"Id":17410,"DoctorTitle":"副主任医师","ChiefComplaint":"","StatusCode":11,"DoctorId":2429,"OrderStartTime":"2020-12-03 15:00:00","OrderEndTime":"2020-12-03 15:30:00","DoctorName":"韦冬","Avatar":"https://dev.yizong.cn/1/image/wxapp_doctor_avatar_default.png","DepartmentId":2835,"PatientInfoId":17996,"PatientName":"韦冬","PeriodEndTime":"2020-12-03 18:00:00","Diagnosis":"","isAppraised":0,"Department":"神经外科","LeftOrderSec":-514473,"LeftPaySec":-503673},{"Id":17412,"DoctorTitle":"副主任医师","ChiefComplaint":"","StatusCode":5,"DoctorId":2429,"OrderStartTime":"2020-12-03 15:00:00","OrderEndTime":"2020-12-03 15:30:00","DoctorName":"韦冬","Avatar":"https://dev.yizong.cn/1/image/wxapp_doctor_avatar_default.png","DepartmentId":2835,"PatientInfoId":17998,"PatientName":"韦冬","PeriodEndTime":"2020-12-03 18:00:00","Diagnosis":"","isAppraised":0,"Department":"神经外科","LeftOrderSec":-514473,"LeftPaySec":-503673},{"Id":17404,"DoctorTitle":"副主任医师","ChiefComplaint":"","StatusCode":11,"DoctorId":2429,"OrderStartTime":"2020-12-03 14:30:00","OrderEndTime":"2020-12-03 15:00:00","DoctorName":"韦冬","Avatar":"https://dev.yizong.cn/1/image/wxapp_doctor_avatar_default.png","DepartmentId":2835,"PatientInfoId":17990,"PatientName":"韦冬","PeriodEndTime":"2020-12-03 18:00:00","Diagnosis":"","isAppraised":0,"Department":"神经外科","LeftOrderSec":-516273,"LeftPaySec":-503673},{"Id":17407,"DoctorTitle":"副主任医师","ChiefComplaint":"22222222222222","StatusCode":5,"DoctorId":2429,"OrderStartTime":"2020-12-03 14:30:00","OrderEndTime":"2020-12-03 15:00:00","DoctorName":"韦冬","Avatar":"https://dev.yizong.cn/1/image/wxapp_doctor_avatar_default.png","DepartmentId":2835,"PatientInfoId":17993,"PatientName":"韦冬","PeriodEndTime":"2020-12-03 18:00:00","Diagnosis":"","isAppraised":0,"Department":"神经外科","LeftOrderSec":-516273,"LeftPaySec":-503673},{"Id":17339,"DoctorTitle":"副主任医师","ChiefComplaint":"222222222","StatusCode":5,"DoctorId":2429,"OrderStartTime":"2020-12-01 18:00:00","OrderEndTime":"2020-12-01 18:30:00","DoctorName":"韦冬","Avatar":"https://dev.yizong.cn/1/image/wxapp_doctor_avatar_default.png","DepartmentId":2835,"PatientInfoId":17925,"PatientName":"韦冬","PeriodEndTime":"2020-12-01 21:00:00","Diagnosis":"","isAppraised":0,"Department":"神经外科","LeftOrderSec":-676473,"LeftPaySec":-665673}]
         * TotalCount : 1004
         */

        private int TotalCount;
        private List<DataListBean> DataList;

        public int getTotalCount() {
            return TotalCount;
        }

        public void setTotalCount(int TotalCount) {
            this.TotalCount = TotalCount;
        }

        public List<DataListBean> getDataList() {
            return DataList;
        }

        public void setDataList(List<DataListBean> DataList) {
            this.DataList = DataList;
        }

        public static class DataListBean {
            /**
             * Id : 17487
             * DoctorTitle : 副主任医师
             * ChiefComplaint : 2222222222222222222
             * StatusCode : 11
             * DoctorId : 2429
             * OrderStartTime : 2020-12-04 18:00:00
             * OrderEndTime : 2020-12-04 18:30:00
             * DoctorName : 韦冬
             * Avatar : https://dev.yizong.cn/1/image/wxapp_doctor_avatar_default.png
             * DepartmentId : 2835
             * PatientInfoId : 18073
             * PatientName : 杨春霞
             * PeriodEndTime : 2020-12-04 21:00:00
             * Diagnosis :
             * isAppraised : 0
             * Department : 神经外科
             * LeftOrderSec : -417273
             * LeftPaySec : -406473
             */

            private int Id;
            private String DoctorTitle;
            private String ChiefComplaint;
            private int StatusCode;
            private int DoctorId;
            private String OrderStartTime;
            private String OrderEndTime;
            private String DoctorName;
            private String Avatar;
            private int DepartmentId;
            private int PatientInfoId;
            private String PatientName;
            private String PeriodEndTime;
            private String Diagnosis;
            private int isAppraised;
            private String Department;
            private int LeftOrderSec;
            private int LeftPaySec;

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public String getDoctorTitle() {
                return DoctorTitle;
            }

            public void setDoctorTitle(String DoctorTitle) {
                this.DoctorTitle = DoctorTitle;
            }

            public String getChiefComplaint() {
                return ChiefComplaint;
            }

            public void setChiefComplaint(String ChiefComplaint) {
                this.ChiefComplaint = ChiefComplaint;
            }

            public int getStatusCode() {
                return StatusCode;
            }

            public void setStatusCode(int StatusCode) {
                this.StatusCode = StatusCode;
            }

            public int getDoctorId() {
                return DoctorId;
            }

            public void setDoctorId(int DoctorId) {
                this.DoctorId = DoctorId;
            }

            public String getOrderStartTime() {
                return OrderStartTime;
            }

            public void setOrderStartTime(String OrderStartTime) {
                this.OrderStartTime = OrderStartTime;
            }

            public String getOrderEndTime() {
                return OrderEndTime;
            }

            public void setOrderEndTime(String OrderEndTime) {
                this.OrderEndTime = OrderEndTime;
            }

            public String getDoctorName() {
                return DoctorName;
            }

            public void setDoctorName(String DoctorName) {
                this.DoctorName = DoctorName;
            }

            public String getAvatar() {
                return Avatar;
            }

            public void setAvatar(String Avatar) {
                this.Avatar = Avatar;
            }

            public int getDepartmentId() {
                return DepartmentId;
            }

            public void setDepartmentId(int DepartmentId) {
                this.DepartmentId = DepartmentId;
            }

            public int getPatientInfoId() {
                return PatientInfoId;
            }

            public void setPatientInfoId(int PatientInfoId) {
                this.PatientInfoId = PatientInfoId;
            }

            public String getPatientName() {
                return PatientName;
            }

            public void setPatientName(String PatientName) {
                this.PatientName = PatientName;
            }

            public String getPeriodEndTime() {
                return PeriodEndTime;
            }

            public void setPeriodEndTime(String PeriodEndTime) {
                this.PeriodEndTime = PeriodEndTime;
            }

            public String getDiagnosis() {
                return Diagnosis;
            }

            public void setDiagnosis(String Diagnosis) {
                this.Diagnosis = Diagnosis;
            }

            public int getIsAppraised() {
                return isAppraised;
            }

            public void setIsAppraised(int isAppraised) {
                this.isAppraised = isAppraised;
            }

            public String getDepartment() {
                return Department;
            }

            public void setDepartment(String Department) {
                this.Department = Department;
            }

            public int getLeftOrderSec() {
                return LeftOrderSec;
            }

            public void setLeftOrderSec(int LeftOrderSec) {
                this.LeftOrderSec = LeftOrderSec;
            }

            public int getLeftPaySec() {
                return LeftPaySec;
            }

            public void setLeftPaySec(int LeftPaySec) {
                this.LeftPaySec = LeftPaySec;
            }
        }
    }
}
