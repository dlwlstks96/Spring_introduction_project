package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import spring.MemberDao;
import spring.MemberPrinter;

@Configuration
@Import(AppConf2.class) //AppConf2 설정 파일을 현재 설정 파일과 함께 사용한다는 뜻
public class AppConfImport {

    @Bean
    public MemberDao memberDao() {
        return new MemberDao();
    }

    @Bean
    public MemberPrinter memberPrinter() {
        return new MemberPrinter();
    }

}
