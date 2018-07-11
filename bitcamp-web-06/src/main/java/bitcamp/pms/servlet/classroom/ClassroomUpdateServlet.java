package bitcamp.pms.servlet.classroom;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.dao.ClassRoomDao;
import bitcamp.pms.domain.ClassRoom;

@SuppressWarnings("serial")
@WebServlet("/classroom/update")
public class ClassroomUpdateServlet extends HttpServlet {
    
    @Override
    protected void doPost(
            HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        try {
                ClassRoomDao croom = (ClassRoomDao) getServletContext().getAttribute("classRoomDao");
                ClassRoom cr = new ClassRoom();
                cr.setCrno(Integer.parseInt(request.getParameter("no")));
                cr.setTitle(request.getParameter("title"));
                cr.setStartDate(Date.valueOf(request.getParameter("startDate")));
                cr.setEndDate(Date.valueOf(request.getParameter("endDate")));
                cr.setRoom(request.getParameter("room"));
                
                croom.update(cr);
                request.setAttribute("view", "/classroom/update.jsp");

        } catch (Exception e) {
            request.setAttribute("error", e);
        }
    }
    
}