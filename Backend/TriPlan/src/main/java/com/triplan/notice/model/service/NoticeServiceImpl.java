package com.triplan.notice.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.triplan.notice.model.NoticeDto;
import com.triplan.notice.model.mapper.NoticeMapper;
import com.triplan.util.SizeConstant;

@Service
public class NoticeServiceImpl implements NoticeService {
	
	private NoticeMapper noticeMapper;

	public NoticeServiceImpl(NoticeMapper noticeMapper) {
		super();
		this.noticeMapper = noticeMapper;
	}

	@Override
	public void registNotice(NoticeDto noticeDto) throws Exception {
		noticeMapper.registNotice(noticeDto);
	}

	@Override
	public List<NoticeDto> getNoticeList(Map<String, Object> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		String sortkey = (String) map.get("sortkey");
		param.put("sortkey", sortkey == null || "createDate".equals(sortkey) ? "create_date" : sortkey);
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int pgNo = Integer.parseInt(((String) map.get("pgno") == "" || map.get("pgno") == null) ? "1" : (String) map.get("pgno"));
		int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
		param.put("start", start);
		param.put("listsize", SizeConstant.LIST_SIZE);
		return noticeMapper.getNoticeList(param);
	}

	@Override
	public void modifyNotice(NoticeDto noticeDto) throws Exception {
		noticeMapper.modifyNotice(noticeDto);
	}

	@Override
	public void deleteNotice(int noticeId) throws Exception {
		noticeMapper.deleteNotice(noticeId);
	}

	@Override
	public int getTotalNoticeCount(Map<String, Object> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		String key = (String) map.get("key");
		param.put("key", key == null ? "" : key);
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		return noticeMapper.getTotalNoticeCount(param);
	}

	@Override
	public NoticeDto getNoticeDetail(int noticeId) throws Exception {
		noticeMapper.updateHit(noticeId);
		return noticeMapper.getNoticeDetail(noticeId);
	}

	@Override
	public void updateHit(int noticeId) throws Exception {
		noticeMapper.updateHit(noticeId);
	}

}
