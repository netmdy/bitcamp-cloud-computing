package bitcamp.pms.servlet.board;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.dao.BoardDao;
import bitcamp.pms.domain.Board;

@SuppressWarnings("serial")
@WebServlet("/board/view")
public class BoardViewServlet extends HttpServlet {
    
    @Override
    protected void doGet(
            HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        
        int no = Integer.parseInt(request.getParameter("no"));
        
        response.setContentType("text/html;charset=UTF-8");
        
        try {
                BoardDao boardDao = (BoardDao) getServletContext().getAttribute("boardDao");
                        
                Board board = boardDao.selectOne(no);
                
                request.setAttribute("board", board);
                request.setAttribute("view", "/board/view.jsp");
                
        } catch (Exception e) {
            request.setAttribute("error", e);
        }

    }
    
}