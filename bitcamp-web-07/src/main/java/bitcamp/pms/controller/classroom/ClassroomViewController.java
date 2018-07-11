package bitcamp.pms.controller.classroom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.controller.PageController;
import bitcamp.pms.dao.ClassRoomDao;
import bitcamp.pms.domain.ClassRoom;

public class ClassroomViewController implements PageController{

    ClassRoomDao croom;
    
    public ClassroomViewController(ClassRoomDao croom) {
        this.croom = croom;
    }

    public String service(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        int no = Integer.parseInt(request.getParameter("no"));

            ClassRoom cr = croom.selectOne(no);
            
            request.setAttribute("classRoom", cr);
            return "/classroom/view.jsp";

    }

}