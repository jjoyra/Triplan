package com.triplan.attraction.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.triplan.attraction.model.AttractionDto;
import com.triplan.attraction.model.SidoGugunCodeDto;
import com.triplan.attraction.model.service.AttractionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin("*")
@Api(tags = {"관광지 관리"})
public class AttractionController {

	private AttractionService attractionService;

	public AttractionController(AttractionService attractionService) {
		super();
		this.attractionService = attractionService;
	}
	
	@ApiOperation(value = "관광지 목록 조회", notes = "관광지 <b>전체 또는 검색 목록</b>을 리턴합니다.<br />검색 조건은 title, addr, sido_code, gugun_code, content_type_id로 조회가 가능합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "관광지 목록 OK"), @ApiResponse(code = 500, message = "서버 에러")})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pgno", value = "페이지 번호", required = false, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam(name = "key", value = "검색 조건", required = false, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "word", value = "검색어", required = false, dataType = "String", paramType = "query")
	})
	@GetMapping(value = "/attraction")
	public ResponseEntity<?> getAttractionList(@RequestParam(required = false) Map<String, Object> map, @RequestParam(required = false) String contentTypeList) {
		try {
			List<Integer> contentTypes = null;
			if (contentTypeList != null && !contentTypeList.equals(""))
				contentTypes = Arrays.stream(contentTypeList.split(",")).map(Integer::parseInt).collect(Collectors.toList());
			List<AttractionDto> list = attractionService.getAttractionList(map, contentTypes);
			Map<String, Object> response = new HashMap<>();
			response.put("attractions", list);
			response.put("pgno", map.get("pgno"));
			response.put("key", map.get("key"));
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
	
	@ApiOperation(value = "관광지 목록 개수", notes = "관광지 <b>전체 또는 검색 목록</b>의 개수를 리턴합니다.<br />검색 조건은 title, addr, sidoCode, gugunCode, contentTypeId로 조회가 가능합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "관광지 목록 개수 OK"), @ApiResponse(code = 500, message = "서버 에러")})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "key", value = "검색 조건", required = false, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "word", value = "검색어", required = false, dataType = "String", paramType = "query")
	})
	@GetMapping(value = "/attraction/count")
	public ResponseEntity<?> getTotalAttractionCount(@RequestParam Map<String, Object> map) {
		try {
			int cnt = attractionService.getTotalAttractionCount(map);
			Map<String, Object> response = new HashMap<>();
			response.put("cnt", cnt);
			response.put("key", map.get("key"));
			response.put("word", map.get("word"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@ApiOperation(value = "관광지 추천수 증가", notes = "해당 관광지의 <b>추천수</b>를 증가시킵니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "관광지 추천수 증가 OK"), @ApiResponse(code = 500, message = "서버 에러")})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "contentId", value = "관광지 번호(content_id)", required = false, dataType = "Integer", paramType = "path"),
	})
	@PutMapping(value = "/attraction/{contentId}")
	public ResponseEntity<?> updateAttractionRecommendCnt(@PathVariable("contentId") String contentId) {
		try {
			attractionService.updateAttractionRecommendCnt(Integer.parseInt(contentId));
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@ApiOperation(value = "관광지 투표수 증가", notes = "해당 관광지의 <b>투표수</b>를 증가시킵니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "관광지 투표수 증가 OK"), @ApiResponse(code = 500, message = "서버 에러")})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "contentId", value = "관광지 번호(content_id)", required = false, dataType = "Integer", paramType = "path"),
	})
	@PutMapping(value = "/attraction/ranking/{contentId}")
	public ResponseEntity<?> updateRankingVotes(@PathVariable("contentId") String contentId) {
		try {
			attractionService.updateRankingVotes(Integer.parseInt(contentId));
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@ApiOperation(value = "관광지 상세 정보 조회", notes = "해당 관광지의 <b>상세 정보</b>를 반환합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "관광지 상세 조회 OK"), @ApiResponse(code = 500, message = "서버 에러")})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "contentId", value = "관광지 번호(content_id)", required = false, dataType = "Integer", paramType = "path"),
	})
	@GetMapping(value = "/attraction/{contentId}")
	public ResponseEntity<?> getAttractionDetail(@PathVariable("contentId") String contentId, @RequestParam Map<String, Object> map) {
		try {
			AttractionDto attraction = attractionService.getAttractionDetail(Integer.parseInt(contentId));
			Map<String, Object> response = new HashMap<>();
			response.put("attraction", attraction);
			response.put("pgno", map.get("pgno"));
			response.put("key", map.get("key"));
			response.put("word", map.get("word"));
			if (attraction != null) {
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@ApiOperation(value = "관광지 랭킹 TOP3 목록 조회", notes = "관광지 랭킹의 <b>TOP3 관광지 목록</b>을 반환합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "관광지 TOP3 목록 조회 OK"), @ApiResponse(code = 500, message = "서버 에러")})
	@GetMapping(value = "/attraction/ranking")
	public ResponseEntity<?> getWorldCupTOP3AttractionList() {
		try {
			List<AttractionDto> list = attractionService.getWorldCupTOP3AttractionList();
			Map<String, Object> response = new HashMap<>();
			response.put("attractions", list);
			if (list != null && !list.isEmpty()) {
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@ApiOperation(value = "시도 정보", notes = "전국의 <b>시도 목록</b>을 반환합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "시도 목록 조회 OK"), @ApiResponse(code = 500, message = "서버 에러")})
	@GetMapping(value = "/attraction/sido")
	public ResponseEntity<?> getSidoCode() {
		try {
			List<SidoGugunCodeDto> list = attractionService.getSidoCode();
			Map<String, Object> response = new HashMap<>();
			response.put("sidos", list);
			if (list != null && !list.isEmpty()) {
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	  
	@ApiOperation(value = "구군 정보", notes = "전국의 <b>구군 목록</b>을 반환합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "구군 목록 조회 OK"), @ApiResponse(code = 500, message = "서버 에러")})
	@GetMapping(value = "/attraction/gugun")
	public ResponseEntity<?> getGugunCode(
			@RequestParam("sidoCode") @ApiParam(value = "시도코드", required = true) String sidoCode) {
		try {
			List<SidoGugunCodeDto> list = attractionService.getGugunCodeInSido(Integer.parseInt(sidoCode));
			Map<String, Object> response = new HashMap<>();
			response.put("guguns", list);
			if (list != null && !list.isEmpty()) {
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
