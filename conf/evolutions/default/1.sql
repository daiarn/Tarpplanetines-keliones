# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

-- init script create procs
-- Inital script to create stored procedures etc for mysql platform
DROP PROCEDURE IF EXISTS usp_ebean_drop_foreign_keys;

delimiter $$
--
-- PROCEDURE: usp_ebean_drop_foreign_keys TABLE, COLUMN
-- deletes all constraints and foreign keys referring to TABLE.COLUMN
--
CREATE PROCEDURE usp_ebean_drop_foreign_keys(IN p_table_name VARCHAR(255), IN p_column_name VARCHAR(255))
BEGIN
  DECLARE done INT DEFAULT FALSE;
  DECLARE c_fk_name CHAR(255);
  DECLARE curs CURSOR FOR SELECT CONSTRAINT_NAME from information_schema.KEY_COLUMN_USAGE
    WHERE TABLE_SCHEMA = DATABASE() and TABLE_NAME = p_table_name and COLUMN_NAME = p_column_name
      AND REFERENCED_TABLE_NAME IS NOT NULL;
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

  OPEN curs;

  read_loop: LOOP
    FETCH curs INTO c_fk_name;
    IF done THEN
      LEAVE read_loop;
    END IF;
    SET @sql = CONCAT('ALTER TABLE ', p_table_name, ' DROP FOREIGN KEY ', c_fk_name);
    PREPARE stmt FROM @sql;
    EXECUTE stmt;
  END LOOP;

  CLOSE curs;
END
$$

DROP PROCEDURE IF EXISTS usp_ebean_drop_column;

delimiter $$
--
-- PROCEDURE: usp_ebean_drop_column TABLE, COLUMN
-- deletes the column and ensures that all indices and constraints are dropped first
--
CREATE PROCEDURE usp_ebean_drop_column(IN p_table_name VARCHAR(255), IN p_column_name VARCHAR(255))
BEGIN
  CALL usp_ebean_drop_foreign_keys(p_table_name, p_column_name);
  SET @sql = CONCAT('ALTER TABLE ', p_table_name, ' DROP COLUMN ', p_column_name);
  PREPARE stmt FROM @sql;
  EXECUTE stmt;
END
$$
create table entertainment (
  id                            integer auto_increment not null,
  name                          varchar(255),
  description                   varchar(255),
  price                         double,
  constraint pk_entertainment primary key (id)
);

create table flight_class (
  id                            integer auto_increment not null,
  type                          varchar(255),
  seat_count                    integer,
  vechile_id                    integer,
  constraint pk_flight_class primary key (id)
);

create table hotel (
  id                            integer auto_increment not null,
  name                          varchar(255),
  address                       varchar(255),
  constraint pk_hotel primary key (id)
);

create table luggage (
  id                            integer auto_increment not null,
  dimensions                    varchar(255),
  weight                        integer,
  contents                      varchar(255),
  vechile_id                    integer,
  constraint pk_luggage primary key (id)
);

create table meal (
  id                            integer auto_increment not null,
  name                          varchar(255),
  description                   varchar(255),
  price                         double,
  day_of_the_week               datetime(6),
  constraint pk_meal primary key (id)
);

create table reservation (
  id                            integer auto_increment not null,
  nr                            varchar(255),
  date                          datetime(6),
  take_off_place                varchar(255),
  final_price                   double,
  payment_date                  datetime(6),
  take_off_date                 datetime(6),
  arriving_date                 datetime(6),
  come_back_date                datetime(6),
  state_id                      integer,
  hotel_id                      integer,
  vechile_id                    integer,
  constraint pk_reservation primary key (id)
);

create table reservation_state (
  id                            integer auto_increment not null,
  name                          varchar(255),
  constraint pk_reservation_state primary key (id)
);

create table seat (
  id                            integer auto_increment not null,
  row                           integer,
  column                        integer,
  reservation_id                integer,
  flight_class_id               integer,
  constraint pk_seat primary key (id)
);

create table tour (
  id                            integer auto_increment not null,
  name                          varchar(255),
  description                   varchar(255),
  price                         double,
  seat_count                    integer,
  hotel_id                      integer,
  constraint pk_tour primary key (id)
);

