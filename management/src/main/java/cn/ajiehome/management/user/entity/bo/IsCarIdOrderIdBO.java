package cn.ajiehome.management.user.entity.bo;

import lombok.Data;
import sun.rmi.runtime.Log;

/**
 * @Author: Jie
 * @Date: 2020/12/30
 */
@Data
public class IsCarIdOrderIdBO {
    private Long ordersNumber;
    private String carId;
    private Long number;
}
