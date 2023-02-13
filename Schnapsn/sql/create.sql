create table PLAYER (
  username varchar(10) not null,
  password varchar(200) not null,
  email varchar(50) not null,
  games_played int not null,
  wins int,
  looses int
);
create table CARD (
    card_name varchar(10) not null,
    card_type varchar(10) not null,
    value int not null
);
create table LECTURE (
  lecture_id int not null,
  name varchar(100) not null,
  progress int,
  content varchar(10000)
);
