use triplan;

SELECT * FROM attraction_info;
SELECT * FROM triplan.attraction_detail;
SELECT * FROM triplan.attraction_ranking;

-- 1. 관광지 목록 조회
select i.*, d.overview, recommend_cnt
from attraction_info i left outer join attraction_detail d
using (content_id)
where i.title like "%국립 청태산%"
order by i.content_id desc;

-- 2. 관광지 목록 총 개수
select count(content_id)
from attraction_info;
-- where 생략;

-- 3. 관광지 추천수 증가(attraction_detail)
update attraction_detail
set recommend_cnt = recommend_cnt + 1
where content_id = "125266";

-- 4. 관광지 투표수 증가(attraction_ranking)
update attraction_ranking
set recommend_cnt = recommend_cnt + 1
where content_id = "125266";

-- 5. 관광지 상세 정보 조회
select i.*, d.overview, recommend_cnt
from attraction_info i left outer join attraction_detail d
using (content_id)
where i.content_id = "125266";

-- 6. 월드컵 랭킹 TOP3 조회
select *
from attraction_ranking
order by ranking
limit 3;