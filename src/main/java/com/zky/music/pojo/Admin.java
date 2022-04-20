package com.zky.music.pojo;

import java.io.Serializable;

/**
 * 管理员
 */
public class Admin implements Serializable {
    /*主键*/
    private Integer id;
    /*账号*/
    private String name;
    /*密码*/
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
