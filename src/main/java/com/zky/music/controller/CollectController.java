package com.zky.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.zky.music.pojo.Collect;
import com.zky.music.service.CollectService;
import com.zky.music.utils.Consts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

/**
 * 收藏控制类
 */
@RestController
@RequestMapping("/collect")
@Api(tags = "收藏")
public class CollectController {

    @Autowired
    private CollectService CollectService;

    /**
     * 添加收藏
     */
    @ApiOperation(value = "添加收藏")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addCollect(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String userId = request.getParameter("userId");           //用户id
        String type = request.getParameter("type");               //收藏类型（0歌曲1歌单）
        String songId = request.getParameter("songId");           //歌曲id
        if(songId==null||songId.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"收藏歌曲为空");
            return jsonObject;
        }
        if(CollectService.existSongId(Integer.parseInt(userId),Integer.parseInt(songId))){
            jsonObject.put(Consts.CODE,2);
            jsonObject.put(Consts.MSG,"已收藏");
            return jsonObject;
        }

        //保存到收藏的对象中
        Collect Collect = new Collect();
        Collect.setUserId(Integer.parseInt(userId));
        Collect.setType(new Byte(type));
        Collect.setSongId(Integer.parseInt(songId));

        boolean flag = CollectService.insert(Collect);
        if(flag){   //保存成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"收藏成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"收藏失败");
        return jsonObject;
    }

    /**
     * 删除收藏
     */
    @ApiOperation(value = "取消收藏")
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteCollect(HttpServletRequest request){
        String userId = request.getParameter("userId");           //用户id
        String songId = request.getParameter("songId");           //歌曲id
        boolean flag = CollectService.deleteByUserIdSongId(Integer.parseInt(userId),Integer.parseInt(songId));
        return flag;
    }

    /**
     * 查询所有收藏
     */
    @ApiOperation(value = "查看所有收藏")
    @RequestMapping(value = "/allCollect",method = RequestMethod.GET)
    public Object allCollect(HttpServletRequest request){
        return CollectService.allCollect();
    }

    /**
     * 查询某个用户的收藏列表
     */
    @ApiOperation(value = "用户的收藏列表")
    @RequestMapping(value = "/collectOfUserId",method = RequestMethod.GET)
    public Object collectOfUserId(HttpServletRequest request){
        String userId = request.getParameter("userId");          //用户id
        return CollectService.collectOfUserId(Integer.parseInt(userId));
    }

}
