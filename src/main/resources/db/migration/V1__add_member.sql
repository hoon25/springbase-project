CREATE TABLE member
(
    member_id BIGINT AUTO_INCREMENT NOT NULL,
    name      VARCHAR(255)          NULL,
    password  VARCHAR(255)          NULL,
    CONSTRAINT pk_member PRIMARY KEY (member_id)
);