package com.cloudinteractiveHungChiHang.model;

import com.google.gson.annotations.SerializedName;

public class Item_api {
    @SerializedName(value="albumId")
    private String albumId;
    @SerializedName(value="id")
    private String id;
    @SerializedName(value="title")
    private String title;
    @SerializedName(value="url")
    private String url;
    @SerializedName(value="thumbnailUrl")
    private String thumbnailUrl;



    public Item_api(){}

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
