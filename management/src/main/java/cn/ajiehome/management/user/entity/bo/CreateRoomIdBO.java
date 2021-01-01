package cn.ajiehome.management.user.entity.bo;


import lombok.Data;
/**
 * @Author: Jie
 * @Date: 2020/12/30
 */

@Data
public class CreateRoomIdBO {
    private Integer userId;
    private Integer roomId;
    private Long ordersNumber;
}
