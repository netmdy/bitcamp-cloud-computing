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
            
            String email = member.getEmail();
            System.out.println(email);
            int loginv = memberService.login(member);
            if (loginv == 0) {
                result.put("status", "fail");
            }else {
                System.out.println("login 성공");
                result.put("status", "success");
                result.put("member", memberService.get(email));
            }
            return result;
    }
    
    @RequestMapping("delete")
    public Object delete(int no) throws Exception {
                
        HashMap<String, Object> result = new HashMap<>();
        if (memberService.delete(no) == 0) {
            result.put("status","fail");
            result.put("error","해당 아이디가 없습니다.");
        } else {
            result.put("status", "success");
        }
        return result;
    }


    
}