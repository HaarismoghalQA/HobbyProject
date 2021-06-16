drop schema if exists testdb;
create schema if not exists testdb;
use testdb;

 

create table player
(
    `PlayerId` int not null AUTO_INCREMENT PRIMARY KEY,
    `PlayerName` varchar(255),
    `age` int not null,
    `TeamId` integer
);

 

create table team
(
    `TeamId` integer AUTO_INCREMENT PRIMARY KEY,
    `TeamName` varchar(255), make varchar(255)
);