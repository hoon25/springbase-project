package com.springbase.member.command.application;

import com.springbase.member.command.domain.Member;
import com.springbase.member.command.domain.MemberId;
import com.springbase.member.command.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JoinMemberService {
    private MemberRepository memberRepository;

    @Transactional
    public MemberId join(JoinMemberRequest request) {
        Member member = request.getMember();
        memberRepository.save(member);
        return member.getId();
    }
}
