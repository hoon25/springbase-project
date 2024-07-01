create table member
(
    member_id varchar(255) not null,
    name      varchar(255),
    password  varchar(255),
    primary key (member_id)
) engine=InnoDB