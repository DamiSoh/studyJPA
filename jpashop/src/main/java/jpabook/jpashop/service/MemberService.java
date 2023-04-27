package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    // join()
    @Transactional
    public Long join(Member member){
        // 중복회원 검증 후
        validateDuplicateMember(member);
        // 저장
        memberRepository.save(member);
        return member.getId();
    }

    // validateDuplicateMember()
    public void validateDuplicateMember(Member member) {
        List<Member> result =memberRepository.findByName(member.getName());
        if(!result.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // findMembers()
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    // findOne()
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
