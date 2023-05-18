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
	@ApiModelProperty(value = "역할(회원/관리자)", example = "0")
	private int role;
	
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
	
	@Override
	public String toString() {
		return "MemberDto [memberId=" + memberId + ", password=" + password + ", name=" + name + ", nickname="
				+ nickname + ", imgUrl=" + imgUrl + ", joinDate=" + joinDate + ", role=" + role + "]";
	}
	

}
