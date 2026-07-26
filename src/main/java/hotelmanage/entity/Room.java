package hotelmanage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("room")
public class Room {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String roomNum;
    // 房型id，必须赋值
    private Integer typeId;
    private Integer status;
}