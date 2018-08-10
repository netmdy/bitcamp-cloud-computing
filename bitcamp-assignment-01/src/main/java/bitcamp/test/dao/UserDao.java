package bitcamp.test.dao;

import java.util.List;

import bitcamp.test.domain.User;


public interface UserDao {
    
    public List<User> selectList(int no) ;
    public User selectOne(String name);
    public User selectView(int no);
    
}
