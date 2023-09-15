insert into boardgames(name, min_players, max_players, min_age, bg_year, bgg) values('king of tokyo',2,6,8,2011,'https://boardgamegeek.com/boardgame/70323/king-tokyo');
insert into boardgames(name, min_players, max_players, min_age, bg_year, bgg) values('carcassonne',2,5,7,2000,'https://boardgamegeek.com/boardgame/822/carcassonne');
insert into boardgames(name, min_players, max_players, min_age, bg_year, bgg) values('bunny kingdom',2,4,12,2017,'https://boardgamegeek.com/boardgame/822/carcassonne');

insert into designers(name, last_name, country) values('klaus-jürgen','wrede','germany');
insert into designers(name, last_name, country) values('richard','garfield','usa');

insert into publishers(name, web, country) values('hans im glück','www.hans-im-glueck.de','germany');
insert into publishers(name, web, country) values('iello','www.iello.fr','france');

insert into boardgames_designers(games_id, designers_id) values(1,2);
insert into boardgames_designers(games_id, designers_id) values(2,1);
insert into boardgames_designers(games_id, designers_id) values(3,2);

insert into boardgames_publishers(games_id, publishers_id) values(1,2);
insert into boardgames_publishers(games_id, publishers_id) values(2,1);
insert into boardgames_publishers(games_id, publishers_id) values(3,2);

--insert into boardgames(name, min_players, max_players, min_age, bg_year, bgg) values('marvel united',1,4,14,2020,'https://boardgamegeek.com/boardgame/298047/marvel-united');
--insert into designers(name, last_name, country) values('eric M','lang','canada');
--insert into publishers(name, web, country) values('cmon','www.cmon.com','china');
--
--insert into boardgames_designers(games_id, designers_id) values(4,3);
--insert into boardgames_publishers(games_id, publishers_id) values(4,3);
