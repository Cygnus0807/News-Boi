package com.example.mynews;

import java.io.Serializable;

public class MyData implements Serializable {
    private String imgUrl;
    private String title;
    private String date;

    public MyData(String imgUrl, String title, String date) {
        this.imgUrl=imgUrl;
        this.title=title;
        this.date=date;
    }


    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



}
