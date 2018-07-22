package bitcamp.pms.test;

import org.springframework.context.annotation.Bean;

import bitcamp.pms.test.MemberDao;

public class MySpringConfig {
    
    @Bean("okok")
    public MemberDao getMemberDao( ) {
        return new MemberDao();
    }
}