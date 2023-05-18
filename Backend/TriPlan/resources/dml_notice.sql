SELECT * FROM triplan.notice;

-- 1.
insert into triplan.notice (member_id, title, content, is_must_read, hit, create_date, modify_date)
values ("admin", "공지입니다5", "콘텐츠입니다1", 1, 0, now(), now()),
	   ("admin", "공지입니다6", "콘텐츠입니다1", 0, 0, now(), now());

-- 2.
select member_id, title, is_must_read, create_date, hit
from triplan.notice
where title like "%공지%"
order by is_must_read desc, create_date desc;

-- 3.
update triplan.notice
set title="타이틀", content="콘텐츠", is_must_read=false
where notice_id = 5;

-- 4. 
delete from triplan.notice
where notice_id = 3;

-- 5. 
select count(*)
from triplan.notice
where title like "%공지%"
order by is_must_read desc, create_date desc;

-- 6. 