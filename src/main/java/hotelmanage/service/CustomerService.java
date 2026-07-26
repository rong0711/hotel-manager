package hotelmanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import hotelmanage.entity.Customer;

public interface CustomerService extends IService<Customer> {
    // 根据身份证号查询客户
    Customer getByIdCard(String idCard);
}