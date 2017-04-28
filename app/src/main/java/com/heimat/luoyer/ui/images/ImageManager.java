package com.heimat.luoyer.ui.images;

import java.io.Serializable;

/**
 * Created by code5 on 2017/4/20.
 */
public class ImageManager implements Serializable, Comparable {
    public String LocalPath;//本地url
    public String Url;//web url
    public int Tag;
    public String Descp;
    public boolean isHaveTo = true;

    public ImageManager(String localPath, int tag, String descp) {
        LocalPath = localPath;
        Descp = descp;
        Tag = tag;
    }

    public ImageManager() {

    }

    public ImageManager(int tag, String descp) {
        Tag = tag;
        Descp = descp;
    }

    public ImageManager(int tag, String descp, String url) {
        Tag = tag;
        Descp = descp;
        Url = url;
    }

    public ImageManager(int tag, String descp, String url, boolean isHaveTo) {
        Tag = tag;
        Descp = descp;
        Url = url;
        this.isHaveTo = isHaveTo;

    }

    public ImageManager(int tag, String descp, boolean isHaveTo) {
        Tag = tag;
        Descp = descp;
        this.isHaveTo = isHaveTo;
    }

    @Override
    public String toString() {
        return "ImageManager{" +
                "LocalPath='" + LocalPath + '\'' +
                ", Url='" + Url + '\'' +
                ", Tag=" + Tag +
                ", Descp='" + Descp + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        ImageManager imageManager = (ImageManager) o;
        return this.Tag - imageManager.Tag;
    }
}
