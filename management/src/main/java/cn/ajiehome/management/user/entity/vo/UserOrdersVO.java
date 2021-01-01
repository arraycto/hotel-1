package cn.ajiehome.management.user.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

/**
 * @Author: Jie
 * @Date: 2020/12/29
 */
@Data
public class UserOrdersVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long ordersNumber;
    private Date createDate;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long payNumber;
    private Float payMoney;
    private String description;
    private String houseNumber;
    private Short type;
    private Float roomMoney;
}
