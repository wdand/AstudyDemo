package com.bingkong.bkbase.upload.model;

public class UploadModel {

    private String error;
    private long chunkSize;
    private String groupSubDir;
    private String resourceTempBaseName;
    private String resourceExt;
    private String savedPath;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getGroupSubDir() {
        return groupSubDir;
    }

    public void setGroupSubDir(String groupSubDir) {
        this.groupSubDir = groupSubDir;
    }

    public String getResourceTempBaseName() {
        return resourceTempBaseName;
    }

    public void setResourceTempBaseName(String resourceTempBaseName) {
        this.resourceTempBaseName = resourceTempBaseName;
    }

    public String getResourceExt() {
        return resourceExt;
    }

    public void setResourceExt(String resourceExt) {
        this.resourceExt = resourceExt;
    }

    public String getSavedPath() {
        return savedPath;
    }

    public void setSavedPath(String savedPath) {
        this.savedPath = savedPath;
    }

    public long getChunkSize() {
        return chunkSize;
    }

    public void setChunkSize(long chunkSize) {
        this.chunkSize = chunkSize;
    }

    @Override
    public String toString() {
        return "UploadModel{" +
                "error='" + error + '\'' +
                ", chunkSize='" + chunkSize + '\'' +
                ", groupSubDir='" + groupSubDir + '\'' +
                ", resourceTempBaseName='" + resourceTempBaseName + '\'' +
                ", resourceExt='" + resourceExt + '\'' +
                ", savedPath='" + savedPath + '\'' +
                '}';
    }
}
