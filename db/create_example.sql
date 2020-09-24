create sequence hibernate_sequence start with 1 increment by 1
create table material
(
    id              bigint not null,
    created_at      timestamp,
    last_updated_at timestamp,
    name            varchar(255),
    primary key (id)
)
create table trash
(
    id              bigint not null,
    created_at      timestamp,
    last_updated_at timestamp,
    name            varchar(255),
    material_id     bigint,
    primary key (id)
)
create table trash_alias
(
    id              bigint not null,
    created_at      timestamp,
    last_updated_at timestamp,
    name            varchar(255),
    trash_id        bigint,
    primary key (id)
)
create table user
(
    id              bigint not null,
    created_at      timestamp,
    email           varchar(255),
    last_updated_at timestamp,
    password        varchar(255),
    user_role       integer,
    username        varchar(255),
    primary key (id)
)
alter table user
    add constraint UK_sb8bbouer5wak8vyiiy4pf2bx unique (username)
alter table trash
    add constraint FKs2o4tk6ohto8tsxbv2aa3o4h8 foreign key (material_id) references material
alter table trash_alias
    add constraint FKfugyu5imtudycblijeejdk4mx foreign key (trash_id) references trash
create sequence hibernate_sequence start with 1 increment by 1
create table material (id bigint not null, created_at timestamp, last_updated_at timestamp, name varchar(255), primary key (id))
create table trash (id bigint not null, created_at timestamp, last_updated_at timestamp, name varchar(255), material_id bigint, primary key (id))
create table trash_alias (id bigint not null, created_at timestamp, last_updated_at timestamp, name varchar(255), trash_id bigint, primary key (id))
create table user (id bigint not null, created_at timestamp, email varchar(255), last_updated_at timestamp, password varchar(255), user_role integer, username varchar(255), primary key (id))
alter table user add constraint UK_sb8bbouer5wak8vyiiy4pf2bx unique (username)
alter table trash add constraint FKs2o4tk6ohto8tsxbv2aa3o4h8 foreign key (material_id) references material
alter table trash_alias add constraint FKfugyu5imtudycblijeejdk4mx foreign key (trash_id) references trash
