package com.zky.music.pojo;

import lombok.Data;
import java.util.Date;

/**
 * 前端用户
 */
@Data
public class Consumer {
    /*主键*/
    private Integer id;
    /*账号*/
    private String username;
    /*密码*/
    private String password;
    /*性别*/
    private Byte sex;
    /*手机号*/
    private String phoneNum;
    /*电子邮箱*/
    private String email;
    /*生日*/
    private Date birth;
    /*签名*/
    private String introduction;
    /*地区*/
    private String location;
    /*头像*/
    private String avator;
    /*创建时间*/
    private Date createTime;
    /*更新时间*/
    private Date updateTime;
}
