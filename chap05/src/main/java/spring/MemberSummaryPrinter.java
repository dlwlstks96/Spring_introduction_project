package spring;

public class MemberSummaryPrinter extends MemberPrinter {

    @Override
    public void print(Member member) {
        System.out.println(
                "회원 정보: 이메일 = " + member.getEmail() +
                        ", 이름 = " + member.getName() + "\n"
        );
    }

}
