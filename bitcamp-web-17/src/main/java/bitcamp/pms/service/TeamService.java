package bitcamp.pms.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitcamp.pms.dao.TaskDao;
import bitcamp.pms.dao.TeamDao;
import bitcamp.pms.dao.TeamMemberDao;
import bitcamp.pms.domain.Member;
import bitcamp.pms.domain.Team;

@Service
public class TeamService {

    @Autowired TeamDao teamDao;
    @Autowired TeamMemberDao teamMemberDao;
    @Autowired TaskDao taskDao;
   
    public List<Team> list(int pageNo, int pageSize) {
        HashMap<String,Object> params = new HashMap<>();
        params.put("startRowNo", (pageNo - 1) * pageSize);
        params.put("pageSize", pageSize);
        
        return teamDao.selectList(params);
    }
    
    public Team get(String name) {
        return teamDao.selectOne(name);
    }
    
    public Team getWithMembers(String name) {
        return teamDao.selectOneWithMembers(name);
    }
    
    public int add(Team member) {
        return teamDao.insert(member);
    }
    
    public int update(Team member) {
        return teamDao.update(member);
    }
    
    public int delete(String name) {
        HashMap<String,Object> params = new HashMap<>();
        params.put("teamName", name);
        
        teamMemberDao.delete(params);
        taskDao.deleteByTeam(name);
        return teamDao.delete(name);
    }
    
    public boolean isMember(String teamName, String memberId) {
        HashMap<String,Object> params = new HashMap<>();
        params.put("teamName", teamName);
        params.put("memberId", memberId);
        
        return teamMemberDao.isExist(params);
    }
    
    public int addMember(String teamName, String memberId) {
        HashMap<String,Object> params = new HashMap<>();
        params.put("teamName", teamName);
        params.put("memberId", memberId);
        
        return teamMemberDao.insert(params);
    }
    
    public int deleteMember(String teamName, String memberId) {
        HashMap<String,Object> params = new HashMap<>();
        params.put("teamName", teamName);
        params.put("memberId", memberId);
        
        return teamMemberDao.delete(params);
    }
    
    public List<Member> getMembersWithEmail(String teamName) {
        return teamMemberDao.selectListWithEmail(teamName);
    }

    public int getTotal(int pageSize) {
        int count = teamDao.getTotalPage();
        int totalPage = count / pageSize;
        if (count % pageSize > 0) {
            totalPage++;
        }
        
        return totalPage;
    }
    
    
}

