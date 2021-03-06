-- noinspection SqlNoDataSourceInspectionForFile
drop sequence if exists hibernate_sequence;
drop table if exists user;
drop table if exists trash_alias;
drop table if exists trash;
drop table if exists material;


create sequence if not exists hibernate_sequence start with 1 increment by 1;
create table if not exists material
(
    id              bigint not null auto_increment,
    created_at      timestamp,
    last_updated_at timestamp,
    name            varchar(255),
    primary key (id)
);
create table if not exists trash
(
    id              bigint not null auto_increment,
    created_at      timestamp,
    last_updated_at timestamp,
    name            varchar(255),
    material_id     bigint,
    primary key (id)
);
create table if not exists trash_alias
(
    id              bigint not null auto_increment,
    created_at      timestamp,
    last_updated_at timestamp,
    name            varchar(255),
    trash_id        bigint,
    primary key (id)
);
create table if not exists user
(
    id              bigint not null auto_increment,
    created_at      timestamp,
    email           varchar(255),
    last_updated_at timestamp,
    password        varchar(255),
    username        varchar(255),
    primary key (id)
);
alter table trash
    add constraint if not exists FKs2o4tk6ohto8tsxbv2aa3o4h8 foreign key (material_id) references material;
alter table trash_alias
    add constraint if not exists FKfugyu5imtudycblijeejdk4mx foreign key (trash_id) references trash;


alter table user
    add constraint if not exists username_is_unique unique (username);

alter table user
    add column if not exists user_role integer;