CREATE TABLE member
(
    member_id BIGINT AUTO_INCREMENT NOT NULL,
    email     VARCHAR(255)          NOT NULL,
    password  VARCHAR(255)          NOT NULL,
    name      VARCHAR(255)          NOT NULL,
    blocked   BIT(1)                NOT NULL,
    CONSTRAINT pk_member PRIMARY KEY (member_id)
);