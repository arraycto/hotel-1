package cn.ajiehome.management.user.service.impl;

import cn.ajiehome.common.enums.CodeType;
import cn.ajiehome.common.exception.ApplicationException;
import cn.ajiehome.common.exception.entity.bo.ResultBO;
import cn.ajiehome.common.jwt.JwtUtils;
import cn.ajiehome.common.jwt.bo.JwtBeanBO;
import cn.ajiehome.common.utils.SnowFlake;
import cn.ajiehome.management.user.entity.Pay;
import cn.ajiehome.management.user.entity.Talk;
import cn.ajiehome.management.user.entity.User;
import cn.ajiehome.management.user.entity.bo.*;
import cn.ajiehome.management.user.entity.vo.RoomIdleVO;
import cn.ajiehome.management.user.entity.vo.UserInfoVO;
import cn.ajiehome.management.user.entity.vo.UserOrdersVO;
import cn.ajiehome.management.user.mapper.UserMapper;
import cn.ajiehome.management.user.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author: Jie
 * @Date: 2020/12/29
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private SnowFlake snowFlake;

    @Override
    public ResultBO<String> userLogin(UserLoginBO userLoginBO) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, userLoginBO.getUsername()).eq(User::getPassword, userLoginBO.getPassword());
        User user = baseMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new ApplicationException(CodeType.SERVICE_ERROR, "用户信息输入错误");
        } else {
            JwtBeanBO jwtBeanBO = new JwtBeanBO();
            jwtBeanBO.setId(user.getNumber());
            jwtBeanBO.setUserName(user.getUsername());
            return ResultBO.newResultBO(CodeType.OK, jwtUtils.getToken(24 * 60 * 60 * 1000, jwtBeanBO));
        }
    }

    @Override
    public ResultBO<String> userRegister(UserRegisterBO userRegisterBO) {
        User user = new User();
        BeanUtils.copyProperties(userRegisterBO, user);
        System.out.println(user + "-" + userRegisterBO);
        user.setNumber(snowFlake.nextId());
        user.setCreateDate(new Date(System.currentTimeMillis()));
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, userRegisterBO.getUsername());
        User user1 = baseMapper.selectOne(queryWrapper);
        if (user1 != null) {
            throw new ApplicationException(CodeType.SERVICE_ERROR, "用户已经存在");
        } else {
            int insert = baseMapper.insert(user);
            if (insert < 1) {
                throw new ApplicationException(CodeType.SERVICE_ERROR, "注册失败");
            } else {
                return ResultBO.newResultBO(CodeType.OK, "注册成功");
            }
        }

    }

    @Override
    public ResultBO<Object> selectIdleRoom(Long roomNumber) {
        List<RoomIdleVO> roomIdleVOS = baseMapper.selectRoomAndTalk(roomNumber);
        System.out.println(roomNumber + "-" + roomIdleVOS);
        if (roomIdleVOS == null) {
            throw new ApplicationException(CodeType.SERVICE_ERROR, "查询错误");
        } else {
            return ResultBO.newResultBO(CodeType.OK, roomIdleVOS);
        }
    }

    @Override
    public ResultBO<Object> selectOrders() {
        JwtBeanBO jwtBeanBO = JwtUtils.getJwtBeanBO();
        List<UserOrdersVO> userOrdersVOS = baseMapper.selectOrders(jwtBeanBO.getId());
        if (userOrdersVOS == null) {
            throw new ApplicationException(CodeType.SERVICE_ERROR, "订单列表获取失败");
        } else {
            return ResultBO.newResultBO(CodeType.OK, userOrdersVOS);
        }
    }

    @Override
    public ResultBO<Object> selectMe() {
        JwtBeanBO jwtBeanBO = JwtUtils.getJwtBeanBO();
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getNumber, jwtBeanBO.getId());
        User user = baseMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new ApplicationException(CodeType.SERVICE_ERROR, "用户信息获取失败");
        } else {
            UserInfoVO userInfoVO = new UserInfoVO();
            BeanUtils.copyProperties(user, userInfoVO);
            return ResultBO.newResultBO(CodeType.OK, userInfoVO);
        }
    }

    @Override
    public ResultBO<String> updateUserInfo(UserInfoBO userInfoBO) {
        User entity = new User();
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getNumber, userInfoBO.getNumber());
        BeanUtils.copyProperties(userInfoBO, entity);
        entity.setCreateDate(null);
        int update = baseMapper.update(entity, updateWrapper);
        if (update < 1) {
            throw new ApplicationException(CodeType.SERVICE_ERROR, "信息修改失败");
        } else {
            return ResultBO.newResultBO(CodeType.OK, "信息修改成功");
        }
    }

    @Override
    public ResultBO<String> postTalk(PostTalkBO postTalkBO) {
        JwtBeanBO jwtBeanBO = JwtUtils.getJwtBeanBO();
        UserIdAndOrderIdBO userIdAndOrderIdBO = baseMapper.selectUserIdAndOrderId(postTalkBO.getOrdersNumber());

        if (userIdAndOrderIdBO == null) {
            throw new ApplicationException(CodeType.SERVICE_ERROR, "订单查询失败，请重试");
        }
        if (!jwtBeanBO.getId().equals(userIdAndOrderIdBO.getNumber())) {
            throw new ApplicationException(CodeType.SERVICE_ERROR, "您只能评论自己的订单");
        }
        Talk talk = new Talk();
        talk.setTalkNumber(snowFlake.nextId());
        talk.setUserId(userIdAndOrderIdBO.getUserId());
        talk.setOrdersId(userIdAndOrderIdBO.getId());
        talk.setTalkContent(postTalkBO.getTalkContent());
        Integer integer = baseMapper.insertTalk(talk);
        if (integer < 1) {
            throw new ApplicationException(CodeType.SERVICE_ERROR, "评论失败");
        } else {
            return ResultBO.newResultBO(CodeType.OK, "评论成功");
        }
    }

    @Override
    public ResultBO<String> checkOutRoom(CheckOutRoomBO checkOutRoomBO) {
        JwtBeanBO jwtBeanBO = JwtUtils.getJwtBeanBO();
        IsCarIdOrderIdBO isCarIdOrderIdBO = new IsCarIdOrderIdBO();
        isCarIdOrderIdBO.setCarId(checkOutRoomBO.getCarId());
        isCarIdOrderIdBO.setNumber(jwtBeanBO.getId());
        isCarIdOrderIdBO.setOrdersNumber(checkOutRoomBO.getOrdersNumber());
        Integer integer = baseMapper.selectCarIdOrdersId(isCarIdOrderIdBO);
        if (integer!=1){
            throw new ApplicationException(CodeType.SERVICE_ERROR, "订单信息错误，请刷新重试");
        }else {
            Pay pay = new Pay();
            pay.setPayNumber(snowFlake.nextId());
            pay.setPayMoney(checkOutRoomBO.getPayMoney());
            Integer insertPay = baseMapper.insertPay(pay);
            if (insertPay<1){
                throw new ApplicationException(CodeType.SERVICE_ERROR, "支付错误，请刷新重试");
            }else {
                UpdateOrderPayIdBO updateOrderPayIdBO = new UpdateOrderPayIdBO();
                updateOrderPayIdBO.setOrdersNumber(checkOutRoomBO.getOrdersNumber());
                updateOrderPayIdBO.setPayNumber(pay.getPayNumber());
                Integer update = baseMapper.updateOrderPayId(updateOrderPayIdBO);
                if (update<1){
                    throw new ApplicationException(CodeType.SERVICE_ERROR, "支付记录更新错误");
                }else {
                    return ResultBO.newResultBO(CodeType.OK, pay.getPayNumber().toString());
                }
            }
        }
    }

    @Override
    public ResultBO<String> createOrders(CreateOrdersBO createOrdersBO) {
        JwtBeanBO jwtBeanBO = JwtUtils.getJwtBeanBO();
        UserIdAndOrderIdBO userIdAndOrderIdBO = baseMapper.selectCreateOrdersId(createOrdersBO.getRoomNumber(), jwtBeanBO.getId());
        if (userIdAndOrderIdBO==null){
            throw new ApplicationException(CodeType.SERVICE_ERROR, "获取房间信息错误");
        }
        System.out.println(userIdAndOrderIdBO);
        CreateRoomIdBO createRoomIdBO = new CreateRoomIdBO();

       createRoomIdBO.setRoomId(userIdAndOrderIdBO.getId());
       createRoomIdBO.setUserId(userIdAndOrderIdBO.getUserId());
        createRoomIdBO.setOrdersNumber(snowFlake.nextId());
        Integer integer = baseMapper.createOrders(createRoomIdBO);
        if (integer<1){
            throw new ApplicationException(CodeType.SERVICE_ERROR, "创建订单失败");
        }else {
            Integer integer1 = baseMapper.updateRoomStatus(createOrdersBO.getRoomNumber());
            return ResultBO.newResultBO(CodeType.OK, "创建成功");
        }
    }
}
