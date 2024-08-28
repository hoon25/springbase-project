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
@Schema(description = "가게 상세 응답")
public class ShopSimpleResponse {

  @Schema(description = "가게 ID")
  private final Long id;
  @Schema(description = "가게 이름")
  private final String name;
  @Schema(description = "최소 주문 금액")
  private final int minOrderPrice;
  @Schema(description = "배달팁")
  private final int deliveryTip;
  @Schema(description = "가게 카테고리")
  private final ShopCategory shopCategory;

  public static ShopSimpleResponse from(Shop shop) {
    return new ShopSimpleResponse(shop.getId(), shop.getName(),
        shop.getMinOrderPrice(), shop.getDeliveryTip(), shop.getShopCategory());
  }
}
