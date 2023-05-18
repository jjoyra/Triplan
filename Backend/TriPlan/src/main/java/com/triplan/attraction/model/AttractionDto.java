package com.triplan.attraction.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@ApiModel(value = "AttractionDto (관광지정보)", description = "관광지 정보(상세 정보)를 가진 Domain class")
public class AttractionDto {

	@ApiModelProperty(value = "콘텐츠 아이디", example = "0")
	private int contentId;
	@ApiModelProperty(value = "콘텐츠 타입 아이디", example = "0")
	private int contentTypeId;
	@ApiModelProperty(value = "타이틀")
	private String title;
	@ApiModelProperty(value = "주소1")
	private String addr1;
	@ApiModelProperty(value = "주소2")
	private String addr2;
	@ApiModelProperty(value = "우편번호")
	private String zipcode;
	@ApiModelProperty(value = "전화번호")
	private String tel; // null
	@ApiModelProperty(value = "이미지1")
	private String firstImage; // null
	@ApiModelProperty(value = "이미지2")
	private String firstImage2; // null
	@ApiModelProperty(value = "위도")
	private String latitude;
	@ApiModelProperty(value = "경도")
	private String longitude;
	@ApiModelProperty(value = "지도 레벨")
	private String mlevel;

	@ApiModelProperty(value = "구군 코드", example = "0")
	private int gugunCode;
	@ApiModelProperty(value = "시도 코드", example = "0")
	private int sidoCode; // null

	@ApiModelProperty(value = "설명")
	private String overview;
	@ApiModelProperty(value = "추천수", example = "0")
	private int recommendCnt;
	
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	public int getContentTypeId() {
		return contentTypeId;
	}
	public void setContentTypeId(int contentTypeId) {
		this.contentTypeId = contentTypeId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getFirstImage() {
		return firstImage;
	}
	public void setFirstImage(String firstImage) {
		this.firstImage = firstImage;
	}
	public String getFirstImage2() {
		return firstImage2;
	}
	public void setFirstImage2(String firstImage2) {
		this.firstImage2 = firstImage2;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getMlevel() {
		return mlevel;
	}
	public void setMlevel(String mlevel) {
		this.mlevel = mlevel;
	}
	public int getGugunCode() {
		return gugunCode;
	}
	public void setGugunCode(int gugunCode) {
		this.gugunCode = gugunCode;
	}
	public int getSidoCode() {
		return sidoCode;
	}
	public void setSidoCode(int sidoCode) {
		this.sidoCode = sidoCode;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public int getRecommendCnt() {
		return recommendCnt;
	}
	public void setRecommendCnt(int recommendCnt) {
		this.recommendCnt = recommendCnt;
	}
	
}
