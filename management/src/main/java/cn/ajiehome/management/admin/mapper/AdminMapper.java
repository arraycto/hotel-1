package cn.ajiehome.management.admin.mapper;

import cn.ajiehome.management.admin.entity.Admin;
import cn.ajiehome.management.admin.entity.bo.AdminOrderInfoBO;
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
}
