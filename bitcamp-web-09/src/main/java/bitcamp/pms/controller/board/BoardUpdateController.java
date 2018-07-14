package bitcamp.pms.controller.board;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.annotation.Autowired;
import bitcamp.pms.annotation.Controller;
import bitcamp.pms.annotation.RequestMapping;
import bitcamp.pms.dao.BoardDao;
import bitcamp.pms.domain.Board;

@Controller("/board/update")
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
    @RequestMapping
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