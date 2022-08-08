package com.sgxy.supermarket.controller;

import com.github.pagehelper.PageInfo;
import com.sgxy.supermarket.entity.User;
import com.sgxy.supermarket.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("userList")
    public JSONObject userList(HttpServletRequest request, HttpServletResponse response) {
        JSONObject data = new JSONObject();
        int page = Integer.parseInt(request.getParameter("page"));
        int size = Integer.parseInt(request.getParameter("limit"));
        String userName = request.getParameter("userName");
        List<User> users = userService.queryUserByName(page, size, userName);
        PageInfo pageInfo = new PageInfo(users);
        data.put("code", 0);
        data.put("msg", "");
        data.put("count", (int) pageInfo.getTotal());
        data.put("data", users);
        return data;
    }

    @RequestMapping("to_addUser")
    public String to_addUser() {
        return "addUser";
    }

    @ResponseBody
    @RequestMapping("addUser")
    public JSONObject addUser(HttpServletRequest request, HttpServletResponse response) {
        JSONObject data = new JSONObject();
        Integer count = 0;
        try {
            User user = (User) JSONObject.toBean(JSONObject.fromObject(request.getParameter("user")), User.class);
            count = userService.addUser(user);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (count > 0) {
                data.put("code", 200);
                data.put("msg", "");
            } else {
                data.put("code", 500);
                data.put("msg", "添加失败");
            }
        }
        return data;
    }


    @ResponseBody
    @RequestMapping("updateUser")
    public JSONObject updateUser(HttpServletRequest request, HttpServletResponse response) {
        JSONObject data = new JSONObject();
        Integer count = 0;
        try {
            User user = (User) JSONObject.toBean(JSONObject.fromObject(request.getParameter("user")), User.class);
            count = userService.updateUser(user);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (count > 0) {
                data.put("code", 200);
                data.put("msg", "");
            } else {
                data.put("code", 500);
                data.put("msg", "更新失败");
            }
        }
        return data;
    }


    @ResponseBody
    @RequestMapping("deleteUser")
    public JSONObject deleteUser(HttpServletRequest request, HttpServletResponse response) {
        JSONObject data = new JSONObject();
        Integer userId = Integer.parseInt(request.getParameter("userId"));
        Integer count = 0;
        try {
            count = userService.deleteUser(userId);
        } catch (DataAccessException e) {
            System.out.println(e);
        } finally {
            if (count > 0) {
                data.put("code", 200);
                data.put("msg", "");
            } else {
                data.put("code", 500);
                data.put("msg", "删除错误");
            }
        }
        return data;
    }

    @ResponseBody
    @RequestMapping("deleteUserBatch")
    public JSONObject deleteUserBatch(HttpServletRequest request, HttpServletResponse response) {
        JSONObject data = new JSONObject();
        String userIds = request.getParameter("userIds");
        boolean flag = false;
        try {
            flag = userService.deleteUserBatch(userIds);
        } catch (DataAccessException e) {
            System.out.println(e);
        } finally {
            if (flag) {
                data.put("code", 200);
                data.put("msg", "");
            } else {
                data.put("code", 500);
                data.put("msg", "批量删除错误");
            }
        }
        return data;
    }
}
