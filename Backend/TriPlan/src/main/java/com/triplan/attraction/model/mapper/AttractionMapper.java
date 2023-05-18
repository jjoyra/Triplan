package com.triplan.attraction.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.triplan.attraction.model.AttractionDto;
import com.triplan.attraction.model.SidoGugunCodeDto;

@Mapper
public interface AttractionMapper {
	
	// 1. 관광지 목록 조회
	// 필터/검색 컬럼 : 관광지명(title), 주소(addr1, addr2), 지역별(sido_code, gugun_code), 콘텐츠별(content_type_id)
	List<AttractionDto> getAttractionList(Map<String, Object> map) throws Exception;
	
	// 2. 관광지 목록 총 개수
	int getTotalAttractionCount(Map<String, Object> map) throws Exception;
	
	// 3. 관광지 추천수 증가(attraction_detail)
	void updateAttractionRecommendCnt(int contentId) throws Exception;
	
	// 4. 관광지 투표수 증가(attraction_ranking)
	void updateRankingVotes(int contentId) throws Exception;

	// 5. 관광지 상세 정보 조회
	AttractionDto getAttractionDetail(int contentId) throws Exception;
	
	// 6. 월드컵 추천수 TOP3 관광지 조회
	List<AttractionDto> getWorldCupTOP3AttractionList() throws Exception;
	
	// 7. 시도 조회
	List<SidoGugunCodeDto> getSidoCode() throws Exception;
	
	// 8. 구군 조회 
	List<SidoGugunCodeDto> getGugunCodeInSido(int sidoCode) throws Exception;
	
}
