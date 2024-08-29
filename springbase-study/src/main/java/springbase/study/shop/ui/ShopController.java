package springbase.study.shop.ui;

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
import springbase.study.shop.application.ShopService;
import springbase.study.shop.domain.Shop;
import springbase.study.shop.infrastructure.ShopSearchCond;
import springbase.study.shop.ui.dto.req.ShopSaveRequest;
import springbase.study.shop.ui.dto.req.ShopUpdateRequest;
import springbase.study.shop.ui.dto.resp.ShopDetailResponse;
import springbase.study.shop.ui.dto.resp.ShopSimpleResponse;

@RestController
@RequestMapping(value = "shops")
@RequiredArgsConstructor
public class ShopController {

  private final ShopService shopService;

  @PostMapping("")
  public ResponseEntity<Void> save(@RequestBody @Validated ShopSaveRequest req) {
    Long shopId = shopService.save(req);
    return ResponseEntity.created(URI.create("/shops/" + shopId)).build();
  }

  @GetMapping("")
  public ResponseEntity<Page<ShopSimpleResponse>> search(
      @ParameterObject ShopSearchCond condition, @ParameterObject Pageable pageable) {
    Page<ShopSimpleResponse> response = shopService.search(condition, pageable);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ShopDetailResponse> findById(@PathVariable(value = "id") Long id) {
    ShopDetailResponse response = shopService.findById(id);
    return ResponseEntity.ok(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> update(@PathVariable(value = "id") Long id,
      @RequestBody @Validated ShopUpdateRequest req) {
    shopService.update(id, req);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
    shopService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
