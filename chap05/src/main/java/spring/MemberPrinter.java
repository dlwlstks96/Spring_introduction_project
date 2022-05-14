package spring;

import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier("printer")
public class MemberPrinter {

    public void print(Member member) {
        System.out.println("회원 정보: 아이디 = " + member.getId() +
                ", 이메일 = " + member.getEmail() +
                ", 이름 = " + member.getName() +
                ", 등록일 = " + member.getRegisterDateTime());
    }

}
