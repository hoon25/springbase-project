package springbase.study.shop.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springbase.study.shop.domain.Shop;
import springbase.study.shop.infrastructure.ShopRepository;
import springbase.study.shop.infrastructure.ShopSearchCond;
import springbase.study.shop.ui.dto.req.ShopSaveRequest;
import springbase.study.shop.ui.dto.req.ShopUpdateRequest;
import springbase.study.shop.ui.dto.resp.ShopDetailResponse;
import springbase.study.shop.ui.dto.resp.ShopSimpleResponse;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ShopService {

  private final ShopRepository shopRepository;

  @Transactional
  public Long save(ShopSaveRequest req) {
    Shop shop = req.toShop();
    shopRepository.save(shop);
    return shop.getId();
  }

  public Page<ShopSimpleResponse> search(ShopSearchCond condition, Pageable pageable) {
    Page<Shop> shops = shopRepository.search(condition, pageable);
    return shops.map(ShopSimpleResponse::from);
  }

  public ShopDetailResponse findById(Long id) {
    Shop shop = shopRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("가게를 찾을 수 없습니다."));
    return ShopDetailResponse.from(shop);
  }

  @Transactional
  public void update(Long id, ShopUpdateRequest req) {
    shopRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("가게를 찾을 수 없습니다."))
        .update(req.getName(), req.getDescription(), req.getMinOrderPrice(), req.getDeliveryTip(),
            req.getShopCategory());
  }

  @Transactional
  public void delete(Long id) {
    shopRepository.deleteById(id);
  }
}
