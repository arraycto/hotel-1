package cn.ajiehome.management.admin.controller;

import cn.ajiehome.common.annotations.AllowToken;
import cn.ajiehome.common.exception.entity.bo.ResultBO;
import cn.ajiehome.management.admin.entity.bo.AdminLoginBO;
import cn.ajiehome.management.admin.entity.vo.AdminUpdateRoomVO;
import cn.ajiehome.management.user.entity.bo.UserUpdateBO;
import cn.ajiehome.management.user.entity.vo.UserInfoVO;
import cn.ajiehome.management.admin.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Jie
 * @Date: 2020/12/26
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @PostMapping("/login")
    @CrossOrigin
    public ResultBO adminLogin(@RequestBody AdminLoginBO adminLoginBO) {
        return adminService.adminLogin(adminLoginBO);
    }

    @GetMapping("/select/all")
    @AllowToken
    @CrossOrigin
    public List<UserInfoVO> selectAllUser() {
        return adminService.selectAll();
    }

    @PostMapping("/update/user/info")
    @CrossOrigin
    @AllowToken
    public ResultBO<String> updateOneUser(@RequestBody UserUpdateBO userUpdateBO) {
        return adminService.updateOneUser(userUpdateBO);
    }

    @PostMapping("/delete/user/info")
    @CrossOrigin
    @AllowToken
    public ResultBO<String> deleteOneUser(@RequestBody UserUpdateBO userUpdateBO) {
        return adminService.deleteUserOne(userUpdateBO);
    }

    @GetMapping("/select/orders/all")
    @CrossOrigin
    @AllowToken
    public ResultBO<Object> selectOrdersAll() {
        return adminService.selectOrdersAll();
    }

    @GetMapping("/select/room/all")
    @CrossOrigin
    @AllowToken
    public ResultBO<Object> selectRoomAll() {
        return adminService.selectRoomAll();
    }
    @PostMapping("/update/room/info")
    @CrossOrigin
    @AllowToken
    public ResultBO<String> updateRoomInfo(@RequestBody AdminUpdateRoomVO adminUpdateRoomVO) {
        System.out.println(adminUpdateRoomVO);
        return adminService.updateRoomInfo(adminUpdateRoomVO);
    }
}
