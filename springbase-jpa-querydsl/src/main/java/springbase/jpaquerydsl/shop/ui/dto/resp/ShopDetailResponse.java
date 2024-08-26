package springbase.jpaquerydsl.shop.ui.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import springbase.jpaquerydsl.shop.domain.Shop;
import springbase.jpaquerydsl.shop.domain.ShopCategory;

@Getter
@AllArgsConstructor
@Schema(description = "가게 상세 응답")
public class ShopDetailResponse {

  @Schema(description = "가게 ID")
  private final Long id;
  @Schema(description = "가게 이름")
  private final String name;
  @Schema(description = "가게 설명")
  private final String description;
  @Schema(description = "최소 주문 금액")
  private final int minOrderPrice;
  @Schema(description = "배달팁")
  private final int deliveryTip;
  @Schema(description = "가게 카테고리")
  private final ShopCategory shopCategory;

  public static ShopDetailResponse from(Shop shop) {
    return new ShopDetailResponse(shop.getId(), shop.getName(), shop.getDescription(),
        shop.getMinOrderPrice(), shop.getDeliveryTip(), shop.getShopCategory());
  }
}