create table vechile (
  id                            integer auto_increment not null,
  name                          varchar(255),
  fuel_id                       integer,
  constraint pk_vechile primary key (id)
);

create table vechile_fuel (
  id                            integer auto_increment not null,
  name                          varchar(255),
  constraint pk_vechile_fuel primary key (id)
);

create table vechile_speed (
  id                            integer auto_increment not null,
  name                          varchar(255),
  constraint pk_vechile_speed primary key (id)
);

create table voyage_price (
  id                            integer auto_increment not null,
  calculation_date              datetime(6),
  distance_from_earth           double,
  speed_id                      integer,
  constraint pk_voyage_price primary key (id)
);

create index ix_flight_class_vechile_id on flight_class (vechile_id);
alter table flight_class add constraint fk_flight_class_vechile_id foreign key (vechile_id) references vechile (id) on delete restrict on update restrict;

create index ix_luggage_vechile_id on luggage (vechile_id);
alter table luggage add constraint fk_luggage_vechile_id foreign key (vechile_id) references vechile (id) on delete restrict on update restrict;

create index ix_reservation_state_id on reservation (state_id);
alter table reservation add constraint fk_reservation_state_id foreign key (state_id) references reservation_state (id) on delete restrict on update restrict;

create index ix_reservation_hotel_id on reservation (hotel_id);
alter table reservation add constraint fk_reservation_hotel_id foreign key (hotel_id) references hotel (id) on delete restrict on update restrict;

create index ix_reservation_vechile_id on reservation (vechile_id);
alter table reservation add constraint fk_reservation_vechile_id foreign key (vechile_id) references vechile (id) on delete restrict on update restrict;

create index ix_seat_reservation_id on seat (reservation_id);
alter table seat add constraint fk_seat_reservation_id foreign key (reservation_id) references reservation (id) on delete restrict on update restrict;

create index ix_seat_flight_class_id on seat (flight_class_id);
alter table seat add constraint fk_seat_flight_class_id foreign key (flight_class_id) references flight_class (id) on delete restrict on update restrict;

create index ix_tour_hotel_id on tour (hotel_id);
alter table tour add constraint fk_tour_hotel_id foreign key (hotel_id) references hotel (id) on delete restrict on update restrict;

create index ix_vechile_fuel_id on vechile (fuel_id);
alter table vechile add constraint fk_vechile_fuel_id foreign key (fuel_id) references vechile_fuel (id) on delete restrict on update restrict;

create index ix_voyage_price_speed_id on voyage_price (speed_id);
alter table voyage_price add constraint fk_voyage_price_speed_id foreign key (speed_id) references vechile_speed (id) on delete restrict on update restrict;


# --- !Downs

alter table flight_class drop foreign key fk_flight_class_vechile_id;
drop index ix_flight_class_vechile_id on flight_class;

alter table luggage drop foreign key fk_luggage_vechile_id;
drop index ix_luggage_vechile_id on luggage;

alter table reservation drop foreign key fk_reservation_state_id;
drop index ix_reservation_state_id on reservation;

alter table reservation drop foreign key fk_reservation_hotel_id;
drop index ix_reservation_hotel_id on reservation;

alter table reservation drop foreign key fk_reservation_vechile_id;
drop index ix_reservation_vechile_id on reservation;

alter table seat drop foreign key fk_seat_reservation_id;
drop index ix_seat_reservation_id on seat;

alter table seat drop foreign key fk_seat_flight_class_id;
drop index ix_seat_flight_class_id on seat;

alter table tour drop foreign key fk_tour_hotel_id;
drop index ix_tour_hotel_id on tour;

alter table vechile drop foreign key fk_vechile_fuel_id;
drop index ix_vechile_fuel_id on vechile;

alter table voyage_price drop foreign key fk_voyage_price_speed_id;
drop index ix_voyage_price_speed_id on voyage_price;

drop table if exists entertainment;

drop table if exists flight_class;

drop table if exists hotel;

drop table if exists luggage;

drop table if exists meal;

drop table if exists reservation;

drop table if exists reservation_state;

drop table if exists seat;

drop table if exists tour;

drop table if exists vechile;

drop table if exists vechile_fuel;

drop table if exists vechile_speed;

drop table if exists voyage_price;

