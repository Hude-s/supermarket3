package com.sgxy.supermarket.interceptor;

import com.sgxy.supermarket.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // System.out.println("uri: " + request.getRequestURI());
        HttpSession session = request.getSession();
        // 登录请求放行
        if (request.getRequestURI().contains("login")) {
            return true;
        }
        if (request.getRequestURI().contains("toLogin")) {
            return true;
        }
        if(request.getRequestURI().contains("welcome")){
            return false;
        }

        User loginUser = (User) session.getAttribute("loginUser");
        // 如果用户已登陆也放行
        if (null != loginUser) {
            return true;
        }

        System.out.println("\"request.getContextPath()\" = " + request.getContextPath());
        System.out.println("\"request.getRequestURI()\" = " + request.getRequestURI());

        request.getRequestDispatcher("/error.html").forward(request, response);
        // 用户没有登陆跳转到登陆页面
//        response.sendRedirect( "/error.html");

        return false;
    }
}
