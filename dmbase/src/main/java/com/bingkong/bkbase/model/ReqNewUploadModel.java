package com.bingkong.bkbase.model;

import java.util.List;

public class ReqNewUploadModel {

    /**
     * uploadId : 247f289c-6997-11e8-bc30-00163e085727
     * url : https://dev-api.unokiwi.com/uploads/2018/06/06/1397cfb66f5b4cf1bf4c01fd025205a0.png
     */
    List<ListBean> files;

    public List<ListBean> getFiles() {
        return files;
    }

    public void setFiles(List<ListBean> files) {
        this.files = files;
    }

    public static class ListBean {
        private String uploadId;
        private String url;

        public String getUploadId() {
            return uploadId;
        }

        public void setUploadId(String uploadId) {
            this.uploadId = uploadId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "UploadResultInfo{" +
                    "uploadId='" + uploadId + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }

        public String getChangeUrl() {
            return url;
        }

    }


}


