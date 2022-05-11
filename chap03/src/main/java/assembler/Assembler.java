package assembler;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberRegisterService;

public class Assembler {

    private MemberDao memberDao;
    private MemberRegisterService regSvc;
    private ChangePasswordService pwdSvc;

    public Assembler() {
        memberDao = new MemberDao();
        //memberDao = new CachedMemberDao();
        //memberDao를 상속 받은 CachedMemberDao 클래스가 있다고 가정하자.
        //위와 같이 memberDao 객체 생성 시 갈아 끼우기만 하면
        //나머지 의존 관계가 자동으로 완성된다.
        regSvc = new MemberRegisterService(memberDao);
        pwdSvc = new ChangePasswordService();
        pwdSvc.setMemberDao(memberDao);
    }

    public MemberDao getMemberDao() {
        return memberDao;
    }

    public MemberRegisterService getMemberRegisterService() {
        return regSvc;
    }

    public ChangePasswordService getChangePasswordService() {
        return pwdSvc;
    }
}
