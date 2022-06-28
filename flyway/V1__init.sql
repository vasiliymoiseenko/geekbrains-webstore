DROP TABLE IF EXISTS public.users CASCADE;
CREATE TABLE IF NOT EXISTS public.users
(
    id         BIGSERIAL                     NOT NULL,
    username   character varying(255) UNIQUE NOT NULL,
    password   character varying(255)        NOT NULL,
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);
INSERT INTO public.users (username, password)
VALUES ('User1', '$2y$10$VZCgR1yw9TenUaOkNtnk3eboZCF37OJ/mXIzM0AUQk9PcFsu1vfMi'),
       ('User2', '$2y$10$fpeZ.gpe9TRlba6fMbS79OaQZW3qPUY4wK24Be3yOBTdUtIYa0w6e'),
       ('User3', '$2y$10$Zbj34uxmdU9uzleAIZM53e1O0K4wmPmYuDgh5CJ309grnCEwK7lV2'),
       ('User4', '$2y$10$2SBmvlwAWBcFvEDjuiXW5uFKxnoSUc7oo4xTjyOa5U79p.642Kiv6');

DROP TABLE IF EXISTS public.profiles CASCADE;
CREATE TABLE IF NOT EXISTS public.profiles
(
    id          BIGSERIAL                    NOT NULL,
    first_name  character varying(80)        NOT NULL,
    middle_name character varying(80)        NOT NULL,
    last_name   character varying(80)        NOT NULL,
    email       character varying(80) UNIQUE NOT NULL,
    phone       bigint UNIQUE                NOT NULL,
    CONSTRAINT profiles_pkey PRIMARY KEY (id),
    CONSTRAINT users_fkey FOREIGN KEY (id) REFERENCES public.users (id)

);

INSERT INTO public.profiles (first_name, middle_name, last_name, email, phone)
VALUES ('John1', 'Johny1', 'Jonson1', 'email1@email.ru', 7999888665522),
       ('John2', 'Johny2', 'Jonson2', 'email2@email.ru', 7999888665523),
       ('John3', 'Johny3', 'Jonson3', 'email3@email.ru', 7999888665524),
       ('John4', 'Johny4', 'Jonson4', 'email4@email.ru', 7999888665525);



DROP TABLE IF EXISTS public.roles CASCADE;
CREATE TABLE IF NOT EXISTS public.roles
(
    id         BIGSERIAL              NOT NULL,
    name       character varying(255) NOT NULL,
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    CONSTRAINT roles_pkey PRIMARY KEY (id)
);
INSERT INTO public.roles (name)
VALUES ('ROLE_USER'),
       ('ROLE_MANAGER'),
       ('ROLE_ADMIN'),
       ('ROLE_SUPERADMIN');

DROP TABLE IF EXISTS public.users_roles CASCADE;
CREATE TABLE IF NOT EXISTS public.users_roles
(
    user_id    bigint NOT NULL,
    role_id    bigint NOT NULL,
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    CONSTRAINT users_roles_pkey PRIMARY KEY (user_id, role_id),
    CONSTRAINT role_fkey FOREIGN KEY (role_id) REFERENCES public.roles (id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT user_fkey FOREIGN KEY (user_id) REFERENCES public.users (id) ON UPDATE CASCADE ON DELETE CASCADE
);
INSERT INTO public.users_roles (user_id, role_id)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4);

DROP TABLE IF EXISTS public.categories CASCADE;
CREATE TABLE IF NOT EXISTS public.categories
(
    id         BIGSERIAL                     NOT NULL,
    title      character varying(255) UNIQUE NOT NULL,
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    CONSTRAINT categories_pkey PRIMARY KEY (id)
);
INSERT INTO public.categories (title)
VALUES ('Appliances'),
       ('Electrinics');

