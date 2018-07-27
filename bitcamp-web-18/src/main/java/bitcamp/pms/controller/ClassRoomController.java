package bitcamp.pms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bitcamp.pms.domain.ClassRoom;
import bitcamp.pms.service.ClassRoomService;

@Controller
@RequestMapping("/classroom")
public class ClassRoomController {

    @Autowired
    ClassRoomService classRoomService;
    
    @PostMapping("add")
    public String add(ClassRoom cr) throws Exception {
            classRoomService.add(cr);
            return "redirect:list";
    }
    
    @RequestMapping("form")
    public void form() {
    }
    
    @RequestMapping("list")
    public String list(@RequestParam(defaultValue="1") int page,
                        @RequestParam(defaultValue="3") int size, Model model)
            throws Exception {
           
            List<ClassRoom> list = classRoomService.list(page,size);
            model.addAttribute("list", list);
            model.addAttribute("page", page);
            model.addAttribute("size", size);
            model.addAttribute("totalPage", classRoomService.getTotalPage(size));
            
            return "classroom/list";
    }
    
    @RequestMapping("view/{no}")
    public String view(@PathVariable int no, Model model)
            throws Exception {

            ClassRoom cr = classRoomService.get(no);
            
            model.addAttribute("classRoom", cr);
            return "classroom/view";
    }
    
    @RequestMapping("update")
    public String update(ClassRoom classRoom) throws Exception{
                
                if(classRoomService.update(classRoom) == 0) {
                    throw new Exception("업데이트가 실패하였습니다.");
                }

                return "classroom/update";
    }
    
    @RequestMapping("delete")
    public String delete(int no) throws Exception {
        
            classRoomService.delete(no);
            return "classroom/delete";
    }

}
