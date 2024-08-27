package springbase.jpaquerydsl.shop.acceptance.atdd.step;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import springbase.jpaquerydsl.shop.ui.dto.req.ShopSaveRequest;
import springbase.jpaquerydsl.shop.ui.dto.req.ShopUpdateRequest;

public class ShopStep {

  public static final String SHOP_BASE_PATH = "/shops";
  public static final String SHOP_RESOURCE_PATH = SHOP_BASE_PATH + "/{shopId}";

  public static ExtractableResponse<Response> 가게_생성_요청(ShopSaveRequest request) {
    return RestAssured
        .given().log().all()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(request)
        .when().post(SHOP_BASE_PATH)
        .then().log().all().extract();
  }

  public static ExtractableResponse<Response> 가게_조회_요청(Long shopId) {
    return RestAssured
        .given().log().all()
        .when().get(SHOP_RESOURCE_PATH, shopId)
        .then().log().all().extract();
  }

  public static ExtractableResponse<Response> 가게_수정_요청(Long shopId, ShopUpdateRequest request) {
    return RestAssured
        .given().log().all()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(request)
        .when().put(SHOP_RESOURCE_PATH, shopId)
        .then().log().all().extract();
  }

  public static ExtractableResponse<Response> 가게_삭제_요청(Long shopId) {
    return RestAssured
        .given().log().all()
        .when().delete(SHOP_RESOURCE_PATH, shopId)
        .then().log().all().extract();
  }

  public static Long 가게_생성_ID_추출(ExtractableResponse<Response> response) {
    return Long.valueOf(response.header(HttpHeaders.LOCATION).split("/")[2]);
  }
}
