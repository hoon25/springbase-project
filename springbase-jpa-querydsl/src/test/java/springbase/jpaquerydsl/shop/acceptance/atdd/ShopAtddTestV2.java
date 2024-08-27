package springbase.jpaquerydsl.shop.acceptance.atdd;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import springbase.jpaquerydsl.shop.domain.ShopCategory;
import springbase.jpaquerydsl.shop.ui.dto.req.ShopSaveRequest;
import springbase.jpaquerydsl.utils.context.AcceptanceTest;

/**
 * V2
 * <p>
 * 개선된점
 * V1에서 Shop CRUD 관련 중복되는 코드들 private static 함수로 추출
 * <p>
 * 현재
 * V1에 비해서 가독성이 좋아지고, 테스트 대상이 명확히 보임
 * <p>
 * 문제점
 * E2E라면 다른 곳에서도 Shop을 사용하는데(상위객체일 수록) 어떻게 할 것 인가?
 * 또한 테스트파일자체가 길어져서 가독성이 떨어질 수 있다.
 */
@AcceptanceTest
public class ShopAtddTestV2 {

  static final String shopName1 = "shop1";

  /**
   * Given 가게 입력정보로
   * When 가게를 생성하면
   * Then 201이 반환된다.
   */
  @Test
  void 가게_생성_성공() {
    // given
    ShopSaveRequest request = shopSaveRequestForm(shopName1);

    // when
    ExtractableResponse<Response> response = shopSaveRequest(request);

    // then
    assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
  }

  /**
   * Given 가게를 생성하고
   * When 가게 ID로 가게를 조회하면
   * Then 가게가 조회된다.
   */
  @Test
  void 가게_조회_성공() {
    // given
    ShopSaveRequest request = shopSaveRequestForm(shopName1);
    ExtractableResponse<Response> createResponse = shopSaveRequest(request);
    Long shopId = Long.valueOf(createResponse.header(HttpHeaders.LOCATION).split("/")[2]);

    // when
    ExtractableResponse<Response> response = shopSearchRequest(shopId);

    // then
    String searchShopName = response.body().jsonPath().getString("data.name");
    assertThat(searchShopName).isEqualTo(shopName1);
  }

  /**
   * Given 가게를 생성하고
   * When 가게이름을 수정하면
   * Then 가게이름이 수정된다.
   */
  @Test
  void 가게_수정_성공() {
    // given
    ShopSaveRequest request = shopSaveRequestForm(shopName1);
    ExtractableResponse<Response> createResponse = shopSaveRequest(request);
    Long shopId = Long.valueOf(createResponse.header(HttpHeaders.LOCATION).split("/")[2]);
    // given
    String updateName = "가게2";
    ShopSaveRequest updateRequest = shopSaveRequestForm(updateName);

    // when
    shopUpdateRequest(updateRequest, shopId);

    // then
    ExtractableResponse<Response> response = shopSearchRequest(shopId);
    String searchShopName = response.body().jsonPath().getString("data.name");
    assertThat(searchShopName).isEqualTo(updateName);
  }


  /**
   * Given 가게를 생성하고
   * When 가게를 삭제하면
   * Then 가게가 조회되지 않는다
   */
  @Test
  void 가게_삭제_성공() {
    // given
    ShopSaveRequest request = shopSaveRequestForm(shopName1);
    ExtractableResponse<Response> createResponse = shopSaveRequest(request);
    Long shopId = Long.valueOf(createResponse.header(HttpHeaders.LOCATION).split("/")[2]);

    // when
    shopDeleteRequest(shopId);

    // then
    ExtractableResponse<Response> response = shopSearchRequest(shopId);
    assertThat(response.statusCode())
        .isEqualTo(HttpStatus.BAD_REQUEST.value());
  }

  private static ExtractableResponse<Response> shopSaveRequest(ShopSaveRequest request) {
    return RestAssured
        .given().log().all()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(request)
        .when().post("/shops")
        .then().log().all().extract();
  }

  private static ExtractableResponse<Response> shopSearchRequest(Long shopId) {
    return RestAssured
        .given().log().all()
        .when().get("/shops/{shopId}", shopId)
        .then().log().all().extract();
  }

  private static void shopUpdateRequest(ShopSaveRequest updateRequest, Long shopId) {
    RestAssured
        .given().log().all()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(updateRequest)
        .when().put("/shops/{shopId}", shopId)
        .then().log().all().extract();
  }

  private static void shopDeleteRequest(Long shopId) {
    RestAssured
        .given().log().all()
        .when().delete("/shops/{shopId}", shopId)
        .then().log().all().extract();
  }

  private static ShopSaveRequest shopSaveRequestForm(String shopName1) {
    return new ShopSaveRequest(shopName1, "맛있는 음식점", 10000, 2000,
        ShopCategory.CHINA_FOOD);
  }
}
