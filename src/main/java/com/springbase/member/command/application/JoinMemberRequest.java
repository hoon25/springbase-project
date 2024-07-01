package com.springbase.member.command.application;

import com.springbase.member.command.domain.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JoinMemberRequest {

  @Schema(description = "회원 이름")
  private String name;
  @Schema(description = "회원 비밀번호")
  private String password;

  public Member toMember() {
    return new Member(name, password);
  }
}
