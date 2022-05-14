package com.zky.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.zky.music.service.AdminService;
import com.zky.music.utils.Consts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@Api(tags = "登录接口")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 判断是否登录成功
     */
    @ApiOperation(value = "登录")
    @RequestMapping(value = "/admin/login/status",method = RequestMethod.POST)
    public Object loginStatus(HttpServletRequest request, HttpSession session){
        JSONObject jsonObject = new JSONObject();
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        boolean flag = adminService.verifyPassword(name,password);
        if(flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"登录成功");
            session.setAttribute(Consts.NAME,name);
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"用户名或密码错误");
        return jsonObject;
    }

    /**
     * 打包项目接口
     */
    @ApiOperation(value = "后台接口")
    @GetMapping(value = "/manage")
    public ModelAndView index() {
        return new ModelAndView("/src/main/resources/static/screenIndex/index.html");
    }
}
