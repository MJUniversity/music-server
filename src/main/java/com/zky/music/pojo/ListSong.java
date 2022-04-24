package com.zky.music.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 歌单里面的歌曲
 */
@Data
public class ListSong implements Serializable {

    private Integer id;     //主键

    private Integer songId; //歌曲id

    private Integer songListId; //歌单id

}
