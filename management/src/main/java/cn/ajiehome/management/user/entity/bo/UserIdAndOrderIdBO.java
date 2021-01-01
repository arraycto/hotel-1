package cn.ajiehome.management.user.entity.bo;

import io.swagger.models.auth.In;
import lombok.Data;
import org.omg.PortableInterceptor.INACTIVE;

/**
 * @Author: Jie
 * @Date: 2020/12/30
 */
@Data
public class UserIdAndOrderIdBO {
    private Integer userId;
    private Integer id;
    private Long number;
}
