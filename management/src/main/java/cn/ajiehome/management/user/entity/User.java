package cn.ajiehome.management.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: Jie
 * @Date: 2020/12/24
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends Model<User> {
    /**
     * 用户ID
     */

    @TableId(value = "id",type = IdType.INPUT)
    private Integer id;
    /**
     * 用户编号
     */
    private Long number;
    /**
     * 用户名称
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 手机号啊
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
     * 创建时间
     */
    private Long createDate;
}
