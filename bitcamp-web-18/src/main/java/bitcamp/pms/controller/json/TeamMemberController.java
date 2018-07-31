package bitcamp.pms.controller.json;

import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bitcamp.pms.service.MemberService;
import bitcamp.pms.service.TeamService;

@RestController
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
    public Object add(
            @RequestParam("teamName") String teamName,
            @RequestParam("memberId") String memberId
            ) throws Exception {
        
        HashMap<String,Object> result = new HashMap<>();
        if (teamService.get(teamName) == null) {
            result.put("status","fail");
            result.put("error", teamName + " 팀은 존재하지 않습니다.");
        }
        if (memberService.get(memberId) == null) {
            result.put("status","fail");
            result.put("error", "해당 회원이 없습니다!");
        }
        if (teamService.isMember(teamName, memberId)) {
            result.put("status","fail");
            result.put("message", "이미 등록된 회원입니다.");
        }
        teamService.addMember(teamName, memberId);
        return result;
    }
    
    @RequestMapping("delete")
    public Object delete(
            @RequestParam("teamName") String teamName,
            @RequestParam("memberId") String memberId
            ) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        if (teamService.deleteMember(teamName, memberId) == 0) {
            result.put("status","fail");
            result.put("error", "해당 회원이 없습니다!");
        }else {
            result.put("status", "success");
        }
        return result;
        // 개발자가 요청이나 응답헤더를 직접 작성하여 값을 주고 받으로 한다면,
        // URL 인코딩과 URL 디코딩을 손수 해 줘야 한다.
    }
    
    @RequestMapping("list")
    public Object list(
            @RequestParam("name") String teamName
            ) throws Exception {
        HashMap<String,Object> data = new HashMap<>();
        return data.put("members", teamService.getMembersWithEmail(teamName));
    }
}