package springbase.jpaquerydsl.shop.ui;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springbase.jpaquerydsl.core.ui.ApiCommonResponse;
import springbase.jpaquerydsl.shop.application.ShopService;
import springbase.jpaquerydsl.shop.domain.Shop;
import springbase.jpaquerydsl.shop.infrastructure.ShopSearchCond;
import springbase.jpaquerydsl.shop.ui.dto.req.ShopSaveRequest;
import springbase.jpaquerydsl.shop.ui.dto.req.ShopUpdateRequest;
import springbase.jpaquerydsl.shop.ui.dto.resp.ShopDetailResponse;
import springbase.jpaquerydsl.shop.ui.dto.resp.ShopSimpleResponse;

@RestController
@RequestMapping("shops")
@RequiredArgsConstructor
@Tag(name = "Shop", description = "가게 API")
public class ShopController {

  private final ShopService shopService;

  @PostMapping("")
  public ResponseEntity<ApiCommonResponse<Void>> save(@RequestBody @Validated ShopSaveRequest req) {
    Long shopId = shopService.save(req);
    return ResponseEntity.created(URI.create("/shops/" + shopId)).build();
  }

  @GetMapping("")
  public ResponseEntity<ApiCommonResponse<Page<ShopSimpleResponse>>> search(
      @ParameterObject ShopSearchCond condition, @ParameterObject Pageable pageable) {
    Page<Shop> shops = shopService.search(condition, pageable);
    return ResponseEntity.ok(new ApiCommonResponse<>(shops.map(ShopSimpleResponse::from)));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ShopDetailResponse> findById(@PathVariable Long id) {
    Shop shop = shopService.findById(id);
    ShopDetailResponse response = ShopDetailResponse.from(shop);
    return ResponseEntity.ok(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiCommonResponse<Void>> update(@PathVariable Long id,
      @RequestBody @Validated ShopUpdateRequest req) {
    shopService.update(id, req);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiCommonResponse<Void>> delete(@PathVariable Long id) {
    shopService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
