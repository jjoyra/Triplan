package com.triplan.notice.controller;

import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.triplan.notice.model.NoticeDto;
import com.triplan.notice.model.service.NoticeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin("*")
@Api(tags = { "공지사항 관리" })
public class NoticeController {

	private NoticeService noticeService;

	public NoticeController(NoticeService noticeService) {
		super();
		this.noticeService = noticeService;
	}

	// 1. 공지사항 등록
	@ApiOperation(value = "공지사항 등록", notes = "<b>공지사항을 등록</b>합니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "공지사항 등록 OK"), @ApiResponse(code = 500, message = "서버 에러") })
	@PostMapping("/notice")
	public ResponseEntity<?> registNotice(@ApiParam(value = "공지사항 Dto") @RequestBody NoticeDto noticeDto) {
		try {
			noticeService.registNotice(noticeDto);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	// 2. 공지사항 목록 조회
	@ApiOperation(value = "공지사항 목록 조회", notes = "공지사항 <b>전체 또는 검색 목록</b>을 리턴합니다.<br />검색 조건은 memberId, title로 조회가 가능합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "공지사항 목록 조회 OK"), @ApiResponse(code = 500, message = "서버 에러")})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pgno", value = "페이지 번호", required = false, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam(name = "sortkey", value = "정렬조건", required = false, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "word", value = "검색어", required = false, dataType = "String", paramType = "query")
	})
	@GetMapping("/notice")
	public ResponseEntity<?> getNoticeList(@RequestParam Map<String, Object> map) {
		try {
			List<NoticeDto> list = noticeService.getNoticeList(map);
			Map<String, Object> response = new HashMap<>();
			response.put("notices", list);
			response.put("pgno", map.get("pgno"));
			response.put("sortkey", map.get("sortkey"));
			response.put("word", map.get("word"));
			if (list != null && !list.isEmpty()) {
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	// 3. 공지사항 수정
	@ApiOperation(value = "공지사항 수정", notes = "<b>공지사항을 수정</b>합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "공지사항 수정 OK"), @ApiResponse(code = 500, message = "서버 에러")})
	@PutMapping("/notice")
	public ResponseEntity<?> modifyNotice(@ApiParam(value = "공지사항 객체") @RequestBody NoticeDto noticeDto) {
		try {
			noticeService.modifyNotice(noticeDto);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	// 4. 공지사항 삭제
	@ApiOperation(value = "공지사항 삭제", notes = "<b>공지사항을 삭제</b>합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "공지사항 삭제 OK"), @ApiResponse(code = 500, message = "서버 에러")})
	@DeleteMapping("/notice/{noticeId}")
	public ResponseEntity<?> deleteNotice(@ApiParam(value = "공지사항 아이디") @PathVariable String noticeId) {
		try {
			noticeService.deleteNotice(Integer.parseInt(noticeId));
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	// 5. 공지사항 목록 총 개수
	@ApiOperation(value = "공지사항 목록 총 개수", notes = "공지사항 <b>전체 또는 검색 목록</b>의 개수를 리턴합니다.<br />검색 조건은 memberId, title로 조회가 가능합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "공지사항 목록 총 개수 OK"), @ApiResponse(code = 500, message = "서버 에러")})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "key", value = "검색 조건", required = false, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "word", value = "검색어", required = false, dataType = "String", paramType = "query")
	})
	@GetMapping("/notice/count")
	public ResponseEntity<?> getTotalNoticeCount(@RequestParam Map<String, Object> map) {
		try {
			int cnt = noticeService.getTotalNoticeCount(map);
			Map<String, Object> response = new HashMap<>();
			response.put("cnt", cnt);
			response.put("key", map.get("key"));
			response.put("word", map.get("word"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	// 6. 공지사항 상세 조회
	@ApiOperation(value = "공지사항 상세 조회", notes = "해당 <b>공지사항의 상세 정보</b>를 리턴합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "공지사항 상세 조회 OK"), @ApiResponse(code = 500, message = "서버 에러")})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pgno", value = "페이지 번호", required = false, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam(name = "key", value = "검색 조건", required = false, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "word", value = "검색어", required = false, dataType = "String", paramType = "query")
	})
	@GetMapping("/notice/{noticeId}")
	public ResponseEntity<?> getNoticeDetail(@ApiParam(value = "공지사항 아이디") @PathVariable("noticeId") int noticeId, @RequestParam Map<String, Object> map) {
		try {
			NoticeDto notice = noticeService.getNoticeDetail(noticeId);
			Map<String, Object> response = new HashMap<>();
			response.put("notice", notice);
			response.put("pgno", map.get("pgno"));
			response.put("key", map.get("key"));
			response.put("word", map.get("word"));
			if (notice != null) {
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	// 7. 공지사항 조회수 증가
	@ApiOperation(value = "공지사항 조회수 증가", notes = "<b>공지사항 조회수</b>를 증가 시킵니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "공지사항 조회수 증가 OK"), @ApiResponse(code = 500, message = "서버 에러")})
	@PutMapping("/notice/{noticeId}")
	public ResponseEntity<?> updateHit(@ApiParam(value = "공지사항 아이디") @PathVariable("noticeId") int noticeId) {
		try {
			noticeService.updateHit(noticeId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
