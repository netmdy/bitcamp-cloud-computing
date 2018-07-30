package bitcamp.pms.controller.json;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bitcamp.pms.domain.ClassRoom;
import bitcamp.pms.service.ClassRoomService;

@RestController
@RequestMapping("/classroom")
public class ClassRoomController {

    @Autowired
    ClassRoomService classRoomService;
    
    @PostMapping("add")
    public Object add(ClassRoom cr) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
            classRoomService.add(cr);
            result.put("status","success");
            return result;
    }

    @RequestMapping("list")
    public Object list(@RequestParam(defaultValue="1") int page,
                        @RequestParam(defaultValue="3") int size)
            throws Exception {
           
        HashMap<String, Object> data = new HashMap<>();
            List<ClassRoom> list = classRoomService.list(page,size);
            data.put("list", list);
            data.put("page", page);
            data.put("size", size);
            data.put("totalPage", classRoomService.getTotalPage(size));
            
            return data;
    }
    
    @RequestMapping("view/{no}")
    public Object view(@PathVariable int no)
            throws Exception {

        HashMap<String, Object> data = new HashMap<>();
            
            data.put("classRoom", classRoomService.get(no));
            return data;
    }
    
    @RequestMapping("update")
    public Object update(ClassRoom classRoom) throws Exception{
                
        HashMap<String, Object> result = new HashMap<>();
                if(classRoomService.update(classRoom) == 0) {
                    result.put("status", "fail");
                    result.put("error", "해당 강의가 없습니다");
                }else {
                    result.put("status","success");
                }
                return result;
    }
    
    @RequestMapping("delete")
    public Object delete(int no) throws Exception {
        
        HashMap<String, Object> result = new HashMap<>();
            if (classRoomService.delete(no) == 0) {
                result.put("status", "fail");
                result.put("error", "해당 강의가 없습니다");
            }else {
                result.put("status","success");
            }
            return result;
    }

}
