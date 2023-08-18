-- https://www.geeksforgeeks.org/postgresql-create-table/?ref=lbp
-- standard
CREATE TABLE "user"
(
    id serial PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    active BOOLEAN,
    created_on TIMESTAMP NOT NULL,
    last_modified TIMESTAMP NOT NULL
);

-- uuid
CREATE TABLE "user"
(
    id uuid DEFAULT uuid_generate_v4 (),
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    active BOOLEAN,
    created_on TIMESTAMP NOT NULL,
    last_login TIMESTAMP NOT NULL
);

ALTER TABLE "user" ALTER COLUMN "active" SET DEFAULT TRUE;

-- To use UUID in Postgres:
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- INSERT INTO table_name(column1, column2, …) VALUES (value1, value2, …);

-- sodonn, cathy, maggie
INSERT INTO "user" (username, password, email, active, created_on, last_modified) VALUES ('sodonn', '12345', 'test@gmail.com', true, CURRENT_TIMESTAMP, now());
INSERT INTO "user" (username, password, email, active, created_on, last_modified) VALUES ('maggie', '12345', 'maggie@gmail.com', true, CURRENT_TIMESTAMP, now());
INSERT INTO "user" (username, password, email, active, created_on, last_modified) VALUES ('cathy', '12345', 'cathy@gmail.com', true, CURRENT_TIMESTAMP, now());

CREATE TABLE "role"
(
    role_id serial PRIMARY KEY,
    role_name VARCHAR (250) UNIQUE NOT NULL
);

CREATE TABLE "user_role"
(
    user_id integer NOT NULL,
    role_id integer NOT NULL,
    grant_date TIMESTAMP WITHOUT TIME ZONE,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT user_role_role_id_fkey FOREIGN KEY (role_id)
        REFERENCES "role" (role_id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT user_role_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES "user" (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE "posts"
(
    post_id serial PRIMARY KEY,
    title VARCHAR(100) UNIQUE NOT NULL,
    description VARCHAR(350) NOT NULL,
    body TEXT NOT NULL,
    draft Boolean NOT NULL DEFAULT true,
    created_date TIMESTAMP NOT NULL,
    published_date TIMESTAMP NOT NULL
);

CREATE TABLE "post_creator"
(
    user_id integer NOT NULL,
    post_id integer NOT NULL,
    PRIMARY KEY (user_id, post_id),
    CONSTRAINT post_creator_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES "user" (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT post_creator_post_id_fkey FOREIGN KEY (post_id)
        REFERENCES "posts" (post_id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Queries:
ALTER TABLE "posts" ADD CONSTRAINT unique_title UNIQUE (title);

ALTER TABLE "user" ADD COLUMN active BOOLEAN DEFAULT true;

DROP TABLE public.[name of table];

-- Helpful psql commands:

-- Connect to DB
\c

-- Display Users
\du

-- info about connection
\conninfo

-- change password
\password

-- list tables
\dt

-- describe table
\d [TABLE_NAME]

-- check expanded display
\x