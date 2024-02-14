package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService{

    /**
     * 인터페이스에만 의존하도록 코드를 변경
     */
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /**
     * 인터페이스와 추상클래스 모두 의존하고 있으므로 DIP를 위반하고 있다 !!!!
     */



    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
