package bitcamp.pms.controller.board;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import bitcamp.pms.dao.BoardDao;

@Controller
public class BoardDeleteController {
    
    BoardDao boardDao;
    
    public BoardDeleteController(BoardDao boardDao) {
        this.boardDao = boardDao;
    }
    public BoardDeleteController() {}
    @Autowired
    public void setBoardDao(BoardDao boardDao) {
        this.boardDao = boardDao;
    }
    @RequestMapping("/board/delete")
    public String delete(
            HttpServletRequest request, 
            HttpServletResponse response) throws Exception{
        
        int no = Integer.parseInt(request.getParameter("no"));
        
        response.setContentType("text/html;charset=UTF-8");
                
                    
                int del = boardDao.delete(no);
                request.setAttribute("del", del);
                return "board/delete";
            
    }
    
}