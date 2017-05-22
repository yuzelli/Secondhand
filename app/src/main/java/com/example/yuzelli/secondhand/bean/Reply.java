package com.example.yuzelli.secondhand.bean;

import java.io.Serializable;

/**
 * Created by 51644 on 2017/5/21.
 */

public class Reply implements Serializable {

    private String phone;
    private String content;


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
