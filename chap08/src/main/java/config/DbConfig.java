package config;


import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbConfig {

    //JDBC, DataSource에 대한 공부 필요함
    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        DataSource ds = new DataSource(); //DataSource 객체 생성
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver"); //JDBC 드라이버 클래스를 mysql 드라이버 클래스로 사용
        //JDBC URL 지정
        ds.setUrl("jdbc:mysql://localhost/spring5fs?characterEncoding=utf8");
        ds.setUsername("spring5");
        ds.setPassword("spring5");
        ds.setInitialSize(2);
        ds.setMaxActive(10);
        return ds;
    }

}
