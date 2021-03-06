// Controller 규칙에 따라 메서드 작성
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
@WebServlet("/classroom/add")
public class ClassroomAddServlet extends HttpServlet {
    
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("view", "/classroom/form.jsp");
    }

    @Override
    protected void doPost(
            HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        try {
             ClassRoomDao croom = (ClassRoomDao) getServletContext().getAttribute("classRoomDao");
             ClassRoom cr = new ClassRoom();
             cr.setTitle(request.getParameter("title"));
             cr.setStartDate(Date.valueOf(request.getParameter("startDate")));
             cr.setEndDate(Date.valueOf(request.getParameter("endDate")));
             cr.setRoom(request.getParameter("room"));

             croom.insert(cr);
             request.setAttribute("view", "/classroom/insert.jsp");
             
        }catch (Exception e) {
            request.setAttribute("error", e);
        }
    }
    
}
