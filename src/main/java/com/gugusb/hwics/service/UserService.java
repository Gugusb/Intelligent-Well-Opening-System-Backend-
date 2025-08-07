package com.gugusb.hwics.service;

import com.gugusb.hwics.exception.ExceptionAdvice;
import com.gugusb.hwics.mapper.UserMapper;
import com.gugusb.hwics.pojo.User;
import com.gugusb.hwics.pojo.dto.UserDTO;
import com.gugusb.hwics.service.Interface.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService implements IUserService {

    @Autowired
    UserMapper userMapper;

    //测试定时方法
//    @Retryable(value = {DataAccessException.class},
//            maxAttempts = 3,
//            backoff = @Backoff(delay = 5000))
//    @Scheduled(fixedRateString = "${user.update.interval:10000}")
//    public void periodicUserUpdate() {
//        try {
//            User userPojo = new User();
//            userPojo.setUserName("newUser");
//            userMapper.save(userPojo);
//            System.out.println("addUser succeed");
//        } catch (Exception ex) {
//            Logger log = LoggerFactory.getLogger(ExceptionAdvice.class);
//            log.error("用户更新失败", ex);
//            throw ex; // 触发重试机制
//        }
//    }

    @Override
    public User add(UserDTO user) {
        User userPojo = new User();
        BeanUtils.copyProperties(user, userPojo);
        return userMapper.save(userPojo);
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
}
