package springbase.jpaquerydsl.shop.infrastructure;

import static springbase.jpaquerydsl.shop.domain.QShop.shop;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import springbase.jpaquerydsl.shop.domain.Shop;
import springbase.jpaquerydsl.shop.domain.ShopCategory;

@RequiredArgsConstructor
public class ShopRepositoryImpl implements ShopRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  @Override
  public Page<Shop> search(ShopSearchCond condition, Pageable pageable) {
    QueryResults<Shop> results = queryFactory.selectFrom(shop)
        .where(
            nameLike(condition.getName()),
            shopCategoryEq(condition.getShopCategory()),
            underMinOrderPrice(condition.getUnderMinOrderPrice()),
            overMinOrderPrice(condition.getOverMinOrderPrice()),
            underDeliveryTip(condition.getUnderDeliveryTip()),
            overDeliveryTip(condition.getOverDeliveryTip())
        )
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetchResults();
    List<Shop> content = results.getResults();
    long total = results.getTotal();
    return new PageImpl<>(content, pageable, total);
  }

  private BooleanExpression nameLike(String name) {
    return name != null ? shop.name.contains(name) : null;
  }

  private BooleanExpression shopCategoryEq(ShopCategory shopCategory) {
    return shopCategory != null ? shop.shopCategory.eq(shopCategory) : null;
  }

  private BooleanExpression underMinOrderPrice(Integer minOrderPrice) {
    return minOrderPrice != null ? shop.minOrderPrice.loe(minOrderPrice) : null;
  }

  private BooleanExpression overMinOrderPrice(Integer minOrderPrice) {
    return minOrderPrice != null ? shop.minOrderPrice.goe(minOrderPrice) : null;
  }

  private BooleanExpression underDeliveryTip(Integer deliveryTip) {
    return deliveryTip != null ? shop.deliveryTip.loe(deliveryTip) : null;
  }

  private BooleanExpression overDeliveryTip(Integer deliveryTip) {
    return deliveryTip != null ? shop.deliveryTip.goe(deliveryTip) : null;
  }
}
