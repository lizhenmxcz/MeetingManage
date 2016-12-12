package com.leezy.meet.service;

import com.leezy.meet.model.Meeting;
import com.leezy.meet.model.Participate;
import com.leezy.meet.model.User;

import java.util.Date;
import java.util.List;

/**
 * Created by lizhen on 2016/12/8.
 */
public interface UserService {
        List<User> userList();
        User getUserByPhoneOrEmail(String emailOrPhone, Short state);
        User getUserById(Long userId);
        User login(String username, String userpwd);
        User adminLogin(String username, String userpwd);
        Boolean updateUser(User user);
        Boolean delUser(Long id);
        Boolean addUser(User user);
        List<Meeting> getNewMeetings(Date nowtime);


}
