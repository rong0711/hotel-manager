package hotelmanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import hotelmanage.entity.Room;

public interface RoomService extends IService<Room> {
    // 根据房间号查询房间
    Room getByRoomNum(String roomNum);
}