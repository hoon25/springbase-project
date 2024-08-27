package springbase.jpaquerydsl.shop.domain;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import springbase.jpaquerydsl.coupon.domain.Coupon;

@Entity
@Table(name = "shops")
@NoArgsConstructor(access = PROTECTED)
@Getter
public class Shop {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "shop_id")
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private Integer minOrderPrice;

  @Column(nullable = false)
  private Integer deliveryTip;

  @Column(nullable = false)
  private Long orderCount;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, columnDefinition = "varchar(255)")
  private ShopCategory shopCategory;

  // 가게 쿠폰
  @OneToMany(mappedBy = "shop")
  private List<Coupon> coupons = new ArrayList<>();


  private Shop(String name, String description, Integer minOrderPrice, Integer deliveryTip,
      ShopCategory shopCategory,
      Long orderCount,
      List<Coupon> coupons) {
    this.name = name;
    this.description = description;
    this.minOrderPrice = minOrderPrice;
    this.deliveryTip = deliveryTip;
    this.orderCount = orderCount;
    this.shopCategory = shopCategory;
    this.coupons = coupons;
  }

  public static Shop create(String name, String description, int minOrderPrice, int deliveryTip,
      ShopCategory shopCategory) {
    return new Shop(name, description, minOrderPrice, deliveryTip,
        shopCategory, 0L, null);
  }

  public void update(String name, String description, int minOrderPrice, int deliveryTip,
      ShopCategory shopCategory) {
    this.name = name;
    this.description = description;
    this.minOrderPrice = minOrderPrice;
    this.deliveryTip = deliveryTip;
    this.shopCategory = shopCategory;
  }
}
