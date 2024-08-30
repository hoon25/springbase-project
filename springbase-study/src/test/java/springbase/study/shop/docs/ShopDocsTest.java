package springbase.study.shop.docs;

import static com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper.document;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import springbase.study.shop.acceptance.atdd.fixture.ShopFixture;
import springbase.study.shop.acceptance.atdd.step.ShopStep;
import springbase.study.shop.application.ShopService;
import springbase.study.shop.domain.ShopCategory;
import springbase.study.shop.ui.dto.req.ShopSaveRequest;
import springbase.study.shop.ui.dto.resp.ShopDetailResponse;
import springbase.study.utils.docs.ConstrainedFields;
import springbase.study.utils.docs.DocsRestAssuredTest;

public class ShopDocsTest extends DocsRestAssuredTest {

  @MockBean
  private ShopService shopService;

  @Test
  @Disabled
  void shopGet() {
    // given
    when(shopService.findById(1L))
        .thenReturn(ShopDetailResponse.builder()
            .id(1L)
            .name("가게1")
            .description("설명")
            .minOrderPrice(10000)
            .deliveryTip(2000)
            .shopCategory(ShopCategory.CHINA_FOOD)
            .build());
    // then
    spec.filter(document("{class-name}/{method-name}",
        pathParameters(parameterWithName("shopId").description("가게 ID")),
        responseFields(
            fieldWithPath("data.id").description("가게 ID"),
            fieldWithPath("data.name").description("가게 이름"),
            fieldWithPath("data.description").description("가게 설명"),
            fieldWithPath("data.minOrderPrice").description("최소 주문금액"),
            fieldWithPath("data.deliveryTip").description("배달팁"),
            fieldWithPath("data.shopCategory").description("가게 카테고리")
        ))
    );
    ShopStep.가게_조회_문서(1L, spec);
  }

  @Test
  @Disabled
  void shopPost() {
    when(shopService.save(any(ShopSaveRequest.class)))
        .thenReturn(1L);

    ConstrainedFields fields = new ConstrainedFields(ShopSaveRequest.class);

    spec.filter(document("{class-name}/{method-name}",
        requestFields(
            fields.withPath("name").description("가게 이름"),
            fields.withPath("description").description("가게 설명"),
            fields.withPath("minOrderPrice").description("최소 주문금액"),
            fields.withPath("deliveryTip").description("배달팁"),
            fields.withPath("shopCategory").description("가게 카테고리")
        ), responseHeaders(headerWithName(HttpHeaders.LOCATION).description("생성된 가게 ID"))
    ));
    ShopStep.가게_생성_문서(ShopFixture.가게1_생성_요청_본문(), spec);
  }
}
