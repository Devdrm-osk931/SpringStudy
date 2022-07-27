package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        //TestBean 등록
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    //임의의 테스트 클래스
    static class TestBean {
        //의존관계가 없으면 메서드 자체가 호출이 되지 않는다.
        //Member는 스프링 빈이 아니므로 호출이 되지 않는다.
        @Autowired(required = false)
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        //의존관계가 없어도 호출은 되지만, null로 들어온다
        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        //Java8이 제공하는 Optional 문법, 값이 들어오지 않는다면 Optional.empty로 출력한다.
        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }

    }

}
