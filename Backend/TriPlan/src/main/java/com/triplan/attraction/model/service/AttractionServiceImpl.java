package com.triplan.attraction.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.triplan.attraction.model.AttractionDto;
import com.triplan.attraction.model.SidoGugunCodeDto;
import com.triplan.attraction.model.mapper.AttractionMapper;
import com.triplan.util.SizeConstant;

@Service
public class AttractionServiceImpl implements AttractionService {

	private AttractionMapper attractionMapper;

	public AttractionServiceImpl(AttractionMapper attractionMapper) {
		super();
		this.attractionMapper = attractionMapper;
	}

	@Override
	public List<AttractionDto> getAttractionList(Map<String, Object> map, List<Integer> ContentTypeList) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		String key = (String) map.get("key");
		param.put("key", key == null ? "" : key);
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		param.put("sidoCode", map.get("sidoCode"));
		param.put("gugunCode", map.get("gugunCode"));
		
		param.put("contentTypeList", ContentTypeList);
		int pgNo = Integer.parseInt(((String) map.get("pgno") == "" || map.get("pgno") == null) ? "1" : (String) map.get("pgno"));
		int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
		param.put("start", start);
		param.put("listsize", SizeConstant.LIST_SIZE);
		return attractionMapper.getAttractionList(param);
	}

	@Override
	public int getTotalAttractionCount(Map<String, Object> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		String key = (String) map.get("key");
		param.put("key", key == null ? "" : key);
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		return attractionMapper.getTotalAttractionCount(param);
	}
	
	@Override
	public void updateAttractionRecommendCnt(int contentId) throws Exception {
		attractionMapper.updateAttractionRecommendCnt(contentId);
	}

	@Override
	public void updateRankingVotes(int contentId) throws Exception {
		attractionMapper.updateRankingVotes(contentId);
	}

	@Override
	public AttractionDto getAttractionDetail(int contentId) throws Exception {
		return attractionMapper.getAttractionDetail(contentId);
	}

	@Override
	public List<AttractionDto> getWorldCupTOP3AttractionList() throws Exception {
		return attractionMapper.getWorldCupTOP3AttractionList();
	}

	@Override
	public List<SidoGugunCodeDto> getSidoCode() throws Exception {
		return attractionMapper.getSidoCode();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunCodeInSido(int sidoCode) throws Exception {
		return attractionMapper.getGugunCodeInSido(sidoCode);
	}
	
	
	
}
