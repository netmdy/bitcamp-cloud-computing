package bitcamp.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitcamp.test.dao.MemberDao;
import bitcamp.test.domain.Member;

@Service
public class MemberService {
    
    @Autowired
    MemberDao memberDao;

    public void add(Member member) {
        memberDao.insert(member);
    }

    public int login(Member member) {
        
        return memberDao.login(member);
    }
    
}
