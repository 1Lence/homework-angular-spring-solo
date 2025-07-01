--liquibase formatted sql

--changeset petrov:1
create type status_state as enum (
    'AVAILABLE',
    'BORROWED'
    );


create table if not exists book
(
    id             BIGSERIAL primary key not null,
    title          text                  not null,
    author         varchar(255)          not null,
    isbn           varchar(30)           not null,
    published_date date                  not null,
    status         status_state          not null default 'BORROWED'
);

create table if not exists users
(
    id        BIGSERIAL primary key not null,
    user_name varchar(128)          not null,
    full_name varchar(128)          not null,
    email     varchar(128)          not null
);

create table if not exists loan
(
    book_id     bigint references book (id) on delete cascade,
    user_id     bigint references users (id) on delete cascade,
    loan_date   DATE not null,
    return_date DATE not null,
    unique (book_id, user_id)
);