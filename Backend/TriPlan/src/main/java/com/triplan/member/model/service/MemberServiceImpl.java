package com.triplan.member.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.triplan.member.model.MemberDto;
import com.triplan.member.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;

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
		if (map.get("memberId") == null && map.get("password") == null) {
			return null;
		}
		return memberMapper.loginMember(map);
	}

	// @Override
	// public List<MemberDto> listMember(Map<String, Object> map) throws
	// SQLException {
	// return memberMapper.listMember(map);
	// }

	@Override
	public MemberDto getMember(String memberId) throws SQLException {
		return memberMapper.getMember(memberId);
	}

	@Override
	public MemberDto getOtherMember(String memberId) throws SQLException {
		return memberMapper.getOtherMember(memberId);
	}

	@Override
	public List<String> getFollowerList(String memberId) throws SQLException {
		return memberMapper.getFollowerList(memberId);
	}

	@Override
	public List<String> getFolloweeList(String memberId) throws SQLException {
		return memberMapper.getFolloweeList(memberId);
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

	@Override
	public void saveRefreshToken(String memberId, String refreshToken) throws SQLException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("memberId", memberId);
		map.put("token", refreshToken);
		memberMapper.saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String memberId) throws SQLException {
		return memberMapper.getRefreshToken(memberId);
	}

	@Override
	public void deleteRefreshToken(String memberId) throws SQLException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("memberId", memberId);
		map.put("token", null);
		memberMapper.deleteRefreshToken(map);
	}

	@Override
	public void follow(Map<String, Object> map) throws SQLException {
		memberMapper.follow(map);
	}
	
	@Override
	public void unfollow(Map<String, Object> map) throws SQLException {
		memberMapper.unfollow(map);
	}

}
