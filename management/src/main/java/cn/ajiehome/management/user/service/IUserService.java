package cn.ajiehome.management.user.service;

import cn.ajiehome.common.exception.entity.bo.ResultBO;
import cn.ajiehome.management.user.entity.bo.*;

/**
 * @author Jie
 */
public interface IUserService {
    /**
     * 用户登录
     * @param userLoginBO 用户信息
     * @return 结果
     */
    ResultBO<String> userLogin(UserLoginBO userLoginBO);

    /**
     * 用户注册
     * @param userRegisterBO 注册信息
     * @return 结果
     */
    ResultBO<String> userRegister(UserRegisterBO userRegisterBO);

    /**
     * 查询所有空闲的房间
     * @param roomNumber 房间编号
     * @return 结果
     */
    ResultBO<Object> selectIdleRoom(Long roomNumber);

    /**
     * 查询本人所有的订单
     * @return 结果
     */
    ResultBO<Object> selectOrders();

    /**
     * 查询用户自己的信息
     * @return 结果
     */
    ResultBO<Object> selectMe();

    /**
     * 修改用户自己的信息
     * @param userInfoBO 信息
     * @return 结果
     */
    ResultBO<String> updateUserInfo(UserInfoBO userInfoBO);

    /**
     * 发布评论
     * @param postTalkBO 评论
     * @return 结果
     */
    ResultBO<String> postTalk(PostTalkBO postTalkBO);

    /**
     * 退房
     * @param checkOutRoomBO 退房信息
     * @return 结果
     */
    ResultBO<String> checkOutRoom(CheckOutRoomBO checkOutRoomBO);

    /**
     * 创建订单
     * @param createOrdersBO 数据
     * @return 结果
     */
    ResultBO<String> createOrders(CreateOrdersBO createOrdersBO);
}
