package com.leezy.meet.controller;

import com.leezy.meet.model.User;
import com.leezy.meet.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by lizhen on 2016/12/8.
 */

@Controller
@RequestMapping("/user")
public class UserController {

    private Logger log = Logger.getLogger(UserController.class);
    @Resource
    private UserService userService;

    @RequestMapping("/showUser")
    public String showUser(HttpServletRequest request, Model model){
        log.info("查询所有用户信息");
        List<User> userList = userService.getAllUser();
        model.addAttribute("userList",userList);
        return "showUser";
    }
    @RequestMapping("/login")
    public String login(HttpServletRequest request, User user){
        User resultUser = userService.login(user.getUserName(),user.getUserPwd());
        if (resultUser == null) {
            request.setAttribute("user", resultUser);
            request.setAttribute("errorMsg", "用户名或密码错误");
            return "login";
        }else {
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", resultUser);
            return "redirect:/main.jsp";
        }
    }
}