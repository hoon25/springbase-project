package springbase.jpaquerydsl.shop.ui.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import springbase.jpaquerydsl.shop.domain.Shop;


@Getter
public class ShopSaveRequest {

  @Schema(description = "가게 이름")
  private String name;

  @Schema(description = "가게 설명")
  private String description;

  @Schema(description = "최소 주문 금액")
  private int minOrderPrice;

  @Schema(description = "배달팁")
  private int deliveryTip;

  @Schema(description = "가게 카테고리")
  private String shopCategory;

  public Shop toShop() {
    return Shop.create(name, description, minOrderPrice, deliveryTip, shopCategory);
  }
}
