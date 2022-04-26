DROP TABLE IF EXISTS public.products;
CREATE TABLE IF NOT EXISTS public.products (id BIGSERIAL NOT NULL, price double precision, title character varying(255), CONSTRAINT products_pkey PRIMARY KEY (id));
INSERT INTO public.products (title, price) VALUES ('TV', 1000.0), ('PS5', 250.99), ('X-Box', 500.0), ('Iphone', 2000.0), ('Xiaomi', 300.00);

DROP TABLE IF EXISTS  public.customers;
CREATE TABLE IF NOT EXISTS public.customers (id BIGSERIAL NOT NULL, name character varying(255), CONSTRAINT customers_pkey PRIMARY KEY (id));
INSERT INTO public.customers (name) VALUES ('Иванов И.И.'), ('Петров П.П.'), ('Сидоров С.С.');