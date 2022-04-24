package com.zky.music.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
/**
 * 收藏
 */
public class Collect implements Serializable {
    private Integer id;     //主键
    private Integer userId; //用户id
    private Byte type;      //收藏类型（0歌曲1歌单）
    private Integer songId; //歌曲id
    private Integer songListId; //歌单id
    private Date createTime;    //收藏时间
}
