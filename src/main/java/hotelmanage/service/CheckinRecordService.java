package hotelmanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import hotelmanage.entity.CheckinRecord;
import java.util.List;

public interface CheckinRecordService extends IService<CheckinRecord> {
    // 查询带房间号、客户姓名的完整入住记录
    List<CheckinRecord> getRecordWithRoomCustomer();
}