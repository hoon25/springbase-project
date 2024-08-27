package springbase.study.coupon.domain;

import static jakarta.persistence.GenerationType.IDENTITY;
import static jakarta.persistence.InheritanceType.SINGLE_TABLE;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import springbase.study.shop.domain.Shop;

@Entity
@Table(name = "coupons")
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn(name = "coupon_type")
public abstract class Coupon {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "coupon_id")
  private Long id;

  @Column(nullable = false)
  private String name;

  @ManyToOne
  @JoinColumn(name = "shop_id", nullable = false)
  private Shop shop;

  @Column(nullable = false)
  private Integer initQuantity;

  @Column(nullable = false)
  private Integer remainQuantity;
}
