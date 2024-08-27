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
 * V1
 * <p>
 * 현재
 * 중복제거 없이 E2E 테스트 작성
 * <p>
 * 문제점
 * 가독성이 좋지 않음, 테스트하고자 하는 것이 명확히 드러나지 않는다
 */
@AcceptanceTest
public class ShopAtddTestV1 {

  static final String shopName1 = "shop1";

  /**
   * Given 가게 입력정보로
   * When 가게를 생성하면
   * Then 201이 반환된다.
   */
  @Test
  void 가게_생성_성공() {
    // given
    ShopSaveRequest request = new ShopSaveRequest(shopName1, "맛있는 음식점", 10000, 2000,
        ShopCategory.CHINA_FOOD);

    // when
    ExtractableResponse<Response> response = RestAssured
        .given().log().all()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(request)
        .when().post("/shops")
        .then().log().all().extract();

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
    ShopSaveRequest request = new ShopSaveRequest(shopName1, "맛있는 음식점", 10000, 2000,
        ShopCategory.CHINA_FOOD);
    ExtractableResponse<Response> createResponse = RestAssured
        .given().log().all()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(request)
        .when().post("/shops")
        .then().log().all().extract();
    Long shopId = Long.valueOf(createResponse.header(HttpHeaders.LOCATION).split("/")[2]);

    // when
    ExtractableResponse<Response> response = RestAssured
        .given().log().all()
        .when().get("/shops/{shopId}", shopId)
        .then().log().all().extract();

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
    ShopSaveRequest request = new ShopSaveRequest(shopName1, "맛있는 음식점", 10000, 2000,
        ShopCategory.CHINA_FOOD);
    ExtractableResponse<Response> createResponse = RestAssured
        .given().log().all()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(request)
        .when().post("/shops")
        .then().log().all().extract();
    Long shopId = Long.valueOf(createResponse.header(HttpHeaders.LOCATION).split("/")[2]);
    // given
    String updateName = "가게2";
    ShopSaveRequest updateRequest = new ShopSaveRequest(updateName, "맛있는 음식점", 10000, 2000,
        ShopCategory.CHINA_FOOD);

    // when
    RestAssured
        .given().log().all()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(updateRequest)
        .when().put("/shops/{shopId}", shopId)
        .then().log().all().extract();

    // then
    ExtractableResponse<Response> response = RestAssured
        .given().log().all()
        .when().get("/shops/{shopId}", shopId)
        .then().log().all().extract();
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
    ShopSaveRequest request = new ShopSaveRequest(shopName1, "맛있는 음식점", 10000, 2000,
        ShopCategory.CHINA_FOOD);
    ExtractableResponse<Response> createResponse = RestAssured
        .given().log().all()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(request)
        .when().post("/shops")
        .then().log().all().extract();
    Long shopId = Long.valueOf(createResponse.header(HttpHeaders.LOCATION).split("/")[2]);

    // when
    RestAssured
        .given().log().all()
        .when().delete("/shops/{shopId}", shopId)
        .then().log().all().extract();

    // then
    ExtractableResponse<Response> response = RestAssured
        .given().log().all()
        .when().get("/shops/{shopId}", shopId)
        .then().log().all().extract();
    assertThat(response.statusCode())
        .isEqualTo(HttpStatus.BAD_REQUEST.value());
  }
}
