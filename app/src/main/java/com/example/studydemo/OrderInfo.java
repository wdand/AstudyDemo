package com.example.studydemo;

import java.util.List;

public class OrderInfo {


    /**
     * code : 1
     * result : {"pageCount":746,"returnCount":true,"order_ist_ads":{},"dataList":[{"orderno":"C2012091631582582","dict_order_return_status":0,"packaging_total":0,"phone_show_groupbuy_type":0,"storeid":349213,"title":"上饶市昌盛大药房有限公司信州区渡口店","accountid":1,"dict_order_status":10,"selflift_code":"","statusName":"暂未付款","trafficno":"","dict_bool_cold_storage":0,"storelogourl":"","shipping_total":5,"create_time":"2020-12-09 16:31:58","total_price":19.27,"medicineCount":21,"phone_show_type":"-1","updatePastHours":0,"phone":"","inquiryid":0,"trafficid":0}],"reserveTip":"因商品库存实时变化，请您尽快完成付款，以免订单交易失败。","rowCount":746,"HistorOrder":true}
     * msg : null
     */

    private int code;
    private Object msg;


    /**
     * result : {"pageCount":746,"returnCount":true,"dataList":[{"orderno":"C2012091631582582","dict_order_return_status":0,"packaging_total":0,"phone_show_groupbuy_type":0,"storeid":349213,"title":"上饶市昌盛大药房有限公司信州区渡口店","accountid":1,"dict_order_status":10,"selflift_code":"","statusName":"暂未付款","trafficno":"","dict_bool_cold_storage":0,"storelogourl":"","shipping_total":5,"create_time":"2020-12-09 16:31:58","total_price":19.27,"medicineCount":21,"phone_show_type":"-1","updatePastHours":0,"phone":"","inquiryid":0,"trafficid":0}],"reserveTip":"因商品库存实时变化，请您尽快完成付款，以免订单交易失败。","rowCount":746,"HistorOrder":true}
     */

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * pageCount : 746
         * returnCount : true
         * dataList : [{"orderno":"C2012091631582582","dict_order_return_status":0,"packaging_total":0,"phone_show_groupbuy_type":0,"storeid":349213,"title":"上饶市昌盛大药房有限公司信州区渡口店","accountid":1,"dict_order_status":10,"selflift_code":"","statusName":"暂未付款","trafficno":"","dict_bool_cold_storage":0,"storelogourl":"","shipping_total":5,"create_time":"2020-12-09 16:31:58","total_price":19.27,"medicineCount":21,"phone_show_type":"-1","updatePastHours":0,"phone":"","inquiryid":0,"trafficid":0}]
         * reserveTip : 因商品库存实时变化，请您尽快完成付款，以免订单交易失败。
         * rowCount : 746
         * HistorOrder : true
         */

        private int pageCount;
        private boolean returnCount;
        private String reserveTip;
        private int rowCount;
        private boolean HistorOrder;
        private List<DataListBean> dataList;

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public boolean isReturnCount() {
            return returnCount;
        }

        public void setReturnCount(boolean returnCount) {
            this.returnCount = returnCount;
        }

        public String getReserveTip() {
            return reserveTip;
        }

        public void setReserveTip(String reserveTip) {
            this.reserveTip = reserveTip;
        }

        public int getRowCount() {
            return rowCount;
        }

        public void setRowCount(int rowCount) {
            this.rowCount = rowCount;
        }

        public boolean isHistorOrder() {
            return HistorOrder;
        }

        public void setHistorOrder(boolean HistorOrder) {
            this.HistorOrder = HistorOrder;
        }

        public List<DataListBean> getDataList() {
            return dataList;
        }

        public void setDataList(List<DataListBean> dataList) {
            this.dataList = dataList;
        }

