CREATE TABLE product
(
    product_id BIGINT AUTO_INCREMENT NOT NULL,
    name       VARCHAR(255)          NOT NULL,
    category   VARCHAR(255)          NOT NULL,
    price      BIGINT                NOT NULL,
    stock      INT                   NOT NULL,
    CONSTRAINT pk_product PRIMARY KEY (product_id)
);