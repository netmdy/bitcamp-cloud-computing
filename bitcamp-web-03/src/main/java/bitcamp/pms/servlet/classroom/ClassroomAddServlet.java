// Controller 규칙에 따라 메서드 작성
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
@WebServlet("/classroom/add")
public class ClassroomAddServlet extends HttpServlet {
    
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
        out.println("<title>강의 등록</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>강의 등록 결과</h1>");
        
        try {
             ClassRoomDao croom = (ClassRoomDao) getServletContext().getAttribute("classRoomDao");
             ClassRoom cr = new ClassRoom();
             cr.setTitle(request.getParameter("title"));
             cr.setStartDate(Date.valueOf(request.getParameter("startDate")));
             cr.setEndDate(Date.valueOf(request.getParameter("endDate")));
             cr.setRoom(request.getParameter("room"));

             croom.insert(cr);
             out.println("<p>등록 성공!</p>");
        }catch (Exception e) {
            out.println("<p>등록 실패 ㅇㅅㅇ</p>");
        }
        out.println("</body>");
        out.println("</html>");
    }
    
}
