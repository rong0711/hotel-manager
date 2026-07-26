package hotelmanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import hotelmanage.entity.CheckinRecord;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface CheckinRecordMapper extends BaseMapper<CheckinRecord> {
    // 联查入住记录，带出房间号、客户姓名
    List<CheckinRecord> selectRecordWithRoomAndCustomer();
}