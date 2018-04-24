create table sector(
  id bigint not null,
  name varchar(255) not null
);

create table user(
  id bigint auto_increment,
  name varchar(255) not null,
  agree_to_terms boolean not null,
  primary key (id)
);

create table user_sectors(
  user_id bigint references user(id) on delete cascade,
  sector_id bigint references sector(id) on delete cascade,
  unique (user_id, sector_id)
);