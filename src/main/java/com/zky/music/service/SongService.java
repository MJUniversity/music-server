package com.zky.music.service;

import com.zky.music.pojo.Song;
import java.util.List;

/**
 * 歌曲service接口
 */
public interface SongService {
    /**
     *增加
     */
    public boolean insert(Song song);

    /**
     *修改
     */
    public boolean update(Song song);

    /**
     * 删除
     */
    public boolean delete(Integer id);

    /**
     * 根据主键查询整个对象
     */
    public Song selectByPrimaryKey(Integer id);

    /**
     * 查询所有歌曲
     */
    public List<Song> allSong();

    /**
     * 根据歌名精确查询列表
     */
    public List<Song> songOfName(String name);

    /**
     * 根据歌名模糊查询列表
     */
    public List<Song> likeSongOfName(String name);

    /**
     * 根据歌手id查询
     */
    public List<Song> songOfSingerId(Integer singerId);
}
