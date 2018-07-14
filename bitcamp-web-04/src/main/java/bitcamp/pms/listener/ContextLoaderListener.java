package bitcamp.pms.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import bitcamp.pms.dao.ClassRoomDao;
import bitcamp.pms.dao.MemberDao;
import bitcamp.pms.dao.TeamDao;
import bitcamp.pms.dao.TeamMemberDao;

@WebListener
public class ContextLoaderListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {
    
        System.out.println("ServletContextListener 실행 ㅇㅅㅇ");
        
        MemberDao memberDao = new MemberDao(
                "jdbc:mysql://13.209.8.213:3306/studydb","study","1111");
        ClassRoomDao classRoomDao = new ClassRoomDao(
                "jdbc:mysql://13.209.8.213:3306/studydb","study","1111");
        TeamDao teamDao = new TeamDao(
                "jdbc:mysql://13.209.8.213:3306/studydb","study","1111");
        TeamMemberDao teamMemberDao = new TeamMemberDao(
                "jdbc:mysql://13.209.8.213:3306/studydb","study","1111");
                
        
        ServletContext sc = sce.getServletContext();
        sc.setAttribute("memberDao", memberDao);
        sc.setAttribute("classRoomDao", classRoomDao);
        sc.setAttribute("teamDao", teamDao);
        sc.setAttribute("teamMemberDao", teamMemberDao);
    }
    
}
