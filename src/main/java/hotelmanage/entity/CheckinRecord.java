package hotelmanage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class CheckinRecord {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer roomId;
    private Integer customerId;
    private Integer adminId;
    private Date checkinTime;
    private Date checkoutTime;
    private BigDecimal totalMoney;
    private Integer status;
}