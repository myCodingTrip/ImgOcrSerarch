package com.my.search.bean;

public class PicDesc {
    String path;
    String picDesc;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPicDesc() {
        return picDesc;
    }

    public void setPicDesc(String picDesc) {
        this.picDesc = picDesc;
    }

    @Override
    public String toString() {
        return "PicDesc{" +
                "path='" + path + '\'' +
                ", picDesc='" + picDesc + '\'' +
                '}';
    }
}
