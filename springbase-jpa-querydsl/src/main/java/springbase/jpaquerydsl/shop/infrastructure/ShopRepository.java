package springbase.jpaquerydsl.shop.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import springbase.jpaquerydsl.shop.domain.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long>, ShopRepositoryCustom {


}
