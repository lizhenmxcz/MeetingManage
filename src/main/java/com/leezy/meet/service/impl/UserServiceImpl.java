package com.leezy.meet.service.impl;

import com.leezy.meet.dao.MeetingDao;
import com.leezy.meet.dao.UserDao;
import com.leezy.meet.model.Meeting;
import com.leezy.meet.model.User;
import com.leezy.meet.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by lizhen on 2016/12/8.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;
    @Resource
    private MeetingDao meetingDao;

    public User getUserById(Long userId) {
        return userDao.selectUserById(userId);
    }

    public User getUserByPhoneOrEmail(String emailOrPhone, Short state) {
        return userDao.selectUserByPhoneOrEmail(emailOrPhone,state);
    }

    public List<User> userList() {
        return userDao.userList();
    }
    public User login(String username, String pwd){
        return userDao.login(username,pwd);
    }
    public User adminLogin(String username, String pwd){
        return userDao.adminLogin(username,pwd);
    }
    public Boolean updateUser(User user){
        userDao.updateUser(user);
        return true;
    }
    public Boolean delUser(Long id){
        userDao.delUser(id);
        return true;
    }
    public Boolean addUser(User user){
        userDao.addUser(user);
        return true;
    }
    public List<Meeting> getNewMeetings(Date nowtime) {
        return meetingDao.getNewMeetings(nowtime);
    }
}