DROP TABLE IF EXISTS public.products CASCADE;
CREATE TABLE IF NOT EXISTS public.products
(
    id          BIGSERIAL                     NOT NULL,
    category_id bigint                        NOT NULL,
    price       numeric                       NOT NULL,
    title       character varying(255) UNIQUE NOT NULL,
    created_at  timestamp without time zone,
    updated_at  timestamp without time zone,
    CONSTRAINT products_pkey PRIMARY KEY (id),
    CONSTRAINT categories_fkey FOREIGN KEY (category_id) REFERENCES public.categories (id) ON UPDATE CASCADE ON DELETE CASCADE
);
INSERT INTO public.products (category_id, title, price)
VALUES (2, 'TV', 1000.0),
       (2, 'PS5', 250.99),
       (2, 'X-Box', 500.0),
       (2, 'Iphone', 2000.0),
       (2, 'Xiaomi', 300.0),
       (2, 'IBM PC', 2000.99),
       (1, 'Microwave', 100.0),
       (1, 'Washing machine', 125.0),
       (1, 'Oven', 349.5),
       (2, 'Notepad', 100.0),
       (2, 'Notebook', 2500.0),
       (1, 'Vacuum cleaner', 123.99),
       (1, 'Hair dryer', 50.0),
       (1, 'Iron', 20.0),
       (1, 'Air conditioning', 325.25),
       (1, 'Clock', 10.0),
       (1, 'Humidifier', 111.0),
       (1, 'Teapot', 50.0),
       (1, 'Fridge', 1000.0),
       (1, 'Dishwasher', 300.00);

DROP TABLE IF EXISTS public.comments CASCADE;
CREATE TABLE IF NOT EXISTS public.comments
(
    id         BIGSERIAL              NOT NULL,
    username   character varying(255) NOT NULL,
    text       character varying      NOT NULL,
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    CONSTRAINT comments_pkey PRIMARY KEY (id),
    CONSTRAINT users_fkey FOREIGN KEY (username) REFERENCES public.users (username) ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE IF EXISTS public.statuses CASCADE;
CREATE TABLE IF NOT EXISTS public.statuses
(
    id         BIGSERIAL                     NOT NULL,
    title      character varying(255) UNIQUE NOT NULL,
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    CONSTRAINT statuses_pkey PRIMARY KEY (id)
);
INSERT INTO public.statuses (title)
VALUES ('Awaiting payment'),
       ('Paid'),
       ('In delivery'),
       ('Completed');

DROP TABLE IF EXISTS public.orders CASCADE;
CREATE TABLE IF NOT EXISTS public.orders
(
    id         BIGSERIAL              NOT NULL,
    username   character varying(255) NOT NULL,
    phone      character varying(255) NOT NULL,
    address    character varying(255) NOT NULL,
    price      numeric                NOT NULL,
    status_id  bigint                 NOT NULL,
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    CONSTRAINT orders_pkey PRIMARY KEY (id),
    CONSTRAINT users_fkey FOREIGN KEY (username) REFERENCES public.users (username) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT statuses_fkey FOREIGN KEY (status_id) REFERENCES public.statuses (id) ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE IF EXISTS public.order_items CASCADE;
CREATE TABLE IF NOT EXISTS public.order_items
(
    id                BIGSERIAL NOT NULL,
    order_id          bigint    NOT NULL,
    product_id        bigint    NOT NULL,
    quantity          int       NOT NULL,
    price_per_product numeric   NOT NULL,
    price             numeric   NOT NULL,
    comment_id        bigint,
    created_at        timestamp without time zone,
    updated_at        timestamp without time zone,
    CONSTRAINT order_items_pkey PRIMARY KEY (id),
    CONSTRAINT products_fkey FOREIGN KEY (product_id) REFERENCES public.products (id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT orders_fkey FOREIGN KEY (order_id) REFERENCES public.orders (id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT comments_fkey FOREIGN KEY (comment_id) REFERENCES public.comments (id) ON UPDATE CASCADE ON DELETE CASCADE
);


