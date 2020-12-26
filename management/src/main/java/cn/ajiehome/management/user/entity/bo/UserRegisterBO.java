package cn.ajiehome.management.user.entity.bo;

import lombok.Data;

import java.nio.channels.Pipe;

/**
 * @Author: Jie
 * @Date: 2020/12/24
 */
@Data
public class UserRegisterBO {
    private String username;
    private String password;
    private Short sex;
}
