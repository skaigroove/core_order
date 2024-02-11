package hello.core;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig { // 관리자 설정 // cmd + e : 과거 히스토리 파일이 다 나온다 recent file

    public MemberService memberService(){
        return new MemberServiceImpl(new MemoryMemberRepository()); // MemoryMemberRepository가 생성
    }

    public OrderService orderService(){
        return new OrderServiceImpl(new MemoryMemberRepository(), new RateDiscountPolicy()) ;
    }
}

