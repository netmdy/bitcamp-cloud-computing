package bitcamp.pms.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitcamp.pms.dao.TaskDao;
import bitcamp.pms.domain.Task;

@Service
public class TaskService {

    @Autowired TaskDao taskDao;
    
    public List<Task> list(String teamName, int page, int size) {
        HashMap<String,Object> params = new HashMap<>();
        
        params.put("startIndex", (page - 1) * size);
        params.put("pageSize", size);
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

    public int getTotalPage(int size, String teamName) {
        int count = taskDao.countAll(teamName);
        int totalPage = count /size;
        if (count % size > 0)
            totalPage++;
        
        System.out.println("count" + count);
        System.out.println(totalPage);
        return totalPage;
    }
}
