package com.gugusb.hwics.service.Interface;

import com.gugusb.hwics.pojo.User;
import com.gugusb.hwics.pojo.dto.UserDTO;

public interface IUserService {
    Boolean add(UserDTO user);

    User getUser(int userId);

    User edit(UserDTO user);

    void deleteUser(int userId);


    boolean login(UserDTO user);
}
