CREATE DATABASE triplan;
use triplan;

CREATE TABLE IF NOT EXISTS `member` (
	`member_id`	VARCHAR(16)	NOT NULL,
	`password`	VARCHAR(45)	NOT NULL,
	`name`	VARCHAR(16)	NOT NULL,
	`nickname`	VARCHAR(16)	NOT NULL,
	`img_url`	VARCHAR(1000)	NULL,
	`join_date`	TIMESTAMP	NOT NULL 	DEFAULT current_timestamp,
    `role` TINYINT NOT NULL DEFAULT 1 COMMENT '관리자(0)/회원(1)',
    `open_myplan` BOOLEAN NOT NULL DEFAULT TRUE,
    `open_favo_plan` BOOLEAN NOT NULL DEFAULT TRUE,
    `open_favo_attraction` BOOLEAN NOT NULL DEFAULT TRUE,
    `token` VARCHAR(1000) NULL DEFAULT NULL,
    `comment` VARCHAR(500) DEFAULT NULL COMMENT '상태 메시지',
    PRIMARY KEY(`member_id`) 
);

CREATE TABLE IF NOT EXISTS `notice` (
	`notice_id`	INT	NOT NULL	auto_increment,
	`member_id`	VARCHAR(16)	NOT NULL,
	`title`	VARCHAR(30)	NOT NULL	COMMENT '공지사항 제목',
	`content`	VARCHAR(100)	NOT NULL	COMMENT '공지사항 내용',
	`is_must_read`	BOOLEAN	NOT NULL	DEFAULT true	COMMENT '필독 여부: true(1)/false(0)',
	`create_date`	TIMESTAMP	NOT NULL 	DEFAULT current_timestamp,
	`modify_date`	TIMESTAMP	NOT NULL 	DEFAULT current_timestamp,
	`hit`	INT	NOT NULL	DEFAULT 0,
    PRIMARY KEY (`notice_id`, `member_id`),
    CONSTRAINT `FK_member_TO_notice_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS `follow_list` (
	`follow_list_id`	INT	NOT NULL	auto_increment,
	`follower_id`	VARCHAR(16)	NOT NULL	COMMENT '나를 팔로우 하는 사람',
	`followee_id`	VARCHAR(16)	NOT NULL	COMMENT '내가 팔로우 하는 사람',
	PRIMARY KEY (`follow_list_id`),
    CONSTRAINT `FK_member_TO_follow_list_1` FOREIGN KEY (`follower_id`) REFERENCES `member` (`member_id`) ON UPDATE CASCADE,
    CONSTRAINT `FK_member_TO_follow_list_2` FOREIGN KEY (`followee_id`) REFERENCES `member` (`member_id`) ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS `sido` (
	`sido_code`	INT	NOT NULL,
	`sido_name`	VARCHAR(30)	NOT NULL,
    PRIMARY KEY (`sido_code`)
);

CREATE TABLE IF NOT EXISTS `gugun` (
	`gugun_code`	INT	NOT NULL,
	`sido_code`	INT	NOT NULL,
	`gugun_name`	VARCHAR(30)	NOT NULL,
    PRIMARY KEY(`gugun_code`, `sido_code`),
    CONSTRAINT `FK_sido_TO_gogun_1` FOREIGN KEY (`sido_code`) REFERENCES `sido` (`sido_code`)
);

CREATE TABLE IF NOT EXISTS `attraction_info` (
	`content_id`	INT	NOT NULL,
	`content_type_id`	INT	NOT NULL	COMMENT '관광지 태그',
	`title`	VARCHAR(100)	NOT NULL,
	`addr1`	VARCHAR(100)	NOT NULL,
	`addr2`	VARCHAR(50)	NOT NULL,
	`zipcode`	VARCHAR(50)	NOT NULL,
	`tel`	VARCHAR(50)	NULL,
	`first_image`	VARCHAR(200)	NULL,
	`first_image2`	VARCHAR(200)	NULL,
	`latitude`	DECIMAL(20, 17)	NOT NULL,
	`longitude`	DECIMAL(20, 17)	NOT NULL,
	`mlevel`	VARCHAR(2)	NOT NULL,
	`gugun_code`	INT	NOT NULL,
	`sido_code`	INT	NOT NULL,
    PRIMARY KEY (`content_id`),
    CONSTRAINT `FK_gogun_TO_attraction_info_1` FOREIGN KEY (`gugun_code`) REFERENCES `gugun` (`gugun_code`),
	CONSTRAINT `FK_sido_TO_attraction_info_1` FOREIGN KEY (`sido_code`) REFERENCES `sido` (`sido_code`)
);

CREATE TABLE IF NOT EXISTS `attraction_detail` (
	`content_id`	INT	NOT NULL,
	`overview`	VARCHAR(10000)	NULL,
	`recommend_cnt`	INT	NOT NULL	COMMENT '추천수',
    PRIMARY KEY (`content_id`),
    CONSTRAINT `FK_attraction_info_TO_attraction_detail_1` FOREIGN KEY (`content_id`) REFERENCES `attraction_info` (`content_id`)
);

CREATE TABLE IF NOT EXISTS `plan` (
	`plan_id`	INT	NOT NULL	auto_increment,
	`title`	VARCHAR(30)	NOT NULL,
	`start_date`	DATE	NOT NULL,
	`end_date`	DATE	NOT NULL,
	`thumbnail_url`	VARCHAR(1000)	NOT NULL,
    PRIMARY KEY (`plan_id`)
);

CREATE TABLE IF NOT EXISTS `community` (
	`community_id`	INT	NOT NULL	auto_increment,
	`member_id`	VARCHAR(16)	NOT NULL,
	`title`	VARCHAR(30)	NOT NULL,
	`content`	VARCHAR(100)	NOT NULL,
	`recommend_cnt`	INT	NOT NULL	DEFAULT 0,
	`hit`	INT	NOT NULL	DEFAULT 0,
	`create_date`	TIMESTAMP	NOT NULL 	DEFAULT current_timestamp,
	`modify_date`	TIMESTAMP	NOT NULL 	DEFAULT current_timestamp,
    PRIMARY KEY (`community_id`, `member_id`),
    CONSTRAINT `FK_member_TO_community_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS `review` (
	`review_id`	INT	NOT NULL	auto_increment,
	`member_id`	VARCHAR(16)	NOT NULL,
	`title`	VARCHAR(30)	NOT NULL,
	`content`	VARCHAR(100)	NOT NULL,
	`recommend_cnt`	INT	NOT NULL	DEFAULT 0,
	`hit`	INT	NOT NULL	DEFAULT 0,
	`create_date`	TIMESTAMP	NOT NULL 	DEFAULT current_timestamp,
	`modify_date`	TIMESTAMP	NOT NULL 	DEFAULT current_timestamp,
	`plan_id`	INT	NOT NULL,
    `rating` INT NOT NULL DEFAULT 0 COMMENT '별점',
    `companion` INT NULL NULL DEFAULT 0 COMMENT '동행자(0: 혼자, 1: 친구와, 2: 연인과, 3: 가족과, 4: 부모님과, 5: 배우자와, 6: 반려동물과)',
    `price` INT NULL NULL DEFAULT 0 COMMENT '여행 경비',
    PRIMARY KEY (`review_id`, `member_id`, `plan_id`),
    CONSTRAINT `FK_member_TO_review_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON UPDATE CASCADE,
	CONSTRAINT `FK_plan_TO_review_1` FOREIGN KEY (`plan_id`) REFERENCES `plan` (`plan_id`) ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS `course_list` (
	`plan_id`	INT	NOT NULL	auto_increment,
	`content_id`	INT	NOT NULL,
	`order`	int	NOT NULL	COMMENT '코스 순서',
    `comment` VARCHAR(1000) NULL COMMENT '코스 설명/리뷰',
    PRIMARY KEY (`plan_id`, `content_id`),
    CONSTRAINT `FK_plan_TO_course_list_1` FOREIGN KEY (`plan_id`)REFERENCES `plan` (`plan_id`),
    CONSTRAINT `FK_attraction_info_TO_course_list_1` FOREIGN KEY (`content_id`) REFERENCES `attraction_info` (`content_id`)
);

CREATE TABLE IF NOT EXISTS `member_plan_list` (
	`member_plan_list_id`	INT	NOT NULL	auto_increment,
	`member_id`	VARCHAR(16)	NOT NULL,
	`plan_id`	INT	NOT NULL,
    `role` TINYINT NOT NULL DEFAULT 0 COMMENT 'OWNER(0)/MEMBER(1)',
     PRIMARY KEY (`member_plan_list_id`),
     CONSTRAINT `FK_plan_TO_member_plan_list_1` FOREIGN KEY (`plan_id`) REFERENCES `plan` (`plan_id`),
     CONSTRAINT `FK_member_TO_member_plan_list_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS `attraction_ranking` (
	`attraction_ranking_id`	INT	NOT NULL	auto_increment,
	`content_id`	INT	NOT NULL,
	`vote_cnt`	INT	NOT NULL	DEFAULT 0,
    PRIMARY KEY (`attraction_ranking_id`, `content_id`),
    CONSTRAINT `FK_attraction_info_TO_attraction_ranking_1` FOREIGN KEY (`content_id`) REFERENCES `attraction_info` (`content_id`)
);

DROP TABLE IF EXISTS `member_favo_attraction`;

CREATE TABLE `member_favo_attraction` (
	`member_id`	VARCHAR(16)	NOT NULL,
	`content_id`	INT	NOT NULL,
	PRIMARY KEY (`member_id`, `content_id`),
	CONSTRAINT `FK_member_TO_member_favo_attraction_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`),
	CONSTRAINT `FK_attraction_info_TO_member_favo_attraction_1` FOREIGN KEY (`content_id`) REFERENCES `attraction_info` (`content_id`)
);

DROP TABLE IF EXISTS `member_favo_plan`;

CREATE TABLE `member_favo_plan` (
	`member_id`	VARCHAR(16)	NOT NULL,
	`plan_id`	INT	NOT NULL	auto_increment,
	PRIMARY KEY (`member_id`, `plan_id`),
	CONSTRAINT `FK_member_TO_member_favo_plan_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`),
	CONSTRAINT `FK_plan_TO_member_favo_plan_1` FOREIGN KEY (`plan_id`) REFERENCES `plan` (`plan_id`)
);
