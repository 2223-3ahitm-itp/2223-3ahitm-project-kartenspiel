create table PLAYER
(
    playerId     int primary key GENERATED ALWAYS AS IDENTITY,
    username     varchar(10)  not null,
    password     varchar(200) not null,
    email        varchar(50)  not null,
    games_played int,
    wins         int,
    losses       int
);
create table CARD
(
    name  varchar(10) not null,
    type  varchar(10) not null,
    value int         not null
);
create table LECTURE
(
    lectureId int primary key GENERATED ALWAYS AS IDENTITY,
    name      varchar(100) not null,
    content   varchar(10000)
);
