package bitcamp.pms.servlet.classroom;

import java.io.IOException;
import java.util.HashMap;
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

        // DB에서 가져올 테이터의 페이지 정보
        HashMap<String,Object> params =new HashMap<>();
        if (request.getParameter("page") != null &&
                request.getParameter("size")!=null) {
            int page = Integer.parseInt(request.getParameter("page"));
            int size = Integer.parseInt(request.getParameter("size"));
            params.put("startIndex", (page -1) * size);
            params.put("pageSize", size);
        }
        
        response.setContentType("text/html;charset=UTF-8");

        try {
            
            ClassRoomDao cRoom = (ClassRoomDao) getServletContext().getAttribute("classRoomDao");
            List<ClassRoom> list = cRoom.selectList(params);
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