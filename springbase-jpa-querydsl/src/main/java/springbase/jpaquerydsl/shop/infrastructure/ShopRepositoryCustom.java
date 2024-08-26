package springbase.jpaquerydsl.shop.infrastructure;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import springbase.jpaquerydsl.shop.domain.Shop;

public interface ShopRepositoryCustom {

  Page<Shop> search(ShopSearchCond condition, Pageable pageable);
}
