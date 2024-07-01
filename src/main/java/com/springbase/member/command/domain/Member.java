package com.springbase.member.command.domain;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = PROTECTED)
public class Member {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "member_id")
  private Long id;

  @Column(nullable = false)
  private String email;

  @Embedded
  private Password password;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private boolean blocked;

  public Member(String name, String password) {
    this(name, new Password(password));
  }

  private Member(String name, Password password) {
    this.name = name;
    this.password = password;
    this.blocked = false;
  }

  public Long getId() {
    return id;
  }
}
