package springbase.study.shop.docs;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.restdocs.request.RequestDocumentation.relaxedQueryParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.web.util.UriComponentsBuilder;
import springbase.study.shop.acceptance.atdd.fixture.ShopFixture;
import springbase.study.shop.application.ShopService;
import springbase.study.shop.domain.ShopCategory;
import springbase.study.shop.infrastructure.ShopSearchCond;
import springbase.study.shop.ui.dto.req.ShopSaveRequest;
import springbase.study.shop.ui.dto.resp.ShopDetailResponse;
import springbase.study.shop.ui.dto.resp.ShopSimpleResponse;
import springbase.study.utils.docs.ConstrainedFields;
import springbase.study.utils.docs.DocsWebMvcTest;

public class ShopDocsMvcTest extends DocsWebMvcTest {

  @MockBean
  private ShopService shopService;

  @Test
  void shopGet() throws Exception {
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
    this.mockMvc.perform(RestDocumentationRequestBuilders.get("/shops/{shopId}", 1L))
        .andExpect(status().isOk())
        .andDo(customDocument(
            pathParameters(parameterWithName("shopId").description("가게 ID"))
            , responseFields(
                fieldWithPath("data.id").description("가게 ID"),
                fieldWithPath("data.name").description("가게 이름"),
                fieldWithPath("data.description").description("가게 설명"),
                fieldWithPath("data.minOrderPrice").description("최소 주문금액"),
                fieldWithPath("data.deliveryTip").description("배달팁"),
                fieldWithPath("data.shopCategory").description("가게 카테고리")
            ))
        );
  }

  @Test
  void shopGetQuery() throws Exception {
    ShopSimpleResponse shop1 = ShopSimpleResponse.builder()
        .id(1L)
        .name("Shop1")
        .minOrderPrice(10000)
        .deliveryTip(2000)
        .shopCategory(ShopCategory.CHINA_FOOD)
        .build();
    ShopSimpleResponse shop2 = ShopSimpleResponse.builder()
        .id(2L)
        .name("Shop2")
        .minOrderPrice(20000)
        .deliveryTip(3000)
        .shopCategory(ShopCategory.CHINA_FOOD)
        .build();
    when(shopService.search(any(ShopSearchCond.class), any(Pageable.class)))
        .thenReturn(new PageImpl<>(List.of(shop1, shop2)));

    this.mockMvc.perform(get(UriComponentsBuilder.fromUriString("/shops")
            .queryParam("page", 0)
            .queryParam("size", 10)
//            .queryParam("name", "Shop")
//            .queryParam("lowerBoundMinOrderPrice", 10000)
//            .queryParam("upperBoundMinOrderPrice", 20000)
//            .queryParam("lowerBoundDeliveryTip", 2000)
//            .queryParam("upperBoundDeliveryTip", 3000)
//            .queryParam("shopCategory", "CHINA_FOOD")
            .build()
            .toUri().toString()))
        .andExpect(status().isOk())
        .andDo(customDocument(
            relaxedQueryParameters(
                parameterWithName("page").optional().description("페이지 번호"),
                parameterWithName("size").optional().description("페이지 크기"),
                parameterWithName("name").optional().description("가게 이름"),
                parameterWithName("lowerBoundMinOrderPrice").optional()
                    .description("최소 주문금액 하한선"),
                parameterWithName("upperBoundMinOrderPrice").optional()
                    .description("최소 주문금액 상한선"),
                parameterWithName("lowerBoundDeliveryTip").optional().description("배달팁 하한선"),
                parameterWithName("upperBoundDeliveryTip").optional().description("배달팁 상한선"),
                parameterWithName("shopCategory").optional().description("가게 카테고리")),
            responseFields(
                fieldWithPath("data.content[].id").description("가게 ID"),
                fieldWithPath("data.content[].name").description("가게 이름"),
                fieldWithPath("data.content[].minOrderPrice").description("최소 주문금액"),
                fieldWithPath("data.content[].deliveryTip").description("배달팁"),
                fieldWithPath("data.content[].shopCategory").description("가게 카테고리"),
                fieldWithPath("data.pageable").description("페이지 정보"),
                fieldWithPath("data.last").description("마지막 페이지 여부"),
                fieldWithPath("data.totalPages").description("전체 페이지 수"),
                fieldWithPath("data.totalElements").description("전체 요소 수"),
                fieldWithPath("data.first").description("첫 페이지 여부"),
                fieldWithPath("data.size").description("페이지 크기"),
                fieldWithPath("data.number").description("페이지 번호"),
                fieldWithPath("data.numberOfElements").description("현재 페이지의 요소 수"),
                fieldWithPath("data.empty").description("비어있는지 여부"),
                fieldWithPath("data.sort.empty").description("정렬 여부"),
                fieldWithPath("data.sort.sorted").description("정렬 여부"),
                fieldWithPath("data.sort.unsorted").description("정렬 여부")))
        );
  }

  @Test
  void shopPost() throws Exception {
    when(shopService.save(any(ShopSaveRequest.class)))
        .thenReturn(1L);

    ConstrainedFields fields = new ConstrainedFields(ShopSaveRequest.class);
    this.mockMvc.perform(post("/shops")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(ShopFixture.가게1_생성_요청_본문())))
        .andExpect(status().isCreated())
        .andDo(customDocument(
                requestFields(
                    fields.withPath("name").description("가게 이름"),
                    fields.withPath("description").description("가게 설명"),
                    fields.withPath("minOrderPrice").description("최소 주문금액"),
                    fields.withPath("deliveryTip").description("배달팁"),
                    fields.withPath("shopCategory").description("가게 카테고리")),
                responseHeaders(headerWithName(HttpHeaders.LOCATION).description("생성된 가게 ID"))
            )
        );
  }
}
