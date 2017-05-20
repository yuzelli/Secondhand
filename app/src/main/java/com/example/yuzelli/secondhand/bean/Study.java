package com.example.yuzelli.secondhand.bean;

import java.io.Serializable;

/**
 * Created by 51644 on 2017/5/20.
 */

public class Study implements Serializable {

    private String content;
    private String phone;

    public Study(String content, String phone) {
        this.content = content;
        this.phone = phone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
