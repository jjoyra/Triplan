package com.triplan.notice.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.triplan.notice.model.NoticeDto;

@Mapper
public interface NoticeMapper {

	// 1. 공지사항 등록
	void registNotice(NoticeDto noticeDto) throws Exception;
	
	// 2. 공지사항 목록 조회
	List<NoticeDto> getNoticeList(Map<String, Object> map) throws Exception;
	
	// 3. 공지사항 수정
	void modifyNotice(NoticeDto noticeDto) throws Exception;

	// 4. 공지사항 삭제
	void deleteNotice(int noticeId) throws Exception;
	
	// 5. 공지사항 목록 총 개수
	int getTotalNoticeCount(Map<String, Object> map) throws Exception;
	
	// 6. 공지사항 상세 조회
	NoticeDto getNoticeDetail(int noticeId) throws Exception;
	
	// 7. 공지사항 조회수 증가
	void updateHit(int noticeId) throws Exception;
	
}
