package bitcamp.pms.controller.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import bitcamp.pms.dao.BoardDao;
import bitcamp.pms.domain.Board;

@Controller
@RequestMapping("/board/")
public class BoardController {
//    사용할 dao 선언해주기
    @Autowired
    BoardDao boardDao;

    @RequestMapping("list")
    public String list(Model model) throws Exception {
// boardDao에 있는 selectList실행하고 List<Board>형식으로 list에 저장하기
        List<Board> list = boardDao.selectList();
// request 속성에 list란 이름이로 list 담기             
        model.addAttribute("list", list);
// board/list.jsp 문자열을 Dispatcherservlet으로 보내기 
        return "board/list";
    }

    @GetMapping("form")
    public String form() {
        return "board/form";
    }

    @PostMapping("add")
    public String add(Board board) throws Exception {

        boardDao.insert(board);
        return "redirect:list";
    }

    @RequestMapping("delete")
    public String delete(int no) throws Exception {

        boardDao.delete(no);
        return "board/delete";
    }
    
    @RequestMapping("update")
    public String update(Board board) throws Exception {
        
                int up = boardDao.update(board);
                if (up == 0) {
                    throw new Exception("업데이트가 적용 되지 않았어요");
                }
                return "board/update";

    }
    
    @RequestMapping("view/{no}")
    public String view(@PathVariable int no, Model model) throws Exception {
                
                Board board = boardDao.selectOne(no);
                
                model.addAttribute("board", board);
                return "board/view";
    }

}
