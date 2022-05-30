package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc //스프링 MVC 설정  (기본적인 구성 설정해줌) , WebMvcConfigurer 인터페이스는 스프링 MVC 의 개별 설정을 조정
public class MvcConfig implements WebMvcConfigurer {


    //DispatcherServlet
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }


    //JSP 이용해서 컨트롤러 실행 결과를 보여주기 위한 설정 추가
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/view/", ".jsp");
    }
}
