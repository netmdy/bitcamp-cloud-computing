package bitcamp.pms.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bitcamp.pms.domain.Team;
import bitcamp.pms.service.TeamService;

@Controller
@RequestMapping("/team")
public class TeamController {

    @Autowired TeamService teamService;

    @RequestMapping("form")
    public void form() {
    }
    
    @RequestMapping("add")
    public String add(Team team) throws Exception {
        teamService.add(team);
        return "redirect:list";
    }
    
    @RequestMapping("delete")
    public String delete(@RequestParam("name") String name) throws Exception {
        int count = teamService.delete(name);
        if (count == 0) {
            throw new Exception ("해당 팀이 없습니다.");
        }
        return "redirect:list";
    }
    
    @RequestMapping("list{page}")
    public void list(@MatrixVariable(defaultValue="1") int pageNo,
            @MatrixVariable(defaultValue="3") int pageSize,
            Map<String,Object> map) throws Exception { 
        
        map.put("list", teamService.list(pageNo, pageSize));
    }
    
    @RequestMapping("update")
    public String update(Team team) throws Exception {
        
        int count = teamService.update(team);
        if (count == 0) {
            throw new Exception("<p>해당 팀이 존재하지 않습니다.</p>");
        }
        return "redirect:list";
    }
    
    @RequestMapping("{name}")
    public String view(
            @PathVariable String name,
            Map<String,Object> map) throws Exception {
        
        Team team = teamService.getWithMembers(name);
        if (team == null) {
            throw new Exception("유효하지 않은 팀입니다.");
        }
        map.put("team", team);
        return "team/view";
    }
    
}
