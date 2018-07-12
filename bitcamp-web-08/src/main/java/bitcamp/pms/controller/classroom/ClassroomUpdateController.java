package bitcamp.pms.controller.classroom;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.annotation.RequestMapping;
import bitcamp.pms.dao.ClassRoomDao;
import bitcamp.pms.domain.ClassRoom;

public class ClassroomUpdateController {
    
    ClassRoomDao croom;
    
    public ClassroomUpdateController(ClassRoomDao croom) {
        this.croom = croom;
    }

    @RequestMapping
    public String update(
            HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        
                ClassRoom cr = new ClassRoom();
                cr.setCrno(Integer.parseInt(request.getParameter("no")));
                cr.setTitle(request.getParameter("title"));
                cr.setStartDate(Date.valueOf(request.getParameter("startDate")));
                cr.setEndDate(Date.valueOf(request.getParameter("endDate")));
                cr.setRoom(request.getParameter("room"));
                
                croom.update(cr);
                return "/classroom/update.jsp";
    }
    
}