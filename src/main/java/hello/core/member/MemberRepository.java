package hello.core.member;

public interface MemberRepository {

    void save(Member member); // 새로운 멤버를 hashmap에 저장하는 메소드
    Member findById(Long memberId); // Id로 멤버를 찾는 메소드
}
