package bitcamp.pms.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitcamp.pms.dao.ClassRoomDao;
import bitcamp.pms.domain.ClassRoom;

@Service
public class ClassRoomService {

    @Autowired
    ClassRoomDao classRoomDao;
    
    public List<ClassRoom> list(int page, int size) {
        
        HashMap<String,Object> params = new HashMap<>();
        
        params.put("startIndex", (page -1) * size);
        params.put("pageSize", size);
        
        return classRoomDao.selectList(params);
    }

    public void add(ClassRoom classRoom) {
        classRoomDao.insert(classRoom);
        
    }

    public void delete(int no) {
         classRoomDao.delete(no);
        
    }

    public int update(ClassRoom classRoom) {
        return classRoomDao.update(classRoom);
    }

    public ClassRoom get(int no) {
        return classRoomDao.selectOne(no);
    }

    public int getTotalPage(int size) {
        
        int count = classRoomDao.countAll();
        int totalPage = count /size;
        if(count % size > 0)
            totalPage++;
        
        return totalPage;
    }
    
    
    
}
