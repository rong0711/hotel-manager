package hotelmanage.controller;

import hotelmanage.entity.CheckinRecord;
import hotelmanage.entity.Customer;
import hotelmanage.entity.Room;
import hotelmanage.entity.RoomType;
import hotelmanage.service.CheckinRecordService;
import hotelmanage.service.CustomerService;
import hotelmanage.service.RoomService;
import hotelmanage.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/checkinRecord")
public class CheckinRecordController {

    @Autowired
    private CheckinRecordService checkinRecordService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomTypeService roomTypeService;
    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String list(Model model) {
        List<CheckinRecord> recordList = checkinRecordService.list();
        List<Room> roomList = roomService.list();
        List<Customer> customerList = customerService.list();
        model.addAttribute("recordList", recordList);
        model.addAttribute("roomList", roomList);
        model.addAttribute("customerList", customerList);
        return "checkinRecord/list";
    }

    // 跳新增入住页面（传入房型、全部房间、客户列表）
    @GetMapping("/add")
    public String toAddPage(@RequestParam(required = false) Integer selectedTypeId, Model model) {
        List<RoomType> typeList = roomTypeService.list();
        List<Room> allRoomList = roomService.list();
        List<Customer> customerList = customerService.list();
        model.addAttribute("typeList", typeList);
        model.addAttribute("allRoomList", allRoomList);
        model.addAttribute("customerList", customerList);
        model.addAttribute("selectedTypeId", selectedTypeId);
        return "checkinRecord/add";
    }

    // 保存入住记录
    @GetMapping("/save")
    public String saveRecord(
            @RequestParam Integer roomId,
            @RequestParam Integer customerId,
            @RequestParam BigDecimal totalMoney
    ) {
        CheckinRecord record = new CheckinRecord();
        record.setRoomId(roomId);
        record.setCustomerId(customerId);
        record.setTotalMoney(totalMoney);
        record.setCheckinTime(new Date());
        record.setStatus(0); // 0=入住中
        record.setAdminId(1);
        checkinRecordService.save(record);

        // 更新房间状态为占用
        Room room = new Room();
        room.setId(roomId);
        room.setStatus(1);
        roomService.updateById(room);
        return "redirect:/checkinRecord/list";
    }

    // 跳编辑页面
    @GetMapping("/edit")
    public String toEditPage(@RequestParam Integer id, Model model) {
        CheckinRecord record = checkinRecordService.getById(id);
        List<Room> allRoomList = roomService.list();
        List<Customer> customerList = customerService.list();
        model.addAttribute("record", record);
        model.addAttribute("allRoomList", allRoomList);
        model.addAttribute("customerList", customerList);
        return "checkinRecord/edit";
    }
    // 更新入住记录
    @GetMapping("/update")
    public String update(CheckinRecord record) {
        // 1. 查询旧记录
        CheckinRecord oldRecord = checkinRecordService.getById(record.getId());
        // 2. 如果更换了房间，旧房间恢复空闲
        if(!oldRecord.getRoomId().equals(record.getRoomId())){
            Room oldRoom = new Room();
            oldRoom.setId(oldRecord.getRoomId());
            oldRoom.setStatus(0);
            roomService.updateById(oldRoom);
            // 新房间设为占用
            Room newRoom = new Room();
            newRoom.setId(record.getRoomId());
            newRoom.setStatus(1);
            roomService.updateById(newRoom);
        }
        // 3. 更新入住记录
        checkinRecordService.updateById(record);
        return "redirect:/checkinRecord/list";
    }

    // 退房操作
    @GetMapping("/checkout")
    public String checkout(@RequestParam Integer id) {
        // 查询入住记录
        CheckinRecord record = checkinRecordService.getById(id);
        // 修改退房时间、状态为已退房
        record.setCheckoutTime(new Date());
        record.setStatus(1);
        checkinRecordService.updateById(record);

        // 同步把房间状态改为空闲0
        Room room = new Room();
        room.setId(record.getRoomId());
        room.setStatus(0);
        roomService.updateById(room);

        return "redirect:/checkinRecord/list";
    }

    // 删除入住记录
    @GetMapping("/delete")
    public String delete(@RequestParam Integer id) {
        checkinRecordService.removeById(id);
        return "redirect:/checkinRecord/list";
    }
}