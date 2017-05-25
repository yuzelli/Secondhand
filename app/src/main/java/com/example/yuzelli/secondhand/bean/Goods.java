package com.example.yuzelli.secondhand.bean;

import java.io.Serializable;

/**
 * Created by 51644 on 2017/5/21.
 * 商品实体类
 */

public class Goods implements Serializable {
    private String img;
    private int price;
    private String title;
    private String content;

    public Goods(String img, int price, String title, String content) {
        this.img = img;
        this.price = price;
        this.title = title;
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
