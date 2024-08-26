package springbase.jpaquerydsl.shop.infrastructure;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springbase.jpaquerydsl.shop.domain.ShopCategory;

@Getter
@Setter
@NoArgsConstructor
public class ShopSearchCond {

  private String name;
  private Integer underMinOrderPrice;
  private Integer overMinOrderPrice;
  private Integer underDeliveryTip;
  private Integer overDeliveryTip;
  private ShopCategory shopCategory;


}
