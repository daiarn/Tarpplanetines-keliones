create table allergen (
  id                            integer auto_increment not null,
  name                          varchar(255),
  constraint pk_allergen primary key (id)
);

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

create table mealallergen (
  meal_id                       integer not null,
  allergen_id                   integer not null,
  constraint pk_mealallergen primary key (meal_id,allergen_id)
);

create table my_reservation (
  id                            integer auto_increment not null,
  email                         varchar(255),
  constraint pk_my_reservation primary key (id)
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
  my_reservation_id             integer,
  constraint pk_reservation primary key (id)
);

create table reservation_state (
  id                            integer auto_increment not null,
  state_name                    varchar(255),
  constraint pk_reservation_state primary key (id)
);

create table room (
  id                            integer auto_increment not null,
  floor                         integer,
  room_number                   integer,
  beds_count                    integer,
  bed_type                      varchar(255),
  hotel_id                      integer,
  constraint pk_room primary key (id)
);

create table seat (
  id                            integer auto_increment not null,
  row                           integer,
  col                           integer,
  reservation_id                integer,
  flight_class_id               integer,
  constraint pk_seat primary key (id)
);

create table seatentertainment (
  seat_id                       integer not null,
  entertainment_id              integer not null,
  constraint pk_seatentertainment primary key (seat_id,entertainment_id)
);

create table seatmeal (
  seat_id                       integer not null,
  meal_id                       integer not null,
  constraint pk_seatmeal primary key (seat_id,meal_id)
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
  reservation_id                integer,
  constraint pk_voyage_price primary key (id)
);

create index ix_flight_class_vechile_id on flight_class (vechile_id);
alter table flight_class add constraint fk_flight_class_vechile_id foreign key (vechile_id) references vechile (id) on delete restrict on update restrict;

create index ix_luggage_vechile_id on luggage (vechile_id);
alter table luggage add constraint fk_luggage_vechile_id foreign key (vechile_id) references vechile (id) on delete restrict on update restrict;

create index ix_mealallergen_meal on mealallergen (meal_id);
alter table mealallergen add constraint fk_mealallergen_meal foreign key (meal_id) references meal (id) on delete restrict on update restrict;

create index ix_mealallergen_allergen on mealallergen (allergen_id);
alter table mealallergen add constraint fk_mealallergen_allergen foreign key (allergen_id) references allergen (id) on delete restrict on update restrict;

create index ix_reservation_state_id on reservation (state_id);
alter table reservation add constraint fk_reservation_state_id foreign key (state_id) references reservation_state (id) on delete restrict on update restrict;

create index ix_reservation_hotel_id on reservation (hotel_id);
alter table reservation add constraint fk_reservation_hotel_id foreign key (hotel_id) references hotel (id) on delete restrict on update restrict;

create index ix_reservation_vechile_id on reservation (vechile_id);
alter table reservation add constraint fk_reservation_vechile_id foreign key (vechile_id) references vechile (id) on delete restrict on update restrict;

create index ix_reservation_my_reservation_id on reservation (my_reservation_id);
alter table reservation add constraint fk_reservation_my_reservation_id foreign key (my_reservation_id) references my_reservation (id) on delete restrict on update restrict;

create index ix_room_hotel_id on room (hotel_id);
alter table room add constraint fk_room_hotel_id foreign key (hotel_id) references hotel (id) on delete restrict on update restrict;

create index ix_seat_reservation_id on seat (reservation_id);
alter table seat add constraint fk_seat_reservation_id foreign key (reservation_id) references reservation (id) on delete restrict on update restrict;

create index ix_seat_flight_class_id on seat (flight_class_id);
alter table seat add constraint fk_seat_flight_class_id foreign key (flight_class_id) references flight_class (id) on delete restrict on update restrict;

create index ix_seatentertainment_seat on seatentertainment (seat_id);
alter table seatentertainment add constraint fk_seatentertainment_seat foreign key (seat_id) references seat (id) on delete restrict on update restrict;

create index ix_seatentertainment_entertainment on seatentertainment (entertainment_id);
alter table seatentertainment add constraint fk_seatentertainment_entertainment foreign key (entertainment_id) references entertainment (id) on delete restrict on update restrict;

create index ix_seatmeal_seat on seatmeal (seat_id);
alter table seatmeal add constraint fk_seatmeal_seat foreign key (seat_id) references seat (id) on delete restrict on update restrict;

create index ix_seatmeal_meal on seatmeal (meal_id);
alter table seatmeal add constraint fk_seatmeal_meal foreign key (meal_id) references meal (id) on delete restrict on update restrict;

create index ix_tour_hotel_id on tour (hotel_id);
alter table tour add constraint fk_tour_hotel_id foreign key (hotel_id) references hotel (id) on delete restrict on update restrict;

create index ix_vechile_fuel_id on vechile (fuel_id);
alter table vechile add constraint fk_vechile_fuel_id foreign key (fuel_id) references vechile_fuel (id) on delete restrict on update restrict;

create index ix_voyage_price_speed_id on voyage_price (speed_id);
alter table voyage_price add constraint fk_voyage_price_speed_id foreign key (speed_id) references vechile_speed (id) on delete restrict on update restrict;

create index ix_voyage_price_reservation_id on voyage_price (reservation_id);
alter table voyage_price add constraint fk_voyage_price_reservation_id foreign key (reservation_id) references reservation (id) on delete restrict on update restrict;
