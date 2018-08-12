package bitcamp.test.controller.json;
 
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bitcamp.test.domain.User;
import bitcamp.test.service.UserService;
 
@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired UserService userService;
    
    @RequestMapping("list")
    public Object list(int no) throws Exception{
        
        List<User> list = userService.list(no);
        HashMap<String,Object> data = new HashMap<>();
        
        data.put("list", list);
        return data;
    }
   
    @RequestMapping("view/{uno}")
    public Object view(@PathVariable int uno) throws Exception{
        
        HashMap<String,Object> data = new HashMap<>();
        data.put("user", userService.get(uno));    
        return data; 
    }

    @RequestMapping("search/{sName}")
    public Object search(@PathVariable String sName) throws Exception{
        
        List<User> list = userService.getSearch(sName);
        HashMap<String,Object> data = new HashMap<>();
        data.put("list", list );    
        return data; 
    }
    
    @RequestMapping("update")
    public Object update(User user)
            throws Exception {
        HashMap<String, Object> result = new HashMap<>();
            if (userService.update(user) == 0) {
                result.put("status","fail");
                result.put("error","해당 아이디가 없습니다.");

            } else {
                result.put("status", "success");
                result.put("uno", user.getUno());
                result.put("no", user.getNo());
            }
            return result;
    }
    
    @PostMapping("add")
    public Object add(User user) throws Exception {
        
        HashMap<String, Object> result = new HashMap<>();
        if (userService.add(user) == 0) {
            result.put("status", "fail");
        }else {
            result.put("status", "success");
            result.put("uno", user.getUno());
            result.put("no", user.getNo());
        }

        return result;
    }
    
    @RequestMapping("delete")
    public Object delete(int uno) throws Exception {
                
        HashMap<String, Object> result = new HashMap<>();
        if (userService.delete(uno) == 0) {
            result.put("status","fail");
            result.put("error","해당 아이디가 없습니다.");
        } else {
            result.put("status", "success");
        }
        return result;
    }
    
}