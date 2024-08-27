package springbase.study.coupon.domain;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "RATE")
public class RateCoupon extends Coupon{

  @Column(nullable = false)
  private Integer rateDiscount;
}
