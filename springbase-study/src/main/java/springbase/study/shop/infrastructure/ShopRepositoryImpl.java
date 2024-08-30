package springbase.study.shop.infrastructure;

import static springbase.study.shop.domain.QShop.shop;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import springbase.study.shop.domain.Shop;
import springbase.study.shop.domain.ShopCategory;

@RequiredArgsConstructor
public class ShopRepositoryImpl implements ShopRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  @Override
  public Page<Shop> search(ShopSearchCond condition, Pageable pageable) {
    QueryResults<Shop> results = queryFactory.selectFrom(shop)
        .where(
            nameLike(condition.getName()),
            shopCategoryEq(condition.getShopCategory()),
            lowerBoundMinOrderPrice(condition.getLowerBoundMinOrderPrice()),
            upperBoundMinOrderPrice(condition.getUpperBoundMinOrderPrice()),
            lowerBoundDeliveryTip(condition.getLowerBoundDeliveryTip()),
            upperBoundDeliveryTip(condition.getUpperBoundDeliveryTip())
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

  private BooleanExpression lowerBoundMinOrderPrice(Integer minOrderPrice) {
    return minOrderPrice != null ? shop.minOrderPrice.loe(minOrderPrice) : null;
  }

  private BooleanExpression upperBoundMinOrderPrice(Integer minOrderPrice) {
    return minOrderPrice != null ? shop.minOrderPrice.goe(minOrderPrice) : null;
  }

  private BooleanExpression lowerBoundDeliveryTip(Integer deliveryTip) {
    return deliveryTip != null ? shop.deliveryTip.loe(deliveryTip) : null;
  }

  private BooleanExpression upperBoundDeliveryTip(Integer deliveryTip) {
    return deliveryTip != null ? shop.deliveryTip.goe(deliveryTip) : null;
  }
}
