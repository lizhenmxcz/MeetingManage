package com.leezy.meet.service;

import com.leezy.meet.model.User;

import java.util.List;

/**
 * Created by lizhen on 2016/12/8.
 */
public interface UserService {
        List<User> getAllUser();
        User getUserByPhoneOrEmail(String emailOrPhone, Short state);
        User getUserById(Long userId);
        User login(String username, String userpwd);
}
