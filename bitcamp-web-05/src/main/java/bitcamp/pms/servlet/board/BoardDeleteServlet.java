package bitcamp.pms.servlet.board;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.dao.BoardDao;

@SuppressWarnings("serial")
@WebServlet("/board/delete")
public class BoardDeleteServlet extends HttpServlet {
    
    @Override
    protected void doGet(
            HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        
        int no = Integer.parseInt(request.getParameter("no"));
        
        response.setContentType("text/html;charset=UTF-8");
                
        try {
                BoardDao boardDao = (BoardDao) getServletContext().getAttribute("boardDao");
                    
                int del = boardDao.delete(no);
                request.setAttribute("del", del);
                RequestDispatcher rd = request.getRequestDispatcher("/board/delete.jsp");
                rd.include(request, response);
            
        } catch (Exception e) {
            
        }
    }
    
}