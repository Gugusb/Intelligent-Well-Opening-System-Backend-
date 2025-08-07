package com.gugusb.hwics.mapper;

import com.gugusb.hwics.pojo.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends CrudRepository<User, Integer> {

}
