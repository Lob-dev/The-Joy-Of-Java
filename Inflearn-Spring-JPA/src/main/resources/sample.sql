create table category
(
	category_id bigint not null,
	name        varchar(255),
	parent_id   bigint,
	primary key (category_id)
)

create table category_item
(
	category_id bigint not null,
	item_id     bigint not null
)

create table delivery
(
	delivery_id bigint not null,
	city        varchar(255),
	street      varchar(255),
	zipcode     varchar(255),
	status      varchar(255),
	primary key (delivery_id)
)

create table item
(
	dtype          varchar(31) not null,
	item_id        bigint      not null,
	name           varchar(255),
	price          integer,
	stock_quantity integer,
	artist         varchar(255),
	etc            varchar(255),
	author         varchar(255),
	isbn           varchar(255),
	actor          varchar(255),
	director       varchar(255),
	primary key (item_id)
)

create table member
(
	member_id bigint not null,
	city      varchar(255),
	street    varchar(255),
	zipcode   varchar(255),
	name      varchar(255),
	primary key (member_id)
)

create table order_item
(
	order_item_id bigint not null,
	order_count   integer,
	order_price   integer,
	item_id       bigint,
	order_id      bigint,
	primary key (order_item_id)
)

create table orders
(
	order_id    bigint not null,
	order_date  timestamp,
	status      varchar(255),
	delivery_id bigint,
	member_id   bigint,
	primary key (order_id)
)


alter table category
	add constraint FK2y94svpmqttx80mshyny85wqr
		foreign key (parent_id)
			references category

alter table category_item
	add constraint FKu8b4lwqutcdq3363gf6mlujq
		foreign key (item_id)
			references item

alter table category_item
	add constraint FKcq2n0opf5shyh84ex1fhukcbh
		foreign key (category_id)
			references category

alter table order_item
	add constraint FKija6hjjiit8dprnmvtvgdp6ru
		foreign key (item_id)
			references item

alter table order_item
	add constraint FKt4dc2r9nbvbujrljv3e23iibt
		foreign key (order_id)
			references orders

alter table orders
	add constraint FKtkrur7wg4d8ax0pwgo0vmy20c
		foreign key (delivery_id)
			references delivery

alter table orders
	add constraint FKpktxwhj3x9m4gth5ff6bkqgeb
		foreign key (member_id)
			references member