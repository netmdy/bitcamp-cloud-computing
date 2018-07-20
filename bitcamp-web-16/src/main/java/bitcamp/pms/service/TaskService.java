package bitcamp.pms.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import bitcamp.pms.dao.TaskDao;
import bitcamp.pms.domain.Task;

@Service
public class TaskService {

TaskDao taskDao;
    
    public List<Task> list(String teamName, int pageNo, int pageSize) {
        HashMap<String,Object> params = new HashMap<>();
        params.put("startRowNo", (pageNo - 1) * pageSize);
        params.put("pageSize", pageSize);
        params.put("teamName", teamName);
        
        return taskDao.selectList(params);
    }
    
    public Task get(int no) {
        return taskDao.selectOne(no);
    }
    
    public int add(Task task) {
        return taskDao.insert(task);
    }
    
    public int update(Task task) {
        return taskDao.update(task);
    }
    
    public int delete(int no) {
        return taskDao.delete(no);
    }
}
