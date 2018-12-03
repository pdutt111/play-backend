# --- First database schema

# --- !Ups

create table company (
  id                        bigint not null,
  name                      varchar(255),
  constraint pk_company primary key (id))
;

create table computer (
  id                        bigint not null,
  name                      varchar(255),
  introduced                timestamp,
  discontinued              timestamp default '1970-01-01 00:00:01',
  company_id                bigint,
  constraint pk_computer primary key (id))
;
create table user (
  id                        bigint not null auto_increment,
  email                      varchar(255),
  first_name                  varchar(255),
  last_name                   varchar(255),
  password                   varchar(500),
  research_areas              text,
  position                   varchar(255),
  affiliation                varchar(255),
  phone                      varchar(255),
  fax                        varchar(255),
  address                    text,
  city                       varchar(255),
  country                    varchar(255),
  zip                        bigint,
  constraint pk_user primary key (id)
);

create index idx_user on user(email);
-- create sequence company_seq start with 1000;

-- create sequence computer_seq start with 1000;

alter table computer add constraint fk_computer_company_1 foreign key (company_id) references company (id) on delete restrict on update restrict;
create index ix_computer_company_1 on computer (company_id);


# --- !Downs

-- SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists company;

drop table if exists computer;

-- SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists company_seq;

drop sequence if exists computer_seq;

