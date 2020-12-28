package cn.ajiehome.management.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Jie
 * @Date: 2020/12/26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "admin")
public class Admin extends Model<Admin> {
    @TableId(value = "id",type = IdType.INPUT)
    private Integer id;
    /**
     * 编号
     */
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
     * 部门
     */
    private String department;
    /**
     * 等级
     */
    private Short rank;

}
