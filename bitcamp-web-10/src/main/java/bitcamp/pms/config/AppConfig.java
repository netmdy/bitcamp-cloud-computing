package bitcamp.pms.config;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    public AppConfig() {
        System.out.println("AppConfig() 호출");
    }
    /*
    @Bean("SqlSessionFactory")
    public SqlSessionFactory getSqlSessionFactory() throws Exception {
        System.out.println("AppConfig.getSqlSessionFactory() 호출");
        String resource = "bitcamp/pms/config/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
          new SqlSessionFactoryBuilder().build(inputStream);

        return sqlSessionFactory;
    }*/
    
}

