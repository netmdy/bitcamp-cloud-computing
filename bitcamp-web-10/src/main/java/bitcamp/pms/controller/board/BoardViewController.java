package bitcamp.pms.controller.board;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import bitcamp.pms.annotation.RequestMapping;
import bitcamp.pms.dao.BoardDao;
import bitcamp.pms.domain.Board;

@Controller("/board/view")
public class BoardViewController {
    
    BoardDao boardDao;
    
    public BoardViewController(BoardDao boardDao) {
        this.boardDao = boardDao;
    }
    
    public BoardViewController() {}
    @Autowired
    public void setBoardDao(BoardDao boardDao) {
        this.boardDao = boardDao;
    }
    @RequestMapping
    public String view(
            HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        
        int no = Integer.parseInt(request.getParameter("no"));
        
        response.setContentType("text/html;charset=UTF-8");
        
                Board board = boardDao.selectOne(no);
                
                request.setAttribute("board", board);
                return "/board/view.jsp";

    }
    
}