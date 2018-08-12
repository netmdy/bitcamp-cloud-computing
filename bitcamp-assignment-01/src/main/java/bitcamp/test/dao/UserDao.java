package bitcamp.test.dao;

import java.util.List;

import bitcamp.test.domain.User;


public interface UserDao {
    
    public List<User> selectList(int no) ;
    public User selectOne(int uno);
    public int update(User user);
    public int insert(User user);
    public int delete(int uno);
    public List<User> searchName(String sName);
}
