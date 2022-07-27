package hello.core;


import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// Annotation이 붙어있는 클래스를 쭉 스캔하면서 자동으로 빈에 등록할 것인데, AppConfig.class 또는 TestConfig 등을 제외하기 위해서 Configuration.class는 제외시키는 코드
// 기존에 연습한 코드를 유지하기 위해서 필터링을 해준 것 ~_~
// 실무에선 그냥 모두 컴포넌트 스캔을 한다
// basePackages -> 탐색할 패키지의 시작 위치를 지정한다, 이것이 없으면 모든 자바 코드를 스캔해야함, 라이브러리까지.... 많은 시간이 소요되며 효율적이지 못하다.
// 디폴트는 무엇인가?? -> 지정하지 않으면 AutoAppConfig가 위치한 패키지를 시작으로 하위 패키지를 모두 스캔한다.
// 관례 상 Configuration 파일 --> AppConfig와 같은 파일을 패키지 최상단에 두고 따로 basePackages, basePackageClasses와 같은 설정을 따로 하지 않는다.
@ComponentScan(
//        basePackages = "hello.core",
//        basePackageClasses = AutoAppConfig.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

//    // 충돌 테스트
//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
}
