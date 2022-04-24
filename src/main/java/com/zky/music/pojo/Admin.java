package com.zky.music.pojo;

import lombok.Data;

import java.io.Serializable;
@Data

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

}
