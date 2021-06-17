drop table if exists player CASCADE;
drop table if exists team CASCADE;

 

create table player
(
    PlayerId int not null AUTO_INCREMENT PRIMARY KEY,
    PlayerName varchar(255),
    age int not null,
    TeamId int
);

 

create table team
(
    `TeamId` integer AUTO_INCREMENT PRIMARY KEY,
    `TeamName` varchar(255)
);