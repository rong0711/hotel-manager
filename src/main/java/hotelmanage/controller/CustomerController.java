package hotelmanage.controller;

import hotelmanage.entity.Customer;
import hotelmanage.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // 客户列表
    @GetMapping("/list")
    public String list(Model model) {
        List<Customer> list = customerService.list();
        model.addAttribute("list", list);
        return "customer/list";
    }

    // 跳新增页面
    @GetMapping("/add")
    public String toAddPage() {
        return "customer/add";
    }

    // 保存客户（新增强制清空id，走自增）
    @GetMapping("/save")
    public String save(Customer customer) {
        customer.setId(null);
        customerService.save(customer);
        return "redirect:/customer/list";
    }

    // 跳编辑页面：id非必选兜底，防止直接访问url报错
    @GetMapping("/edit")
    public String toEditPage(@RequestParam(required = false, defaultValue = "0") Integer id, Model model) {
        if (id == 0) {
            return "redirect:/customer/list";
        }
        Customer customer = customerService.getById(id);
        model.addAttribute("customer", customer);
        return "customer/edit";
    }

    // 修改客户
    @GetMapping("/update")
    public String update(Customer customer) {
        customerService.updateById(customer);
        return "redirect:/customer/list";
    }

    // 删除客户
    @GetMapping("/delete")
    public String delete(@RequestParam Integer id) {
        customerService.removeById(id);
        return "redirect:/customer/list";
    }
}