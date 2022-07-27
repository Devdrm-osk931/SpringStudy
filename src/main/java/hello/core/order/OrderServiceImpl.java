package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor //final이 붙은 변수를 파라미터로 받는 생성자를 만들어준다.
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    // 다른 의존성 주입 방법
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

//    private MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy;

//    필드 주입: 외부에서 변경이 불가능해서 테스트가 어렵다는 단점이 있어서 요즘은 많이 사용하지 않는다
//    @Autowired private MemberRepository memberRepository;
//    @Autowired private DiscountPolicy discountPolicy;


    //수정자
    //수정자 주입 --> memberRepository, discountPolicy 변수가 final이어선 안됨
    //Autowired 필수
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }


    //생성자 주입
    //생성자가 하나만 있을때에는 Autowired 생략해도 무방하지만, 생성자가 여러개 존재하는 경우에는 꼭 명시를 해주어야 함
    //lombok의 requiredArgsConstructor가 이것을 생성해줌!
//    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
