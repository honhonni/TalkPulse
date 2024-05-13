package cn.edu.ncu.talkpulse.controller;

import cn.edu.ncu.talkpulse.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    @Autowired
    private IUserService userService;
}
