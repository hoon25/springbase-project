package springbase.study.shop.ui.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import springbase.study.shop.domain.Shop;
import springbase.study.shop.domain.ShopCategory;

@Getter
@AllArgsConstructor
@Builder
public class ShopSimpleResponse {

  private final Long id;
  private final String name;
  private final int minOrderPrice;
  private final int deliveryTip;
  private final ShopCategory shopCategory;

  public static ShopSimpleResponse from(Shop shop) {
    return new ShopSimpleResponse(shop.getId(), shop.getName(),
        shop.getMinOrderPrice(), shop.getDeliveryTip(), shop.getShopCategory());
  }
}
