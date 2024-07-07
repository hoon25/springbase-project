create table member
(
    member_id bigint not null auto_increment,
    name      varchar(255),
    password  varchar(255),
    primary key (member_id)
) engine=InnoDB