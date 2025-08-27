package com.gugusb.hwics.service;

import com.gugusb.hwics.mapper.UserMapper;
import com.gugusb.hwics.pojo.User;
import com.gugusb.hwics.pojo.dto.UserDTO;
import com.gugusb.hwics.service.Interface.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Boolean add(UserDTO user) {
        User userPojo = new User();
        userPojo.setUserName(user.getUserName());
        userPojo.setPassword(user.getPassword());
        Optional<User> opUser = userMapper.findByUserName(user.getUserName());
        if(opUser.isEmpty()) {
            userMapper.save(userPojo);
            return true;
        }
        return false;
    }

    @Override
    public User getUser(int userId) {
        return userMapper.findById(userId).orElseThrow(() -> {
            throw new IllegalArgumentException("ID not found");
        });
    }

    @Override
    public User edit(UserDTO user) {
        User userPojo = new User();
        BeanUtils.copyProperties(user, userPojo);
        return userMapper.save(userPojo);
    }

    @Override
    public void deleteUser(int userId) {
        userMapper.deleteById(userId);
    }

    @Override
    public boolean login(UserDTO user) {
        String userName = user.getUserName();
        String password = user.getPassword();
        Optional<User> opUser = userMapper.findByUserName(userName);
        if(opUser.isEmpty()) {return false;}
        User fdUser = opUser.get();
        return userName.equals(fdUser.getUserName()) && password.equals(fdUser.getPassword());
    }
}
