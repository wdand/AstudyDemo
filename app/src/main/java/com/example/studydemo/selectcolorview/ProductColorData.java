package com.example.studydemo.selectcolorview;

import java.util.List;

public class ProductColorData {
    public int type;
    public String color;
    public String path;
    public String id;
    public String colorName;
    public List<String> pathList;//钻石素材，一个id可以多个图片，可以随机选一种

    @Override
    public String toString() {
        return "type " + type + " color " + color + " path " + path + " name " + colorName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        if(color!=null) {
            if(!color.contains("#")){
                color="#"+color;
            }
        }
        this.color = color;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getPathList() {
        return pathList;
    }

    public void setPathList(List<String> pathList) {
        this.pathList = pathList;
    }

    public ProductColorData clone(ProductColorData materialData) {
        ProductColorData result = new ProductColorData();
        if(materialData != null) {
            result.setColor(materialData.getColor());
            result.setType(materialData.getType());
            result.setPath(materialData.getPath());
            result.setPathList(materialData.getPathList());
        }

        return result;
    }
}
