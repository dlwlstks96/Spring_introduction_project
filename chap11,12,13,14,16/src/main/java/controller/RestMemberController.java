package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

//스프링 MVC에서 JSON 형식으로 데이터를 응답하기 위해선
//@Controller 애노테이션 대신 @RestController 애노테이션을 사용하면 된다.
@RestController
public class RestMemberController {

    private MemberDao memberDao;
    private MemberRegisterService registerService;

    @GetMapping("/api/members")
    public List<Member> members() {
        return memberDao.selectAll();
    }

    //응답 처리 결과가 정상 혹은 비정상일 경우 둘 다 JSON으로 응답하기 위해
    //그렇지 않으면 이전에는 정상일땐 JSON, 비정상일땐 HTML로 응답했다.
    @GetMapping("/api/members/{id}")
    public ResponseEntity<Object> member(@PathVariable Long id) {
        Member member = memberDao.selectById(id);
        if (member == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("no member"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(member); //ResponseEntity.status(상태 코드).body(객체)
    }

    //Json 형식으로 전송된 요청 데이터를 커맨드 객체로 전달받는 방법은
    //커맨드 객체에 @RequestBody 애노테이션만 붙이면 된다.
    @PostMapping("/api/members")
    public void newMember(
            @RequestBody @Valid RegisterRequest regReq,
            HttpServletResponse response) throws IOException {
        try {
            Long newMemberId = registerService.regist(regReq);
            response.setHeader("Location", "/api/members/" + newMemberId);
            response.setStatus(HttpServletResponse.SC_CREATED);
        } catch (DuplicateMemberException dupEx) {
            response.sendError(HttpServletResponse.SC_CONFLICT);
        }
    }


    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public void setMemberRegisterService(MemberRegisterService registerService) {
        this.registerService = registerService;
    }
}
