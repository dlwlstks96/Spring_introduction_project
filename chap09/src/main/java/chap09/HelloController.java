package chap09;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    //메소드가 처리할 요청 경로를 지정한다
    //Model 파라미터는 컨트롤러의 처리 결과를 뷰에 전달할 때 사용
    @GetMapping("/hello")
    public String hello(Model model,
                        @RequestParam(value = "name", required = false) String name) {
        model.addAttribute("greeting", "안녕하세요, " + name);
        return "hello";
    }

}
