package com.bingkong.bkbase.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.List;

//creatd by bingsen xie 20181118
public class DesignModel {
    public static class ProductColor {
        String name;
        String hex;
        String board;

        public ProductColor() {
            hex = "#ffffff";//产品主颜色
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setHex(String hex) {
            this.hex = hex;
        }

        public String getHex() {
            return hex;
        }

        public String getBoard() {
            return board;
        }

        public void setBoard(String board) {
            this.board = board;
        }
    }

    public static class CreateDesignInfo {
        String name;
        String commission;
        String tags;
        String desc;
        Integer active;
        Integer draft;
        String thumbnailPath;
        String primaryMediaId;
        List<DesignDetailViewBean> views;
        List<ProductBean> products;

        public void setDraft(Integer draft) {
            this.draft = draft;
        }

        public Integer getDraft() {
            return draft;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCommission() {
            return commission;
        }

        public void setCommission(String commission) {
            this.commission = commission;
        }

        public void setViews(List<DesignDetailViewBean> views) {
            this.views = views;
        }

        public String getThumbnailPath() {
            return thumbnailPath;
        }

        public void setThumbnailPath(String thumbnailPath) {
            this.thumbnailPath = thumbnailPath;
        }

        public void setActive(Integer active) {
            this.active = active;
        }

        public Integer getActive() {
            return active;
        }

        public String getDesc() {
            return desc;
        }

        public String getTags() {
            return tags;
        }

        public List<DesignDetailViewBean> getViews() {
            return views;
        }

        public List<ProductBean> getProducts() {
            return products;
        }

        public String getPrimaryMediaId() {
            return primaryMediaId;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public void setPrimaryMediaId(String primaryMediaId) {
            this.primaryMediaId = primaryMediaId;
        }

        public void setProducts(List<ProductBean> products) {
            this.products = products;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }
    }


    public static class ReqCommissionModel {
        private String commission;

        public String getCommission() {
            return commission;
        }

        public void setCommission(String str) {
            this.commission = str;
        }
    }

    public static class ReqTags {
        private String tags;

        public String getTags() {
            return tags;
        }

        public void setTags(String str) {
            this.tags = str;
        }
    }

    public static class ReqNameAndDesc {
        String name;
        String desc;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static class ResActive {
        int active;

        public int getActive() {
            return active;
        }

        public void setActive(int active) {
            this.active = active;
        }

    }

    public static class ReqActive {
        String active;

        public String getActive() {
            return active;
        }

        public void setActive(String active) {
            this.active = active;
        }
    }

    public static class DesignDetailViewBean extends ViewBaseInfoBean {
        protected String id;
        protected String designVersionId;

        public void setDesignVersionId(String designVersionId) {
            this.designVersionId = designVersionId;
        }

        public String getDesignVersionId() {
            return designVersionId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class ViewBaseInfoBean {
        protected String name;
        protected String code;
        protected String thumbnailPath;
        protected String data;
        protected String embroideryPrice;
        protected String gemsPrice;
        protected String embossPrice;
        protected Boolean designOnly;

        public String getMeshResultImage() {
            return meshResultImage;
        }

        public void setMeshResultImage(String meshResultImage) {
            this.meshResultImage = meshResultImage;
        }

        public String getPrintingPrice() {
            return printingPrice;
        }

        public void setPrintingPrice(String printingPrice) {
            this.printingPrice = printingPrice;
        }

        protected String printingPrice;

        protected String meshResultImage; //A mesh result picture generated according design elements.

        public void setCode(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

        public Boolean isDesignOnly() {
            return designOnly;
        }

        public void setDesignOnly(boolean designOnly) {
            this.designOnly = designOnly;
        }

        public String getEmbossPrice() {
            return embossPrice;
        }

        public String getEmbroideryPrice() {
            return embroideryPrice;
        }

        public void setEmbossPrice(String embossPrice) {
            this.embossPrice = embossPrice;
        }

        public String getGemsPrice() {
            return gemsPrice;
        }

        public void setEmbroideryPrice(String embroideryPrice) {
            this.embroideryPrice = embroideryPrice;
        }

        public void setGemsPrice(String gemsPrice) {
            this.gemsPrice = gemsPrice;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public String getThumbnailPath() {
            return thumbnailPath;
        }

        public void setThumbnailPath(String thumbnailPath) {
            this.thumbnailPath = thumbnailPath;
        }
    }

    public static class ProductViewInfoBean extends ViewBaseInfoBean implements Parcelable {
        protected String adjustData;

        public ProductViewInfoBean() {

        }

        public ProductViewInfoBean(Parcel in) {
            this.name = in.readString();
            this.thumbnailPath = in.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.name);
            dest.writeString(this.thumbnailPath);

        }

        @SuppressWarnings("unused")
        public static final Parcelable.Creator<ProductViewInfoBean> CREATOR
                = new Parcelable.Creator<ProductViewInfoBean>() {
            @Override
            public ProductViewInfoBean createFromParcel(Parcel in) {
                return new ProductViewInfoBean(in);
            }

            @Override
            public ProductViewInfoBean[] newArray(int size) {
                return new ProductViewInfoBean[size];
            }
        };


        public String getAdjustData() {
            return adjustData;
        }

        public void setAdjustData(String adjustData) {
            this.adjustData = adjustData;
        }
    }

    //TODO 该bean是拉取后来得到的，不是提交design生成的
    public static class ProductBean implements Parcelable {
        protected String baseMediaId;
        protected String productId;
        protected String thumbnailPath;
        protected String productName;


        protected String mediaName;
        protected List<CategoryItem> mediaCategory;
        protected ProductColor productColor;//确保不为空


        protected List<ProductViewInfoBean> views;

        public String getMediaName() {
            return mediaName;
        }

        public void setMediaName(String mediaName) {
            this.mediaName = mediaName;
        }

        public List<CategoryItem> getMediaCategory() {
            return mediaCategory;
        }

        public void setMediaCategory(List<CategoryItem> mediaCategory) {
            this.mediaCategory = mediaCategory;
        }

        protected ProductBean(Parcel in) {
            baseMediaId = in.readString();
            productId = in.readString();
            thumbnailPath = in.readString();
            productName = in.readString();
            productColor = new ProductColor();
            productColor.setBoard(in.readString());
            productColor.setHex(in.readString());
            productColor.setName(in.readString());
            views = in.readArrayList(ProductViewInfoBean.class.getClassLoader());
            mediaName = in.readString();
            mediaCategory = in.readArrayList(CategoryItem.class.getClassLoader());
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.baseMediaId);
            dest.writeString(this.productId);
            dest.writeString(this.thumbnailPath);
            dest.writeString(this.productName);
            dest.writeString(this.productColor.getBoard());
            dest.writeString(this.productColor.getHex());
            dest.writeString(this.productColor.getName());
            dest.writeList(this.views);
            dest.writeString(this.mediaName);
            dest.writeList(this.mediaCategory);
        }

        @SuppressWarnings("unused")
        public static final Parcelable.Creator<ProductBean> CREATOR
                = new Parcelable.Creator<ProductBean>() {
            @Override
            public ProductBean createFromParcel(Parcel in) {
                return new ProductBean(in);
            }

            @Override
            public ProductBean[] newArray(int size) {
                return new ProductBean[size];
            }
        };

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public ProductColor getProductColor() {
            return productColor;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public void setThumbnailPath(String thumbnailPath) {
            this.thumbnailPath = thumbnailPath;
        }

        public String getThumbnailPath() {
            return thumbnailPath;
        }

        public List<ProductViewInfoBean> getViews() {
            return views;
        }

        public void setViews(List<ProductViewInfoBean> views) {
            this.views = views;
        }

        public String getBaseMediaId() {
            return baseMediaId;
        }

        public void setBaseMediaId(String baseMediaId) {
            this.baseMediaId = baseMediaId;
        }
    }

    public static class ReqCommentInfo {
        protected String comment;
        protected String photoUrl;

        public void setPhotoUrl(String photoUrl) {
            this.photoUrl = photoUrl;
        }

        public String getPhotoUrl() {
            return photoUrl;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }
    }

    //The user watching numbers of the design
    //The information cacluated by backend.
    public static class DesignViewsStatistics {
        String total;
        List<String> list;
        List<String> months;

        public List<String> getList() {
            return list;
        }

        public List<String> getMonths() {
            return months;
        }

        public String getTotal() {
            return total;
        }

        public void setList(List<String> list) {
            this.list = list;
        }

        public void setMonths(List<String> months) {
            this.months = months;
        }

        public void setTotal(String total) {
            this.total = total;
        }
    }

    //The sales numbers of the design
    //The information cacluated by backend.
    public static class DesignSalesStatistics extends DesignViewsStatistics {

    }

    public static class DesignDetail {
        DesignDetailInfo detail;

        public DesignDetailInfo getDetail() {
            return detail;
        }

        public void setDetail(DesignDetailInfo detail) {
            this.detail = detail;
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }
    }

    public static class DesignDetailInfo {
        /*
        "id": "de77ec2f-e169-11e8-8c4e-00163e085727",
      "referenceDesignId": "",
      "latestVersionId": "de7826dd-e169-11e8-8c4e-00163e085727",
      "name": "design2",
      "designStatus": 0,
      "auditStatus": 0,
      "active": 1,
      "designerId": "147d01b2-bd38-41f3-bc61-fc51612f5e31",
      "primaryMediaId": "1adef826-6a60-11e8-bc30-00163e085727",
      "primaryProductId": "de79f823-e169-11e8-8c4e-00163e085727",
      "thumbnailPath": "http:\/\/hadwewefs1212df2423ha11ha11221341155311114.jpg",
      "commission": "300.00",
      "tags": "T-shirt,Man",
      "desc": "xxx",
      "previews": "23",
      "averageRating": "0.00",
      "likes": "0",
      "comments": "0",
      "lev1Comments": "0",
      "price": "500.00",
      "priceRange": "$500.00-$500.00",
      "embroidery": "200.00",
      "gems": "150.00",
      "totalSales": 1000,
      "thisMonthSales": 101,
      "ranking": "1",
      "isLiked": 0,
      "designer": {
        "userId": "147d01b2-bd38-41f3-bc61-fc51612f5e31",
        "userName": "uk1",
        "avatarUrl": "https:\/\/unokiwidev.yotach.net\/uploads\/avatar\/4.jpg"
      },
       "products": [
        {
          "baseMediaId": "1adef826-6a60-11e8-bc30-00163e085727",
          "thumbnailPath": "http:\/\/hahaha444.jpg",
          "views": [
            {
              "name": "front",
              "thumbnailPath": "http:\/\/123.jpg",
              "data": "front",
              "adjustData": "dfdf"
            }]
            }]
       */
        String id;
        String referenceDesignId;
        String latestVersionId;
        String name;
        int designStatus;
        int auditStatus;
        int active;
        int isAuthorized;
        int allowCustomize;

        int isEditedStoreListing;
        Integer draft;
        String designerId;
        String primaryMediaId;
        String primaryProductId;
        String thumbnailPath;
        String commission;
        String referenceCommission;
        Integer customizeDepth;
        String desc;
        String tags;
        String previews;
        String averageRating;
        String likes;
        String comments;
        String lev1Comments;
        int isValidDesign;
        String price;
        String priceRange;
        String mediaPriceRange;
        String mediaMinPrice;
        String mediaMaxPrice;
        String embroidery;
        String gems;
        String lastVersionPrice;
        String reposts;
        int totalSales;
        int thisMonthSales;
        int isLiked;
        String ranking;
        Designer designer;
        List<DesignDetailViewBean> views;
        List<ProductBean> products;
        private List<DesignModel.CategoryItem> mediaCategory;

        public int getEditable() {
            return editable;
        }

        public void setEditable(int editable) {
            this.editable = editable;
        }

        private int editable;

        public List<DesignModel.CategoryItem> getMediaCategory() {
            return mediaCategory;
        }

        public void setMediaCategory(List<DesignModel.CategoryItem> mediaCategory) {
            this.mediaCategory = mediaCategory;
        }


        public int getIsEditedStoreListing() {
            return isEditedStoreListing;
        }

        public void setIsEditedStoreListing(int isEditedStoreListing) {
            this.isEditedStoreListing = isEditedStoreListing;
        }

        public int getIsAuthorized() {
            return isAuthorized;
        }

        public void setIsAuthorized(int isAuthorized) {
            this.isAuthorized = isAuthorized;
        }

        public int getAllowCustomize() {
            return allowCustomize;
        }

        public void setAllowCustomize(int allowEdit) {
            this.allowCustomize = allowEdit;
        }


        public Integer getCustomizeDepth() {
            return customizeDepth;
        }

        public void setCustomizeDepth(Integer customizeDepth) {
            this.customizeDepth = customizeDepth;
        }

        public String getReferenceCommission() {
            return this.referenceCommission;
        }

        public void setReferenceCommission(String referenceCommission) {
            this.referenceCommission = referenceCommission;
        }

        public String getReposts() {
            return reposts;
        }

        public void setReposts(String reposts) {
            this.reposts = reposts;
        }

        public String getLastVersionPrice() {
            return lastVersionPrice;
        }

        public void setLastVersionPrice(String lastVersionPrice) {
            this.lastVersionPrice = lastVersionPrice;
        }

        public void setDraft(Integer draft) {
            this.draft = draft;
        }

        public Integer getDraft() {
            return draft;
        }

        public String getMediaMaxPrice() {
            return mediaMaxPrice;
        }

        public String getMediaMinPrice() {
            return mediaMinPrice;
        }

        public List<DesignDetailViewBean> getViews() {
            return views;
        }

        public void setViews(List<DesignDetailViewBean> views) {
            this.views = views;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }

        public int getThisMonthSales() {
            return thisMonthSales;
        }

        public String getMediaPriceRange() {
            return mediaPriceRange;
        }

        public void setMediaPriceRange(String range) {
            this.mediaPriceRange = range;
        }

        public void setThisMonthSales(int thisMonthSales) {
            this.thisMonthSales = thisMonthSales;
        }

        public int getIsLiked() {
            return isLiked;
        }

        public Designer getDesigner() {
            return designer;
        }

        public String getEmbroidery() {
            return embroidery;
        }

        public int getTotalSales() {
            return totalSales;
        }

        public String getGems() {
            return gems;
        }

        public String getLev1Comments() {
            return lev1Comments;
        }

        public int getIsValidDesign() {
            return isValidDesign;
        }

        public void setIsValidDesign(int isValidDesign) {
            this.isValidDesign = isValidDesign;
        }

        public String getPriceRange() {
            return priceRange;
        }

        public void setDesigner(Designer designer) {
            this.designer = designer;
        }

        public List<ProductBean> getProducts() {
            return products;
        }

        public void setProducts(List<ProductBean> products) {
            this.products = products;
        }

        public void setEmbroidery(String embroidery) {
            this.embroidery = embroidery;
        }

        public void setGems(String gems) {
            this.gems = gems;
        }

        public void setIsLiked(int isLiked) {
            this.isLiked = isLiked;
        }

        public void setLev1Comments(String lev1Comments) {
            this.lev1Comments = lev1Comments;
        }

        public void setPriceRange(String priceRange) {
            this.priceRange = priceRange;
        }

        public void setTotalSales(int totalSales) {
            this.totalSales = totalSales;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public void setPrimaryMediaId(String primaryMediaId) {
            this.primaryMediaId = primaryMediaId;
        }

        public String getPrimaryMediaId() {
            return primaryMediaId;
        }

        public String getTags() {
            return tags;
        }

        public void setActive(int active) {
            this.active = active;
        }

        public void setThumbnailPath(String thumbnailPath) {
            this.thumbnailPath = thumbnailPath;
        }

        public String getThumbnailPath() {
            return thumbnailPath;
        }

        public void setCommission(String commission) {
            this.commission = commission;
        }

        public String getCommission() {
            return commission;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public int getActive() {
            return active;
        }

        public int getAuditStatus() {
            return auditStatus;
        }

        public int getDesignStatus() {
            return designStatus;
        }

        public String getAverageRating() {
            return averageRating;
        }

        public String getComments() {
            return comments;
        }

        public String getDesignerId() {
            return designerId;
        }

        public String getId() {
            return id;
        }

        public String getLatestVersionId() {
            return latestVersionId;
        }

        public String getLikes() {
            return likes;
        }

        public String getPreviews() {
            return previews;
        }

        public String getPrice() {
            return price;
        }

        public String getPrimaryProductId() {
            return primaryProductId;
        }

        public String getRanking() {
            return ranking;
        }

        public String getReferenceDesignId() {
            return referenceDesignId;
        }

        public void setAuditStatus(int auditStatus) {
            this.auditStatus = auditStatus;
        }

        public void setAverageRating(String averageRating) {
            this.averageRating = averageRating;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public void setDesignerId(String designerId) {
            this.designerId = designerId;
        }

        public void setDesignStatus(int designStatus) {
            this.designStatus = designStatus;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setLatestVersionId(String latestVersionId) {
            this.latestVersionId = latestVersionId;
        }

        public void setLikes(String likes) {
            this.likes = likes;
        }

        public void setPreviews(String previews) {
            this.previews = previews;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public void setPrimaryProductId(String primaryProductId) {
            this.primaryProductId = primaryProductId;
        }

        public void setRanking(String ranking) {
            this.ranking = ranking;
        }

        public void setReferenceDesignId(String referenceDesignId) {
            this.referenceDesignId = referenceDesignId;
        }

    }

    /*
              "designer": {
            "userId": "147d01b2-bd38-41f3-bc61-fc51612f5e31",
                    "userName": "uk1",
                    "avatarUrl": "https:\/\/unokiwidev.yotach.net\/uploads\/avatar\/4.jpg"
        },
    */
    public static class Designer {
        String userId;
        String userName;
        String avatarUrl;

        public String getUserId() {
            return userId;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public String getUserName() {
            return userName;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }

    public static class SizesAvailableBean implements Parcelable {
        /**
         * name : S
         */
        public SizesAvailableBean() {

        }

        private String name;
        private HashMap<String, String> info;

        public void setInfo(HashMap<String, String> info) {
            this.info = info;
        }

        public HashMap<String, String> getInfo() {
            return info;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public SizesAvailableBean(Parcel in) {
            this.name = in.readString();
            this.info = in.readHashMap(HashMap.class.getClassLoader());
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.name);
            dest.writeMap(this.info);
        }

        @SuppressWarnings("unused")
        public static final Parcelable.Creator<SizesAvailableBean> CREATOR
                = new Parcelable.Creator<SizesAvailableBean>() {
            @Override
            public SizesAvailableBean createFromParcel(Parcel in) {
                return new SizesAvailableBean(in);
            }

            @Override
            public SizesAvailableBean[] newArray(int size) {
                return new SizesAvailableBean[size];
            }
        };
    }

    public static class CategoryItem implements Parcelable {
        String id;
        String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        protected CategoryItem(Parcel in) {
            id = in.readString();
            name = in.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.name);
        }

        @SuppressWarnings("unused")
        public static final Parcelable.Creator<CategoryItem> CREATOR
                = new Parcelable.Creator<CategoryItem>() {
            @Override
            public CategoryItem createFromParcel(Parcel in) {
                return new CategoryItem(in);
            }

            @Override
            public CategoryItem[] newArray(int size) {
                return new CategoryItem[size];
            }
        };
    }

    public static class MediaCategoryList {
        List<CategoryItem> mediaCategory;

        public List<CategoryItem> getMediaCategory() {
            return mediaCategory;
        }

        public void setMediaCategory(List<CategoryItem> mediaCategory) {
            this.mediaCategory = mediaCategory;
        }
    }
}
