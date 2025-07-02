--liquibase formatted sql

--changeset petrov:1
ALTER TABLE loan
    DROP CONSTRAINT loan_book_id_user_id_key;

--changeset petrov:2
ALTER TABLE loan ALTER COLUMN loan_date set default NOW()
