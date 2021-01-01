package cn.ajiehome.management.user.entity.bo;

import cn.ajiehome.common.exception.entity.bo.ResultBO;
import lombok.Data;

/**
 * @Author: Jie
 * @Date: 2020/12/29
 */
@Data
public class UserRegisterBO {
    private String username;
    private String password;
    private String rePassword;
    private Long phone;
    private Short sex;
}
