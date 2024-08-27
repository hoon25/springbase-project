package springbase.study.shop.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import springbase.study.shop.domain.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long>, ShopRepositoryCustom {


}
