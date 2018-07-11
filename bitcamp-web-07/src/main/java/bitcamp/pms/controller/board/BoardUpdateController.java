package bitcamp.pms.controller.board;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.controller.PageController;
import bitcamp.pms.dao.BoardDao;
import bitcamp.pms.domain.Board;

public class BoardUpdateController implements PageController{

    BoardDao boardDao;
    
    
    public BoardUpdateController(BoardDao boardDao) {
        this.boardDao = boardDao;
    }


    public String service(
            HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        
        response.setContentType("text/html;charset=UTF-8");
        
                Board board = new Board();
                
                board.setBno(Integer.parseInt(request.getParameter("no")));
                board.setCont(request.getParameter("content"));
                board.setTitl(request.getParameter("title"));
                
                boardDao.update(board);
                return "/board/update.jsp";
            

    }
    
}