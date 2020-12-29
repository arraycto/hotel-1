package cn.ajiehome.management.admin.entity.vo;

import lombok.Data;

/**
 * @Author: Jie
 * @Date: 2020/12/29
 */
@Data
public class AdminUpdateRoomVO {
    private Long roomNumber;
    private String houseNumber;
    private Float roomMoney;
    private String roomImage;
    private String description;
}
