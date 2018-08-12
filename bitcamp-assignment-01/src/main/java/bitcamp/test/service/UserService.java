package bitcamp.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitcamp.test.dao.UserDao;
import bitcamp.test.domain.User;

@Service
public class UserService {
    
    @Autowired
    UserDao userDao;

    public List<User> list(int no) {
        return userDao.selectList(no);
    }

    public User get(int uno) {
        return userDao.selectOne(uno);
    }

    public int update(User user) {
        int count =userDao.update(user);
                
    return count;
    }

    public int add(User user) {

        return userDao.insert(user);
    }

    public int delete(int uno) {

        return userDao.delete(uno);
    }

    public List<User> getSearch(String sName) {
        return userDao.searchName(sName);
    }

}
