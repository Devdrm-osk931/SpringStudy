package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryMemberRepository implements MemberRepository{

    //concurrent HashMap 을 쓰는것이 실무에는 더 적합하다; 동시성 문제 떄문에! 동시에 여러군데에서 접근할 때 문제를 해결하는 방안
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
