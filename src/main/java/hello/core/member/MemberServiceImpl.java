package hello.core.member;

public class MemberServiceImpl implements MemberService{


    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 다형성에 의해 MemberRespository interface가 아닌, MemorymemberRepository class의 method들이 호출된다.

    /**
     *  DIP를 위반하지 않기 위해, AppConfig 에서 인스턴스를 생성해야 하므로,
     *  MemberService interface의 구현체인 MemberServiceImpl class 에서는,
     *  변수 생성과 생성자만 구현해놓는다.
     */
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
