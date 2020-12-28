package cn.ajiehome.management.admin.service;

import cn.ajiehome.common.exception.entity.bo.ResultBO;
import cn.ajiehome.management.admin.entity.bo.AdminLoginBO;
import cn.ajiehome.management.admin.entity.bo.AdminOrderInfoBO;
import cn.ajiehome.management.user.entity.bo.UserUpdateBO;
import cn.ajiehome.management.user.entity.vo.UserInfoVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jie
 */
public interface IAdminService {
    /**
     * 管理员登录
     * @param adminLoginBO 登录信息
     * @return token
     */
    ResultBO<String> adminLogin(AdminLoginBO adminLoginBO);

    /**
     * 查询所有的用户信息
     * @return 结果
     */
    List<UserInfoVO> selectAll();

    /**
     * 修改用户信息
     * @param userUpdateBO 用户信息
     * @return 结果
     */
    ResultBO<String> updateOneUser(UserUpdateBO userUpdateBO);

    /**
     * 删除用户
     * @param userUpdateBO 用户信息
     * @return 结果
     */
    ResultBO<String> deleteUserOne(UserUpdateBO userUpdateBO);

    ResultBO<Object> selectOrdersAll();
}
