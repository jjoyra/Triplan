package com.triplan.member.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.triplan.member.model.MemberDto;

@Mapper
public interface MemberMapper {
	int idCheck(String memberId) throws SQLException;
	void joinMember(MemberDto memberDto) throws SQLException;
	MemberDto loginMember(Map<String, String> map) throws SQLException;
	
	/* Admin */
//	List<MemberDto> listMember(Map<String, Object> map) throws SQLException;
	MemberDto getMember(String memberId) throws SQLException;
	void updateMember(MemberDto memberDto) throws SQLException;
	void deleteMember(String memberId) throws SQLException;
	
	String findPassword(Map<String, Object> map) throws SQLException;

}
