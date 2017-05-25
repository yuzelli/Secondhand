package com.example.yuzelli.secondhand.bean;

import java.io.Serializable;

/**
 * Created by 51644 on 2017/5/20.
 * 用户实体类
 */

public class UserInfo implements Serializable {
    private String name;
    private String phone;
    private String pass;
    private String content;

    private String niaji;
    private String zhuangye;

    public String getNiaji() {
        return niaji;
    }

    public void setNiaji(String niaji) {
        this.niaji = niaji;
    }

    public String getZhuangye() {
        return zhuangye;
    }

    public void setZhuangye(String zhuangye) {
        this.zhuangye = zhuangye;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
