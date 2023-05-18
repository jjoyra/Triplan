package com.triplan.member.controller;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.triplan.member.model.MemberDto;
import com.triplan.member.model.mapper.MemberMapper;
import com.triplan.member.model.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
//@RequestMapping("/user")
@CrossOrigin("*")
@Api(tags = { "회원관리" })
public class MemberController {

	private MemberService memberService;

	public MemberController(MemberService memberService) {
		super();
		this.memberService = memberService;
	}

	// id 체크
	@GetMapping("/user/idcheck/{memberId}")
	@ApiOperation(value = "Id 중복 체크", notes = "회원 가입 시 <b>아이디 중복 체크</b>를 합니다.")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "memberId", value = "회원 아이디", required = true, dataType = "String", paramType = "path") })
	public String idCheck(@PathVariable("memberId") String memberId) throws SQLException {
		int cnt = memberService.idCheck(memberId);
		return cnt + "";
	}

	// 회원가입
	@PostMapping("/user")
	@ApiOperation(value = "회원가입", notes = "회원가입을 하면 <b>회원 정보를  insert</b>합니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "회원가입 성공"), @ApiResponse(code = 500, message = "서버 에러") })
	public ResponseEntity<?> join(@ApiParam(value = "회원 Dto") MemberDto memberDto) {
		try {
			memberService.joinMember(memberDto);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	// 회원 정보 수정
	@PutMapping("/user")
	@ApiOperation(value = "회원 정보 수정", notes = "<b>회원 정보를 수정</b>합니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "회원 정보 수정 성공"), @ApiResponse(code = 500, message = "서버 에러") })
	public ResponseEntity<?> userModify(@ApiParam(value = "회원 Dto") MemberDto memberDto) {
		try {
			memberService.updateMember(memberDto);
			MemberDto member = memberService.getMember(memberDto.getMemberId());
			return new ResponseEntity<MemberDto>(member, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	// 회원 정보 상세 조회
	@GetMapping("/user/{memberId}")
	@ApiOperation(value = "회원 정보 조회", notes = "<b>회원 상세 정보</b>를 return합니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "회원정보 불러오기 성공"), @ApiResponse(code = 500, message = "서버 에러") })
	@ApiImplicitParams({
			@ApiImplicitParam(name = "memberId", value = "회원 아이디", required = true, dataType = "String", paramType = "path") })
	public ResponseEntity<?> getMember(@PathVariable("memberId") String memberId) {
		try {
			MemberDto member = memberService.getMember(memberId);
			return new ResponseEntity<MemberDto>(member, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

//	// 회원 탈퇴
	@PutMapping("/user/{memberId}")
	@ApiOperation(value = "회원 탈퇴", notes = "회원이 탈퇴할 때 <b>회원 정보를 delete</b>합니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "회원 탈퇴 성공"), @ApiResponse(code = 500, message = "서버 에러") })
	@ApiImplicitParams({
			@ApiImplicitParam(name = "memberId", value = "회원 아이디", required = true, dataType = "String", paramType = "path") })
	public ResponseEntity<?> userDelete(@PathVariable("memberId") String memberId) {
		try {
			memberService.deleteMember(memberId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

//	// 로그인
//	@PostMapping()
//	public ResponseEntity<?> login() {
//		return null;
//	}
//	
//	// 로그아웃
//	
	// 비밀번호 찾기
	@GetMapping("/user/password")
	@ApiOperation(value = "비밀번호 찾기", notes = "회원의 이름과 아이디를 입력하면 <b>비밀번호를 return</b>합니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "비밀번호 찾기 성공"), @ApiResponse(code = 500, message = "서버 에러") })
	@ApiImplicitParams({
			@ApiImplicitParam(name = "memberId", value = "회원 아이디", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "name", value = "회원 이름", required = true, dataType = "String", paramType = "query") })
	public ResponseEntity<?> findPassword(@RequestParam Map<String, Object> map) {
		try {
			String password = memberService.findPassword(map);
			return new ResponseEntity<String>(password, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
