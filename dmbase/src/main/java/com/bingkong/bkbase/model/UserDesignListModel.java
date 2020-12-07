package com.bingkong.bkbase.model;

import java.util.List;

public class UserDesignListModel {

    /**
     * list : [{"id":"2822a36c-77c4-11e8-bc30-00163e085727","referenceDesignId":"","latestVersionId":"28234f0c-77c4-11e8-bc30-00163e085727","name":"design1","designStatus":0,"auditStatus":0,"tags":"silhouette short,FlowyTankTop","thumbnailPath":"https://unokiwidev.yotach.net/uploads/shorts-front-silhouette.png","previews":"0","thisMonthSales":101}]
     * pagination : {"page":1,"limit":10,"total":1}
     */

    private PaginationBean pagination;
    private List<ListBean> list;

    public PaginationBean getPagination() {
        return pagination;
    }

    public void setPagination(PaginationBean pagination) {
        this.pagination = pagination;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class PaginationBean {
        /**
         * page : 1
         * limit : 10
         * total : 1
         */

        private int page;
        private int limit;
        private int total;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }

    public static class ListBean {
        /**
         * id : 2822a36c-77c4-11e8-bc30-00163e085727
         * referenceDesignId :
         * "primaryProductId": "076852a4-871e-11e8-be26-f6c809d40cbc",
         * latestVersionId : 28234f0c-77c4-11e8-bc30-00163e085727
         * name : design1
         * designStatus : 0
         * auditStatus : 0
         * tags : silhouette short,FlowyTankTop
         * thumbnailPath : https://unokiwidev.yotach.net/uploads/shorts-front-silhouette.png
         * previews : 0
         * thisMonthSales : 101
         */

        private String id;
        private String referenceDesignId;
        private String primaryProductId;
        private String latestVersionId;
        private String name;
        private int designStatus;
        private int auditStatus;
        private int draft;
        private String tags;
        private String thumbnailPath;
        private String previews;
        private String price;
        private int thisMonthSales;

        public int getDraft() {
            return draft;
        }

        public void setDraft(int draft) {
            this.draft = draft;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getReferenceDesignId() {
            return referenceDesignId;
        }

        public void setReferenceDesignId(String referenceDesignId) {
            this.referenceDesignId = referenceDesignId;
        }

        public String getPrimaryProductId() {
            return this.primaryProductId;
        }

        public void setPrimaryProductId(String primaryProductId) {
            this.primaryProductId = primaryProductId;
        }

        public String getLatestVersionId() {
            return latestVersionId;
        }

        public void setLatestVersionId(String latestVersionId) {
            this.latestVersionId = latestVersionId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getDesignStatus() {
            return designStatus;
        }

        public void setDesignStatus(int designStatus) {
            this.designStatus = designStatus;
        }

        public int getAuditStatus() {
            return auditStatus;
        }

        public void setAuditStatus(int auditStatus) {
            this.auditStatus = auditStatus;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public String getThumbnailPath() {
            return thumbnailPath;
        }

        public void setThumbnailPath(String thumbnailPath) {
            this.thumbnailPath = thumbnailPath;
        }

        public String getPreviews() {
            return previews;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public void setPreviews(String previews) {
            this.previews = previews;
        }

        public int getThisMonthSales() {
            return thisMonthSales;
        }

        public void setThisMonthSales(int thisMonthSales) {
            this.thisMonthSales = thisMonthSales;
        }
    }
}
