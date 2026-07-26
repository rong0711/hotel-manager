package hotelmanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import hotelmanage.entity.Room;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoomMapper extends BaseMapper<Room> {
}