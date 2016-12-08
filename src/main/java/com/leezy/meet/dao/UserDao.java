package com.leezy.meet.dao;

import com.leezy.meet.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lizhen on 2016/12/8.
 */

@Repository
public interface UserDao {

    User selectUserById(@Param("userId") Long userId);

    User selectUserByPhoneOrEmail(@Param("emailOrPhone") String emailOrPhone, @Param("state") Short state);

    User login(@Param("userName") String userName, @Param("userPwd") String userPwd);

    List<User> selectAllUser();
}