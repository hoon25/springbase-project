package springbase.study.shop.ui.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import springbase.study.shop.domain.Shop;
import springbase.study.shop.domain.ShopCategory;

@Getter
@AllArgsConstructor
@Builder
public class ShopDetailResponse {

  private final Long id;
  private final String name;
  private final String description;
  private final int minOrderPrice;
  private final int deliveryTip;
  private final ShopCategory shopCategory;

  public static ShopDetailResponse from(Shop shop) {
    return new ShopDetailResponse(shop.getId(), shop.getName(), shop.getDescription(),
        shop.getMinOrderPrice(), shop.getDeliveryTip(), shop.getShopCategory());
  }
}
