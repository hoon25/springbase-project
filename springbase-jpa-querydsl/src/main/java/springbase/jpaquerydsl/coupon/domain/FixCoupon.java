package springbase.jpaquerydsl.coupon.domain;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "FIX")
public class FixCoupon extends Coupon {

  @Column(nullable = false)
  private Integer fixDiscount;
}
