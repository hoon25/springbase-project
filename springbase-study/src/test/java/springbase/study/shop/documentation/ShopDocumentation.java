package springbase.study.shop.documentation;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import springbase.study.shop.application.ShopService;
import springbase.study.shop.domain.Shop;
import springbase.study.shop.domain.ShopCategory;
import springbase.study.utils.docs.Documentation;

public class ShopDocumentation extends Documentation {

  @MockBean
  private ShopService shopService;

  @Test
  void test() throws Exception {
    Mockito.when(shopService.findById(1L))
        .thenReturn(Shop.create("가게1", "설명", 10000, 2000, ShopCategory.CHINA_FOOD));
    this.mockMvc.perform(RestDocumentationRequestBuilders.get("/shops/{id}", 1L))
        .andExpect(status().isOk())
        .andDo(
            document("shop-get",
                pathParameters(
                    parameterWithName("id").description("가게 ID")
                ),
                responseFields(
                    fieldWithPath("data.id").description("가게 ID"),
                    fieldWithPath("data.name").description("가게 이름"),
                    fieldWithPath("data.description").description("가게 설명"),
                    fieldWithPath("data.minOrderPrice").description("최소 주문금액"),
                    fieldWithPath("data.deliveryTip").description("배달팁"),
                    fieldWithPath("data.shopCategory").description("가게 카테고리")
                )
            )
        );
  }
}
