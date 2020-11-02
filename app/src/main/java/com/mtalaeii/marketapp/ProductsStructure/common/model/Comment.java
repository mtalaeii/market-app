package com.mtalaeii.marketapp.ProductsStructure.common.model;

import com.google.gson.annotations.SerializedName;

public class Comment {
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("fullName")
    private String fullName;
    @SerializedName("content")
    private String content;
    @SerializedName("date")
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
