package bitcamp.pms.controller.json;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bitcamp.pms.domain.Team;
import bitcamp.pms.service.TeamService;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired TeamService teamService;

    @RequestMapping("add")
    public Object add(Team team) throws Exception {
        
        HashMap<String, Object> result = new HashMap<>();
        if (teamService.add(team) == 0) {
            result.put("status","fail");
            result.put("error","해당 팀이 이미 존재 합니다");
        }else {
            result.put("status","success");
        }
        return result;
    }
    
    @RequestMapping("delete")
    public Object delete(@RequestParam("name") String name) throws Exception {
        
        HashMap<String, Object> result = new HashMap<>();
        int count = teamService.delete(name);
        if (count == 0) {
            result.put("status","fail");
            result.put("error","해당 팀이 없습니다.");
        }else {
            result.put("status","success");
        }
        return result;
    }
    
    @RequestMapping("list")
    public Object list(@RequestParam(defaultValue="1") int page,
            @RequestParam(defaultValue="3") int size
            ) throws Exception { 
        HashMap<String,Object> map = new HashMap<>();
        if (page < 1) page = 1;
        if (size < 1 || size > 20) size = 3;

        map.put("list", teamService.list(page, size));
        map.put("totalPage", teamService.getTotal(size));
        map.put("page", page);
        map.put("size", size);
        return map;
    }
    
    @RequestMapping("update")
    public Object update(Team team) throws Exception {
        
        HashMap<String, Object> result = new HashMap<>();
        int count = teamService.update(team);
        if (count == 0) {
            result.put("status","fail");
            result.put("error","해당 팀이 존재하지 않습니다");
        }else {
            result.put("status","success");
        }
        return result;
    }
    
    @RequestMapping("view/{name}")
    public Object view(
            @PathVariable String name
            ) throws Exception {
        
        HashMap<String,Object> map = new HashMap<>();
        Team team = teamService.getWithMembers(name);
        if (team == null) {
            throw new Exception("유효하지 않은 팀입니다.");
        }
        map.put("team", team);
        return map;
    }
    
}
