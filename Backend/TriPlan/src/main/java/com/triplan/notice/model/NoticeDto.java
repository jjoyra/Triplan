package com.triplan.notice.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@ApiModel(value = "NoticeDto (공지사항)", description = "공지사항 정보를 가진 Domain class")
public class NoticeDto {

	@ApiModelProperty(value = "공지사항 아이디", example = "0")
	private int noticeId;
	@ApiModelProperty(value = "작성자 아이디")
	private String memberId;
	@ApiModelProperty(value = "제목")
	private String title;
	@ApiModelProperty(value = "내용")
	private String content;
	@ApiModelProperty(value = "필독 여부")
	private boolean isMustRead; // 필독: true
	@ApiModelProperty(value = "조회수", example = "0")
	private int hit;

	@ApiModelProperty(value = "작성일자")
	private String createDate;
	@ApiModelProperty(value = "수정일자")
	private String modifyDate;
	
	public int getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isMustRead() {
		return isMustRead;
	}
	public void setMustRead(boolean isMustRead) {
		this.isMustRead = isMustRead;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	@Override
	public String toString() {
		return "NoticeDto [noticeId=" + noticeId + ", memberId=" + memberId + ", title=" + title + ", content="
				+ content + ", isMustRead=" + isMustRead + ", hit=" + hit + ", createDate=" + createDate
				+ ", modifyDate=" + modifyDate + "]";
	}
	
	
	
	
}
