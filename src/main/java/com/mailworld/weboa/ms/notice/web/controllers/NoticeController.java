package com.mailworld.weboa.ms.notice.web.controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mailworld.weboa.ms.base.domain.JSONResp;
import com.mailworld.weboa.ms.notice.domain.Notice;
import com.mailworld.weboa.ms.notice.domain.NoticeInfo;
import com.mailworld.weboa.ms.notice.domain.NoticeItem;
import com.mailworld.weboa.ms.notice.service.NoticeService;
import com.mailworld.weboa.ms.notice.web.dto.DtoNotice;
import com.mailworld.weboa.ms.notice.web.dto.DtoNotice.DtoNoticeItem;

@RestController
@CrossOrigin
@RequestMapping(value="/notice")
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public JSONResp<String> createNotice(HttpServletRequest request){
		JSONResp<String> json = new JSONResp<>();
		
		ObjectMapper om = new ObjectMapper();
		
		try {
			DtoNotice dto = om.readValue(request.getInputStream(), DtoNotice.class);
			NoticeInfo info = this.get(dto);
			info.getNotice().setAuthor(5); // TODO
			this.noticeService.publishNoticeInfo(info);
			json.setCode(0);
			json.setMessage("success");
			json.setData(Arrays.asList(info.getId()));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return json;
	}
	
	private Function<DtoNoticeItem, NoticeItem> dtoItem2NoticeItem = (dI) -> {
		NoticeItem nI = new NoticeItem();
		nI.setName(dI.getName());
		nI.setValue(dI.getValue());
		return nI;
	};
	
	private NoticeInfo get(DtoNotice dto) {
		NoticeInfo info = new NoticeInfo();
		
		Notice notice = new Notice();
		notice.setTitle(dto.getTitle());
		notice.setCategory(dto.getCategory());
		notice.setType(dto.getType());
		notice.setValidity(dto.getValidity());
		info.setNotice(notice);
		
		NoticeItem content = new NoticeItem();
		content.setValue(dto.getContent());
		info.setContent(content);
		
		if( dto.getAttachments() != null && dto.getAttachments().size() > 0 ){
			List<NoticeItem> attachments = dto.getAttachments().stream()
					.map(dtoItem2NoticeItem).collect(Collectors.toList());
			info.setAttachments(attachments);
		}
		
		if( dto.getReceivers() != null && dto.getReceivers().size() > 0 ){
			List<NoticeItem> receivers = dto.getReceivers().stream()
					.map(dtoItem2NoticeItem).collect(Collectors.toList());
			info.setReceivers(receivers);
		}
		
		return info;
	}

}
