package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.MemberDao;

@Configuration
public class AppCtx {

    //스프링이 제공하는 DB 연동 기능은 DataSource 를 사용해서 DB Connection 을 구함
    //DB 연동에 사용할 DataSource 를 스프링 빈으로 등록하고 DB 연동 기능을 구현한
    //Bean 객체는 DataSource 를 주입 받아 사용한다.
    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        DataSource ds = new DataSource(); //DataSource 객체 생성
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost/spring5s?characterEncoding=utf8");
        ds.setUsername("spring5");
        ds.setPassword("spring5");
        ds.setInitialSize(2);
        ds.setMaxActive(100);
        return ds;
    }

    @Bean
    public MemberDao memberDao() {
        return new MemberDao(dataSource());
    }

}
