package com.zky.music.service;

import com.zky.music.pojo.SongList;
import java.util.List;

/**
 * 歌单service接口
 */
public interface SongListService {
    /**
     *增加
     */
    public boolean insert(SongList songList);

    /**
     *修改
     */
    public boolean update(SongList songList);

    /**
     * 删除
     */
    public boolean delete(Integer id);

    /**
     * 根据主键查询整个对象
     */
    public SongList selectByPrimaryKey(Integer id);

    /**
     * 查询所有歌单
     */
    public List<SongList> allSongList();

    /**
     * 根据标题精确查询歌单列表
     */
    public List<SongList> songListOfTitle(String title);

    /**
     * 根据标题模糊查询歌单列表
     */
    public List<SongList> likeTitle(String title);

    /**
     * 根据风格模糊查询歌单列表
     */
    public List<SongList> likeStyle(String style);

}
