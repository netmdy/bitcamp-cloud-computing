package bitcamp.test.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class PreLoginInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("pre 들어옴");
        
        Object value = request.getSession().getAttribute("login");
        
        if(value == null) { response.sendRedirect("/html/member/login.html"); }
        
        return false;
    }

    
}
