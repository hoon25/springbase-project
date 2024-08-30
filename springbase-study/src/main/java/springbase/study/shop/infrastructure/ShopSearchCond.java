package springbase.study.shop.infrastructure;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springbase.study.shop.domain.ShopCategory;

@Getter
@Setter
@NoArgsConstructor
public class ShopSearchCond {

  private String name;
  private Integer lowerBoundMinOrderPrice;
  private Integer upperBoundMinOrderPrice;
  private Integer lowerBoundDeliveryTip;
  private Integer upperBoundDeliveryTip;
  private ShopCategory shopCategory;


}
