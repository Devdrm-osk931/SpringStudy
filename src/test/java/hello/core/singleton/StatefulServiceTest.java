package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //Thread A: A 사용자 10,000원 주문
        int price1 = statefulService1.order("userA", 10000);
        //Thread B: B 사용자 20,000원 주문
        int price2 = statefulService2.order("userB", 20000);

//        int price = statefulService1.getPrice();
        System.out.println("price1 = " + price1 + " price2 = " + price2);

        Assertions.assertThat(price1).isEqualTo(10000);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}