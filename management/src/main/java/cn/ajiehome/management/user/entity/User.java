package cn.ajiehome.management.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @Author: Jie
 * @Date: 2020/12/26
 */
@Data
@TableName("user")
public class User {
    @TableId(value = "id",type = IdType.INPUT)
    private Integer id;
    private Long number;
    private String username;
    private String password;
    private Long phone;
    private String carId;
    private Short sex;
    private Date createDate;
}
