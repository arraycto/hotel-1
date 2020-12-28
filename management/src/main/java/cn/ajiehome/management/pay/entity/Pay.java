package cn.ajiehome.management.pay.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Jie
 * @Date: 2020/12/28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("play")
public class Pay extends Model<Pay> {
    @TableId(value = "id",type = IdType.INPUT)
    private Integer id;
    private Long payNumber;
    private Float payMoney;
}
