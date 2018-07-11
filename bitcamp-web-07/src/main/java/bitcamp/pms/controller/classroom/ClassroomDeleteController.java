package bitcamp.pms.controller.classroom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.controller.PageController;
import bitcamp.pms.dao.ClassRoomDao;

public class ClassroomDeleteController implements PageController{
    
    ClassRoomDao croom;
    
    public ClassroomDeleteController(ClassRoomDao croom) {
        this.croom = croom;
    }

    public String service(
            HttpServletRequest request, 
            HttpServletResponse response) throws Exception {

        int no = Integer.parseInt(request.getParameter("no"));
        
            croom.delete(no);
            return "/classroom/delete.jsp";
    }
    
}

