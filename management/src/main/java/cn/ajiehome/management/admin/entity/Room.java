package cn.ajiehome.management.admin.entity;

import lombok.Data;

/**
 * @Author: Jie
 * @Date: 2020/12/30
 */
@Data
public class Room {
    private Integer id;
    private Long roomNumber;
    private String houseNumber;
    private Short type;
    private Float roomMoney;
    private String roomImage;
    private String description;
}
