package cn.ajiehome.management.admin.service.impl;

import cn.ajiehome.common.enums.CodeType;
import cn.ajiehome.common.exception.ApplicationException;
import cn.ajiehome.common.exception.entity.bo.ResultBO;
import cn.ajiehome.common.jwt.JwtUtils;
import cn.ajiehome.common.jwt.bo.JwtBeanBO;
import cn.ajiehome.management.admin.entity.Admin;
import cn.ajiehome.management.admin.entity.bo.AdminLoginBO;
import cn.ajiehome.management.admin.entity.bo.AdminOrderInfoBO;
import cn.ajiehome.management.admin.entity.bo.AdminRoomBO;
import cn.ajiehome.management.admin.entity.vo.AdminUpdateRoomVO;
import cn.ajiehome.management.user.entity.bo.UserUpdateBO;
import cn.ajiehome.management.user.entity.vo.UserInfoVO;
import cn.ajiehome.management.admin.mapper.AdminMapper;
import cn.ajiehome.management.admin.service.IAdminService;
import cn.ajiehome.management.user.entity.User;
import cn.ajiehome.management.user.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.mail.imap.protocol.ID;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Jie
 * @Date: 2020/12/26
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResultBO<String> adminLogin(AdminLoginBO adminLoginBO) {
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getUsername, adminLoginBO.getUsername()).eq(Admin::getPassword, adminLoginBO.getPassword());
        Admin admin = baseMapper.selectOne(queryWrapper);
        if (admin != null) {
            JwtBeanBO jwtBeanBO = new JwtBeanBO();
            jwtBeanBO.setUserName(adminLoginBO.getUsername());
            jwtBeanBO.setId(admin.getNumber());
            return ResultBO.newResultBO(CodeType.OK, jwtUtils.getToken(24 * 60 * 60 * 1000, jwtBeanBO));
        } else {
            throw new ApplicationException(CodeType.DATE_SEARCH_DEFEAT, "查无此用户");
        }
    }

    @Override
    public List<UserInfoVO> selectAll() {
        checkAdmin();
        List<User> users = userMapper.selectList(null);
        return users.stream().map(user -> {
            UserInfoVO userInfoVO = new UserInfoVO();
            BeanUtils.copyProperties(user, userInfoVO);
            System.out.println(userInfoVO);
            return userInfoVO;
        }).collect(Collectors.toList());
    }

    @Override
    public ResultBO<String> updateOneUser(UserUpdateBO userUpdateBO) {
        checkAdmin();
        User user = new User();
        BeanUtils.copyProperties(userUpdateBO, user);
        LambdaQueryWrapper<User> updateWrapper = new LambdaQueryWrapper<>();
        updateWrapper.eq(User::getNumber, user.getNumber());
        int update = userMapper.update(user, updateWrapper);
        if (update > 0) {
            return ResultBO.newResultBO(CodeType.OK, "修改成功");
        } else {
            throw new ApplicationException(CodeType.DATE_SEARCH_DEFEAT, "修改失败");
        }
    }

    @Override
    public ResultBO<String> deleteUserOne(UserUpdateBO userUpdateBO) {
        checkAdmin();
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getNumber, userUpdateBO.getNumber());
        int delete = userMapper.delete(wrapper);
        if (delete > 0) {
            return ResultBO.newResultBO(CodeType.OK, "删除成功");
        } else {
            throw new ApplicationException(CodeType.DATE_SEARCH_DEFEAT, "删除失败");
        }
    }

    @Override
    public ResultBO<Object> selectOrdersAll() {
       checkAdmin();

        List<AdminOrderInfoBO> adminOrderInfoBOS = baseMapper.selectOrdersAll();
        if (adminOrderInfoBOS==null){
            throw new ApplicationException(CodeType.SERVICE_ERROR, "查询错误");
        }else {
            return ResultBO.newResultBO(CodeType.OK,adminOrderInfoBOS);
        }

    }

    @Override
    public ResultBO<Object> selectRoomAll() {
        checkAdmin();
        List<AdminRoomBO> adminRoomBOS = baseMapper.selectRoomAll();
        if (adminRoomBOS==null){
            throw new ApplicationException(CodeType.SERVICE_ERROR, "查询错误");
        }else {
            return ResultBO.newResultBO(CodeType.OK, adminRoomBOS);
        }
    }

    @Override
    public ResultBO<String> updateRoomInfo(AdminUpdateRoomVO adminUpdateRoomVO) {
        checkAdmin();
        Integer integer = baseMapper.updateRoomInfo(adminUpdateRoomVO);
        if (integer<1){
            throw new ApplicationException(CodeType.SERVICE_ERROR, "修改失败");
        }else {
            return ResultBO.newResultBO(CodeType.OK, "修改成功");
        }
    }

    public void checkAdmin() {
        JwtBeanBO jwtBeanBO = JwtUtils.getJwtBeanBO();
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getNumber, jwtBeanBO.getId());
        Admin admin = baseMapper.selectOne(queryWrapper);
        if (admin == null || admin.getRank() < 1) {
            throw new ApplicationException(CodeType.SERVICE_IO_ERROR, "您没有权限");
        }
    }
}
