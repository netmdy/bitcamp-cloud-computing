package bitcamp.pms.controller;

import java.net.URLEncoder;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bitcamp.pms.service.MemberService;
import bitcamp.pms.service.TeamService;

@Controller
@RequestMapping("/team/member")
public class TeamMemberController {
    
    TeamService teamService;
    MemberService memberService;
    
    public TeamMemberController(
            TeamService teamService,
            MemberService memberService) {
        
        this.teamService = teamService;
        this.memberService = memberService;
    }
    
    @RequestMapping("add")
    public String add(
            @RequestParam("teamName") String teamName,
            @RequestParam("memberId") String memberId,
            Map<String,Object> map) throws Exception {
        
        if (teamService.get(teamName) == null) {
            throw new Exception(teamName + " 팀은 존재하지 않습니다.");
        }

        if (memberService.get(memberId) == null) {
            map.put("message", "해당 회원이 없습니다!");
            return "team/member/fail";
        }
        
        if (teamService.isMember(teamName, memberId)) {
            map.put("message", "이미 등록된 회원입니다.");
            return "team/member/fail";
        }
        teamService.addMember(teamName, memberId);
        return "redirect:../" + 
                URLEncoder.encode(teamName, "UTF-8");
    }
    
    @RequestMapping("delete")
    public String delete(
            @RequestParam("teamName") String teamName,
            @RequestParam("memberId") String memberId,
            Map<String,Object> map) throws Exception {
         
        int count = teamService.deleteMember(teamName, memberId);
        if (count == 0) {
            map.put("message", "해당 회원이 없습니다!");
            return "team/member/fail";
        }
        return "redirect:../" + 
                URLEncoder.encode(teamName, "UTF-8");
        // 개발자가 요청이나 응답헤더를 직접 작성하여 값을 주고 받으로 한다면,
        // URL 인코딩과 URL 디코딩을 손수 해 줘야 한다.
    }
    
    @RequestMapping("list")
    public void list(
            @RequestParam("name") String teamName,
            Map<String,Object> map) throws Exception {
        map.put("members", teamService.getMembersWithEmail(teamName));
    }
}