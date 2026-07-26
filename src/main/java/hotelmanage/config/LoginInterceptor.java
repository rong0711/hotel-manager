package hotelmanage.config;

import hotelmanage.entity.Admin;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Admin loginUser = (Admin) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            // 未登录跳登录页
            response.sendRedirect("/admin/login");
            return false;
        }
        return true;
    }
}