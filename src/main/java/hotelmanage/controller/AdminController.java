package hotelmanage.controller;

import hotelmanage.entity.Admin;
import hotelmanage.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    // 登录页面 templates/admin/login.html
    @GetMapping("/login")
    public String toLogin() {
        return "admin/login";
    }

    // 登录提交
    @PostMapping("/login")
    public String login(String username, String password, Model model, HttpSession session) {
        Admin admin = adminService.lambdaQuery()
                .eq(Admin::getUsername, username)
                .one();
        if (admin == null || !admin.getPassword().equals(password)) {
            model.addAttribute("msg", "账号或密码错误");
            return "admin/login";
        }
        session.setAttribute("loginUser", admin);
        // 登录成功跳首页，不带.html
        return "redirect:/index";
    }

    // 退出登录，跳转首页 /index
    @GetMapping("/logout")
    public void logout(HttpSession session, HttpServletResponse response) throws IOException {
        session.invalidate();
        response.sendRedirect("/index");
    }
}