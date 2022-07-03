package controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import spring.Member;
import spring.MemberDao;
import spring.MemberRegisterService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//스프링 MVC에서 JSON 형식으로 데이터를 응답하기 위해선
//@Controller 애노테이션 대신 @RestController 애노테이션을 사용하면 된다.
@RestController
public class RestMemberController {

    private MemberDao memberDao;
    private MemberRegisterService memberRegisterService;

    @GetMapping("/api/members")
    public List<Member> members() {
        return memberDao.selectAll();
    }

    @GetMapping("/api/members/{id}")
    public Member member(@PathVariable Long id,
                         HttpServletResponse response) throws IOException {
        Member member = memberDao.selectById(id);
        if (member == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return member;
    }

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public void setMemberRegisterService(MemberRegisterService memberRegisterService) {
        this.memberRegisterService = memberRegisterService;
    }
}
