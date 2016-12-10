package com.leezy.meet.controller;

import com.leezy.meet.model.Meeting;
import com.leezy.meet.model.User;
import com.leezy.meet.service.MeetingService;
import com.leezy.meet.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    @Resource
    private MeetingService meetingService;

    @RequestMapping("/login")
    public String login(HttpServletRequest request, User user){
        log.info(user.getUserName()+user.getUserPwd());
        User resultUser = userService.login(user.getUserName(),user.getUserPwd());
        if (resultUser == null) {
            request.setAttribute("user", resultUser);
            request.setAttribute("errorMsg", "用户名或密码错误");
            return "redirect:/index.jsp";
        }else {
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", resultUser);
            return "user/index";
        }
    }
    @RequestMapping("/sign")
    public String sign(User user){
        userService.addUser(user);
        return "redirect:/index.jsp";
    }
    @RequestMapping("/getNewMeetings")
    public String getNewMeetings(ModelMap model) throws ParseException {
        Date nowtime = new Date();
        //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Date time = df.parse(df.format(nowtime));
        //log.info(df.format(nowtime));
        List<Meeting> meetings = meetingService.getNewMeetings(nowtime);
        model.addAttribute("newMeetings", meetings);
        return "user/newMeetings";
    }
}