package com.springbase.member.command.domain;

import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = PROTECTED)
public class Member {

  @EmbeddedId
  private MemberId id;

  private String name;

  private Password password;

  public Member(String name, String password) {
    this(name, new Password(password));
  }

  private Member(String name, Password password) {
    this.name = name;
    this.password = password;
  }

  public MemberId getId() {
    return id;
  }
}
