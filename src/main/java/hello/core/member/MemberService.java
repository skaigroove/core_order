package hello.core.member;

public interface MemberService {
    void join(Member member); // 멤버를 생성한 Repo에 추가하여 회원가입 시키는 메소드
    Member findMember(Long MemberId); // Id를 통해 Repo에 있는 멤버를 찾는 메소드
}
