package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;

import java.util.Optional;

public class MemberService {
    // 서비스를 만드려면 회원 리포지토리가 필요할거임.
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /*
    회원 가입
     */
    public Long join(Member member){
        // 같은 이름이 있는 중복 회원 X
        Optional<Member> result = memberRepository.findByName(member.getName());

        // result.ifPresent : result의 값이 null이 아니면 람다를 실행한다.
        // Optional이 생겨서 쓸 수 있게 되었다.
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });

        //  Member member1 = result.get(); 처럼 값을 직접 꺼내는건 권장하지 않는다.
        // Member member1 = result.orElseGet(); 값이 있으면 꺼내고 없으면 안꺼낸다.

        memberRepository.save(member);
        return member.getId();
    }

}
