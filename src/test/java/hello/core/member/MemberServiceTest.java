package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }
    @Test
    void join() {

        //given: 주어진 정보가
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when: 특정 환경에서
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then: 어떻게 되야한다!
        //Assertions 를 이용하여 검증을 함 --> member와 findMember가 동일한지 검증..!
        Assertions.assertThat(member).isEqualTo(findMember);

    }
}
