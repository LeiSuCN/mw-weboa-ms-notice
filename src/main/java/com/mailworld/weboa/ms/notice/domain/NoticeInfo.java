package com.mailworld.weboa.ms.notice.domain;

import java.util.List;

public class NoticeInfo {
	
	private String id;
	
	private Notice notice;
	
	private List<NoticeItem> attachments;
	
	private NoticeItem content;
	
	private List<NoticeItem> receivers;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	public List<NoticeItem> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<NoticeItem> attachments) {
		this.attachments = attachments;
	}

	public NoticeItem getContent() {
		return content;
	}

	public void setContent(NoticeItem content) {
		this.content = content;
	}

	public List<NoticeItem> getReceivers() {
		return receivers;
	}

	public void setReceivers(List<NoticeItem> receivers) {
		this.receivers = receivers;
	}
}
