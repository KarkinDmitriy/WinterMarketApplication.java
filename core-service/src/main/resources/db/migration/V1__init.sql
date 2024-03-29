create table categories(
    id bigserial primary key,
    title varchar(255) unique ,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp

);
insert into categories(title) values ('Food'), ('Others');


create table products
(
    id bigserial primary key,
    title varchar(255),
    category_id bigint references categories(id),
    price int,
     created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp

);
insert into products (title, price, category_id) values
('Milk', 80, 1), ('Bread', 25, 1), ('Cheese', 300, 1);

create table users
(
    id         bigserial primary key,
    username   varchar(36) not null,
    password   varchar(80) not null
);

create table roles
(
    id         bigserial primary key,
    name       varchar(50) not null
);

create table users_roles
(
    user_id    bigint not null references users (id),
    role_id    bigint not null references roles (id),
    primary key (user_id, role_id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (username, password)
values ('bob', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i'),
       ('john', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);



