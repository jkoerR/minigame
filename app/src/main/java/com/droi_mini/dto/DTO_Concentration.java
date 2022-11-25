package com.droi_mini.dto;

import java.io.Serializable;

public class DTO_Concentration implements Serializable {

    int img_url;
    String text;

    public DTO_Concentration(int img_url, String text) {
        this.img_url = img_url;
        this.text = text;
    }

    public int getImg_url() {
        return img_url;
    }

    public void setImg_url(int img_url) {
        this.img_url = img_url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}