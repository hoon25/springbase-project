package com.springbase.member.command.application;

import com.springbase.member.command.domain.Member;
import com.springbase.member.command.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JoinMemberService {

  private final MemberRepository memberRepository;

  @Transactional
  public Long join(JoinMemberRequest request) {
    Member member = request.toMember();
    memberRepository.save(member);
    return member.getId();
  }
}
