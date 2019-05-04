DROP TABLE SET_TENNIS_PLAYER CASCADE;
DROP TABLE SCORE_GAME CASCADE;
DROP TABLE GAME CASCADE;
DROP TABLE SCORE_SET CASCADE;
DROP TABLE PLAYER CASCADE;
DROP TABLE SET_TENNIS CASCADE;

TRUNCATE TABLE SET_TENNIS_PLAYER;
TRUNCATE TABLE SCORE_GAME;
TRUNCATE TABLE GAME;
TRUNCATE TABLE SCORE_SET;
--TRUNCATE TABLE PLAYER;
TRUNCATE TABLE SET_TENNIS;

select * from player;
select * from game where set_id = 14501;
select * from score_game where game_id = 15401;
select * from set_tennis;

select distinct set_id from set_tennis_player where player_id in (1401,1501);




--ALTER SEQUENCE SEQ_PLAYER RESTART WITH 1;
ALTER SEQUENCE SEQ_GAME RESTART WITH 1;
ALTER SEQUENCE SEQ_SCORE_GAME RESTART WITH 1;
ALTER SEQUENCE SEQ_SET_TENNIS RESTART WITH 1;


DROP SEQUENCE SEQ_SCORE_GAME;