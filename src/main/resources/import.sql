insert into boardgames(name, min_players, max_players, min_age, bg_year, likes, bgg) values('King of Tokyo',2,6,8,2011,2,'https://boardgamegeek.com/boardgame/70323/king-tokyo');
insert into boardgames(name, min_players, max_players, min_age, bg_year, likes, bgg) values('Carcassonne',2,5,7,2000,10,'https://boardgamegeek.com/boardgame/822/carcassonne');

insert into designers(name, last_name, country) values('Klaus-Jürgen','Wrede','Germany');
insert into designers(name, last_name, country) values('Richard','Garfield','USA');

insert into publishers(name, web, country) values('Hans im Glück','http://www.hans-im-glueck.de/','Germany');
insert into publishers(name, web, country) values('IELLO','https://linktr.ee/IELLO','France');

insert into boardgames_designers(games_id, designers_id) values(1,2);
insert into boardgames_designers(games_id, designers_id) values(2,1);

insert into boardgames_publishers(games_id, publishers_id) values(2,1);
insert into boardgames_publishers(games_id, publishers_id) values(1,2);