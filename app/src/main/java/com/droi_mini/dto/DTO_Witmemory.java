package com.droi_mini.dto;

import java.io.Serializable;

public class DTO_Witmemory implements Serializable {
    int num;
    String isSuc;
    String isFail;
    boolean isAlpah;

    public DTO_Witmemory(int num, String isSuc, String isFail, boolean isAlpah) {
        this.num = num;
        this.isSuc = isSuc;
        this.isFail = isFail;
        this.isAlpah = isAlpah;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getIsSuc() {
        return isSuc;
    }

    public void setIsSuc(String isSuc) {
        this.isSuc = isSuc;
    }

    public boolean isAlpah() {
        return isAlpah;
    }

    public void setAlpah(boolean alpah) {
        isAlpah = alpah;
    }

    public String getIsFail() {
        return isFail;
    }

    public void setIsFail(String isFail) {
        this.isFail = isFail;
    }
}