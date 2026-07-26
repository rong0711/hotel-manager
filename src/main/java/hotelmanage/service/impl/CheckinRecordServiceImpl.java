package hotelmanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import hotelmanage.entity.CheckinRecord;
import hotelmanage.mapper.CheckinRecordMapper;
import hotelmanage.service.CheckinRecordService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CheckinRecordServiceImpl extends ServiceImpl<CheckinRecordMapper, CheckinRecord>
        implements CheckinRecordService {

    @Override
    public List<CheckinRecord> getRecordWithRoomCustomer() {
        return baseMapper.selectRecordWithRoomAndCustomer();
    }
}