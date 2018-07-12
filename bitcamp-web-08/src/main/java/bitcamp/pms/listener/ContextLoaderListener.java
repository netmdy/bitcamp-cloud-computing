package bitcamp.pms.listener;

import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import bitcamp.pms.controller.MemberAddController;
import bitcamp.pms.controller.MemberDeleteController;
import bitcamp.pms.controller.MemberListController;
import bitcamp.pms.controller.MemberUpdateController;
import bitcamp.pms.controller.MemberViewController;
import bitcamp.pms.controller.board.BoardAddController;
import bitcamp.pms.controller.board.BoardDeleteController;
import bitcamp.pms.controller.board.BoardListController;
import bitcamp.pms.controller.board.BoardUpdateController;
import bitcamp.pms.controller.board.BoardViewController;
import bitcamp.pms.controller.classroom.ClassroomAddController;
import bitcamp.pms.controller.classroom.ClassroomDeleteController;
import bitcamp.pms.controller.classroom.ClassroomListController;
import bitcamp.pms.controller.classroom.ClassroomUpdateController;
import bitcamp.pms.controller.classroom.ClassroomViewController;
import bitcamp.pms.dao.BoardDao;
import bitcamp.pms.dao.ClassRoomDao;
import bitcamp.pms.dao.MemberDao;

@WebListener
public class ContextLoaderListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {
    
        System.out.println("ServletContextListener 실행 ㅇㅅㅇ");
        
        try {
        String resource = "bitcamp/pms/config/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
          new SqlSessionFactoryBuilder().build(inputStream);
        
        MemberDao memberDao = new MemberDao(sqlSessionFactory);
        BoardDao boardDao = new BoardDao(sqlSessionFactory);
        ClassRoomDao classroomDao = new ClassRoomDao(sqlSessionFactory);
        
        ServletContext sc = sce.getServletContext();
        sc.setAttribute("/member/list", new MemberListController(memberDao));
        sc.setAttribute("/member/view", new MemberViewController(memberDao));
        sc.setAttribute("/member/update", new MemberUpdateController(memberDao));
        sc.setAttribute("/member/delete", new MemberDeleteController(memberDao));
        sc.setAttribute("/member/add", new MemberAddController(memberDao));
        sc.setAttribute("/board/list", new BoardListController(boardDao));
        sc.setAttribute("/board/view", new BoardViewController(boardDao));
        sc.setAttribute("/board/update", new BoardUpdateController(boardDao));
        sc.setAttribute("/board/delete", new BoardDeleteController(boardDao));
        sc.setAttribute("/board/add", new BoardAddController(boardDao));
        sc.setAttribute("/classroom/list", new ClassroomListController(classroomDao));
        sc.setAttribute("/classroom/view", new ClassroomViewController(classroomDao));
        sc.setAttribute("/classroom/update", new ClassroomUpdateController(classroomDao));
        sc.setAttribute("/classroom/delete", new ClassroomDeleteController(classroomDao));
        sc.setAttribute("/classroom/add", new ClassroomAddController(classroomDao));
        
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    
}
