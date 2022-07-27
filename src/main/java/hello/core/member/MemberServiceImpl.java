package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    private final MemberRepository memberRepository;

    // 의존 관계 주입을 위해서 생성자에 Autowired 애노테이션 추가가 필요하다.
    // 컴포넌트 스캔을 사용할 때, 클래스를 빈에 등록하며 의존 관계를 주입해줄 방법이 필요하다.
    @Autowired  // ac.getBean(MemberRepository.class) 와 같은 동작이 일어난다.
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

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
