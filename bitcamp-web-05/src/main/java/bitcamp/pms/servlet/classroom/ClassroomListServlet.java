package bitcamp.pms.servlet.classroom;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.dao.ClassRoomDao;
import bitcamp.pms.domain.ClassRoom;

@SuppressWarnings("serial")
@WebServlet("/classroom/list")
public class ClassroomListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try {
            
            ClassRoomDao cRoom = (ClassRoomDao) getServletContext().getAttribute("classRoomDao");
            List<ClassRoom> list = cRoom.selectList();
            request.setAttribute("list", list);

            RequestDispatcher rd = request.getRequestDispatcher("/classroom/list.jsp");
            rd.include(request, response);

        } catch (Exception e) {
            request.setAttribute("error", e);
            RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
        }
    }
    
}