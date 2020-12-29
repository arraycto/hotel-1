package cn.ajiehome.management.admin.entity.bo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

/**
 * @Author: Jie
 * @Date: 2020/12/28
 */
@Data
public class AdminOrderInfoBO {
    /**
     * 订单创建时间
     */
    private Date createDate;
    /**
     * 订单编号
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long ordersNumber;
    /**
     * 房间号
     */
    private String houseNumber;
    /**
     * 房间类型
     */
    private Short type;

    /**
     * 房间价格
     */
    private Float roomMoney;
    /**
     * 房间图片
     */
    private String roomImage;
    /**
     * 房间描述
     */
    private String description;
    /**
     * 支付金额
     */
    private Float payMoney;

    private String username;

    private String phone;
    private Short sex;


}
