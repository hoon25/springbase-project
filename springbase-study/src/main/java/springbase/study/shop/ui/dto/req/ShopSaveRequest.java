package springbase.study.shop.ui.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import springbase.study.shop.domain.Shop;
import springbase.study.shop.domain.ShopCategory;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShopSaveRequest {

  @NotEmpty
  private String name;

  @NotEmpty
  private String description;

  @PositiveOrZero
  @NotNull
  private int minOrderPrice;

  @PositiveOrZero
  @NotNull
  private int deliveryTip;

  @NotNull
  private ShopCategory shopCategory;

  public Shop toShop() {
    return Shop.create(name, description, minOrderPrice, deliveryTip, shopCategory);
  }
}
