package bitcamp.pms.servlet.board;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.dao.BoardDao;
import bitcamp.pms.domain.Board;

@SuppressWarnings("serial")
@WebServlet("/board/update")
public class BoardUpdateServlet extends HttpServlet {
    
    @Override
    protected void doPost(
            HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");

        
        try {
                Board board = new Board();
                
                board.setBno(Integer.parseInt(request.getParameter("no")));
                board.setCont(request.getParameter("content"));
                board.setTitl(request.getParameter("title"));
            
                BoardDao boardDao = new BoardDao();
                
                boardDao.update(board);
                
                RequestDispatcher rd = request.getRequestDispatcher("/board/update.jsp");
                rd.include(request, response);
            
        } catch (Exception e) {
            request.setAttribute("error", e);
            RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
            rd.forward(request, response);
            
        }
    }
    
}