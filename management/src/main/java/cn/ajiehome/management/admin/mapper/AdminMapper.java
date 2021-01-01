package cn.ajiehome.management.admin.mapper;

import cn.ajiehome.management.admin.entity.Admin;
import cn.ajiehome.management.admin.entity.Room;
import cn.ajiehome.management.admin.entity.bo.AdminOrderInfoBO;
import cn.ajiehome.management.admin.entity.bo.AdminRoomBO;
import cn.ajiehome.management.admin.entity.vo.AdminUpdateRoomVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Jie
 * @Date: 2020/12/26
 */
@Component
public interface AdminMapper extends BaseMapper<Admin> {
    /**
     * 查询所有的订单记录信息
     * @return 订单信息
     */
    List<AdminOrderInfoBO> selectOrdersAll();

    /**
     * 查询所有房间状态
     * @return 结果
     */
    List<AdminRoomBO> selectRoomAll();

    /**
     * 修改房间信息
     * @param adminUpdateRoomVO 房间信息
     * @return 结果
     */
    Integer updateRoomInfo(AdminUpdateRoomVO adminUpdateRoomVO);

    /**
     * 添加房间
     * @param room 房间锡尼希
     * @return 结果
     */
    Integer addRoom(Room room);
}
