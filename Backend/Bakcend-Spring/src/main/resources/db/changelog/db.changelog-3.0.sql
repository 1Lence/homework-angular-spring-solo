--liquibase formatted sql

--changeset petrov:1
ALTER TABLE loan ADD COLUMN id BIGSERIAL PRIMARY KEY;

ALTER TABLE loan DROP CONSTRAINT IF EXISTS loan_book_id_user_id_key;