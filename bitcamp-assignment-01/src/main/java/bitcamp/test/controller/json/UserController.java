package bitcamp.test.controller.json;
 
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
        
//        data.put("user", userService.view(no));
        
        return data;
        
    }
   
    @RequestMapping("view/{name}")
    public Object view(@PathVariable String name) throws Exception{
        
        HashMap<String,Object> data = new HashMap<>();
        data.put("user", userService.get(name));    
        return data; 
        }
    
}