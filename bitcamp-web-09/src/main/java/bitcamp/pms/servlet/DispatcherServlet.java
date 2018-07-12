package bitcamp.pms.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.annotation.RequestMapping;
import bitcamp.pms.context.ApplicationContext;

@SuppressWarnings("serial")
@WebServlet("/app/*")
public class DispatcherServlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 클라이언트가 요청한 서비스 url을 알려줌 /app/* 에서 *에 해당하는 값을 추출한다
        // 예 ) /app/member/list ==> /member/list를 추출한다
        String pathInfo = request.getPathInfo();

        //include 하는쪽에서 설정해줘야함
        response.setContentType("text/html;charset=UTF-8");
        
        // ServletContext  보관소에 저장된 iocContainer 를 꺼낸다
        ApplicationContext iocContainer =
                (ApplicationContext) getServletContext().getAttribute("iocContainer");
        
        
        // 페이지 컨트롤러 실행
        try {
            // iocContainer 저장된 페이지 컨트롤러를 찾는다.
            Object pageController = iocContainer.getBean(pathInfo);
            
            if (pageController == null) throw new Exception("해당 url에 대해 서비스를 처리할 수 없습니다.");
            
            // 페이지 컨트롤러에 있는 메서드 중에서 @RequestMapping 이라는 애노테이션이 붙은 메서드를 찾아 호출한다
            Method requestHandler = getRequestHandler(pageController.getClass());
            
            if (requestHandler == null)
                throw new Exception("요청 핸들러를 찾지 못했습니다.");
            
            // 페이지 컨트롤러의 메서드르 호출한다.
            String view = (String) requestHandler.invoke(pageController, request, response);
        
        if (view.startsWith("redirect:")) {
            response.sendRedirect(view.substring(9));
            
        }else{
            RequestDispatcher rd = request.getRequestDispatcher(view);
            rd.include(request, response);
            }
        }catch (Exception e) {
            request.setAttribute("error", e);
            RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
        }
    }

    private Method getRequestHandler(Class<?> clazz) {
        
        // 클래스 정보에서 메서드 정보를 추출한다.
        Method[] methods = clazz.getMethods();
        
        // 메서드 중에서 @RequestMapping 애노테이션이 붙은 메서드를 찾아낸다.
        for(Method m: methods) {
           RequestMapping anno =  m.getAnnotation(RequestMapping.class);
           if (anno != null)
               return m;
        }
        return null;
    }
}
