create schema if not exists testdb

use testdb


drop table if exists player 
drop table if exists team  
create table player (player_id bigint generated by default as identity, player_name varchar(255), age integer not null, team_team_id bigint, primary key (player_id))
create table team (team_id bigint generated by default as identity, team_name varchar(255), primary key (team_id))


