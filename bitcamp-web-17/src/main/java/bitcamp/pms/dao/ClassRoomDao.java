package bitcamp.pms.dao;

import java.util.HashMap;
import java.util.List;

import bitcamp.pms.domain.ClassRoom;

public interface ClassRoomDao {

    public List<ClassRoom> selectList(HashMap<String, Object> params);
    public ClassRoom selectOne(int no);
    public int update(ClassRoom classRoom);
    public int delete(int no) ;
    public int insert(ClassRoom classRoom);
    public int countAll();
}
