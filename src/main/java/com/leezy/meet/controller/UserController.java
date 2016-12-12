package com.leezy.meet.controller;

import com.leezy.meet.model.Meeting;
import com.leezy.meet.model.Participate;
import com.leezy.meet.model.User;
import com.leezy.meet.service.MeetingService;
import com.leezy.meet.service.ParticipateService;
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
import java.util.ArrayList;
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
    @Resource
    private ParticipateService participateService;

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
    public String getNewMeetings(ModelMap model,HttpServletRequest request) throws ParseException {
        Date nowtime = new Date();
        List<Meeting> meetings = meetingService.getNewMeetings(nowtime);
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("currentUser");
        List<Participate> list = participateService.getParticipated(user);
        List<Meeting> ms = new ArrayList<Meeting>();
        for (int i = 0; i < meetings.size(); i++) {
            int k=0;
            for (int j = 0; j < list.size(); j++) {
                Meeting m = list.get(j).getMeeting();
                if(meetings.get(i).getId()==m.getId()){
                    k=1;
                    break;
                }
            }
            if(k==0){
                ms.add(meetings.get(i));
            }
        }
        model.addAttribute("newMeetings", ms);
        return "user/newMeetings";
    }
    @RequestMapping("/addParticipatePage")
    public String addparticipatePage(Long id,ModelMap model){
        Meeting meeting = meetingService.getMeetingById(id);
        model.addAttribute("meeting",meeting);
        return "user/addParticipate";
    }
    @RequestMapping("/addParticipate")
    public String addParticipate(Long meeting_id,Participate participate,ModelMap model,HttpServletRequest request) throws ParseException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("currentUser");
        log.info(user.getUserName());
        Meeting meeting = meetingService.getMeetingById(meeting_id);
        participate.setUser(user);
        participate.setMeeting(meeting);
        participateService.addParticipate(participate);
        return getNewMeetings(model,request);
    }
    @RequestMapping("/getParticipated")
    public String getParticipated(ModelMap model,HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("currentUser");
        List<Participate> list = participateService.getParticipated(user);
        List<Participate> expireList = new ArrayList<Participate>();
        List<Participate> soonList = new ArrayList<Participate>();
        for (int i = 0; i < list.size(); i++) {
            Participate p = list.get(i);
            if(p.getMeeting().getMeetStartTime().before(new Date())){
                expireList.add(p);
            }
            else soonList.add(p);

        }
        model.addAttribute("soonList",soonList);
        model.addAttribute("expireList",expireList);
        return "user/participatedList";
    }
}