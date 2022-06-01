package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.DuplicateMemberException;
import spring.MemberRegisterService;
import spring.RegisterRequest;

@Controller
public class RegisterController {

    private MemberRegisterService memberRegisterService;

    public void setMemberRegisterService(
            MemberRegisterService memberRegisterService) {
        this.memberRegisterService = memberRegisterService;
    }

    @RequestMapping("/register/step1")
    public String handleStep1() {
        return "register/step1";
    }

    @PostMapping("/register/step2")
    public String handleStep2(
            @RequestParam(value = "agree", defaultValue = "false") Boolean agree) {
        if (!agree) {
            return "register/step1";
        }
        return "register/step2";
    }


    //RegisterRequest 는 /java/spring 폴더 내의 직접 작성한 클래스
    //스프링은 요청 파라미터의 값을 커맨드 객체에 담아준다
    @PostMapping("/register/step3")
    public String handleStep3(RegisterRequest regReq) {
        try {
            memberRegisterService.regist(regReq);
            return "register/step3";
        } catch (DuplicateMemberException ex) {
            return "register/step2";
        }
    }

    //step2는 post 방식만 정상 작동이므로
    //get 방식에 대해선 리다이렉트 처리
    @GetMapping("/register/step2")
    public String handleStep2Get() {
        return "redirect:/register/step1";
    }



}
