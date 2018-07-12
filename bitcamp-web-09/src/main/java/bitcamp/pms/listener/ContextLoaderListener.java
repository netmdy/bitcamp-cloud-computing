package bitcamp.pms.listener;

import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import bitcamp.pms.context.ApplicationContext;

@WebListener
public class ContextLoaderListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {
    
        System.out.println("ServletContextListener 실행 ㅇㅅㅇ");
        
        try {
            ApplicationContext iocContainer = new ApplicationContext("bitcamp.pms");
            
            String resource = "bitcamp/pms/config/mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory =
              new SqlSessionFactoryBuilder().build(inputStream);
            
            iocContainer.addBean("sqlSessionFactory", sqlSessionFactory);
            
            iocContainer.refresh();
            
//            프론트 컨트롤러가 사용할수 있도록 iocContainer를
//            servletContext에 저장하기
            ServletContext sc = sce.getServletContext();
            sc.setAttribute("iocContainer", iocContainer);
        
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    
}
