package springbase.study.shop.ui.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
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

  @Schema(description = "가게 이름")
  @NotEmpty
  private String name;

  @Schema(description = "가게 설명")
  @NotEmpty
  private String description;

  @Schema(description = "최소 주문 금액")
  @PositiveOrZero
  @NotNull
  private int minOrderPrice;

  @Schema(description = "배달팁")
  @PositiveOrZero
  @NotNull
  private int deliveryTip;

  @Schema(description = "가게 카테고리")
  @NotNull
  private ShopCategory shopCategory;
}
