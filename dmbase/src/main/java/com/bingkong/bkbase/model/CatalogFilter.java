package com.bingkong.bkbase.model;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

public class CatalogFilter {
    public Map<String, String> mfilter = new HashMap<String, String>();

    public CatalogFilter() {
        mfilter.put("mc", "Man");
        mfilter.put("mcid", "7c12e14e-9580-11e8-bc30-00163e085727");
        mfilter.put("sc", "All Over T");
        mfilter.put("scid", "");
        mfilter.put("st", "Hot");
        mfilter.put("ts", "All Time");
    }

    public static boolean ObjEquals(Object a, Object b) {
        if (a == null) {
            return false;
        }
        return a.equals(b);
    }

    public static boolean isValueMatched(String key, Map<String, Object> f1, Map<String, Object> f2) {
        if ((f1 == null) || (f2 == null)) {
            return false;
        }
        String v1 = (String) f1.get(key);
        String v2 = (String) f2.get(key);
        if ((v1 == null) && (v2 == null)) {
            return true;
        }
        return ObjEquals(v1, v2);
    }

    public static void cloneValue(String key, Map<String, Object> target, Map<String, Object> f2) {
        if ((target == null) || (f2 == null)) return;
        target.put(key, f2.get(key));
    }

    public void Rebuild(String jsonStr) {
        mfilter.clear();
        Gson gs = new Gson();
        mfilter = gs.fromJson(jsonStr, new TypeToken<Map<String, String>>() {
        }.getType());
    }

    public CatalogFilter(String jsonStr) {
        Gson gs = new Gson();
        mfilter = gs.fromJson(jsonStr, new TypeToken<Map<String, String>>() {
        }.getType());
    }

    public Map<String, String> getMfilter() {
        return mfilter;
    }

    public boolean ifSorttypeNeedParam(String st) {
        if (st.equals("Top")) return true;
        else return false;
    }

    public void replaceFilter(String key, String str) {
        mfilter.remove("key");
        mfilter.put(key, str);
    }

    public String GetJsonString() {
        Gson g = new Gson();
        String jsonString = g.toJson(mfilter);
        return jsonString;
    }

    public static HashMap<String, Object> getDesignParams(int pageno,
                                                          int pagelimit,
                                                          int draft,
                                                          String keyword,
                                                          String categoryName,
                                                          String categoryId) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("page", pageno);
        params.put("limit", pagelimit);
        params.put("draft", draft);

        if (null != keyword && !TextUtils.isEmpty(keyword)) {
            params.put("keyword", keyword);
        }
        //If the subcatagory had been set, the catagory should be the subcatagory id.
        if ((categoryName != null) && (categoryId != null)) {
            if ((categoryId.length() > 0) && (categoryName.contains("All") == false)) {
                params.put("categoryId", categoryId);
            }
        }
        if (null != keyword && !TextUtils.isEmpty(keyword)) {
            params.put("keyword", keyword);
        }
        //st is sorttype, it should be one of "Hot,New,Top".
        //ts is timesetting, is is depeond on the setting of Hot,New,Top.
//        if((cf!=null)&&
//                (cf.mfilter.get("st")!=null)&&
//                (cf.mfilter.get("ts")!=null)) {
//            params.put(cf.mfilter.get("st"), cf.mfilter.get("ts"));
//        }
        return params;
    }

    public static HashMap<String, Object> getParams(int pageno,
                                                    int pagelimit,
                                                    String keyword,
                                                    String categoryName,
                                                    String categoryId,
                                                    CatalogFilter cf) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("page", pageno);
        params.put("limit", 10);

        if (null != keyword && !TextUtils.isEmpty(keyword)) {
            params.put("keyword", keyword);
        }
        //If the subcatagory had been set, the catagory should be the subcatagory id.
        if ((categoryName != null) && (categoryId != null)) {
            if ((categoryId.length() > 0) && (categoryName.contains("All") == false)) {
                if ((cf != null) &&
                        (cf.mfilter.get("scid") != null) &&
                        (cf.mfilter.get("scid").length() == 0)) {
                    categoryId = cf.mfilter.get("scid");
                }
                params.put("categoryId", categoryId);
            }
        }
        if (null != keyword && !TextUtils.isEmpty(keyword)) {
            params.put("keyword", keyword);
        }
        //st is sorttype, it should be one of "Hot,New,Top".
        //ts is timesetting, is is depeond on the setting of Hot,New,Top.
        if ((cf != null) &&
                (cf.mfilter.get("st") != null) &&
                (cf.mfilter.get("ts") != null)) {
            params.put(cf.mfilter.get("st"), cf.mfilter.get("ts"));
        }
        return params;
    }
}
