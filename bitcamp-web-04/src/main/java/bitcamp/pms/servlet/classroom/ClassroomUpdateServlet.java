package bitcamp.pms.servlet.classroom;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
                ClassRoom cr = new ClassRoom();
                cr.setCrno(Integer.parseInt(request.getParameter("no")));
                cr.setTitle(request.getParameter("title"));
                cr.setStartDate(Date.valueOf(request.getParameter("startDate")));
                cr.setEndDate(Date.valueOf(request.getParameter("endDate")));
                cr.setRoom(request.getParameter("room"));
                
                update(cr);
                
                out.println("<p>변경 완료</p>");
                
        } catch (Exception e) {
            out.println("<p>변경 실패!</p>");
            e.printStackTrace(out);
        }
        out.println("</body>");
        out.println("</html>");
    }
    public void update(ClassRoom cr) throws Exception{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (
                Connection con = DriverManager.getConnection(
                    "jdbc:mysql://13.209.8.213:3306/studydb",
                    "study", "1111");
                PreparedStatement stmt = con.prepareStatement(
                    "update pms2_classroom set titl=?, sdt=?, edt=?, room=? where crno=?");) {
                
                stmt.setString(1, cr.getTitle());
                stmt.setDate(2, cr.getStartDate());
                stmt.setDate(3, cr.getEndDate());
                stmt.setString(4, cr.getRoom());
                stmt.setInt(5, cr.getCrno());
                
                stmt.executeUpdate();
                    
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}