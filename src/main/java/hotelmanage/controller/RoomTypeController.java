package hotelmanage.controller;

import hotelmanage.entity.RoomType;
import hotelmanage.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/roomType")
public class RoomTypeController {

    @Autowired
    private RoomTypeService roomTypeService;

    @GetMapping("/list")
    public String list(Model model){
        // 查询全部房型，存入key=list
        List<RoomType> list = roomTypeService.list();
        model.addAttribute("list", list);
        return "roomType/list";
    }

    // 编辑/新增页面
    @GetMapping("/edit")
    public String edit(@RequestParam(required = false) Integer id, Model model){
        RoomType roomType = null;
        // 有id则查询，无id为新增，new空对象
        if(id != null){
            roomType = roomTypeService.getById(id);
        }else{
            roomType = new RoomType();
        }
        // 一定存入roomType，保证页面不会拿到null
        model.addAttribute("roomType", roomType);
        return "roomType/edit";
    }

    // 保存/更新
    @GetMapping("/save")
    public String save(RoomType roomType){
        roomTypeService.saveOrUpdate(roomType);
        return "redirect:/roomType/list";
    }

    // 删除房型
    @GetMapping("/delete")
    public String delete(Integer id){
        roomTypeService.removeById(id);
        return "redirect:/roomType/list";
    }
}