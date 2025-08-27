package com.gugusb.hwics.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private HttpSession session;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session2 = request.getSession(false);
//        Cookie[] cookies = request.getCookies();
//        System.out.println(cookies.length);
//        System.out.println(cookies[0].getValue());
//        System.out.println(cookies[1].getValue());
//        System.out.println(cookies[2].getValue());
        if (session2 == null || session2.getAttribute("currentUser") == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("{\"code\": 401, \"message\": \"Please Login Firstly\"}");
            return false;
        }
        return true;
    }
}