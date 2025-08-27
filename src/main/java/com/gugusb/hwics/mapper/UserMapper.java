package com.gugusb.hwics.mapper;

import com.gugusb.hwics.pojo.DFP1;
import com.gugusb.hwics.pojo.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserMapper extends CrudRepository<User, Integer> {
    Optional<User> findFirstByOrderByUserNameDesc();
    Optional<User> findByUserName(String userName);
}
