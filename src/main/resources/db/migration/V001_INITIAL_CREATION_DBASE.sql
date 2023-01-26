-- Создание схемы
CREATE SCHEMA IF NOT EXISTS my_shop;

-- Создание таблицы ProductDto
CREATE TABLE IF NOT EXISTS my_shop.product
(
    id          serial,
    name        text             NOT NULL,
    description text             NOT NULL,
    price       double precision NOT NULL,
    primary key (id)
);

-- Создание таблицы client
CREATE TABLE IF NOT EXISTS my_shop.client
(
    id    serial,
    name  text    NOT NULL,
    email text    NOT NULL,
    phone text    NOT NULL,
    count integer NOT NULL,
    primary key (id)
);

-- Создание таблицы order
CREATE TABLE IF NOT EXISTS my_shop.address
(
    id           serial,
    fk_client_id integer NOT NULL,
    country      text    NOT NULL,
    city         text    NOT NULL,
    street       text    NOT NULL,
    house        text    NOT NULL,
    primary key (id),
    foreign key (fk_client_id) references my_shop.client (id)
);

-- Создание таблицы order
CREATE TABLE IF NOT EXISTS my_shop.order
(
    id           serial,
    fk_client_id integer NOT NULL,
    primary key (id),
    foreign key (fk_client_id) references my_shop.client (id)
);

-- Создание таблицы order_item
CREATE TABLE IF NOT EXISTS my_shop.order_item
(
    id            serial,
    fk_order_id   integer NOT NULL,
    fk_product_id integer NOT NULL,
    count         integer NOT NULL,
    primary key (id),
    foreign key (fk_order_id) references my_shop.order (id),
    foreign key (fk_product_id) references my_shop.product (id)
);



