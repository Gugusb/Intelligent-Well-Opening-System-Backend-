package com.gugusb.hwics.controller;

import com.gugusb.hwics.pojo.User;
import com.gugusb.hwics.pojo.dto.UserDTO;
import com.gugusb.hwics.service.Interface.IUserService;
import com.gugusb.hwics.utils.MessageRespnser;
import org.hibernate.annotations.DialectOverride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    //Add
    @PostMapping("/adduser")
    public MessageRespnser<User> add(@RequestBody UserDTO user) {
        User newUser = userService.add(user);
        return MessageRespnser.success(newUser);
    }

    //Find
    @GetMapping("/getuser/{userId}")
    public MessageRespnser<User> getUserById(@PathVariable int userId) {
        User checkUser = userService.getUser(userId);
        return MessageRespnser.success(checkUser);
    }

    //Add
    @PutMapping("/edituser")
    public MessageRespnser<User> edit(@RequestBody UserDTO user) {
        System.out.println("qaq");
        User newUser = userService.edit(user);
        return MessageRespnser.success(newUser);
    }

    //Delate
    @DeleteMapping("/deleteuser/{userId}")
    public MessageRespnser<User> delete(@PathVariable int userId) {
        userService.deleteUser(userId);
        return MessageRespnser.success(null);
    }
}
