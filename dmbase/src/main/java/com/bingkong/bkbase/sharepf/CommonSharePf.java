package com.bingkong.bkbase.sharepf;


import android.content.Context;

public class CommonSharePf extends UnoKiSPBase {

    public static final String IM_SP_NAME = "common_pref";

    public static final String HAS_AGREE_PRIVACY_POLICY = "HAS_AGREE_PRIVACY_POLICY";


    private CommonSharePf(String spName) {
        super(spName);
    }

    private static class Holder {
        private static CommonSharePf instance = new CommonSharePf(IM_SP_NAME);
    }

    public static CommonSharePf getInstance() {
        return Holder.instance;
    }

    public static boolean isAcceptFirstTerm(Context context) {
        return CommonSharePf.getInstance().getBoolean(context,
                CommonSharePf.HAS_AGREE_PRIVACY_POLICY,
                false);
    }
}
