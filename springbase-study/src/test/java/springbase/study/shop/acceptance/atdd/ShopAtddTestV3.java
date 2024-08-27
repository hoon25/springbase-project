package springbase.study.shop.acceptance.atdd;

import static org.assertj.core.api.Assertions.assertThat;
import static springbase.study.shop.acceptance.atdd.fixture.ShopFixture.가게1_생성_요청_본문;
import static springbase.study.shop.acceptance.atdd.fixture.ShopFixture.가게_수정_요청_본문;
import static springbase.study.shop.acceptance.atdd.step.ShopStep.가게_삭제_요청;
import static springbase.study.shop.acceptance.atdd.step.ShopStep.가게_생성_ID_추출;
import static springbase.study.shop.acceptance.atdd.step.ShopStep.가게_생성_요청;
import static springbase.study.shop.acceptance.atdd.step.ShopStep.가게_수정_요청;
import static springbase.study.shop.acceptance.atdd.step.ShopStep.가게_조회_요청;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import springbase.study.shop.acceptance.atdd.fixture.ShopFixture;
import springbase.study.shop.ui.dto.req.ShopSaveRequest;
import springbase.study.utils.context.AcceptanceTest;

/**
 * V3
 * <p>
 * 개선된점
 * - Fixture: MockData 생성 모음
 * - Step: CRUD API 호출 및 추출 모음
 * E2E테스트 시 Shop을 사용하는 인접 객체들은 ShopFixture, ShopStep을 사용하면 된다.
 */
@AcceptanceTest
public class ShopAtddTestV3 {

  /**
   * Given 가게 입력정보로
   * When 가게를 생성하면
   * Then 201이 반환된다.
   */
  @Test
  void 가게_생성_성공() {
    // given
    ShopSaveRequest request = 가게1_생성_요청_본문();

    // when
    ExtractableResponse<Response> response = 가게_생성_요청(request);

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
    Long shopId = 가게_생성_ID_추출(가게_생성_요청(가게1_생성_요청_본문()));

    // when
    ExtractableResponse<Response> response = 가게_조회_요청(shopId);

    // then
    String 조회된_가게이름 = response.body().jsonPath().getString("data.name");
    assertThat(조회된_가게이름).isEqualTo(ShopFixture.가게1_이름);
  }

  /**
   * Given 가게를 생성하고
   * When 가게이름을 수정하면
   * Then 가게이름이 수정된다.
   */
  @Test
  void 가게_수정_성공() {
    // given
    Long shopId = 가게_생성_ID_추출(가게_생성_요청(가게1_생성_요청_본문()));

    // when
    String 수정하는_이름 = "가게2";
    가게_수정_요청(shopId, 가게_수정_요청_본문(수정하는_이름));

    // then
    String 조회된_이름 = 가게_조회_요청(shopId).body().jsonPath().getString("data.name");
    assertThat(조회된_이름).isEqualTo(수정하는_이름);
  }

  /**
   * Given 가게를 생성하고
   * When 가게를 삭제하면
   * Then 가게가 조회되지 않는다
   */
  @Test
  void 가게_삭제_성공() {
    // given
    Long shopId = 가게_생성_ID_추출(가게_생성_요청(가게1_생성_요청_본문()));

    // when
    가게_삭제_요청(shopId);

    // then
    assertThat(가게_조회_요청(shopId).statusCode())
        .isEqualTo(HttpStatus.BAD_REQUEST.value());
  }
}
