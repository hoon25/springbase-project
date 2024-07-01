package com.springbase.member.command.domain;

import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = PROTECTED)
public class Password {

  @Column(name = "password", nullable = false)
  private String value;

  public Password(String value) {
    this.value = value;
  }

  public boolean match(String password) {
    return this.value.equals(password);
  }

}
