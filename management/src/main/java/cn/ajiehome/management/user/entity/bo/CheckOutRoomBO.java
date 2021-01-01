package cn.ajiehome.management.user.entity.bo;

import lombok.Data;

/**
 * @Author: Jie
 * @Date: 2020/12/30
 */
@Data
public class CheckOutRoomBO {
    private Long ordersNumber;
    private Float payMoney;
    private String carId;
}
