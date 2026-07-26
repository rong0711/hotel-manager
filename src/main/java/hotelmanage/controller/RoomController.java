package hotelmanage.controller;

import hotelmanage.entity.Room;
import hotelmanage.entity.RoomType;
import hotelmanage.service.RoomService;
import hotelmanage.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomTypeService roomTypeService;

    @GetMapping("/list")
    public String list(Model model) {
        // 同时查询房间+全部房型，用于页面匹配名称
        List<Room> pageData = roomService.list();
        List<RoomType> typeList = roomTypeService.list();
        model.addAttribute("pageData", pageData);
        model.addAttribute("typeList", typeList);
        return "room/list";
    }

    // 跳新增页面
    @GetMapping("/add")
    public String toAddPage(Model model) {
        List<RoomType> typeList = roomTypeService.list();
        model.addAttribute("typeList", typeList);
        return "room/add";
    }

    // 保存房间
    @GetMapping("/save")
    public String saveRoom(
            @RequestParam String roomNum,
            @RequestParam Integer typeId,
            @RequestParam(defaultValue = "0") Integer status
    ) {
        Room room = new Room();
        room.setRoomNum(roomNum);
        room.setTypeId(typeId);
        room.setStatus(status);
        roomService.save(room);
        return "redirect:/room/list";
    }

    @GetMapping("/edit")
    public String toEditRoom(@RequestParam Integer id, Model model) {
        Room room = roomService.getById(id);
        // 查出全部房型传给页面下拉
        List<RoomType> typeList = roomTypeService.list();
        model.addAttribute("room", room);
        model.addAttribute("typeList", typeList);
        return "room/edit";
    }

    // 更新房间
    @GetMapping("/update")
    public String updateRoom(Integer id, String roomNum, Integer typeId, Integer status) {
        Room room = new Room();
        room.setId(id);
        room.setRoomNum(roomNum);
        room.setTypeId(typeId);
        room.setStatus(status);
        roomService.updateById(room);
        return "redirect:/room/list";
    }
    // 删除房间
    @GetMapping("/delete")
    public String delete(@RequestParam Integer id) {
        roomService.removeById(id);
        return "redirect:/room/list";
    }
}