package com.gugusb.hwics.controller;

import com.gugusb.hwics.pojo.User;
import com.gugusb.hwics.pojo.dto.UserDTO;
import com.gugusb.hwics.service.Interface.IUserService;
import com.gugusb.hwics.utils.MessageRespnser;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.hibernate.annotations.DialectOverride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @Autowired
    private HttpSession session;

    //Add
    @PostMapping("/adduser")
    public MessageRespnser<String> add(@RequestBody UserDTO user) {
        Boolean isSucceed = userService.add(user);
        if(isSucceed) return MessageRespnser.success(null);
        return MessageRespnser.unsuccess(null);
    }

    @GetMapping("/islogin")
    public MessageRespnser<String> isLogin() {
        // 检查用户是否已登录
        String currentUser = (String) session.getAttribute("currentUser");
        if(currentUser == null) {
            return MessageRespnser.unsuccess("User did not login");
        }
        return MessageRespnser.success("User has logged in");
    }

    //Login
    @PostMapping("/login")
    public MessageRespnser<String> login(@RequestBody UserDTO user, HttpServletResponse response) {
        Boolean isSucceed = userService.login(user);
        if(isSucceed) {
            // 创建session并存储用户信息
            session.setAttribute("currentUser", user.getUserName());
            session.setMaxInactiveInterval(30 * 60); // 30分钟超时

            // 可以额外设置一个安全cookie作为辅助
            Cookie sessionCookie = new Cookie("SESSION_ID", session.getId());
            sessionCookie.setHttpOnly(true);
            sessionCookie.setSecure(true);
            sessionCookie.setPath("/");
            sessionCookie.setMaxAge(30 * 60);
            response.addCookie(sessionCookie);
            System.out.println("创建Cookie: " + response.getHeader("Set-Cookie"));
        }

        if(isSucceed)return MessageRespnser.success("登陆成功");
        return MessageRespnser.unsuccess("登陆失败");
    }

    //Find
    @GetMapping("/getuser/{userId}")
    public MessageRespnser<String> getUserById(@PathVariable int userId) {

        User checkUser = userService.getUser(userId);
        return MessageRespnser.success("查找成功");
    }

    //Add
    @PutMapping("/edituser")
    public MessageRespnser<String> edit(@RequestBody UserDTO user) {
        User newUser = userService.edit(user);
        return MessageRespnser.success("编辑成功");
    }

    //Delate
    @DeleteMapping("/deleteuser/{userId}")
    public MessageRespnser<String> delete(@PathVariable int userId) {
        userService.deleteUser(userId);
        return MessageRespnser.success("移除成功");
    }

    @GetMapping("/checkSession")
    public MessageRespnser<String> checkSession(HttpServletRequest request) {
        HttpSession session2 = request.getSession(false);

        System.out.println("session-auto:" + session.getId());
        System.out.println("session-req:" + session2.getId());

        if (session != null && session.getAttribute("currentUser") != null) {
            return MessageRespnser.success("会话有效");
        }
        return MessageRespnser.unsuccess("会话无效");
    }
}
