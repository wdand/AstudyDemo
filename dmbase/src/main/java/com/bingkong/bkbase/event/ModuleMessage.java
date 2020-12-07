package com.bingkong.bkbase.event;

import android.os.Bundle;

public class ModuleMessage {
    public final static int EVENT_LOGINFINISH = 0;
    public final static int EVENT_REGESTFINISH = 1;
    public final static int EVENT_GETUSER = 2;
    public final static int EVENT_FINISHSELF = 3;
    public final static int EVENT_LOGOUT_FINISH = 4;
    public final static int EVENT_PHOTOSELECT_FINISH = 5;
    public final static int EVENT_BACKPRESS_FINISHSELF = 6;
    public final static int EVENT_MDEDIAL_SELECTED=7;
    public final static int EVENT_GOTOPROFILE_FORUSERID=8;
    public final static int EVENT_MULTI_MDEDIAL_SELECTED=9;
    public static final int MyDesign_EVENT_NEEDSEARCH = 10;
    public static final int MyDesign_EVENT_CHANGEORDER = 11;//change the search order
    public static final int MyDesign_EVENT_CHANGESUBCATAGORY = 12;//
    public static final int MyDesign_EVENT_CHANGETIMESETTING = 13;//
    public static final int MyDesign_EVENT_CHANGEFILTER = 14;//
    public static final int MyDesign_EVENT_SHOWTIMESETSELECTION = 15;//
    public static final int EVENT_MEDIA_SELECTION_CHANGED= 16;//change the search order
    public static final int EVENT_NEW_DESIGN_FINISHED= 17;//
    public static final int EVENT_NEW_POST_FINISHED= 18;//
    public static final int EVENT_NEW_FINISH_DESIGNDETAIL= 19;//
    public static final int EVENT_REQ_GUESTLOGIN= 20;//
    public static final int EVENT_REQ_LOGOUT= 21;//
    public static final int EVENT_REQ_GOTOMYDESIGNS= 22;//
    public static final int EVENT_REQ_KILLDESIGN= 23;//
    public static final int EVENT_CAMERA_ACT_DESTROY= 24;//
    public static final int EVENT_DESIGN_USER_TAKEPHOTO= 25;//
    public static final int EVENT_DESIGN_REQ_AUTOSAVE= 26;//ReqAutoSave data
    public static final int EVENT_DESIGN_RECOVERFRONAUTOSAVE= 27;//ReqAutoSave data
    public static final int EVENT_DESIGN_PHOTOMAP_CHANGED= 28;//Photo bitmap changed data
    public static final int EVENT_DESIGN_PHOTO_TAKED= 29;//Photo bitmap changed data
    public static final int EVENT_PHOTO_URL_BY_RECOVER= 30;//tell complext the url is used.

    public static final int EVENT_BTN_SAVE_DARFTCLICK= 31;//tell complext the url is used.
    public static final int EVENT_BTN_DESIGN_TOPBAR= 32;//tell complext the url is used.
    public static final int EVENT_START_SHARE= 33;//tell complext the url is used.
    public static final int EVENT_RECOVER_DONE = 34;//recover完成
    public static final int EVENT_LOAD_MATERIAL_DONE = 35;//加载素材完成
    public static final int EVENT_PRODUCT_BASE_COLOR_CHANGED = 36;//产品颜色变化
    public static final int EVENT_UPDATE_GLIDE_MEMORY = 37;//Glide需要清理緩存
    public static final int EVENT_UPLOADFAILURE_REPORT = 38;//
    public static final int EVENT_STATICS_REPORT = 39;//
    public static final int EVENT_MEDIA_SWITCHED=40;
    public static final int EVENT_REFRESH_TOKEN=41;
    public static final int EVENT_SWITCH_ACCOUNT=42;
    public static final int EVENT_PROMOTE_SET=43;
    public static final int EVENT_PROMOTE_UNSET=44;
    public static final int EVENT_REGET_PRODUCTLIST=45;
    public static final int EVENT_UPDATE_USERINFO=46;
    public static final int EVNET_GET_LOCATION_SUCCESS = 47;
    public static final int EVNET_GET_LOCATION_FAIL = 48;
    public static final int EVNET_REFRESH_MESH = 49;
    public static final int EVENT_EMAIL_VERIFY_SUCCESS = 50;
    public static final int EVENT_DELAY_EXIT=51;
    public static final int EVT_ITEM_CLICK=60;
    public static final int EVT_DISTRIBUTEMODE_SELECTED=61;
    public static final int EVT_GET_SEARCH_STOCK=62;
    public static final int EVT_STOCKBUYIN_INFO=63;
    public static final int EVT_REFRESH_REQUEST=64;
    public static final int EVT_COMBSTATICSBTN_CLICK=65;


    /**
     * data: event name.
     * The paramenter and other information should been put into bundleData;
     * **/

    /*
    *This event will bring the productid or comment id back in data.
    * If type==0 it is product id else it is commend id.
    */
    public final static int EVENT_REPLY_BACK = 6;

    /*
     *This event will bring the productid or comment id back in data.
     * If type==0 it is product id else it is commend id.
     */
    public final static int EVENT_REPLYANDPOST_BACK = 7;

    private int what;
    private int type;
    private String data;
    private String data2;
    private String password;
    public String destination="";//who should proccess this message.
    public Bundle bundleData=new Bundle();
    public Object object;

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Bundle getBundleData() {
        return bundleData;
    }

    public void setBundleData(Bundle bundleData) {
        this.bundleData = bundleData;
    }

    public ModuleMessage(int type, String data) {
        this.type = type;
        this.data = data;
    }

    public ModuleMessage(int type, Object object) {
        this.type = type;
        this.object = object;
    }

    public ModuleMessage(int type, String data, String password) {
        this.type = type;
        this.data = data;
        this.password = password;
    }

    public String getData2() {
        return data2;
    }

    public void setData2(String data2) {
        this.data2 = data2;
    }

    public int getWhat() {
        return what;
    }

    public ModuleMessage setWhat(int what) {
        this.what = what;
        return this;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setPassword(String data) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
