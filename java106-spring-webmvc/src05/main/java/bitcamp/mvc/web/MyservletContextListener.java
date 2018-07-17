package bitcamp.mvc.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

// 톰캣 서버에게 알람을 받고 싶을때 
// ServletContextListener의 규칙에 따라서 작성해주세영 ㅇㅅㅇ
@WebListener
public class MyservletContextListener implements ServletContextListener{

//    시작할때 알림 받기
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("시작 ㅇㅅㅇ");
    }
//    종료할때 알림 받기
    @Override
        public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("종료 ㅇㅅㅇ");
    }
    
}
