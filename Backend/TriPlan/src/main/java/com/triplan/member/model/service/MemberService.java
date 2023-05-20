package com.triplan.member.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.triplan.member.model.MemberDto;

public interface MemberService {
	int idCheck(String memberId) throws SQLException;
	void joinMember(MemberDto memberDto) throws SQLException;
	MemberDto loginMember(Map<String, String> map) throws SQLException;
	String findPassword(Map<String, Object> map) throws SQLException;

	void saveRefreshToken(String memberId, String refreshToken) throws SQLException;
	Object getRefreshToken(String memberId) throws SQLException;
	void deleteRefreshToken(String memberId) throws SQLException;
	
	/* Admin */
//	List<MemberDto> listMember(Map<String, Object> map) throws SQLException;
	MemberDto getMember(String memberId) throws SQLException;
	void updateMember(MemberDto memberDto) throws SQLException;
	void deleteMember(String memberId) throws SQLException;
	
}
