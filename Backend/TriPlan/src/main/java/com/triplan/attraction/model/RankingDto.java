package com.triplan.attraction.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "RankingDto (관광지랭킹)", description = "관광지 랭킹 정보를 가진 Domain class")
public class RankingDto {

	@ApiModelProperty(value = "관광지 랭킹 아이디", example = "0")
	private int attractionRankingId;
	@ApiModelProperty(value = "콘텐츠 아이디", example = "0")
	private int contentId;
	@ApiModelProperty(value = "랭킹 아이디", example = "0")
	private int ranking;
	@ApiModelProperty(value = "투표수, example = \"0\"")
	private int recommendCnt;
	
	public int getAttractionRankingId() {
		return attractionRankingId;
	}
	public void setAttractionRankingId(int attractionRankingId) {
		this.attractionRankingId = attractionRankingId;
	}
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	public int getRanking() {
		return ranking;
	}
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	public int getRecommendCnt() {
		return recommendCnt;
	}
	public void setRecommendCnt(int recommendCnt) {
		this.recommendCnt = recommendCnt;
	}
	
}
