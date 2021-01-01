package cn.ajiehome.management.user.entity.bo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

/**
 * @Author: Jie
 * @Date: 2020/12/30
 */
@Data
public class UserInfoBO {
    /**
     * 编号
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long number;
    /**
     * 用户
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 手机
     */
    private Long phone;
    /**
     * 身份证
     */
    private String carId;
    /**
     * 性别
     */
    private Short sex;
    /**
     * 创建日期
     */
    private Date createDate;
}
