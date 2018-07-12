package bitcamp.pms.controller.classroom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.annotation.RequestMapping;
import bitcamp.pms.dao.ClassRoomDao;

public class ClassroomDeleteController {
    
    ClassRoomDao croom;
    
    public ClassroomDeleteController(ClassRoomDao croom) {
        this.croom = croom;
    }

    @RequestMapping
    public String delete(
            HttpServletRequest request, 
            HttpServletResponse response) throws Exception {

        int no = Integer.parseInt(request.getParameter("no"));
        
            croom.delete(no);
            return "/classroom/delete.jsp";
    }
    
}

