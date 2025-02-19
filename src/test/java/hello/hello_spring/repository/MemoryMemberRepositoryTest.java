package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

// Junit 이라는 프레임워크로 간편하게 테스트를 해본다.
class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // **중요!! 클래스를 전체 실행하면 메서드들 순서 보장 없이 실행되면서 오류가 발생할 수 있다.
    // 따라서 클리어 해줘야한다.
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }
    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        // 1. junit Assertions 사용법
        // Assertions.assertEquals(member, result); // result, member가 같은지 확인


        // 2. 최근에 쓰는 Assertions :org.assertj.core.api.Assertions
        // 위에서 import static 사용해서 맨앞에 Assertions 사용하는걸 없애줌.
        assertThat(member).isEqualTo(result);
    }

    // findByName이 잘 작동하는지 Test
    @Test
    public void findByName(){
        // 회원을 두명 저장해둠.
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        //when
        List<Member> result = repository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
    }

}
