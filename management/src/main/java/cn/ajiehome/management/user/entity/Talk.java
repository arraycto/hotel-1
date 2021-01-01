package cn.ajiehome.management.user.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author: Jie
 * @Date: 2020/12/30
 */
@Data
public class Talk {
    private Integer id;
    private Long talkNumber;
    private Integer userId;
    private Integer ordersId;
    private String talkContent;
    private Date createDate;
}
