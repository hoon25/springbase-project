package com.springbase.common.model;

import java.util.Objects;
import lombok.Getter;

@Getter
public class Email {

  private String address;

  public Email(String address) {
    this.address = address;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Email email = (Email) o;
    return Objects.equals(address, email.address);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(address);
  }
}
