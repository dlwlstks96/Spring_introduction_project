package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

//@Configuration 애노테이션 이용하여 자바 설정에서의 프로퍼티 사용
@Configuration
public class PropertyConfig {

    //프로퍼티를 지정하는 메소드는 특수한 목적이기에 static으로 지정, 그렇지 않으면 정상 작동 X
    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer configurer =
                new PropertySourcesPlaceholderConfigurer();
        configurer.setLocations(
                new ClassPathResource("db.properties"),
                new ClassPathResource("info.properties"));
        return configurer;
    }

}
