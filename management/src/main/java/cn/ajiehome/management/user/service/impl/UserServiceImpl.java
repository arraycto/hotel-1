package cn.ajiehome.management.user.service.impl;

import cn.ajiehome.common.utils.SnowFlake;
import cn.ajiehome.management.user.entity.User;
import cn.ajiehome.management.user.entity.bo.UserRegisterBO;
import cn.ajiehome.management.user.mapper.UserMapper;
import cn.ajiehome.management.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author: Jie
 * @Date: 2020/12/24
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SnowFlake snowFlake;

    @Override
    public Boolean registerUser(UserRegisterBO userRegisterBO) {
        User user = new User();
        user.setNumber(snowFlake.nextId());
        user.setUsername(userRegisterBO.getUsername());
        user.setPassword(userRegisterBO.getPassword());
        user.setSex(userRegisterBO.getSex());
        user.setCreateDate(System.currentTimeMillis());
        System.out.println(user);
        int insert = userMapper.insert(user);
        return insert > 0;
    }
}
