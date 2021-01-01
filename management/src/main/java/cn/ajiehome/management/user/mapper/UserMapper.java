package cn.ajiehome.management.user.mapper;

import cn.ajiehome.management.user.entity.Pay;
import cn.ajiehome.management.user.entity.Talk;
import cn.ajiehome.management.user.entity.User;
import cn.ajiehome.management.user.entity.bo.CreateRoomIdBO;
import cn.ajiehome.management.user.entity.bo.IsCarIdOrderIdBO;
import cn.ajiehome.management.user.entity.bo.UpdateOrderPayIdBO;
import cn.ajiehome.management.user.entity.bo.UserIdAndOrderIdBO;
import cn.ajiehome.management.user.entity.vo.RoomIdleVO;
import cn.ajiehome.management.user.entity.vo.UserOrdersVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Jie
 */
@Component
public interface UserMapper extends BaseMapper<User> {
    /**
     * 查询房间可用房间的信息
     * @param roomNumber 房间编号 没有就全查
     * @return 结果
     */
    List<RoomIdleVO> selectRoomAndTalk(@Param("roomNumber")Long roomNumber);

    /**
     * 查询用户的所有订单
     * @param userNumber 用户编号
     * @return 结果
     */
    List<UserOrdersVO> selectOrders(@Param("userNumber")Long userNumber);

    /**
     * 查询用户ID和订单ID
     * @param ordersNumber 订单编号
     * @return 结果
     */
    UserIdAndOrderIdBO selectUserIdAndOrderId(@Param("ordersNumber")Long ordersNumber);

    /**
     * 添加评论
     * @param talk 评论对象
     * @return 结果
     */
    Integer insertTalk(Talk talk);

    /**
     * 判断退房是否符合条件
     * @param isCarIdOrderIdBO 数据
     * @return 结果
     */
    Integer selectCarIdOrdersId(IsCarIdOrderIdBO isCarIdOrderIdBO);

    /**
     * 添加支付记录
     * @param pay 数据
     * @return 结果
     */
    Integer insertPay(Pay pay);

    /**
     * 更新订单支付
     * @param updateOrderPayIdBO 数据
     * @return 结果
     */
    Integer updateOrderPayId(UpdateOrderPayIdBO updateOrderPayIdBO);

    /**
     * 添加订单
     * @param createRoomIdBO 数据
     * @return 结果
     */
    Integer createOrders(CreateRoomIdBO createRoomIdBO);

    /**
     * 查询ID
     * @param roomNumber 房间编号
     * @param number 用户编号
     * @return 结果
     */
    UserIdAndOrderIdBO selectCreateOrdersId(@Param("roomNumber")Long roomNumber,@Param("number")Long number);

    /**
     * 修改房间状态
     * @param roomNumber
     * @return
     */
    Integer updateRoomStatus(@Param("roomNumber")Long roomNumber);
}
