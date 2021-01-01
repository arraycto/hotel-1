package cn.ajiehome.management.user.entity.vo;

import cn.ajiehome.management.user.entity.bo.TalkRoomBO;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.List;

/**
 * @Author: Jie
 * @Date: 2020/12/29
 */
@Data
public class RoomIdleVO {
    @JsonSerialize(using= ToStringSerializer.class)
    private Long roomNumber;
    private String houseNumber;
    private Short type;
    private Float roomMoney;
    private String roomImage;
    private String description;
    private List<TalkRoomBO> talkRoomBOList;
}
