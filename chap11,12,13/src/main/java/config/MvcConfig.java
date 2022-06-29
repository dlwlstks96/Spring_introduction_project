package config;

import controller.RegisterRequestValidator;
import interceptor.AuthCheckInterceptor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.*;

//edit configuration 에서 tomcat path 를 webapp 폴더로
//설정해줘야 정상적으로 viewResolver 작동
@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/view/", ".jsp");
	}

	//webMvcConfigurer의 addViewControllers 메소드를 재정의하면
	// 컨트롤러 구현 없이 요청 경로와 뷰 이름을 연결할 수 있다.
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/main").setViewName("main");
	}

	//MessageSource 타입의 빈 추가 -> 뷰 코드에서 파일을 읽어 오기 위함
	//빈의 아이디를 꼭 messageSource로 지정해야만 정상 작동
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource ms =
				new ResourceBundleMessageSource();
		ms.setBasenames("message.label");
		ms.setDefaultEncoding("UTF-8");
		return ms;
	}

	//스프링 MVC는 WebMvcConfigurer 인터페이스의 getValidator() 메소드가
	//리턴한 객체를 글로벌 범위 Validator로 사용
	@Override
	public Validator getValidator() {
		return new RegisterRequestValidator();
	}

	//인터셉터를 설정하는 메소드
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authCheckInterceptor()) //HandlerInterceptor 객체를 설정
				.addPathPatterns("/edit/**"); //addInterceptor() 메소드는 InterceptorRegistration 객체를 리턴하는데
											  //이 객체의 addPathPatterns() 메소드는 인터셉터를 적용할 경로 패턴을 지정
	}

	@Bean
	public AuthCheckInterceptor authCheckInterceptor() {
		return new AuthCheckInterceptor();
	}

}
