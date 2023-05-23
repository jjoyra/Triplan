package com.triplan.member.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "MemberDto (회원정보)", description = " 회원 정보(상세 정보)를 가진 Domain class")
public class MemberDto {
	@ApiModelProperty(value = "회원 ID")
	private String memberId;
	@ApiModelProperty(value = "비밀번호")
	private String password;
	@ApiModelProperty(value = "이름")
	private String name;
	@ApiModelProperty(value = "닉네임")
	private String nickname;
	@ApiModelProperty(value = "이미지 URL")
	private String imgUrl;
	@ApiModelProperty(value = "가입 날짜")
	private String joinDate;
	@ApiModelProperty(value = "역할(회원/관리자)", example = "회원 1, 관리자 0")
	private int role;
	@ApiModelProperty(value = "마이플랜 공개 여부")
	private int openMyplan;
	@ApiModelProperty(value = "좋아요한 플랜 공개 여부")
	private int openFavoPlan;
	@ApiModelProperty(value = "좋아요한 여행지 공개 여부")
	private int openFavoAttraction;
	@ApiModelProperty(value = "상태 메시지")
	private String comment;
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name; 
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public int getOpenMyplan() {
		return openMyplan;
	}
	public void setOpenMyplan(int openMyplan) {
		this.openMyplan = openMyplan;
	}
	public int getOpenFavoPlan() {
		return openFavoPlan;
	}
	public void setOpenFavoPlan(int openFavoPlan) {
		this.openFavoPlan = openFavoPlan;
	}
	public int getOpenFavoAttraction() {
		return openFavoAttraction;
	}
	public void setOpenFavoAttraction(int openFavoAttraction) {
		this.openFavoAttraction = openFavoAttraction;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public String toString() {
		return "MemberDto [memberId=" + memberId + ", password=" + password + ", name=" + name + ", nickname="
				+ nickname + ", imgUrl=" + imgUrl + ", joinDate=" + joinDate + ", role=" + role + ", openMyplan="
				+ openMyplan + ", openFavoPlan=" + openFavoPlan + ", openFavoAttraction=" + openFavoAttraction + "]";
	}
	
}
