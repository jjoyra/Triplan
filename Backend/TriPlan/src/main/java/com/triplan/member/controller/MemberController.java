package com.triplan.member.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
import com.triplan.member.model.service.JwtServiceImpl;
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
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private JwtServiceImpl jwtService;

	@Autowired
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
	public ResponseEntity<?> join(@RequestBody @ApiParam(value = "회원 Dto") MemberDto memberDto) {
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
	@ApiOperation(value = "회원 정보 조회", notes = "<b>회원 상세 정보를 담은 token</b>을 return합니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "회원정보 불러오기 성공"), @ApiResponse(code = 500, message = "서버 에러") })
	@ApiImplicitParams({
			@ApiImplicitParam(name = "memberId", value = "회원 아이디", required = true, dataType = "String", paramType = "path") })
	public ResponseEntity<?> getMember(@PathVariable("memberId") String memberId, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		
		if (jwtService.checkToken(request.getHeader("access-token"))) {
			System.out.println("사용 가능한 토큰~!");
			
			try {
				MemberDto memberDto = memberService.getMember(memberId);
				memberDto.setFollower(memberService.getFollowerList(memberDto.getMemberId()));
				memberDto.setFollowee(memberService.getFolloweeList(memberDto.getMemberId()));
				System.out.println(memberDto);
				resultMap.put("userInfo", memberDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				e.printStackTrace();
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			System.out.println("사용 불가능한 토큰~!");
			resultMap.put("message", FAIL);
			status = HttpStatus.UNAUTHORIZED;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	// 다른 회원 정보 조회
	@GetMapping("/user/other/{memberId}")
	@ApiOperation(value = "다른 회원 정보 조회", notes = "<b>다른 회원 정보를</b> return합니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "다른 회원 정보 불러오기 성공"), @ApiResponse(code = 500, message = "서버 에러") })
	@ApiImplicitParams({
			@ApiImplicitParam(name = "memberId", value = "회원 아이디", required = true, dataType = "String", paramType = "path") })
	public ResponseEntity<?> getOtherMember(@PathVariable("memberId") String memberId, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		MemberDto memberDto;
		try {
			memberDto = memberService.getOtherMember(memberId);
			if (memberDto != null) {
				memberDto.setFollower(memberService.getFollowerList(memberDto.getMemberId()));
				memberDto.setFollowee(memberService.getFolloweeList(memberDto.getMemberId()));
				resultMap.put("userInfo", memberDto);
				resultMap.put("message", SUCCESS);
				return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
			} else {
				resultMap.put("message", FAIL);
				return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.NO_CONTENT);
			}
		} catch (SQLException e) {
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

	// 로그인
	@PostMapping("/user/login")
	@ApiOperation(value = "로그인", notes = "회원 로그인을 합니다.<br /><b>Access-token과 로그인 결과 메시지를 반환</b>합니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "로그인 성공"), @ApiResponse(code = 500, message = "서버 에러") })
	@ApiImplicitParams({
		@ApiImplicitParam(name = "memberId", value = "회원 아이디", required = true, dataType = "String", paramType = "path"),
		@ApiImplicitParam(name = "password", value = "회원 비밀번호", required = true, dataType = "String", paramType = "path")})
	public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> map) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			MemberDto loginMember = memberService.loginMember(map);
			System.out.println("loginMember " + loginMember);
			if (loginMember != null) {
				String accessToken = jwtService.createAccessToken("memberId", loginMember.getMemberId());
				String refreshToken = jwtService.createRefreshToken("memberId", loginMember.getMemberId());
				memberService.saveRefreshToken(map.get("memberId"), refreshToken);
				
				System.out.println("로그인 accessToken 정보 : " + accessToken);
				System.out.println("로그인 refreshToken 정보 : " + refreshToken);
				
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 로그아웃
	@ApiOperation(value = "로그아웃", notes = "회원 정보를 담은 <b>token을 제거</b>합니다.")
	@GetMapping("/user/logout/{memberId}")
	public ResponseEntity<?> removeToken(@PathVariable("memberId") String memberId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			memberService.deleteRefreshToken(memberId);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

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
	
	// access token 재발급
	@ApiOperation(value = "Access Token 재발급", notes = "만료된 <b>access token을 재발급</b>받는다.")
	@PostMapping("/user/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody MemberDto memberDto, HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refresh-token");
		System.out.println("token : " + token + ", memberDto : " + memberDto);
		
		if (jwtService.checkToken(token)) {
			if (token.equals(memberService.getRefreshToken(memberDto.getMemberId()))) {
				String accessToken = jwtService.createAccessToken("memberId", memberDto.getMemberId());
				System.out.println("token : " + accessToken);
				System.out.println("access token 재발급 성공!!");
				resultMap.put("access-token", accessToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			}
		} else {
			System.out.println("사용 불가능한 refresh token!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
