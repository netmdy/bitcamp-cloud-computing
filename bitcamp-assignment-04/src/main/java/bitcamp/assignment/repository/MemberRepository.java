package bitcamp.assignment.repository;

import java.util.HashMap;

import bitcamp.assignment.domain.Member;

public interface MemberRepository {

    int insert(Member member);

    Member findByEmailAndPassword(HashMap<String, Object> params);
    
}
