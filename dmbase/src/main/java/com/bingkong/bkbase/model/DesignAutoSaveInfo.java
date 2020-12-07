package com.bingkong.bkbase.model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.bingkong.bkbase.controller.WorkFlowController;

import java.util.List;

public class DesignAutoSaveInfo {
    public static class ContentAutoSave {
        String designId;    //this design's Id. For new design and customize case, it is empty.
        String primaryMediaId;

        public String getReferenceDesignId() {
            return referenceDesignId;
        }

        public void setReferenceDesignId(String referenceDesignId) {
            this.referenceDesignId = referenceDesignId;
        }

        public String getReferenceCommission() {
            return referenceCommission;
        }

        public void setReferenceCommission(String referenceCommission) {
            this.referenceCommission = referenceCommission;
        }

        public String getCustomBaseId() {
            return customBaseId;
        }

        public void setCustomBaseId(String id) {
            this.customBaseId = id;
        }

        String referenceDesignId;
        String referenceCommission;
        String customBaseId;//For new design
        // For customize case, it is reference design's own id..
        // For Edit old design case,this is its reference designId..

        List<DesignModel.ViewBaseInfoBean> views;
        List<SaveReq.ProductAutoSave> products;

        public WorkFlowController.DesignIdsCondition getMainids() {
            WorkFlowController.DesignIdsCondition dic = new WorkFlowController.DesignIdsCondition();
            dic.setProductId(null);
            dic.setPrimaryMediaId(this.primaryMediaId);
            dic.setLastDesignversionId(null);
            dic.setDesignid(designId);
            return dic;
        }

        public void setPrimaryMediaId(String primaryMediaId) {
            this.primaryMediaId = primaryMediaId;
        }

        public String getPrimaryMediaId() {
            return primaryMediaId;
        }

        public void setViews(List<DesignModel.ViewBaseInfoBean> views) {
            this.views = views;
        }

        public void setDesignId(String designId) {
            this.designId = designId;
        }

        public String getDesignId() {
            return designId;
        }

        public void setProducts(List<SaveReq.ProductAutoSave> products) {
            this.products = products;
        }

        public List<SaveReq.ProductAutoSave> getProducts() {
            return products;
        }

        public List<DesignModel.ViewBaseInfoBean> getViews() {
            return views;
        }
    }

    public static class SaveReq {
        /*
         * 	"content":{"test":"test",
         * 	"test2":"test"}
         * */
        ContentAutoSave content;

        /****
         * Check if content same as saveReq
         *      return: true, they are same, otherwise they are different.
         * */
        public boolean isEqual(SaveReq saveReq) {
            if (saveReq == null) {
                return false;
            }
            Gson gson = new Gson();
            String saveReqJson = gson.toJson(saveReq);
            String thisReqJson = gson.toJson(this);
            return thisReqJson.equals(saveReqJson);
        }

        public static class ProductAutoSave {
            String baseMediaId;
            String thumbnailPath;

            public DesignModel.ProductColor getProductColor() {
                return productColor;
            }

            public void setProductColor(DesignModel.ProductColor productColor) {
                this.productColor = productColor;
            }

            DesignModel.ProductColor productColor;

            protected List<DesignModel.ProductViewInfoBean> views;

            public String getThumbnailPath() {
                return thumbnailPath;
            }

            public void setThumbnailPath(String thumbnailPath) {
                this.thumbnailPath = thumbnailPath;
            }

            public void setBaseMediaId(String baseMediaId) {
                this.baseMediaId = baseMediaId;
            }

            public String getBaseMediaId() {
                return baseMediaId;
            }

            public List<DesignModel.ProductViewInfoBean> getViews() {
                return views;
            }

            public void setViews(List<DesignModel.ProductViewInfoBean> views) {
                this.views = views;
            }
        }

        public ContentAutoSave getContent() {
            return content;
        }

        public void setContent(ContentAutoSave content) {
            this.content = content;
        }

        public SaveReq() {

        }

        public ContentAutoSave swithMainMediaId(String newMediaId) {
            ContentAutoSave cas = getContent();
            String oldPrimary;
            oldPrimary = cas.getPrimaryMediaId();
            cas.setPrimaryMediaId(newMediaId);
            List<SaveReq.ProductAutoSave> products = cas.getProducts();
            if ((products != null) && (products.size() > 0)) {
                for (SaveReq.ProductAutoSave pas : products) {
                    if (pas.getBaseMediaId().equals(oldPrimary)) {
                        pas.setBaseMediaId(newMediaId);
                    }
                }
            }
            return cas;
        }
/*
        public  SaveReq(Parcel in){
            String contentStr=in.readString();
            Gson gson=new Gson();
            content=gson.fromJson(contentStr,ContentAutoSave.class);
        }

        @Override
        public int describeContents() {
            return 0;
        }
        @Override
        public void writeToParcel(Parcel dest, int flags)
        {
            Gson gson=new Gson();
            dest.writeString(gson.toJson(content));
        }
        @SuppressWarnings("unused")
        public static final Parcelable.Creator<SaveReq> CREATOR
                = new Parcelable.Creator<SaveReq>() {
            @Override
            public SaveReq createFromParcel(Parcel in) {
                return new SaveReq(in);
            }
            @Override
            public SaveReq[] newArray(int size) {
                return new SaveReq[size];
            }
        };
*/
    }

    public static class SimpleResponse {
        String code;
        String message;
    }

    public static class AutoSaveInfo {
        String id;
        ContentAutoSave content;
        String createdAt;


        public void setContent(ContentAutoSave content) {
            this.content = content;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public ContentAutoSave getContent() {
            return content;
        }
    }

    public static class AutoSaveData {
        List<AutoSaveInfo> data;

        public List<AutoSaveInfo> getData() {
            return data;
        }

        public void setData(List<AutoSaveInfo> data) {
            this.data = data;
        }
    }
}
