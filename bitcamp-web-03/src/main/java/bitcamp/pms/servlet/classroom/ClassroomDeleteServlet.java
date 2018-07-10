package bitcamp.pms.servlet.classroom;

import java.io.IOException;
import java.io.PrintWriter;

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
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta http-equiv='Refresh' content='1;url=list'>");
        out.println("<title>강의 삭제</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>강의 삭제 결과</h1>");
        
        try {
            ClassRoomDao croom = (ClassRoomDao) getServletContext().getAttribute("classRoomDao");
                    
            croom.delete(no);
//            response.sendRedirect("list");
            out.println("<p>삭제 성공</p>");
            
        } catch (Exception e) {
            out.println("<p>삭제 실패!</p>");
            e.printStackTrace(out);
        }
        out.println("</body>");
        out.println("</html>");
    }
    
}

