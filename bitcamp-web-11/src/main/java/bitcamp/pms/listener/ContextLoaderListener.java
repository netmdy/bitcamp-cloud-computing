package bitcamp.pms.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.context.support.ClassPathXmlApplicationContext;

@WebListener
public class ContextLoaderListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {
    
        System.out.println("ServletContextListener 실행 ㅇㅅㅇ");
        
        try {
            ClassPathXmlApplicationContext iocContainer = 
            new ClassPathXmlApplicationContext("bitcamp/pms/config/application-context.xml");
            
            String[] names = iocContainer.getBeanDefinitionNames();
            for(String name :names) {
                System.out.printf("%s -> %s\n", name,
                        iocContainer.getBean(name).getClass().getName());
            }
            
//            프론트 컨트롤러가 사용할수 있도록 iocContainer를
//            servletContext에 저장하기
            ServletContext sc = sce.getServletContext();
            sc.setAttribute("iocContainer", iocContainer);
        
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    
}
