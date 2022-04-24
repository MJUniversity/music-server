package com.zky.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.zky.music.pojo.Consumer;
import com.zky.music.service.ConsumerService;
import com.zky.music.utils.Consts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.service.Tags;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 前端用户控制类
 */
@RestController
@RequestMapping("/consumer")
@Api(tags = "用户")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    /**
     * 添加前端用户
     */
    @ApiOperation(value = "添加前端用户")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addConsumer(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String username = request.getParameter("username").trim();     //账号
        String password = request.getParameter("password").trim();     //密码
        String sex = request.getParameter("sex").trim();               //性别
        String phoneNum = request.getParameter("phoneNum").trim();     //手机号
        String email = request.getParameter("email").trim();           //电子邮箱
        String birth = request.getParameter("birth").trim();           //生日
        String introduction = request.getParameter("introduction").trim();//签名
        String location = request.getParameter("location").trim();      //地区
        String avator = request.getParameter("avator").trim();          //头像地址

        if(username==null||username.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"用户名不能为空");
            return jsonObject;
        }

        Consumer consumer1 = consumerService.getByUsername(username);
        if(consumer1!=null){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"用户名已存在");
            return jsonObject;
        }

        if(password==null||password.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"密码不能为空");
            return jsonObject;
        }

        //把生日转换成Date格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = new Date();
        try {
            birthDate = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //保存到前端用户的对象中
        Consumer consumer = new Consumer();
        consumer.setUsername(username);
        consumer.setPassword(password);
        consumer.setSex(new Byte(sex));
        consumer.setPhoneNum(phoneNum);
        consumer.setEmail(email);
        consumer.setBirth(birthDate);
        consumer.setIntroduction(introduction);
        consumer.setLocation(location);
        consumer.setAvator(avator);
        boolean flag = consumerService.insert(consumer);
        if(flag){   //保存成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"添加成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"添加失败");
        return jsonObject;
    }

    /**
     * 修改前端用户
     */
    @ApiOperation(value = "修改前端用户")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateConsumer(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();          //主键
        String username = request.getParameter("username").trim();     //账号
        String password = request.getParameter("password").trim();     //密码
        String sex = request.getParameter("sex").trim();               //性别
        String phoneNum = request.getParameter("phoneNum").trim();     //手机号
        String email = request.getParameter("email").trim();           //电子邮箱
        String birth = request.getParameter("birth").trim();           //生日
        String introduction = request.getParameter("introduction").trim();//签名
        String location = request.getParameter("location").trim();      //地区

        if(username==null||username.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"用户名不能为空");
            return jsonObject;
        }
        if(password==null||password.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"密码不能为空");
            return jsonObject;
        }
        //把生日转换成Date格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = new Date();
        try {
            birthDate = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //保存到前端用户的对象中
        Consumer consumer = new Consumer();
        consumer.setId(Integer.parseInt(id));
        consumer.setUsername(username);
        consumer.setPassword(password);
        consumer.setSex(new Byte(sex));
        consumer.setPhoneNum(phoneNum);
        consumer.setEmail(email);
        consumer.setBirth(birthDate);
        consumer.setIntroduction(introduction);
        consumer.setLocation(location);
        boolean flag = consumerService.update(consumer);
        if(flag){   //保存成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"修改成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"修改失败");
        return jsonObject;
    }


    /**
     * 删除前端用户
     */
    @ApiOperation(value = "删除前端用户")
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteConsumer(HttpServletRequest request){
        String id = request.getParameter("id").trim();          //主键
        boolean flag = consumerService.delete(Integer.parseInt(id));
        return flag;
    }

    /**
     * 根据主键查询整个对象
     */
    @ApiOperation(value = "根据主键查询整个对象")
    @RequestMapping(value = "/selectByPrimaryKey",method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest request){
        String id = request.getParameter("id").trim();          //主键
        return consumerService.selectByPrimaryKey(Integer.parseInt(id));
    }

    /**
     * 查询所有前端用户
     */
    @ApiOperation(value = "查询所有前端用户")
    @RequestMapping(value = "/allConsumer",method = RequestMethod.GET)
    public Object allConsumer(HttpServletRequest request){
        return consumerService.allConsumer();
    }

    /**
     * 更新前端用户图片
     */
    @ApiOperation(value = "更新前端用户图片")
    @RequestMapping(value = "/updateConsumerPic",method = RequestMethod.POST)
    public Object updateConsumerPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id")int id){
        JSONObject jsonObject = new JSONObject();
        if(avatorFile.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"文件上传失败");
            return jsonObject;
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName = System.currentTimeMillis()+avatorFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"userImages";
        //如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件地址
        String storeAvatorPath = "/userImages/"+fileName;
        try {
            avatorFile.transferTo(dest);
            Consumer consumer = new Consumer();
            consumer.setId(id);
            consumer.setAvator(storeAvatorPath);
            boolean flag = consumerService.update(consumer);
            if(flag){
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"上传成功");
                jsonObject.put("avator",storeAvatorPath);
                return jsonObject;
            }
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"上传失败");
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"上传失败"+e.getMessage());
        }finally {
            return jsonObject;
        }
    }

    /**
     * 前端用户登录
     */
    @ApiOperation(value = "前端用户登录")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Object login(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String username = request.getParameter("username").trim();     //账号
        String password = request.getParameter("password").trim();     //密码
        if(username==null||username.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"用户名不能为空");
            return jsonObject;
        }
        if(password==null||password.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"密码不能为空");
            return jsonObject;
        }

        //保存到前端用户的对象中
        Consumer consumer = new Consumer();
        consumer.setUsername(username);
        consumer.setPassword(password);
        boolean flag = consumerService.verifyPassword(username,password);
        if(flag){   //验证成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"登录成功");
            jsonObject.put("userMsg",consumerService.getByUsername(username));
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"用户名或密码错误");
        return jsonObject;
    }
}






















