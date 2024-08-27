package springbase.study.shop.acceptance.atdd.fixture;


import springbase.study.shop.domain.ShopCategory;
import springbase.study.shop.ui.dto.req.ShopSaveRequest;
import springbase.study.shop.ui.dto.req.ShopUpdateRequest;

public class ShopFixture {

  public static final String 가게1_이름 = "가게1";

  public static ShopSaveRequest 가게1_생성_요청_본문() {
    return 가게_생성_요청_본문(가게1_이름);
  }

  public static ShopSaveRequest 가게_생성_요청_본문(String shopName) {
    return new ShopSaveRequest(shopName, "맛있는 음식점", 10000, 2000, ShopCategory.CHINA_FOOD);
  }

  public static ShopUpdateRequest 가게_수정_요청_본문(String shopName) {
    return new ShopUpdateRequest(shopName, "맛있는 음식점", 10000, 2000, ShopCategory.CHINA_FOOD);
  }
}
