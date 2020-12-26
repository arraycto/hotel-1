package cn.ajiehome.management.user.service;

import cn.ajiehome.management.user.entity.bo.UserRegisterBO;

/**
 * @author Jie
 */
public interface IUserService {
    /**
     * 用户注册
     * @param userRegisterBO 用户注册信息
     * @return 注册结果
     */
    Boolean registerUser(UserRegisterBO userRegisterBO);
}
