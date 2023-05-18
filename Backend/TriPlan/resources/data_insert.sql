-- Triplan insert DATA

-- --------------------------------------
-- 테이블 : sido, gugun, addraction_info, addraction_detail
-- 1. sido 데이터 가져와서 insert
insert into triplan.sido
SELECT * FROM enjoytrip.sido;

-- 2. gugun 데이터 가져와서 insert
insert into triplan.gugun (gugun_code, gugun_name, sido_code)
SELECT gugun_code, gugun_name, sido_code FROM enjoytrip.gugun;

-- 3. 관광지 정보 데이터 가져와서 insert
insert into triplan.attraction_info
select i.content_id, i.content_type_id, i.title, i.addr1, i.addr2, i.zipcode, i.tel, i.first_image, i.first_image2, i.latitude, i.longitude, i.mlevel, i.gugun_code, i.sido_code
from enjoytrip.attraction_info i;

-- 4. 관광지 상세 정보 데이터 가져와서 insert
insert into triplan.attraction_detail (content_id, overview, recommend_cnt)
SELECT content_id, overview, 0 FROM enjoytrip.attraction_description;

update triplan.attraction_detail set recommend_cnt = 10 where content_id = "125266";
update triplan.attraction_detail set recommend_cnt = 30 where content_id = "125410";
update triplan.attraction_detail set recommend_cnt = 18 where content_id = "125411";
update triplan.attraction_detail set recommend_cnt = 23 where content_id = "125425";
update triplan.attraction_detail set recommend_cnt = 19 where content_id = "125429";

-- 5. 랭킹 데이터 insert
insert into triplan.attraction_ranking (content_id, ranking, recommend_cnt)
SELECT content_id, (SELECT count(*) FROM triplan.attraction_info), 0 FROM triplan.attraction_info;

update triplan.attraction_ranking set ranking = 1, recommend_cnt = 40 where content_id = "125266";
update triplan.attraction_ranking set ranking = 2, recommend_cnt = 290 where content_id = "125410";
update triplan.attraction_ranking set ranking = 3, recommend_cnt = 160 where content_id = "125425";


-- --------------------------------------
-- 테이블 : member(role : 관리자 0, 회원 1)
insert into triplan.member(member_id, password, name, nickname, img_url, join_date, role)
values ("admin", "1234", "관리자", "관리싸피", null, now(), 0),
	   ("ssafy", "1234", "임희선", "임싸피", null, now(), 1),
	   ("test", "1234", "조희라", "조싸피", null, now(), 1);

-- 테이블 : community
insert into triplan.community (member_id, title, content, recommend_cnt, hit, create_date, modify_date)
values ("ssafy", "타이틀입니다1", "콘텐츠입니다1", 0, 0, now(), now()),
	   ("test", "타이틀입니다2", "콘텐츠입니다2", 0, 0, now(), now()),
	   ("test", "타이틀입니다3", "콘텐츠입니다3", 0, 0, now(), now()),
	   ("ssafy", "타이틀입니다4", "콘텐츠입니다4", 0, 0, now(), now());

-- 테이블 : notice
insert into triplan.notice (member_id, title, content, is_must_read, hit, create_date, modify_date)
values ("admin", "공지입니다1", "콘텐츠입니다1", 1, 0, now(), now()),
	   ("admin", "공지입니다2", "콘텐츠입니다2", 1, 0, now(), now()),
	   ("admin", "공지입니다3", "콘텐츠입니다3", 0, 0, now(), now()),
	   ("admin", "공지입니다4", "콘텐츠입니다4", 0, 0, now(), now());


-- temp
/*
select i.content_id, guguni.content_type_id, i.title, i.addr1, i.addr2, i.zipcode, i.tel, i.first_image, i.first_image2, i.latitude, i.longitude, i.mlevel, i.gugun_code, i.sido_code
from enjoytrip.attraction_info i;


select i.content_id, i.content_type_id, i.title, i.addr1, i.addr2, i.zipcode, i.tel, i.first_image, i.first_image2, i.latitude, i.longitude, i.mlevel, i.gugun_code, i.sido_code
from enjoytrip.attraction_info i;


(select i.content_id, i.content_type_id, i.title, i.addr1, i.addr2, i.zipcode, i.tel, i.first_image, i.first_image2, i.latitude, i.longitude, i.mlevel, i.gugun_code, i.sido_code, d.overview
from enjoytrip.attraction_info i left outer join enjoytrip.attraction_description d
using (content_id));
*/
