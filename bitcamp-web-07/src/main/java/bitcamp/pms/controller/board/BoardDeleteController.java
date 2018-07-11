package bitcamp.pms.controller.board;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.controller.PageController;
import bitcamp.pms.dao.BoardDao;

public class BoardDeleteController implements PageController {
    
    BoardDao boardDao;
    
    public BoardDeleteController(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    public String service(
            HttpServletRequest request, 
            HttpServletResponse response) throws Exception{
        
        int no = Integer.parseInt(request.getParameter("no"));
        
        response.setContentType("text/html;charset=UTF-8");
                
                    
                int del = boardDao.delete(no);
                request.setAttribute("del", del);
                return "/board/delete.jsp";
            
    }
    
}