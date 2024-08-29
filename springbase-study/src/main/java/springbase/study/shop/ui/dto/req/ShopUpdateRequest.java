package springbase.study.shop.ui.dto.req;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import springbase.study.shop.domain.ShopCategory;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShopUpdateRequest {

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
}