        public static class DataListBean {
            /**
             * orderno : C2012091631582582
             * dict_order_return_status : 0
             * packaging_total : 0
             * phone_show_groupbuy_type : 0
             * storeid : 349213
             * title : 上饶市昌盛大药房有限公司信州区渡口店
             * accountid : 1
             * dict_order_status : 10
             * selflift_code :
             * statusName : 暂未付款
             * trafficno :
             * dict_bool_cold_storage : 0
             * storelogourl :
             * shipping_total : 5
             * create_time : 2020-12-09 16:31:58
             * total_price : 19.27
             * medicineCount : 21
             * phone_show_type : -1
             * updatePastHours : 0
             * phone :
             * inquiryid : 0
             * trafficid : 0
             */

            private String orderno;
            private int dict_order_return_status;
            private int packaging_total;
            private int phone_show_groupbuy_type;
            private int storeid;
            private String title;
            private int accountid;
            private int dict_order_status;
            private String selflift_code;
            private String statusName;
            private String trafficno;
            private int dict_bool_cold_storage;
            private String storelogourl;
            private int shipping_total;
            private String create_time;
            private double total_price;
            private int medicineCount;
            private String phone_show_type;
            private int updatePastHours;
            private String phone;
            private int inquiryid;
            private int trafficid;

            public String getOrderno() {
                return orderno;
            }

            public void setOrderno(String orderno) {
                this.orderno = orderno;
            }

            public int getDict_order_return_status() {
                return dict_order_return_status;
            }

            public void setDict_order_return_status(int dict_order_return_status) {
                this.dict_order_return_status = dict_order_return_status;
            }

            public int getPackaging_total() {
                return packaging_total;
            }

            public void setPackaging_total(int packaging_total) {
                this.packaging_total = packaging_total;
            }

            public int getPhone_show_groupbuy_type() {
                return phone_show_groupbuy_type;
            }

            public void setPhone_show_groupbuy_type(int phone_show_groupbuy_type) {
                this.phone_show_groupbuy_type = phone_show_groupbuy_type;
            }

            public int getStoreid() {
                return storeid;
            }

            public void setStoreid(int storeid) {
                this.storeid = storeid;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getAccountid() {
                return accountid;
            }

            public void setAccountid(int accountid) {
                this.accountid = accountid;
            }

            public int getDict_order_status() {
                return dict_order_status;
            }

            public void setDict_order_status(int dict_order_status) {
                this.dict_order_status = dict_order_status;
            }

            public String getSelflift_code() {
                return selflift_code;
            }

            public void setSelflift_code(String selflift_code) {
                this.selflift_code = selflift_code;
            }

            public String getStatusName() {
                return statusName;
            }

            public void setStatusName(String statusName) {
                this.statusName = statusName;
            }

            public String getTrafficno() {
                return trafficno;
            }

            public void setTrafficno(String trafficno) {
                this.trafficno = trafficno;
            }

            public int getDict_bool_cold_storage() {
                return dict_bool_cold_storage;
            }

            public void setDict_bool_cold_storage(int dict_bool_cold_storage) {
                this.dict_bool_cold_storage = dict_bool_cold_storage;
            }

            public String getStorelogourl() {
                return storelogourl;
            }

            public void setStorelogourl(String storelogourl) {
                this.storelogourl = storelogourl;
            }

            public int getShipping_total() {
                return shipping_total;
            }

            public void setShipping_total(int shipping_total) {
                this.shipping_total = shipping_total;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public double getTotal_price() {
                return total_price;
            }

            public void setTotal_price(double total_price) {
                this.total_price = total_price;
            }

            public int getMedicineCount() {
                return medicineCount;
            }

            public void setMedicineCount(int medicineCount) {
                this.medicineCount = medicineCount;
            }

            public String getPhone_show_type() {
                return phone_show_type;
            }

            public void setPhone_show_type(String phone_show_type) {
                this.phone_show_type = phone_show_type;
            }

            public int getUpdatePastHours() {
                return updatePastHours;
            }

            public void setUpdatePastHours(int updatePastHours) {
                this.updatePastHours = updatePastHours;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public int getInquiryid() {
                return inquiryid;
            }

            public void setInquiryid(int inquiryid) {
                this.inquiryid = inquiryid;
            }

            public int getTrafficid() {
                return trafficid;
            }

            public void setTrafficid(int trafficid) {
                this.trafficid = trafficid;
            }
        }
    }
}
