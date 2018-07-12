// Controller 규칙에 따라 메서드 작성
package bitcamp.pms.controller.classroom;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.annotation.RequestMapping;
import bitcamp.pms.dao.ClassRoomDao;
import bitcamp.pms.domain.ClassRoom;

public class ClassroomAddController{
    
    ClassRoomDao croom;
    
    public ClassroomAddController(ClassRoomDao croom) {
        this.croom = croom;
    }
    
    @RequestMapping
    public String add(
            HttpServletRequest request, 
            HttpServletResponse response) throws Exception {

        if (request.getMethod().equals("GET")) {
            return "/classroom/form.jsp";
        }
             ClassRoom cr = new ClassRoom();
             cr.setTitle(request.getParameter("title"));
             cr.setStartDate(Date.valueOf(request.getParameter("startDate")));
             cr.setEndDate(Date.valueOf(request.getParameter("endDate")));
             cr.setRoom(request.getParameter("room"));

             croom.insert(cr);
             return "/classroom/insert.jsp";
    }
    
}
