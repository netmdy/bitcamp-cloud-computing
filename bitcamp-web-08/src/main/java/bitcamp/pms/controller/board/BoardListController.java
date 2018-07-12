package bitcamp.pms.controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.annotation.RequestMapping;
import bitcamp.pms.dao.BoardDao;
import bitcamp.pms.domain.Board;

public class BoardListController {
//    사용할 dao 선언해주기
    BoardDao boardDao;
//    contextLoaderListener에서 보내준 db정보를 받아 dao에 넣고 사용할 준비 하기    
    public BoardListController(BoardDao boardDao) {
        this.boardDao = boardDao;
    }
    @RequestMapping
    public String list(
            HttpServletRequest request, 
            HttpServletResponse response) throws Exception{
// boardDao에 있는 selectList실행하고 List<Board>형식으로 list에 저장하기
             List<Board> list = boardDao.selectList();
// request 속성에 list란 이름이로 list 담기             
             request.setAttribute("list", list);
// board/list.jsp 문자열을 Dispatcherservlet으로 보내기 
             return "/board/list.jsp";
    }
    
}

