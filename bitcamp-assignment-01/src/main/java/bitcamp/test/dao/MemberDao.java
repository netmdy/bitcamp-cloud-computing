package bitcamp.test.dao;

import bitcamp.test.domain.Member;


public interface MemberDao {
    
    public int insert(Member member);
    public int login(Member member);
}
