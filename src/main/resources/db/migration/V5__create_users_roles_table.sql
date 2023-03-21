create table public.users_roles
(
    user_id bigint not null
        constraint fk2o0jvgh89lemvvo17cbqvdxaa
            references public.users,
    role_id bigint not null
        constraint fkfivrl5i32jkvd1w39y4h2vn90
            references public.user_roles,
    primary key (user_id, role_id)
);

alter table public.users_roles
    owner to postgres;