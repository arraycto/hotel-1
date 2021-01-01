package cn.ajiehome.management.user.controller;

import cn.ajiehome.common.annotations.AllowToken;
import cn.ajiehome.common.exception.entity.bo.ResultBO;
import cn.ajiehome.management.user.entity.bo.*;
import cn.ajiehome.management.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

/**
 * @Author: Jie
 * @Date: 2020/12/29
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public ResultBO<String> login(@RequestBody UserLoginBO userLoginBO){
        return userService.userLogin(userLoginBO);
    }
    @PostMapping("/register")
    public ResultBO<String> register(@RequestBody UserRegisterBO userRegisterBO){
        return userService.userRegister(userRegisterBO);
    }

    /**
     * 查询所有空闲房间
     * @return 结果
     */
    @GetMapping("/select/room/talk/{roomNumber}")
    public ResultBO<Object> selectIdleRoom(@PathVariable Long roomNumber){
        return userService.selectIdleRoom(roomNumber);
    }

    @GetMapping("/select/room/talk")
    public ResultBO<Object> selectIdleRoomAll(){
        return userService.selectIdleRoom(null);
    }

    @GetMapping("/select/orders")
    @AllowToken
    public ResultBO<Object> selectOrders(){
        return userService.selectOrders();
    }

    @GetMapping("/select/me")
    @AllowToken
    public ResultBO<Object> selectMe(){
        return userService.selectMe();
    }

    @PostMapping("/update/user/info")
    @AllowToken
    public ResultBO<String> updateUserInfo(@RequestBody UserInfoBO userInfoBO){
        return userService.updateUserInfo(userInfoBO);
    }
    @PostMapping("/post/talk")
    @AllowToken
    public ResultBO<String> postTalk(@RequestBody PostTalkBO postTalkBO){
        return userService.postTalk(postTalkBO);
    }
    @PostMapping("/check/out/room")
    @AllowToken
    public ResultBO<String> checkOutRoom(@RequestBody CheckOutRoomBO checkOutRoomBO){
        return userService.checkOutRoom(checkOutRoomBO);
    }
    @PostMapping("/create/orders")
    @AllowToken
    public ResultBO<String> createOrders(@RequestBody CreateOrdersBO createOrdersBO){
        return userService.createOrders(createOrdersBO);
    }
}