package bitcamp.test.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor{

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

        System.out.println("post 들어옴");
            System.out.println(handler);
            System.out.println(modelAndView.getViewName());
            System.out.println(modelAndView.getModel());
            
            
            HttpSession session = request.getSession();
            session.setAttribute("login", "user");
            
            response.sendRedirect("/html/member/list.html");
    }

}
