package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
service의 메서드 이름들은 비즈니스 로직에 가깝게 작성을 해야한다.
 */


public class MemberService {
    // 서비스를 만드려면 회원 리포지토리가 필요할거임.
    private final MemberRepository memberRepository;


    // Dependency Injection : DI , 의존 관계 주입
    // MemberService에서 MemberRepository를 만들지 않고 외부에서 주입 받음.

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
        회원 가입
         */
    public Long join(Member member){
        /*
        // 같은 이름이 있는 중복 회원 X
        Optional<Member> result = memberRepository.findByName(member.getName());

        // result.ifPresent : result의 값이 null이 아니면 람다를 실행한다.
        // Optional이 생겨서 쓸 수 있게 되었다.
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        */
        //  Member member1 = result.get(); 처럼 값을 직접 꺼내는건 권장하지 않는다.
        // Member member1 = result.orElseGet(); 값이 있으면 꺼내고 없으면 안꺼낸다.

        // 같은 이름이 있는 중복 회원 X
        // 위의 코드 두 줄을 한번에 작성하는 법
        // findByname의 결과가 Optional이므로 바로 .ifPresent 사용 가능
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    // 전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
