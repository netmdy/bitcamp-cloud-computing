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
        
        System.out.println("list들어옴");
            return userDao.selectList(no);
    }

    public User get(String name) {
        return userDao.selectOne(name);
    }

    public Object view(int no) {
        System.out.println("view들어옴");
        return userDao.selectView(no);
    }

}
