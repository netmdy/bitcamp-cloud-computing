package bitcamp.pms.servlet.classroom;

import java.io.IOException;
import java.io.PrintWriter;
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
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta http-equiv='Refresh' content='1;url=list'>");
        out.println("<title>강의 변경</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>강의 변경 결과</h1>");
        
        try {
                ClassRoomDao croom = (ClassRoomDao) getServletContext().getAttribute("classRoomDao");
                ClassRoom cr = new ClassRoom();
                cr.setCrno(Integer.parseInt(request.getParameter("no")));
                cr.setTitle(request.getParameter("title"));
                cr.setStartDate(Date.valueOf(request.getParameter("startDate")));
                cr.setEndDate(Date.valueOf(request.getParameter("endDate")));
                cr.setRoom(request.getParameter("room"));
                
                
                croom.update(cr);
                
                out.println("<p>변경 완료</p>");
                
        } catch (Exception e) {
            out.println("<p>변경 실패!</p>");
            e.printStackTrace(out);
        }
        out.println("</body>");
        out.println("</html>");
    }
    
}