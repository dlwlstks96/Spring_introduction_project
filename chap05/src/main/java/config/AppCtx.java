package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import spring.*;

@Configuration //스프링의 설정 클래스
@ComponentScan(basePackages = {"spring"})
public class AppCtx {

//    @Bean //아래 메소드가 생성한 객체를 스프링 빈으로 설정
//    public MemberDao memberDao() {
//        return new MemberDao();
//    }

//    @Bean
//    public MemberRegisterService memberRegSvc() {
//        return new MemberRegisterService();
//    }

//    @Bean
//    public ChangePasswordService changePwdSvc() {
//        return new ChangePasswordService();
//    }

    @Bean
    @Qualifier("printer")
    public MemberPrinter memberPrinter1() {
        return new MemberPrinter();
    }

    @Bean
    @Qualifier("summaryPrinter")
    public MemberSummaryPrinter memberPrinter2() {
        return new MemberSummaryPrinter();
    }

    @Bean
    public VersionPrinter versionPrinter() {
        VersionPrinter versionPrinter = new VersionPrinter();
        versionPrinter.setMajorVersion(5);
        versionPrinter.setMinorVersion(0);
        return versionPrinter;
    }

}
