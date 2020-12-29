package cn.ajiehome.management.admin.entity.bo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @Author: Jie
 * @Date: 2020/12/28
 */
@Data
public class AdminRoomBO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long roomNumber;
    private String houseNumber;
    private Short type;
    private Short roomStatus;
    private Float roomMoney;
    private String roomImage;
    private String description;
    private Long ordersNumber;
    private String username;
}
