package bitcamp.pms.servlet.classroom;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.dao.ClassRoomDao;
import bitcamp.pms.domain.ClassRoom;

@SuppressWarnings("serial")
@WebServlet("/classroom/view")
public class ClassroomViewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int no = Integer.parseInt(request.getParameter("no"));

        response.setContentType("text/html;charset=UTF-8");

        try {
            ClassRoomDao croom = (ClassRoomDao) getServletContext().getAttribute("classRoomDao");
                    
            ClassRoom cr = croom.selectOne(no);
            
            request.setAttribute("classRoom", cr);
            
            RequestDispatcher rd = request.getRequestDispatcher("/classroom/view.jsp");
            rd.include(request, response);

        } catch (Exception e) {
            request.setAttribute("error", e);
            RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
        }
    }

}