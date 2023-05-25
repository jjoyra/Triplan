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
insert into triplan.attraction_ranking (content_id, vote_cnt)
SELECT content_id, 0 FROM triplan.attraction_info;

update triplan.attraction_ranking set vote_cnt = 40 where content_id = "125266";
update triplan.attraction_ranking set vote_cnt = 290 where content_id = "125410";
update triplan.attraction_ranking set vote_cnt = 160 where content_id = "125425";


-- --------------------------------------
-- 테이블 : member(role : 관리자 0, 회원 1)
insert into triplan.member(member_id, password, name, nickname, img_url, join_date, role, comment)
values ("admin", "1234", "관리자", "관리싸피", null, now(), 0, '나 관리자야'),
	   ("ssafy", "1234", "임희선", "임싸피", null, now(), 1, '나는 싸피'),
	   ("test", "1234", "조희라", "조싸피", null, now(), 1, null);

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

-- 테이블 : plan(순서 plan -> member_plan_list -> course_list)
insert into triplan.plan(title, start_date, end_date, thumbnail_url)
values ("경주 당일치기 코스", "2023-05-23", "2023-05-23", "https://file.notion.so/f/s/348bbb4d-1f00-438c-951a-6c72f69f6a6d/4.jpg?id=4451f9fd-5e37-45a8-8349-4df0fed00515&table=block&spaceId=83d31fd5-de0f-493d-93c5-8cdbc56e0df4&expirationTimestamp=1684889653093&signature=CLIWMHEGEWxjvuY5nwOyAxb5WhVu-q7eNlF_siBfcRQ&downloadName=4.jpg"),
	   ("부산 바다 보러", "2023-05-03", "2023-05-08", "https://file.notion.so/f/s/fc83c716-7695-4274-9c1a-ed7867e6fc74/2.jpg?id=19d1d84b-a992-48d3-9a0a-3e1a4fed62ce&table=block&spaceId=83d31fd5-de0f-493d-93c5-8cdbc56e0df4&expirationTimestamp=1684889664248&signature=iruRnOh_VkaSMKzmoBG-9Pqyc5JAyzv_vl3QdS6ybcM&downloadName=2.jpg"),
       ("제주도", "2022-04-23", "2022-04-25", "https://file.notion.so/f/s/de8e180c-b127-4f37-b9f9-c725b1b598df/3.jpg?id=d481c737-9693-4b76-8027-83c557a748a1&table=block&spaceId=83d31fd5-de0f-493d-93c5-8cdbc56e0df4&expirationTimestamp=1684889635130&signature=hbztYUL43qvRkawEU8wC82kLhrjcpOlelDluP76YsaI&downloadName=3.jpg");

-- 테이블 : member_plan_list(순서 plan -> member_plan_list -> course_list): 플랜 멤버 초대
insert into triplan.member_plan_list(member_id, plan_id, role)
values ("ssafy", "1", 0),
	     ("test", "1", 1);

-- 테이블 : course_list(순서 plan -> member_plan_list -> course_list)
insert into triplan.course_list(plan_id, content_id, `order`, comment)
values (1, '2849095', 1, '내 금고'),
	     (1, '2840968', 2, '뭔데 뭐하는 곳인데'),
       (1, '2840934', 3, '경주 가면 황리단길 꼭 가야지 볼거 진짜 많음 OO 가게 꼭 가! 진짜 맛있어');

-- 테이블 : review
insert into triplan.review (member_id, people_cnt, title, content, recommend_cnt, hit, create_date, modify_date, start_date, end_date, plan_id, rating, companion, is_total_price, price)
values ("test", 3, "경주로 가자", "부모님이랑 당일치기로 경주 다녀왔다!! 첨성대 빵 꼭 먹어줘", 0, 0, now(), now(), "2023-05-29", "2023-05-29", 1, 4, 4, false, 170000),
	   ("ssafy", 1, "무조건 부산 ㄱ", "밤바다 보고 싶으면 광안리로 가", 2, 0, now(), now(), "2023-04-20", "2023-04-23", 2, 4, 0, false, 354000),
       ("ssafy", 4, "반드시 추천하는 제주도 여행", "친구들이랑 진짜 짱 재밌게 놀다 옴 ㄱㄱ", 3, 0, now(), now(), "2023-05-06", "2023-05-13", 3, 5, 1, true, 600000);

-- 테이블 : follow_list
insert into triplan.follow_list(follower_id, followee_id)
values ('ssafy', 'test'),
	   ('ssafy', 'admin'),
	   ('test', 'ssafy');



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
