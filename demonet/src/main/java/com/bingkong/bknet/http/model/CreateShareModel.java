package com.bingkong.bknet.http.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CreateShareModel {
    public final static String TYPE_PRODUCT = "product";//its value should match with database on server.
    public final static String TYPE_POST = "post";//its value should match with database on server.
    public final static String TYPE_DESIGN = "design";//its value should match with database on server.
    public final static String SCHEME_URL = "unokiwi";

    public static String getAppUrl(String type, String id) {
        return SCHEME_URL + "://?type=" + type + "&id=" + id;
    }

    public static class Result {
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        String id;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        String url;
    }

    public static class Req implements Parcelable {
        /*
            "title":"Test share",
            "sourceId":"123455",
            "sourceType":"post",
            "description":"23232323",
            "imgUrl":"https://123.jpg",
            "appUrl":"unokiwi://unokiwi.com"
         */
        String title;
        String sourceId;
        String sourceType;
        String description;
        String imgUrl;
        String appUrl;

        public Req(String type, String id, String desc, String imageUrl, String strTitle) {
            sourceType = type;
            sourceId = id;
            this.description = desc;
            imgUrl = imageUrl;
            appUrl = CreateShareModel.getAppUrl(type, id);
            this.title = strTitle;
        }

        public Req(Parcel in) {
            title = in.readString();
            sourceId = in.readString();
            sourceType = in.readString();
            description = in.readString();
            imgUrl = in.readString();
            appUrl = in.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.title);
            dest.writeString(this.sourceId);
            dest.writeString(this.sourceType);
            dest.writeString(this.description);
            dest.writeString(this.imgUrl);
            dest.writeString(this.appUrl);
        }

        @SuppressWarnings("unused")
        public static final Parcelable.Creator<CreateShareModel.Req> CREATOR
                = new Parcelable.Creator<CreateShareModel.Req>() {
            @Override
            public CreateShareModel.Req createFromParcel(Parcel in) {
                return new CreateShareModel.Req(in);
            }

            @Override
            public CreateShareModel.Req[] newArray(int size) {
                return new CreateShareModel.Req[size];
            }
        };

        public void setTitle(String title) {
            this.title = title;
        }

        public void setSourceType(String sourceType) {
            this.sourceType = sourceType;
        }

        public String getAppUrl() {
            return appUrl;
        }

        public String getDescription() {
            return description;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public String getSourceId() {
            return sourceId;
        }

        public String getSourceType() {
            return sourceType;
        }

        public String getTitle() {
            return title;
        }

        public void setAppUrl(String appUrl) {
            this.appUrl = appUrl;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public void setSourceId(String sourceId) {
            this.sourceId = sourceId;
        }
    }
}
