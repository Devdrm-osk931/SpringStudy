package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void createOrder() {
        //수정자 주입을 한다면 테스트를 할 때 의존성 주입이 되지 않았기 때문에 null pointer exception 이 발생한다.
        //하지만 실제로 테스트를 할 때 의존성이 뭐였는지 잘 기억이 나지 않는다...!
        //생성자 주입을 사용한다면? 바로 컴파일 오류를 내주면서 필요한 의존성이 뭔지 바로 알 수 있다
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "석교", Grade.VIP));
        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}