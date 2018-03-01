package ssm.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import constant.Constant;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import ssm.model.User;
import ssm.service.UserService;
import utils.FileUploadUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController<User> {

    @Autowired
    UserService userService;

    /**
     * 根据用户名密码登录,返回token和用户id
     * 登录成功，更新用户token
     *
     * @param tel      用户名
     * @param password 密码
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map login(String tel, String password, HttpServletResponse response) {


        User user = userService.getUserByPhone(tel, password);

        if (user == null) {
            return userService.errorRespMap(respMap, "账号密码错误");
        } else {
            long time = System.currentTimeMillis();
            user.setUpdate_time("" + time);
            userService.update(user);
            return userService.successRespMap(respMap, "登陆成功",user);
        }
    }

    /***
     * 注册
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map save(User user) {

        if (userService.getUserByPhoneNum(user.getTel()) != null) {
            System.out.println("用户存在--注册失败");
            return userService.errorRespMap(respMap, "用户已存在");
        }
        user.setName("暂无名");
        user.setDescription("这人很懒，没有简介");
        user.setHeadimg("file/download?filename=default.png&type=0");
        user.setCreate_time(System.currentTimeMillis() + "");
        user.setUpdate_time(System.currentTimeMillis() + "");
        userService.save(user);
        return userService.successRespMap(respMap,"注册成功",user);

    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "deleteuser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map delete(int id) {
        User user1 = userService.get(id);
        if (user1 == null) {
            System.out.println("失败");
            return userService.errorRespMap(respMap, "用户不存在");
        }
        userService.delete(id);
        User user2 = userService.get(id);
        if (user2 == null) {

            return userService.successRespMap(respMap, "删除成功", id);
        } else {
            return userService.errorRespMap(respMap, "删除失败");
        }

    }


    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateuser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map update(@ModelAttribute User user) {

        User user1 = userService.get(user.getId());
        if (user1 == null) {
            return userService.errorRespMap(respMap, "user not exist in db");
        }
        userService.update(user);
        return userService.successRespMap(respMap, "success", user);

    }


    /**
     * 获取全部用户
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "userlist", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Map getAllUser() {
        List<User> cs = userService.list();
        if (cs == null) {
            return userService.errorRespMap(respMap, "error");
        } else {
            return userService.successRespMap(respMap, "success", cs);
        }
    }


    /***
     * 重新设置密码
     * @param oldpsd
     * @param newpsd
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "resetpsd", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map resetpsd(@Param("tel") String tel,@Param("oldpsd") String oldpsd, @Param("newpsd") String newpsd) {

        User user1 = userService.getUserByPhoneNum(tel);
        if (user1 != null & user1.getPassword().equals(oldpsd)) {
            user1.setPassword(newpsd);
            userService.update(user1);
            user1 = userService.getUserByPhoneNum(tel);
            return userService.successRespMap(respMap, "修改成功", user1);
        } else {
            return userService.errorRespMap(respMap, "密码不正确");
        }
    }

    @ResponseBody
    @RequestMapping(value = "QueryUser",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public Map getUserById(@Param("userid")int userid){
        User user = userService.get(userid);
        if (user ==null){
            return userService.errorRespMap(respMap,"用户不存在");
        }
        return userService.successRespMap(respMap,"success",user);
    }

    @ResponseBody
    @RequestMapping(value = "updatemine", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map updatemine(@Param("name") String name, @Param("sex") String sex,@Param("description") String description, @Param("tel") String tel) {

        User user1 = userService.getUserByPhoneNum(tel);
        if (user1 != null ) {
            user1.setName(name);
            user1.setSex(sex);
            user1.setDescription(description);
            long time = System.currentTimeMillis();
            user1.setUpdate_time("" + time);
            userService.update(user1);
             user1 = userService.getUserByPhoneNum(tel);
            return userService.successRespMap(respMap, "修改成功", user1);
        } else {
            return userService.errorRespMap(respMap, "修改失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "QueryUserbyPhone", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map queryuserbyphone(@Param("tel") String tel) {

        User user1 = userService.getUserByPhoneNum(tel);
        if (user1 != null) {
            return userService.successRespMap(respMap, "查找成功", user1);
        } else {
            return userService.errorRespMap(respMap, "查找失败");
        }
    }
    /*/检查参数是否正确
    private boolean checkParams(User user) {

        boolean res = true;
        String name = user.getName();
        String tel = user.getTel();
        String password = user.getPassword();
        String token = user.getToken();
        String description = user.getDescription();
        String sex = user.getSex();

        res &= (name != null || !name.equals(""));
        res &= (sex.equals("男") || sex.equals("女") || sex.equals("") || sex == null);

        return res;

    }*/


}
