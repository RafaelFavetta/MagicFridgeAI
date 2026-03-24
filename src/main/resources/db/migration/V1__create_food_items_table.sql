create table food_items (
    id bigint auto_increment primary key,
    name varchar(255) not null,
    categoria varchar(255) not null,
    quantity numeric(10, 2) not null,
    expirationTime date not null
);