package bitcamp.pms.controller.json;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bitcamp.pms.domain.Board;
import bitcamp.pms.service.BoardService;

@RestController
@RequestMapping("/board/")
public class BoardController {
//    사용할 dao 선언해주기

    @Autowired
    BoardService boardService;

    @RequestMapping("list")
    public Object list(@RequestParam(defaultValue="1") int page, 
            @RequestParam(defaultValue="3") int size) throws Exception {

        if (page < 1) page = 1;
        if (size < 1 || size > 20) size = 3;
        
        List<Board> list = boardService.list(page,size); 
        HashMap<String, Object> data = new HashMap<>();
        
        data.put("list", list);
        data.put("page", page);
        data.put("size", size);
        data.put("totalPage", boardService.getTotalPage(size));
        return data;
    }

    @GetMapping("form")
    public String form() {
        return "board/form";
    }

    @PostMapping("add")
    public Object add(Board board) throws Exception {

        HashMap<String, Object> result = new HashMap<>();
        boardService.add(board);
        result.put("status", "success");
        return result;
    }

    @RequestMapping("delete")
    public Object delete(int no) throws Exception {

        HashMap<String, Object> result = new HashMap<>();
        if (boardService.delete(no) == 0) {
            result.put("staus", "fail");
            result.put("error", "해당 게시글이 없습니다.");
        } else {
            result.put("status","success");
        }
        return result;
        
    }
    
    @RequestMapping("update")
    public Object update(Board board) throws Exception {
        
        HashMap<String, Object> result = new HashMap<>();
        if (boardService.update(board) == 0) {
            result.put("staus", "fail");
            result.put("error", "해당 게시글이 없습니다.");
        } else {
            result.put("status","success");
        }
        return result;
    }
    
    @RequestMapping("view/{no}")
    public Object view(@PathVariable int no) throws Exception {
                
                Board board = boardService.get(no);
                
                HashMap<String, Object> data  = new HashMap<>();
                data.put("board", board);
                return data;
    }

}
