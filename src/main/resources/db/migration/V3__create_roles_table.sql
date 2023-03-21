create table public.user_roles
(
    id    bigint not null
        primary key,
    title varchar(255)
);

alter table public.user_roles owner to postgres;

INSERT INTO public.user_roles (id, title) VALUES (1, 'CLIENT');

INSERT INTO public.user_roles (id, title) VALUES (2, 'ADMIN');