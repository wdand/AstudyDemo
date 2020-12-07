package com.bingkong.bkbase.model;

import java.util.ArrayList;

/**
 * design的products列表信息
 */
public class DesignProducts {
    private String primaryProductId;
    private String primaryMediaId;

    private ArrayList<ProductInfo> products;

    public String getPrimaryProductId() {
        return primaryProductId;
    }

    public String getPrimaryMediaId() {
        return primaryMediaId;
    }

    public ArrayList<ProductInfo> getProducts() {
        return products;
    }

    public class ProductInfo {
        private String productId;
        private String productName;
        private String mediaId;

        public String getProductId() {
            return productId;
        }

        public String getProductName() {
            return productName;
        }

        public String getMediaId() {
            return mediaId;
        }
    }
}
