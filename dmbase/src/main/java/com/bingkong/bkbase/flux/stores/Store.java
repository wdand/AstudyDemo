package com.bingkong.bkbase.flux.stores;


import com.bingkong.bkbase.constant.Constants;
import com.bingkong.bkbase.flux.dispatcher.Dispatcher;
import com.bingkong.bkbase.utils.LogUtils;

import java.util.HashMap;

public class Store {

    protected final Dispatcher dispatcher;

    public Store(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }


    protected void emitStoreChange(String url, HashMap<String, Object> data, Object storeParam) {
        LogUtils.loge("tag", "===============>" + data.toString());
        StoreChangeEvent event = new StoreChangeEvent(url, (Integer) data.get(Constants.CODE), (String) data.get(Constants.MSG), data.get(Constants.DATA),
                data.get(Constants.OBJECT), storeParam, (String) data.get(Constants.REQTOKEN));
        dispatcher.emitChange(event);
    }

    protected void emitStoreChange(String url, HashMap<String, Object> data) {
        emitStoreChange(url, data, null);
    }

    public class StoreChangeEvent {
        public String url;
        public String msg;
        public int code;
        public Object data;
        public Object actionParam;
        public Object storeParam;
        public String reqToken;

        public StoreChangeEvent(String url, int code, String msg, Object data, Object actionParam, Object storeParam, String token) {
            this.code = code;
            this.url = url;
            this.msg = msg;
            this.data = data;
            this.actionParam = actionParam;
            this.storeParam = storeParam;
            this.reqToken = token;
        }

        public void setActionParam(Object actionParam) {
            this.actionParam = actionParam;
        }

        public Object getActionParam() {
            return actionParam;
        }

        public Object getStoreParam() {
            return storeParam;
        }

        public void setStoreParam(Object storeParam) {
            this.storeParam = storeParam;
        }

        @Override
        public String toString() {
            return "StoreChangeEvent{" +
                    "url='" + url + '\'' +
                    ", msg='" + msg + '\'' +
                    ", code=" + code +
                    ", data=" + data +
                    '}';
        }
    }
}
