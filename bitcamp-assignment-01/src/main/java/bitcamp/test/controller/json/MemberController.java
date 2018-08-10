package bitcamp.test.controller.json;
 
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bitcamp.test.domain.Member;
import bitcamp.test.service.MemberService;
 
@RestController
@RequestMapping("/member")
public class MemberController {
    
    @Autowired MemberService memberService;
    
    @PostMapping("add")
    public Object add(Member member) throws Exception {
        
        HashMap<String, Object> result = new HashMap<>();
        memberService.add(member);
        result.put("status", "success");
        return result;
    }
    
    
    @PostMapping("login")
    public Object list(Member member) throws Exception{

            HashMap<String,Object> result = new HashMap<>();
            
            int loginv = memberService.login(member);
            result.put("status", "success");
            result.put("loginv", loginv);
            return result;

    }

    
}