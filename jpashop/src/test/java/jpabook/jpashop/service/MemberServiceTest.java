package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class) //스프링도 같이 올려서 실행해주는 어노테이션
@SpringBootTest // 스프링부트 테스트 쓰겠다.
@Transactional
public class MemberServiceTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;

    @Test
    public void 회원가입() throws Exception{
        //Given
        Member member = new Member();
        member.setName("OH");

        //When
        Long savedId = memberService.join(member);

        //Then
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복회원예외() throws Exception{
        // Given
        Member member1 = new Member();
        Member member2 = new Member();

        member1.setName("OH");
        member2.setName("OH");

//        member2.setName("SOH"); fail 터지는게 보고 싶다면 풀어라.


        // When
        memberService.join(member1);
        memberService.join(member2);

//        // 줄줄 쓰려면 아래처럼 써도 된다.
//        // 그런데 이제 위에  @Test(expected = IllegalStateException.class) 어노테이션에 예상 익셉션을 적어두면, 알아서 pass가 되는
//        memberService.join(member1);
//
//        try{
//            memberService.join(member2);
//        }catch (IllegalStateException e){
//            return;
//        }

        // Then
        fail("예외 발생 해야하는데 안해요"); //junit 기본 제공 메소드인데, test 위에서 exception 터져야 하는데 안 날때 이렇게 한다.

    }
}