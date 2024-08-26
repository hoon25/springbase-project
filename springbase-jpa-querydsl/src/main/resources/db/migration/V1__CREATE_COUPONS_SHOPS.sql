CREATE TABLE coupons
(
    coupon_id       BIGINT AUTO_INCREMENT NOT NULL,
    coupon_type     VARCHAR(31) NULL,
    name            VARCHAR(255) NOT NULL,
    shop_id         BIGINT       NOT NULL,
    init_quantity   INT          NOT NULL,
    remain_quantity INT          NOT NULL,
    fix_discount    INT       NOT NULL,
    rate_discount   INT          NOT NULL,
    CONSTRAINT pk_coupons PRIMARY KEY (coupon_id)
);

CREATE TABLE shops
(
    shop_id         BIGINT AUTO_INCREMENT NOT NULL,
    name            VARCHAR(255) NOT NULL,
    description   VARCHAR(255) NOT NULL,
    shop_category VARCHAR(255) NOT NULL,
    min_order_price INT       NOT NULL,
    delivery_tip    INT       NOT NULL,
    order_count     BIGINT       NOT NULL,
    CONSTRAINT pk_shops PRIMARY KEY (shop_id)
);

ALTER TABLE coupons
    ADD CONSTRAINT FK_COUPONS_ON_SHOP FOREIGN KEY (shop_id) REFERENCES shops (shop_id);