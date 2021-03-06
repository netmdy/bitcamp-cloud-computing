package bitcamp.pms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import bitcamp.pms.dao.MemberDao;
import bitcamp.pms.domain.Member;

@Controller
public class MemberAddController {

    MemberDao memberDao;
    
    public MemberAddController(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Autowired
    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }


    public MemberAddController() {}
    
    @RequestMapping("/member/add")
    public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        들어오는 요청이 get이면 form.jsp로 보냄
        if (request.getMethod().equals("GET")) {
            return "member/form";
        }
    
            Member member = new Member();
            member.setId(request.getParameter("id"));
            member.setEmail(request.getParameter("email"));
            member.setPassword(request.getParameter("password"));
            
            memberDao.insert(member);
            return "redirect:list";
            
    }

}
