package cn.ajiehome.management.user.controller;

import cn.ajiehome.management.user.entity.bo.UserRegisterBO;
import cn.ajiehome.management.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Jie
 * @Date: 2020/12/24
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private final static String HOME = "home";
    @Autowired
    private IUserService userService;
    @PostMapping("/register")
    public String register(@RequestBody UserRegisterBO userRegisterBO, Model model) {
        model.addAttribute("test","测试");
        Boolean aBoolean = userService.registerUser(userRegisterBO);
        return HOME;
    }
}
