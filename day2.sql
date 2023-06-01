CREATE DATABASE study default CHARACTER SET UTF8;
use study;

-- 관리자 테이블
create table admin_user (
	id bigint auto_increment primary key,
    userid varchar(20) not null,
    userpw varchar(20) not null,
    name varchar(20) not null,
    status varchar(10) not null,
    last_login_at timestamp,
    create_by varchar(20),
    reg_date timestamp default now()
);

select * from admin_user;

-- 회원 테이블
create table users (
	id bigint auto_increment primary key,
    userid varchar(20) not null,
    userpw varchar(20) not null,
    name varchar(20) not null,
    hp varchar(20) not null,
    email varchar(50),
    status varchar(20),
    reg_date timestamp default now(),
    update_date timestamp 
);
drop table users;

-- 카테고리 테이블
create table category (
	id bigint auto_increment primary key,
    type varchar(30) not null,
    title varchar(30) not null,
    reg_date timestamp default now(),
    update_by varchar(20),
    update_date timestamp 
);

drop table category;

-- 파트너 테이블
create table partner (
	id bigint auto_increment primary key,
    name varchar(30) not null,
    status varchar(20) not null,
    address varchar(100),
    call_center varchar(20),
    business_number varchar(20),
    ceo_name varchar(20),
    reg_date timestamp default now(),
    update_date timestamp,
    category_id bigint,
    constraint fk_cate foreign key(category_id) references category(id)
);

drop table partner;

-- 아이템 테이블
create table item (
	id bigint auto_increment primary key,
    name varchar(50) not null,
    status varchar(20) not null,
    title varchar(50) not null,
    content varchar(1000) not null,
    price bigint default 0,
    reg_date timestamp default now(),
    create_by varchar(20),
    update_date timestamp,
    update_by varchar(20),
    partner_id bigint,
    constraint fk_item foreign key(partner_id) references partner(id)
);

drop table item;

-- 상품 구매 내역 테이블
create table order_group (
	id bigint auto_increment primary key,
    order_type varchar(20) not null, -- 묶음 구매, 단일 구매
    status varchar(20) not null,
    rev_address varchar(100) not null,
    rev_name varchar(20) not null,
    payment_type varchar(20) not null,
    total_price bigint default 0,
    total_quantity bigint default 0,
    order_at timestamp,
    arrival_date timestamp,
    reg_date timestamp default now(),
    users_id bigint,
    constraint fk_group foreign key(users_id) references users(id)
);

-- 상품 상세
create table order_detail (
	id bigint auto_increment primary key,
    arrival_date timestamp,
    status varchar(20) not null,
    quantity bigint default 0,
    total_price bigint default 0,
    reg_date timestamp default now(),
    item_id bigint,
    order_group_id bigint,
    constraint fk_item_default foreign key(item_id) references item(id),
    constraint fk_order_group foreign key(order_group_id) references order_group(id)
);


drop table order_detail;
drop table order_group;

