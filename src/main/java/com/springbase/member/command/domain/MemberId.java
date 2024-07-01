package com.springbase.member.command.domain;

import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = PROTECTED)
public class MemberId implements Serializable {

  @Column(name = "member_id")
  private String id;

  public MemberId(String id) {
  }

  public String getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
      if (this == o) {
          return true;
      }
      if (o == null || getClass() != o.getClass()) {
          return false;
      }
    MemberId memberId = (MemberId) o;
    return Objects.equals(id, memberId.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
