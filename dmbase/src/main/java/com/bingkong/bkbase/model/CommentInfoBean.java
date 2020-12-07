package com.bingkong.bkbase.model;

public class CommentInfoBean {
    String id;
    String originalId;
    String userId;
    private String username;
    private String firstName;
    private String lastName;
    String productId;
    String productThumbnail;
    String productDesc;
    String productName;
    String body;
    String photoUrl;
    String createdAt;
    String comments;
    String likes;
    int isLiked;
    String avatarUrl;
    String lev1Comments;

    public String getProductDesc() {
        return this.productDesc;
    }

    public void setProductDesc(String desc) {
        this.productDesc = desc;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String pName) {
        this.productName = pName;
    }

    public String getLev1Comments() {
        return this.lev1Comments;
    }

    public void setLev1Comments(String comments) {
        this.lev1Comments = comments;
    }

    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    public void setAvatarUrl(String strAvatar) {
        this.avatarUrl = strAvatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginalId() {
        return originalId;
    }

    public void setOriginalId(String originalId) {
        this.originalId = originalId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String Lastname) {
        this.lastName = Lastname;
    }

    public String getProductThumbnail() {
        return this.productThumbnail;
    }

    public void setProductThumbnail(String productThumbnail) {
        this.productThumbnail = productThumbnail;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String productId) {
        this.body = body;
    }

    public String getPhotoUrl() {
        return this.photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String productId) {
        this.comments = comments;
    }

    public String getLikes() {
        return this.likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public int getIsLiked() {
        return this.isLiked;
    }

    public void setIsLiked(int isLiked) {
        this.isLiked = isLiked;
    }
}
