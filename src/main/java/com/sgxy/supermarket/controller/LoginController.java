package com.sgxy.supermarket.controller;

import com.sgxy.supermarket.entity.User;
import com.sgxy.supermarket.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping("toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("login")
    @ResponseBody
    public JSONObject login(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        JSONObject data = new JSONObject();
        User loginUser = userService.userLogin(userName, password);
        if (loginUser != null) {
            if (loginUser.getState() != 0) {
                session.setAttribute("loginUser", loginUser);

                System.out.println("用户：" + loginUser.getUserName() + " 登录成功！");

                data.put("userName", loginUser.getUserName());
                data.put("code", 200);
                data.put("msg", "成功登录");

            } else {
                data.put("code", 500);
                data.put("msg", "账户被冻结，请联系管理人员");
            }
        } else {
            data.put("code", 500);
            data.put("msg", "用户名或者密码错误，请重新登录");
        }
        return data;
    }

    @RequestMapping("toWelcome")
    public String toWelcome() {
        return "welcome";
    }

    @RequestMapping("loginOut")
    public String loginOut(HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        if (user != null) {
            session.removeAttribute("loginUser");
            System.out.println("用户：" + user.getUserName() + " 注销成功！");
        }
        return "redirect:toLogin";
    }

}
