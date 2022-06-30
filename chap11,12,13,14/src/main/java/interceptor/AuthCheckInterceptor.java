package interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//HandlerInterceptor 구현하면 어디에 적용할지 설정 필요, 본 프로젝트에서는 MvcConfig에 설정
public class AuthCheckInterceptor implements HandlerInterceptor {

    //preHandle() 메소드에서 true를 리턴하면 컨트롤러를 실행
    //false를 리턴하면 로그인 상태가 아니므로 리다이렉트한다.
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Object authInfo = session.getAttribute("authInfo");
            if (authInfo != null) {
                return true;
            }
        }
        response.sendRedirect(request.getContextPath() + "/login"); //requset.getContextPath()는 현재 컨텍스트 경로 리턴
        return false;
    }

}
