package bitcamp.pms.servlet.classroom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.dao.ClassRoomDao;

@SuppressWarnings("serial")
@WebServlet("/classroom/delete")
public class ClassroomDeleteServlet extends HttpServlet {
    
    @Override
    protected void doGet(
            HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {

        int no = Integer.parseInt(request.getParameter("no"));
        
        response.setContentType("text/html;charset=UTF-8");
        
        try {
            ClassRoomDao croom = (ClassRoomDao) getServletContext().getAttribute("classRoomDao");
                    
            croom.delete(no);
            request.setAttribute("view", "/classroom/delete.jsp");
        } catch (Exception e) {
            request.setAttribute("error", e);
        }
    }
    
}

