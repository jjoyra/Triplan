package com.triplan.member.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.triplan.member.model.MemberDto;
import com.triplan.member.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	
	private MemberMapper memberMapper;

	public MemberServiceImpl(MemberMapper memberMapper) {
		super();
		this.memberMapper = memberMapper;
	}

	@Override
	public int idCheck(String memberId) throws SQLException {
		return memberMapper.idCheck(memberId);
	}

	@Override
	public void joinMember(MemberDto memberDto) throws SQLException {
		memberMapper.joinMember(memberDto);
	}

	@Override
	public MemberDto loginMember(Map<String, String> map) throws SQLException {
		return memberMapper.loginMember(map);
	}

//	@Override
//	public List<MemberDto> listMember(Map<String, Object> map) throws SQLException {
//		return memberMapper.listMember(map);
//	}

	@Override
	public MemberDto getMember(String memberId) throws SQLException {
		return memberMapper.getMember(memberId);
	}

	@Override
	public void updateMember(MemberDto memberDto) throws SQLException {
		memberMapper.updateMember(memberDto);

	}

	@Override
	public void deleteMember(String memberId) throws SQLException {
		memberMapper.deleteMember(memberId);
	}

	@Override
	public String findPassword(Map<String, Object> map) throws SQLException {
		return memberMapper.findPassword(map);
	}

}
