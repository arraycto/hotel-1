package cn.ajiehome.management.user.entity.bo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

/**
 * @Author: Jie
 * @Date: 2020/12/29
 */
@Data
public class TalkRoomBO {
    private String username;
    private Short sex;
    private Date createDate;
    private String talkContent;
    @JsonSerialize(using= ToStringSerializer.class)
    private Long talkNumber;
    @JsonSerialize(using= ToStringSerializer.class)
    private Long ordersNumber;
}
