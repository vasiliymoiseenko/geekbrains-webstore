DROP TABLE IF EXISTS public.products CASCADE;
CREATE TABLE IF NOT EXISTS public.products
(
    id    BIGSERIAL              NOT NULL,
    price double precision       NOT NULL,
    title character varying(255) NOT NULL,
    CONSTRAINT products_pkey PRIMARY KEY (id)
);
INSERT INTO public.products (title, price)
VALUES ('TV', 1000.0),
       ('PS5', 250.99),
       ('X-Box', 500.0),
       ('Iphone', 2000.0),
       ('Xiaomi', 300.0),
       ('IBM PC', 2000.99),
       ('Microwave', 100.0),
       ('Washing machine', 125.0),
       ('Oven', 349.5),
       ('Notepad', 100.0),
       ('Notebook', 2500.0),
       ('Vacuum cleaner', 123.99),
       ('Hair dryer', 50.0),
       ('Iron', 20.0),
       ('Air conditioning', 325.25),
       ('Clock', 10.0),
       ('Humidifier', 111.0),
       ('Teapot', 50.0),
       ('Fridge', 1000.0),
       ('Dishwasher', 300.00);

DROP TABLE IF EXISTS public.users CASCADE;
CREATE TABLE IF NOT EXISTS public.users
(
    id   BIGSERIAL              NOT NULL,
    name character varying(255) NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);
INSERT INTO public.users (name)
VALUES ('Иванов И.И.'),
       ('Петров П.П.'),
       ('Сидоров С.С.');

DROP TABLE IF EXISTS public.orders CASCADE;
CREATE TABLE IF NOT EXISTS public.orders
(
    id             BIGSERIAL        NOT NULL,
    purchase_price double precision NOT NULL,
    user_id        bigint           NOT NULL,
    product_id     bigint           NOT NULL,
    CONSTRAINT orders_pkey PRIMARY KEY (id),
    CONSTRAINT user_fkey FOREIGN KEY (user_id) REFERENCES public.users (id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT product_fkey FOREIGN KEY (product_id) REFERENCES public.products (id) ON UPDATE CASCADE ON DELETE CASCADE
);
INSERT INTO public.orders (purchase_price, user_id, product_id)
VALUES (100, 1, 1),
       (110, 1, 1),
       (120, 1, 2),
       (130, 2, 2),
       (140, 3, 3),
       (150, 2, 4),
       (160, 3, 5);