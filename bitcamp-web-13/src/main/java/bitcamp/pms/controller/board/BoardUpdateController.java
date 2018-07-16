package bitcamp.pms.controller.board;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import bitcamp.pms.dao.BoardDao;
import bitcamp.pms.domain.Board;

@Controller
public class BoardUpdateController {

    BoardDao boardDao;
    
    
    public BoardUpdateController(BoardDao boardDao) {
        this.boardDao = boardDao;
    }
    public BoardUpdateController() {}
    @Autowired
    public void setBoardDao(BoardDao boardDao) {
        this.boardDao = boardDao;
    }
    @RequestMapping("/board/update")
    public String update(
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